package com.zhenhui.pandect.data.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.pandect.data.domain.Pronounce;
import com.zhenhui.pandect.utils.JsonUtils;
import org.jooq.Converter;

public class PronounceConverter implements Converter<String, Pronounce> {

    @Override
    public Pronounce from(String databaseObject) {
        return JsonUtils.fromJsonString(databaseObject, new TypeReference<Pronounce>() {
        });
    }

    @Override
    public String to(Pronounce userObject) {
        return JsonUtils.toJsonString(userObject);
    }

    @Override
    public Class<String> fromType() {
        return String.class;
    }

    @Override
    public Class<Pronounce> toType() {
        return Pronounce.class;
    }
}
