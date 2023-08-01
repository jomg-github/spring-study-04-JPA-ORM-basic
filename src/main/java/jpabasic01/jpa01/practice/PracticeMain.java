package jpabasic01.jpa01.practice;

import jpabasic01.jpa01.practice.domain.Member;
import jpabasic01.jpa01.practice.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PracticeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
//            // 주문한 회원 찾기
//            // 1. 주문 조회
//            Order order = em.find(Order.class, 1L);
//
//            // 2. 주문의 회원 ID값 조회
//            Long memberId = order.getMemberId();
//
//            // 3. 회원 조회
//            Member member = em.find(Member.class, memberId);

            Order order = em.find(Order.class, 1L);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
