package com.zhenhui.pandect.vaddin.service;

import com.vaadin.flow.i18n.I18NProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Locale.ROOT;
import static java.util.Objects.isNull;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

@Slf4j
public class I18NProviderImpl implements I18NProvider {

    public static final String NULL_KEY = "###-NULL-KEY-###";
    public static final String EMPTY_KEY = "###-EMPTY-KEY-###";

    public I18NProviderImpl() {
        log.info("I18NProviderImpl02 was found..");
    }


    private final ResourceBundleService resourceBundleService = new ResourceBundleService();

    @Override
    public List<Locale> getProvidedLocales() {
        log.info("getProvidedLocales.. ");
        return resourceBundleService.providedLocalesAsList();
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        log.info("VaadinI18NProvider getTranslation.. key : " + key + " - " + locale);


        final ResourceBundle resourceBundle = resourceBundleService
                .resourceBundleToUse()
                .apply(locale != null ? locale : ROOT);

        return match(
                matchCase(() -> failure("###-" + key + "-###-" + locale)),
                matchCase(() -> isNull(key), () -> failure(NULL_KEY)),
                matchCase(key::isEmpty, () -> failure(EMPTY_KEY)),
                matchCase(() -> resourceBundle.containsKey(key), () -> success(resourceBundle.getString(key)))
        )
                .ifFailed(msg -> log.warn(msg))
                .getOrElse(() -> "###-KEY_NOT_FOUND-" + key + " - " + locale + "-###");
    }
}