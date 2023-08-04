package jpabasic01.jpa01.practice;

import jpabasic01.jpa01.practice.domain.Address;
import jpabasic01.jpa01.practice.domain.Member;

import javax.persistence.*;
import java.util.List;

public class PracticeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setName("조민기");
            member.setAge(31);
            member.setAddress(new Address("경기도", "광교중앙로 145", "16508"));
            em.persist(member);

            createMembers(em, 1000);

            // JPQL
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.address.city = :city", Member.class);
//            query.setParameter("city", "경기도");
//            List<Member> result = query.getResultList();

            // JPA Criteria
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("address").get("city"), "경기도"));
//            List<Member> result = em.createQuery(cq).getResultList();

            // native SQL
//            String query = "SELECT * FROM TB_MEMBER WHERE CITY = '경기도'";
//            List<Member> result = em.createNativeQuery(query, Member.class).getResultList();

            em.flush();
            em.clear();

//            TypedQuery<MemberDTO> query = em.createQuery("select new jpabasic01.jpa01.practice.domain.MemberDTO(m.name, m.age) from Member m where m.id = :memberId", MemberDTO.class);
//            query.setParameter("memberId", member.getId());
//            MemberDTO result = query.getSingleResult();

            TypedQuery<Member> query = em.createQuery("select m from Member m order by m.age desc", Member.class);
            query.setFirstResult(0);
            query.setMaxResults(100);
            List<Member> result = query.getResultList();

            result.forEach(System.out::println);

            System.out.println("result = " + result);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }

    private static void createMembers(EntityManager em, Integer count) {
        for(int i = 1; i <= count; i++) {
            em.persist(new Member("MEMBER " + i, random(1, 100)));
        }
    }

    private static int random(int min, int max) {
        return (int) (Math.random() * (max - min) + 1) * min;
    }
}
