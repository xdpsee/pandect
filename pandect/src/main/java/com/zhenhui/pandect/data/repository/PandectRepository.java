package com.zhenhui.pandect.data.repository;

import com.zhenhui.pandect.data.Tables;
import com.zhenhui.pandect.data.enums.PandectStatus;
import com.zhenhui.pandect.data.enums.PandectType;
import com.zhenhui.pandect.data.tables.records.PandectRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.zhenhui.pandect.data.tables.Pandect.PANDECT_;

@Component
public class PandectRepository {

    @Autowired
    private DSLContext context;

    public void createPandect(PandectType type
            , String title
            , String description
            , String author
            , String cover
            , PandectStatus status) {

        PandectRecord record = new PandectRecord();
        record.setType(type);
        record.setTitle(title);
        record.setDescription(description);
        record.setAuthor(author);
        record.setCover(cover);
        record.setStatus(status);
        record.setCreatedAt(LocalDateTime.now());

        context.insertInto(Tables.PANDECT_)
                .set(record)
                .execute();
    }

    public int queryCountByType(PandectType pandectType) {
        return context.selectCount()
                .from(PANDECT_)
                .where(PANDECT_.TYPE.eq(pandectType))
                .fetchOneInto(Integer.class);
    }

    public List<PandectRecord> queryByTypeOrderByCreatedAt(PandectType type, int offset, int limit) {
        return context.selectFrom(PANDECT_)
                .where(PANDECT_.TYPE.eq(type))
                .orderBy(PANDECT_.CREATED_AT.sortDesc())
                .offset(offset)
                .limit(limit)
                .fetchInto(PandectRecord.class);
    }

    public boolean queryExists(Long pandectId) {
        return context.selectCount()
                .from(PANDECT_)
                .where(PANDECT_.ID.eq(pandectId))
                .fetchOneInto(Integer.class) > 0;
    }

}
