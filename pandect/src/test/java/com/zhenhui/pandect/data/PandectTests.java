package com.zhenhui.pandect.data;

import com.zhenhui.pandect.Application;
import com.zhenhui.pandect.data.enums.PandectStatus;
import com.zhenhui.pandect.data.enums.PandectType;
import com.zhenhui.pandect.data.tables.records.PandectRecord;
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
public class PandectTests {

    @Autowired
    private DSLContext context;

    @Test
    public void testInsert() {

        PandectRecord record = new PandectRecord();
        record.setTitle("test");
        record.setAuthor("anyone");
        record.setCover("");
        record.setDescription("");
        record.setType(PandectType.SINGLE);
        record.setStatus(PandectStatus.NORMAL);
        record.setCreatedAt(LocalDateTime.now());

        int rows = context.insertInto(Tables.PANDECT_).set(record).execute();
        assertEquals(1, rows);
    }

}
