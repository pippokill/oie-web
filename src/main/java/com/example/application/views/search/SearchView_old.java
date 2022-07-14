package com.example.application.views.search;

import com.example.application.data.SearchTriple;
import com.example.application.logic.ServiceCall;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.QueryParameters;
import java.util.HashMap;
import java.util.Map;

@Route(value = "search-old", layout = MainView.class)
@PageTitle("Search (old)")
public class SearchView_old extends Div implements AfterNavigationObserver {

    private TextField subj;
    private TextField pred;
    private TextField obj;
    private Button searchBut;

    private final Grid<SearchTriple> grid = new Grid<>();

    public SearchView_old() {
        addClassName("search-view-old");
        HorizontalLayout searchL = new HorizontalLayout();
        subj = new TextField("Subject");
        pred = new TextField("Predicate");
        obj = new TextField("Object");
        searchBut = new Button("Search");
        searchL.add(subj, pred, obj, searchBut);
        searchL.setVerticalComponentAlignment(FlexComponent.Alignment.END, subj, pred, obj, searchBut);
        searchBut.addClickListener(e -> {
            Map<String, List<String>> parameters = new HashMap();
            if (subj.getValue().length() > 0) {
                parameters.put("subj", Arrays.asList(new String[]{subj.getValue()}));
            }
            if (pred.getValue().length() > 0) {
                parameters.put("pred", Arrays.asList(new String[]{pred.getValue()}));
            }
            if (obj.getValue().length() > 0) {
                parameters.put("obj", Arrays.asList(new String[]{obj.getValue()}));
            }
            searchBut.getUI().ifPresent(ui -> ui.navigate(
                    "search",
                    new QueryParameters(parameters)));
        });
        searchBut.addClickShortcut(Key.ENTER);
        add(searchL);
        setSizeFull();
        //grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(triple -> createTriple(triple));
        add(grid);
    }

    private HorizontalLayout createTriple(SearchTriple triple) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span subjS = new Span(triple.getSubject().getSpan());
        subjS.addClassName("subject");
        Span predS = new Span(triple.getPredicate().getSpan());
        predS.addClassName("predicate");
        Span objS = new Span(triple.getObject().getSpan());
        objS.addClassName("object");
        header.add(subjS, predS, objS);

        HorizontalLayout scoreInfo = new HorizontalLayout();
        scoreInfo.addClassName("header");
        scoreInfo.setSpacing(false);
        scoreInfo.getThemeList().add("spacing-s");
        Span scoreSearchS = new Span(String.valueOf(triple.getSearchScore()));
        scoreSearchS.addClassName("search-score");
        Span scoreS = new Span(String.valueOf(triple.getScore()));
        scoreS.addClassName("score");
        scoreInfo.add(scoreSearchS, scoreS);
        Button viewDetail = new Button("View");
        viewDetail.addClickListener(e -> {
            Map<String, List<String>> parameters = new HashMap();
            parameters.put("docid", Arrays.asList(new String[]{triple.getDocid()}));
            parameters.put("id", Arrays.asList(new String[]{String.valueOf(triple.getId())}));
            viewDetail.getUI().ifPresent(ui -> ui.navigate(
                    "detail",
                    new QueryParameters(parameters)));
        });
        description.add(header, scoreInfo, viewDetail);

        card.add(description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        Map<String, List<String>> parameters = event.getLocation().getQueryParameters().getParameters();
        StringBuilder sb = new StringBuilder();
        if (parameters.get("subj") != null && !parameters.get("subj").isEmpty()) {
            sb.append("+subj:(");
            for (String v : parameters.get("subj")) {
                sb.append(v).append(" ");
            }
            sb.append(") ");
        }
        if (parameters.get("pred") != null && !parameters.get("pred").isEmpty()) {
            sb.append("+pred:(");
            for (String v : parameters.get("pred")) {
                sb.append(v).append(" ");
            }
            sb.append(") ");
        }
        if (parameters.get("obj") != null && !parameters.get("obj").isEmpty()) {
            sb.append("+obj:(");
            for (String v : parameters.get("obj")) {
                sb.append(v).append(" ");
            }
            sb.append(")");
        }
        SearchTriple[] searchTriple = ServiceCall.searchTriple(sb.toString().trim());
        grid.setItems(searchTriple);
    }

}
