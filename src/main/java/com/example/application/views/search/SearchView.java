package com.example.application.views.search;

import com.example.application.data.SearchTriple;
import com.example.application.data.TripleUtils;
import com.example.application.logic.ServiceCall;
import java.util.Arrays;
import java.util.List;

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
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.QueryParameters;
import java.util.HashMap;
import java.util.Map;

@Route(value = "search", layout = MainView.class)
@PageTitle("Search")
public class SearchView extends Div implements AfterNavigationObserver {

    private TextField subj;
    private TextField pred;
    private TextField obj;
    private Button searchBut;
    private final VerticalLayout vl;

    public SearchView() {
        addClassName("search-view");
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
                    "search2",
                    new QueryParameters(parameters)));
        });
        searchBut.addClickShortcut(Key.ENTER);
        add(searchL);

        vl = new VerticalLayout();
        add(vl);
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

        vl.removeAll();
        SearchTriple[] searchTriple = ServiceCall.searchTriple(sb.toString().trim());
        List<List<SearchTriple>> ll = TripleUtils.collapseAndSort(searchTriple, t -> t.getPredicate().getSpan());
        Map<String,String> docs=new HashMap<>();
        for (List<SearchTriple> l : ll) {
            if (!l.isEmpty()) {
                Span predSpan = new Span(l.get(0).getPredicate().getSpan() + " (" + l.size() + ")");
                VerticalLayout vlt = new VerticalLayout();
                for (SearchTriple st : l) {
                    String doctitle=docs.get(st.getDocid());
                    if (doctitle==null) {
                        doctitle=ServiceCall.getPassageById(st.getDocid()).getTitle();
                        docs.put(st.getDocid(),doctitle);
                    }
                    //Span ts = new Span(st.getSubject().getSpan() + " " + st.getPredicate().getSpan() + " " + st.getObject().getSpan() + " (" + st.getScore() + ")");
                    Span ts = new Span(st.getSubject().getSpan() + " " + st.getPredicate().getSpan() + " " + st.getObject().getSpan() + " (" + doctitle + ")");
                    ts.addClassName("triple");
                    ts.addClickListener(e -> {
                        Map<String, List<String>> parametersDetail = new HashMap();
                        parametersDetail.put("docid", Arrays.asList(new String[]{st.getDocid()}));
                        parametersDetail.put("id", Arrays.asList(new String[]{String.valueOf(st.getId())}));
                        ts.getUI().ifPresent(ui -> ui.navigate(
                                "detail",
                                new QueryParameters(parametersDetail)));
                    });
                    vlt.add(ts);
                }
                Details details = new Details(predSpan, vlt);
                details.setOpened(false);
                vl.add(details);
            }
        }
    }

}
