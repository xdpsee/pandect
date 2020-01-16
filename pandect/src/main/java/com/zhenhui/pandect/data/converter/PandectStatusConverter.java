package com.zhenhui.pandect.data.converter;

import com.zhenhui.pandect.data.enums.PandectStatus;
import org.jooq.Converter;

public class PandectStatusConverter implements Converter<Integer, PandectStatus> {

    @Override
    public PandectStatus from(Integer databaseObject) {
        return PandectStatus.valueOf(databaseObject);
    }

    @Override
    public Integer to(PandectStatus userObject) {
        return userObject.code;
    }

    @Override
    public Class<Integer> fromType() {
        return Integer.class;
    }

    @Override
    public Class<PandectStatus> toType() {
        return PandectStatus.class;
    }
}
