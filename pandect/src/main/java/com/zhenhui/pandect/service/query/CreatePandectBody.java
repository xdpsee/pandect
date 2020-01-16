package com.zhenhui.pandect.service.query;

import com.zhenhui.pandect.data.enums.PandectType;
import lombok.Data;

@Data
public class CreatePandectBody {

    private PandectType type;

    private String title;

    private String description;

    private String author;

    private String cover;
}
