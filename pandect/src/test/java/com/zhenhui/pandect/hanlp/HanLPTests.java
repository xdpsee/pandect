package com.zhenhui.pandect.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import org.junit.Test;

import java.util.List;

public class HanLPTests {

    @Test
    public void testPinyin() {

        List<Pinyin> pinyins = HanLP.convertToPinyinList("你好啊，张头！\r\n再来，hello");

        System.out.println(pinyins);

    }

}
