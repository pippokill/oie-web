package com.example.application.views.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteAlias;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Home")
public class HomeView extends HorizontalLayout {

    private TextField subj;
    private TextField pred;
    private TextField obj;
    private Button searchBut;

    public HomeView() {
        addClassName("home-view");
        subj = new TextField("Subject");
        pred = new TextField("Predicate");
        obj = new TextField("Object");
        searchBut = new Button("Search");
        add(subj, pred, obj, searchBut);
        setVerticalComponentAlignment(Alignment.END, subj, pred, obj, searchBut);
        searchBut.addClickListener(e -> {
            Map<String, List<String>> parameters=new HashMap();
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
    }

}
