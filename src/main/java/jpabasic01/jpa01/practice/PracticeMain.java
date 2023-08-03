package jpabasic01.jpa01.practice;

import jpabasic01.jpa01.helloworld.Team;
import jpabasic01.jpa01.practice.domain.Address;
import jpabasic01.jpa01.practice.domain.Member;
import jpabasic01.jpa01.practice.domain.item.Album;
import jpabasic01.jpa01.practice.domain.item.Item;
import jpabasic01.jpa01.practice.domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class PracticeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setName("조민기");
            member.setCreatedAt(LocalDateTime.now());
            member.setModifiedAt(LocalDateTime.now());
            member.setAddress(new Address("경기도", "광교중앙로 145", "16508"));

            em.persist(member);
            em.flush();
            em.clear();

            Member member1 = em.getReference(Member.class, member.getId());

//            System.out.println("address1.equals(address2) = " + address1.equals(address2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
