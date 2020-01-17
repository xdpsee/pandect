package com.zhenhui.pandect.vaddin.views.pages;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.zhenhui.pandect.vaddin.MainLayout;

import static com.zhenhui.pandect.vaddin.views.pages.DashboardView.NAV;

@Route(value= NAV ,layout = MainLayout.class)
public class DashboardView extends Composite<Div> {

    public static final String NAV = "dashboard";

    public DashboardView() {
        getContent().add(new Span("Dashboard"));
    }
}