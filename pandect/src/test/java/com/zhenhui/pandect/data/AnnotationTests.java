package com.zhenhui.pandect.data;

import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.dictionary.py.PinyinDictionary;
import com.zhenhui.pandect.Application;
import com.zhenhui.pandect.data.domain.ContentMarks;
import com.zhenhui.pandect.data.domain.Pronounce;
import com.zhenhui.pandect.data.domain.Word;
import com.zhenhui.pandect.data.tables.records.AnnotationRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = Application.class)
@Transactional("transactionManager")
@RunWith(SpringRunner.class)
public class AnnotationTests {

    @Autowired
    private DSLContext context;

    @Test
    public void testInsert() {

        String content = "“复次，须菩提！菩萨于法，应无所住，行于布施，所谓不住色布施，不住声香味触法布施。须菩提！菩萨应如是布施，不住于相。何以故？若菩萨不住相布施，其福德不可思量。须菩提！于意云何？东方虚空可思量不？”“不也，世尊！”“须菩提！南西北方四维上下虚空可思量不？”“不也，世尊！”“须菩提！菩萨无住相布施，福德亦复如是不可思量。须菩提！菩萨但应如所教住。”";

        Pronounce pronounce = new Pronounce();

        char[] chars = content.toCharArray();
        Pinyin[] pinyins = PinyinDictionary.convertToPinyinArray(content);

        for (int i = 0; i < chars.length; ++i) {
            Word word = new Word();
            word.setChr(chars[i]);
            String pinyin = pinyins[i].getPinyinWithToneMark();
            word.setPinyin("none".equals(pinyin) ? "" : pinyin);
            pronounce.getWords().add(word);
        }

        AnnotationRecord record = new AnnotationRecord();
        record.setSectionId(1L);
        record.setSectionVersion(1L);
        record.setPronounce(pronounce);
        record.setContentMarks(new ContentMarks());
        record.setCreatedAt(LocalDateTime.now());

        int rows = context.insertInto(Tables.ANNOTATION)
                .set(record)
                .returning(Tables.ANNOTATION.ID)
                .execute();

        assertEquals(1, rows);
    }
}
