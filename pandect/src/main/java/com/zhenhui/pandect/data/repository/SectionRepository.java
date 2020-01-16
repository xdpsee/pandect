package com.zhenhui.pandect.data.repository;

import com.zhenhui.pandect.data.Tables;
import com.zhenhui.pandect.data.tables.records.SectionRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SectionRepository {

    @Autowired
    private DSLContext context;

    public void createSection(Long pandectId, @Nullable Long chapterId, String title, String content) {

        SectionRecord record = new SectionRecord();
        record.setPandectId(pandectId);
        record.setChapterId(null == chapterId ? 0L : chapterId);
        record.setTitle(title);
        record.setContent(content);
        record.setCreatedAt(LocalDateTime.now());

        context.insertInto(Tables.SECTION).set(record).execute();
    }

    public int queryCount(Long pandectId, @Nullable Long chapterId) {

        return context.selectCount()
                .from(Tables.SECTION)
                .where(Tables.SECTION.PANDECT_ID.eq(pandectId)
                        .and(Tables.SECTION.CHAPTER_ID.eq(null == chapterId ? 0L : chapterId)))
                .fetchOneInto(Integer.class);

    }

    public SectionRecord queryById(Long sectionId) {

        return context.selectFrom(Tables.SECTION)
                .where(Tables.SECTION.ID.eq(sectionId))
                .orderBy(Tables.SECTION.SEQUENCE.sortAsc())
                .fetchOneInto(SectionRecord.class);
    }

    public List<SectionRecord> querySectionsOrderBySequence(Long pandectId, @Nullable Long chapterId, int offset, int limit) {

        return context.selectFrom(Tables.SECTION)
                .where(Tables.SECTION.PANDECT_ID.eq(pandectId)
                        .and(Tables.SECTION.CHAPTER_ID.eq(null == chapterId ? 0L : chapterId)))
                .orderBy(Tables.SECTION.SEQUENCE.sortAsc())
                .offset(offset)
                .limit(limit)
                .fetchInto(SectionRecord.class);
    }
}
