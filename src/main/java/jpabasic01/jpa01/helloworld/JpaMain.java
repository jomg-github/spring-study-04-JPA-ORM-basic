package jpabasic01.jpa01.helloworld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team("AT 마드리드");
            em.persist(team);

            Player player = new Player();
            player.setName("모라타");
//            player.setTeam(team);
            player.changeTeam(team);
            em.persist(player);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

//    /**
//     * 회원 수정
//     */
//    private static void update(EntityManager em, Long id, String name) {
//        Player player = findById(em, id);
//        player.setName(name);
//    }
//
//    /**
//     * 회원 조회
//     */
//    private static Player findById(EntityManager em, Long id) {
//        Player player = em.find(Player.class, id);
//        System.out.println("member = " + player);
//        return player;
//    }
//
//    /**
//     * 회원 등록
//     */
//    private static Player createMember(EntityManager em, Long id, String name) {
//        Player player = new Player();
//        player.setId(id);
//        player.setName(name);
//        em.persist(player);
//        return player;
//    }
}
