package com.example.application.views.detail;

import com.example.application.data.SearchDoc;
import com.example.application.data.SearchTriple;
import com.example.application.logic.ServiceCall;
import java.util.List;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Route(value = "detail", layout = MainView.class)
@PageTitle("Detail")
public class DetailView extends Div implements AfterNavigationObserver {

    private Grid<DetailCard> grid = new Grid<>();

    private VerticalLayout docL;

    public DetailView() {
        addClassName("detail-view");
        docL = new VerticalLayout();
        add(docL);
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(card -> createCard(card));
        add(grid);
    }

    private HorizontalLayout createCard(DetailCard card) {
        HorizontalLayout cardL = new HorizontalLayout();
        cardL.addClassName("card");
        cardL.setSpacing(false);
        cardL.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Paragraph textS = new Paragraph(card.getText());
        textS.addClassName("text");
        Span subjS = new Span(card.getTriple().getSubject().getSpan());
        subjS.addClassName("subject");
        Span predS = new Span(card.getTriple().getPredicate().getSpan());
        predS.addClassName("predicate");
        Span objS = new Span(card.getTriple().getObject().getSpan());
        objS.addClassName("object");
        header.add(subjS, predS, objS);

        HorizontalLayout scoreInfo = new HorizontalLayout();
        scoreInfo.addClassName("header");
        scoreInfo.setSpacing(false);
        scoreInfo.getThemeList().add("spacing-s");
        Span scoreSearchS = new Span(String.valueOf(card.getTriple().getSearchScore()));
        scoreSearchS.addClassName("search-score");
        Span scoreS = new Span(String.valueOf(card.getTriple().getScore()));
        scoreS.addClassName("score");
        scoreInfo.add(scoreSearchS, scoreS);

        description.add(textS, header, scoreInfo);

        cardL.add(description);
        return cardL;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        Map<String, List<String>> parameters = event.getLocation().getQueryParameters().getParameters();
        String docid = parameters.get("docid").get(0);
        SearchDoc doc = ServiceCall.getPassageById(docid);
        H4 titleS = new H4(String.valueOf(doc.getTitle()));
        titleS.addClassName("doc-title");
        docL.add(titleS);

        List<DetailCard> cards = new ArrayList<>();
        Set<Integer> addedTriples = new HashSet<>();
        SearchTriple[] triplesByDocId = ServiceCall.getTriplesByPassageId(docid);
        for (SearchTriple t : triplesByDocId) {
            addedTriples.add(t.getId());
            cards.add(new DetailCard(doc.getText(), t));
        }

        SearchDoc[] passages = ServiceCall.searchPassageById(doc.getDatasetId());
        for (SearchDoc p : passages) {
            SearchTriple[] triplesByPassageId = ServiceCall.getTriplesByPassageId(p.getId());
            for (SearchTriple t : triplesByPassageId) {
                if (!addedTriples.contains(t.getId())) {
                    cards.add(new DetailCard(p.getText(), t));
                }
            }
        }

        grid.setItems(cards);
    }

}
