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
            // 값을 이전 값과 동일한 값으로 설정하면 더티체킹이 될까? -> 안됨. 스냅샷과 비교 했을 때 달라진 게 없어서.
//            Member member = findById(em, 80L);
//            member.setName("Jo (Modified)");

            // flush가 되도 1차 캐시는 유지 된다는데 스냅샷도 그대로 유지될까? -> 안됨, flush 시점에 스냅샷이 갱신되는 듯함
            // 1 - 회원 생성 (영속성 컨텍스트, 1차 캐시, 스냅샷 생성)
            Member member = createMember(em, 87L, "origin name");

            // 2 - 이름 변경 후 flush (DB 반영됨, 스냅샷은 유지돨까?)
            member.setName("new name");
            em.flush();

            // 3 - 기존 이름으로 다시 변경 (UPDATE 날라간다면 스냅샷은 유지 안됨)
            member.setName("origin name");

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
