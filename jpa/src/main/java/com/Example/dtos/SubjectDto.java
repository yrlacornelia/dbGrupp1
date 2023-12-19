package com.Example.dtos;

import com.Example.Enteties.Subject;
import com.Example.Enteties.User;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * DTO for {@link com.Example.Enteties.Subject}
 */
public class SubjectDto {

    public static Subject getSubject(Integer subjectId) {
        AtomicReference<Subject> subject = new AtomicReference<>(null);

        inTransaction(entityManager -> {
            subject.set(entityManager.find(Subject.class, subjectId));
        });

        return subject.get();

    }

    static void inTransaction(Consumer<EntityManager> work) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                work.accept(entityManager);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }
}

