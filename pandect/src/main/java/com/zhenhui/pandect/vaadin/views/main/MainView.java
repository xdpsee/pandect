package com.zhenhui.pandect.vaadin.views.main;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.zhenhui.pandect.vaadin.AppLayout;

@Route(value = MainView.NAV_MAIN_VIEW, layout = AppLayout.class)
public class MainView extends Composite<Div> {

    public static final String NAV_MAIN_VIEW = "main";

    public MainView() {
        getContent().add(new Span("Page content"));
    }

}
