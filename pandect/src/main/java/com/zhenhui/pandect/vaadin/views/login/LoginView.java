package com.zhenhui.pandect.vaadin.views.login;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.zhenhui.pandect.service.SecurityService;
import com.zhenhui.pandect.vaadin.views.main.MainView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Theme(value = Lumo.class)
@Route(LoginView.NAV_LOGIN_VIEW)
public class LoginView extends Composite<HorizontalLayout> {

    public static final String NAV_LOGIN_VIEW = "login";

    private final SecurityService securityService = new SecurityService();

    public LoginView() {
        LoginOverlay loginOverlay = initLoginOverlay();
        loginOverlay.addLoginListener((loginEvent) -> {
            securityService.checkLogin(loginEvent.getUsername(), loginEvent.getPassword())
                    .ifPresentOrElse(u -> {
                                UI.getCurrent().getSession().setAttribute(SecurityService.User.class, u);
                                loginOverlay.close();
                                UI.getCurrent().navigate(MainView.class);
                            },
                            f -> {
                                UI.getCurrent().getSession().setAttribute(SecurityService.User.class, null);
                                log.info(f);
                            }
                    );
        });
        loginOverlay.setOpened(true);
    }

    private LoginOverlay initLoginOverlay() {

        LoginOverlay overlay = new LoginOverlay();

        Icon icon = VaadinIcon.VAADIN_H.create();
        icon.setSize("30px");
        icon.getStyle().set("top", "-4px");

        H1 title = new H1();
        title.getStyle().set("color", "var(--lumo-base-color)");
        title.add(icon);
        title.add(new Text("Pandect"));
        overlay.setTitle(title);

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation("");
        overlay.setI18n(i18n);

        return overlay;
    }
}