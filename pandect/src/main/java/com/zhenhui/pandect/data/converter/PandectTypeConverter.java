package com.zhenhui.pandect.data.converter;

import com.zhenhui.pandect.data.enums.PandectType;
import org.jooq.Converter;

public class PandectTypeConverter implements Converter<Integer, PandectType> {

    @Override
    public PandectType from(Integer databaseObject) {
        return PandectType.valueOf(databaseObject);
    }

    @Override
    public Integer to(PandectType userObject) {
        return userObject.code;
    }

    @Override
    public Class<Integer> fromType() {
        return Integer.class;
    }

    @Override
    public Class<PandectType> toType() {
        return PandectType.class;
    }
}

