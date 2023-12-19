package com.Example.dtos;

import com.Example.Enteties.School;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class SchoolDto {

    public static School getSchool(int schoolId) {
            AtomicReference<School> subject = new AtomicReference<>(null);

            inTransaction(entityManager -> {
                subject.set(entityManager.find(School.class, schoolId));
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
