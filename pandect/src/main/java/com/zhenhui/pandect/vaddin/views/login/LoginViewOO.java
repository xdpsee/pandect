package com.zhenhui.pandect.vaddin.views.login;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.zhenhui.pandect.vaddin.services.SecurityService;
import com.zhenhui.pandect.vaddin.views.main.MainView;
import lombok.extern.slf4j.Slf4j;

import static com.vaadin.flow.theme.lumo.Lumo.DARK;
import static com.vaadin.flow.theme.lumo.Lumo.LIGHT;

@Slf4j
@Route(LoginViewOO.NAV_LOGIN_VIEW)
@Theme(value = Lumo.class)
public class LoginViewOO extends Composite<HorizontalLayout> {

    public static final String NAV_LOGIN_VIEW = "login";

    private final TextField username = new TextField();
    private final PasswordField password = new PasswordField();
    private final Checkbox rememberMe = new Checkbox();
    private final Button btnLogin = new Button();
    private final Button btnCancel = new Button();

    private final RadioButtonGroup<String> switchTheme = new RadioButtonGroup<>();

    private final SecurityService securityService = new SecurityService();

    public LoginViewOO() {

        HorizontalLayout input = new HorizontalLayout(username, password);
        HorizontalLayout buttons = new HorizontalLayout(btnLogin, btnCancel);
        VerticalLayout groupV = new VerticalLayout(input, new HorizontalLayout(rememberMe, switchTheme), buttons);
        groupV.setDefaultHorizontalComponentAlignment(Alignment.START);
        groupV.setSizeUndefined();


        //I18N
        username.setPlaceholder(getTranslation("login.username.placeholder"));
        password.setPlaceholder(getTranslation("login.password.placeholder"));
        rememberMe.setLabel(getTranslation("login.rememberme.label"));
        btnLogin.setText(getTranslation("login.button.ok.text"));
        btnCancel.setText(getTranslation("login.button.cancel.text"));

        HorizontalLayout content = getContent();
        content.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        content.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        content.setSizeFull();
        content.add(groupV);

        //Actions
        btnCancel.addClickListener(e -> {
            username.clear();
            password.clear();
            rememberMe.clear();
        });

        btnLogin.addClickListener(e -> {
            securityService.checkLogin(username.getValue(),
                    password.getValue())
                    .ifPresentOrElse(u -> {
                                UI.getCurrent()
                                        .getSession()
                                        .setAttribute(SecurityService.User.class, u);
                                UI.getCurrent()
                                        .navigate(MainView.class);
                            },
                            f -> {
                                UI.getCurrent()
                                        .getSession()
                                        .setAttribute(SecurityService.User.class, null);
                                log.info(f);
                            });
        });

        switchTheme.setItems(LIGHT, DARK);
        switchTheme.setValue(LIGHT);
        switchTheme.addValueChangeListener(event -> {
            getUI().ifPresent(ui -> {
                String value = event.getValue();
                log.info("switchTheme value - " + value);
                Page page = ui.getPage();
                page.executeJs("document.documentElement.setAttribute(\"theme\",\" " + value + "\")");
            });
        });
    }

}