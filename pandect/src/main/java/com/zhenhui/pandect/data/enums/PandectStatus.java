package com.zhenhui.pandect.data.enums;

import java.util.Arrays;

public enum PandectStatus {
    PREPARED(0, ""),
    NORMAL(1, "OK"),
    HIDDEN(2, ""),
    DELETED(3, ""),
    ;

    public final int code;
    public final String comment;

    PandectStatus(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static PandectStatus valueOf(int code) {
        return Arrays.stream(values())
                .filter(e -> e.code == code).findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("invalid code: %d", code)));
    }
}
