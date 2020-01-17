package com.zhenhui.pandect.service.query;

import com.zhenhui.pandect.data.enums.PandectType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreatePandectBody {

    @NotNull
    private PandectType type;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private String author;

    private String cover;
}
