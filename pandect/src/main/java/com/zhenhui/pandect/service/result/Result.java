package com.zhenhui.pandect.service.result;

import com.zhenhui.pandect.utils.Timestamp;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Result<T> {

    private Integer status;

    private String message;

    private String timestamp = Timestamp.now();

    private T data;

}
