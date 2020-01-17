package com.zhenhui.pandect.vaddin;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.zhenhui.pandect.vaddin.views.main.MainView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = AppRoot.NAVIGATION_ROOT, layout = AppLayout.class)
public class AppRoot extends Composite<Div> {

    public static final String NAVIGATION_ROOT = "";

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        log.info("navigated to ROOT .. now redirecting..");
        UI.getCurrent().navigate(MainView.class);
    }
}
