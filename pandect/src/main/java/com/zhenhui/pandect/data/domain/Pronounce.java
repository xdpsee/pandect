package com.zhenhui.pandect.data.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pronounce {

    private List<Word> words = new ArrayList<>();

}

