package com.zhenhui.pandect.data.domain;

import com.zhenhui.pandect.data.enums.MarkType;
import lombok.Data;

@Data
public class Mark {

    private Integer index;

    private Integer length;

    private MarkType type;

    private String content;

}

