package com.zhenhui.pandect.data.enums;

public enum MarkType {
    HYPERLINK(1, "url"),
    TEXT(2, "text comment"),
    IMAGE(3, "image"),
    AUDIO(4, "audio"),
    VIDEO(5, "video"),
    GOTO(6, "internal jump")
    ;

    public final int code;
    public final String comment;

    MarkType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}

