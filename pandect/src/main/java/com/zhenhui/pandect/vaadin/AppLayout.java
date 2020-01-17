package com.zhenhui.pandect.vaadin;

import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import com.zhenhui.pandect.service.SecurityService;
import com.zhenhui.pandect.vaadin.views.login.LoginView;
import com.zhenhui.pandect.vaadin.views.pages.DashboardView;
import com.zhenhui.pandect.vaadin.views.pages.ProfileView;
import com.zhenhui.pandect.vaadin.views.pages.TrendsView;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

@Route
public class AppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsiveHybrid> {

    public static final String ITM_DASHBOARD = "main_view.menu.item.dashboard";
    public static final String ITM_PROFILE = "main_view.menu.item.profile";
    public static final String ITM_TRENDS = "main_view.menu.item.trends";
    public static final String ITM_LOGOUT = "main_view.menu.item.logout";
    public static final String TITLE = "main_view.app.title";
    private static final String LOGO_PNG = "logo.png";

    public AppLayout() {
        Image imageLogo = new Image(new StreamResource(LOGO_PNG, () -> AppLayout.class.getResourceAsStream("/" + LOGO_PNG)),
                "Vaadin Logo"
        );
        //app layout specific
        imageLogo.setHeight("var(--app-layout-menu-button-height)");

        final LeftLayouts.LeftResponsiveHybrid appLayout = AppLayoutBuilder.get(LeftLayouts.LeftResponsiveHybrid.class)
                .withTitle(getTranslation(TITLE))
                .withIconComponent(imageLogo)
                .withAppMenu(appMenu())
                .build();

        init(appLayout);

    }

    private Component appMenu() {
        return LeftAppMenuBuilder
                .get()
                .add(getTranslation(ITM_DASHBOARD), DASHBOARD.create(), DashboardView.class)
                .add(getTranslation(ITM_PROFILE), USER.create(), ProfileView.class)
                .add(getTranslation(ITM_TRENDS), TRENDING_UP.create(), TrendsView.class)
                .add(new LeftClickableItem(getTranslation(ITM_LOGOUT), SIGN_OUT.create(), e -> {
                    UI ui = UI.getCurrent();
                    VaadinSession session = ui.getSession();
                    session.setAttribute(SecurityService.User.class, null);
                    session.close();
                    ui.navigate(LoginView.class);
                })).build();
    }
}
