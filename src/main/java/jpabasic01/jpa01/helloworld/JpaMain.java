package jpabasic01.jpa01.helloworld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setName("민기");

            System.out.println("========= BEFORE ==========");
            em.persist(member);
            System.out.println("========= AFTER ==========");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    /**
     * 회원 수정
     */
    private static void update(EntityManager em, Long id, String name) {
        Member member = findById(em, id);
        member.setName(name);
    }

    /**
     * 회원 조회
     */
    private static Member findById(EntityManager em, Long id) {
        Member member = em.find(Member.class, id);
        System.out.println("member = " + member);
        return member;
    }

    /**
     * 회원 등록
     */
    private static Member createMember(EntityManager em, Long id, String name) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        em.persist(member);
        return member;
    }
}
