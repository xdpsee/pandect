package com.zhenhui.pandect.vaadin.service.flow;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.UIInitListener;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.zhenhui.pandect.vaadin.service.SecurityService;
import com.zhenhui.pandect.vaadin.views.login.LoginView;
import lombok.extern.slf4j.Slf4j;
import org.rapidpm.frp.model.Result;

import static com.zhenhui.pandect.vaadin.views.login.LoginView.NAV_LOGIN_VIEW;

@Slf4j
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener((UIInitListener) uiInitEvent -> {
            log.info("init SecurityListener for .. " + uiInitEvent.getUI());
            uiInitEvent.getUI().addBeforeEnterListener(new SecurityListener());
        });
    }

    private static class SecurityListener implements BeforeEnterListener {
        @Override
        public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
            final UI ui = UI.getCurrent();
            final VaadinSession vaadinSession = ui.getSession();

            Result.ofNullable(vaadinSession.getAttribute(SecurityService.User.class))
                    .ifPresentOrElse(u -> log.info("User is logged in : " + u)
                            , failed -> {
                                log.info("Anonymous User: redirecting to Login View");
                                if (!beforeEnterEvent.getNavigationTarget().equals(LoginView.class))
                                    beforeEnterEvent.rerouteTo(NAV_LOGIN_VIEW);
                            }
                    );

        }
    }
}