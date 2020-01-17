package com.zhenhui.pandect.vaadin.views.pages;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.zhenhui.pandect.vaadin.AppLayout;

import static com.zhenhui.pandect.vaadin.views.pages.ProfileView.NAV;


@Route(value=NAV, layout = AppLayout.class)
public class ProfileView extends Composite<Div> {

    public static final String NAV = "profile";

    public ProfileView() {
        getContent().add(new Span("Profile"));
    }
}