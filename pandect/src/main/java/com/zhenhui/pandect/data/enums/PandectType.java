package com.zhenhui.pandect.data.enums;

import java.util.Arrays;

public enum PandectType {
    SINGLE(1, ""),
    BOOK(2, "")
    ;

    public final int code;
    public final String comment;

    PandectType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static PandectType valueOf(int code) {
        return Arrays.stream(values())
                .filter(e -> e.code == code).findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("invalid code: %d", code)));
    }
}
