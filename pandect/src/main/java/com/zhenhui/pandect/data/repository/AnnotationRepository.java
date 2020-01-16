package com.zhenhui.pandect.data.repository;

import com.zhenhui.pandect.data.Tables;
import com.zhenhui.pandect.data.domain.ContentMarks;
import com.zhenhui.pandect.data.domain.Pronounce;
import com.zhenhui.pandect.data.tables.records.AnnotationRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AnnotationRepository {

    @Autowired
    private DSLContext context;

    public Long createAnnotation(Long sectionId
            , Long sectionVersion
            , Pronounce pronounce
            , ContentMarks contentMarks) {

        AnnotationRecord record = new AnnotationRecord();
        record.setSectionId(sectionId);
        record.setSectionVersion(sectionVersion);
        record.setPronounce(pronounce);
        record.setContentMarks(contentMarks);
        record.setCreatedAt(LocalDateTime.now());

        context.insertInto(Tables.ANNOTATION).set(record).execute();
        return record.getId();
    }

    public AnnotationRecord queryAnnotation(Long sectionId, Long sectionVersion) {
        return context.selectFrom(Tables.ANNOTATION)
                .where(Tables.ANNOTATION.SECTION_ID.eq(sectionId)
                        .and(Tables.ANNOTATION.SECTION_VERSION.eq(sectionVersion)))
                .limit(1)
                .fetchOneInto(AnnotationRecord.class);
    }

    public List<AnnotationRecord> queryAnnotations(Long sectionId) {
        return context.selectFrom(Tables.ANNOTATION)
                .where(Tables.ANNOTATION.SECTION_ID.eq(sectionId))
                .orderBy(Tables.ANNOTATION.SECTION_VERSION.sortDesc())
                .fetchInto(AnnotationRecord.class);
    }

    public boolean updatePronounce(Long annotationId, Long version, Pronounce pronounce) {

        return context.update(Tables.ANNOTATION)
                .set(Tables.ANNOTATION.PRONOUNCE, pronounce)
                .set(Tables.ANNOTATION.VERSION, Tables.ANNOTATION.VERSION.plus(1))
                .set(Tables.ANNOTATION.UPDATED_AT, LocalDateTime.now())
                .where(Tables.ANNOTATION.ID.eq(annotationId).and(Tables.ANNOTATION.VERSION.eq(version)))
                .execute() > 0;
    }

    public boolean updateContentMarks(Long annotationId, Long version, ContentMarks contentMarks) {

        return context.update(Tables.ANNOTATION)
                .set(Tables.ANNOTATION.CONTENT_MARKS, contentMarks)
                .set(Tables.ANNOTATION.VERSION, Tables.ANNOTATION.VERSION.plus(1))
                .set(Tables.ANNOTATION.UPDATED_AT, LocalDateTime.now())
                .where(Tables.ANNOTATION.ID.eq(annotationId).and(Tables.ANNOTATION.VERSION.eq(version)))
                .execute() > 0;
    }

}
