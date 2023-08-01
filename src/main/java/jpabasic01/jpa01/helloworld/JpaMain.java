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

            Locker locker = new Locker();
            locker.setName("모라타의 라커");
            em.persist(locker);

            Player player = new Player();
            player.setName("모라타");
            player.setLocker(locker);
            player.changeTeam(team);
            em.persist(player);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
