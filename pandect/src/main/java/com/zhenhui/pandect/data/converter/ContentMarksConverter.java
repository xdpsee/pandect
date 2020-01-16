package com.zhenhui.pandect.data.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.pandect.data.domain.ContentMarks;
import com.zhenhui.pandect.utils.JsonUtils;
import org.jooq.Converter;

public class ContentMarksConverter implements Converter<String, ContentMarks> {

    @Override
    public ContentMarks from(String databaseObject) {
        return JsonUtils.fromJsonString(databaseObject, new TypeReference<ContentMarks>() {
        });
    }

    @Override
    public String to(ContentMarks userObject) {
        return JsonUtils.toJsonString(userObject);
    }

    @Override
    public Class<String> fromType() {
        return String.class;
    }

    @Override
    public Class<ContentMarks> toType() {
        return ContentMarks.class;
    }
}
