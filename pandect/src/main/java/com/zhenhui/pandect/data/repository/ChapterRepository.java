package com.zhenhui.pandect.data.repository;

import com.zhenhui.pandect.data.Tables;
import com.zhenhui.pandect.data.tables.records.ChapterRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ChapterRepository {

    @Autowired
    private DSLContext context;

    public void createChapter(Long pandectId, String title) {
        ChapterRecord record = new ChapterRecord();
        record.setPandectId(pandectId);
        record.setTitle(title);
        record.setCreatedAt(LocalDateTime.now());

        context.insertInto(Tables.CHAPTER).set(record).execute();
    }

    public int queryCount(Long pandectId) {
        return context.selectCount()
                .from(Tables.CHAPTER)
                .where(Tables.CHAPTER.PANDECT_ID.eq(pandectId))
                .fetchOneInto(Integer.class);
    }

    public List<ChapterRecord> queryChaptersOrderBySequence(Long pandectId) {
        return context.selectFrom(Tables.CHAPTER)
                .where(Tables.CHAPTER.PANDECT_ID.eq(pandectId))
                .orderBy(Tables.CHAPTER.SEQUENCE.sortAsc())
                .fetchInto(ChapterRecord.class);
    }

    public void updateSequence(Long pandectId, List<Long> rowIds) {
        final int size = rowIds.size();
        for (int i = 0; i < size; ++i) {
            Long rowId = rowIds.get(i);
            context.update(Tables.CHAPTER)
                    .set(Tables.CHAPTER.SEQUENCE, i)
                    .where(Tables.CHAPTER.ID.eq(rowId).and(Tables.CHAPTER.PANDECT_ID.eq(pandectId)))
                    .execute();
        }
    }

    public int updateChapter(Long pandectId, Long id, String title) {

        return context.update(Tables.CHAPTER)
                .set(Tables.CHAPTER.TITLE, title)
                .where(Tables.CHAPTER.ID.eq(id).and(Tables.CHAPTER.PANDECT_ID.eq(pandectId)))
                .execute();
    }
}

