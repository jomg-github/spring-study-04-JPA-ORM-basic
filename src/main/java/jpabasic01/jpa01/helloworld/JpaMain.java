package jpabasic01.jpa01.helloworld;

import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team1 = new Team("TEAM 1");
            Team team2 = new Team("TEAM 2");
            Team team3 = new Team("TEAM 3");

            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

            em.persist(new Player("PLAYER 1", team1));
            em.persist(new Player("PLAYER 2", team2));
            em.persist(new Player("PLAYER 3", team3));

            em.flush();
            em.clear();

            List<Player> allPlayers = em.createQuery("select p from Player p join fetch p.team", Player.class).getResultList();

            List<String> teamNames = allPlayers.stream().map(p -> p.getTeam().getName()).toList();
            System.out.println(teamNames);

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
