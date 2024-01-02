package com.Example.dtos;

import com.Example.Enteties.Subject;
import java.util.concurrent.atomic.AtomicReference;

/**
 * DTO for {@link com.Example.Enteties.Subject}
 */
public class SubjectDto {

    public static Subject getSubject(Integer subjectId) {
        AtomicReference<Subject> subject = new AtomicReference<>(null);

        InTransactionMethod.inTransaction(entityManager -> subject.set(entityManager.find(Subject.class, subjectId)));

        return subject.get();

    }
}

