package com.zhenhui.pandect.data.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ContentMarks {

    private Map<Integer, List<Mark>> marks = new HashMap<>();

}
