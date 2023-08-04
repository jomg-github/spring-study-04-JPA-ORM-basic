package jpabasic01.jpa01.helloworld;

import jpabasic01.jpa01.practice.domain.Member;
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

            em.persist(new Player("PLAYER 0", team1));
            em.persist(new Player("PLAYER 1", team1));
            em.persist(new Player("PLAYER 2", team2));
            em.persist(new Player("PLAYER 3", team3));

            createPlayers(em, 100, team1);

//            em.flush();
//            em.clear();

//            List<Player> allPlayers = em.createQuery("select p from Player p join fetch p.team", Player.class).getResultList();
//
//            List<String> teamNames = allPlayers.stream().map(p -> p.getTeam().getName()).toList();
//            System.out.println(teamNames);

//            TypedQuery<Team> query = em.createQuery("select t from Team t join fetch t.players", Team.class);
//            query.setFirstResult(0);
//            query.setMaxResults(1);
//            List<Team> result = query.getResultList();

//            List<Player> result = em.createNamedQuery("Player.findByName", Player.class)
//                    .setParameter("name", "PLAYER")
//                    .getResultList();
            List<Player> allPlayers = em.createQuery("select p from Player p", Player.class).getResultList();

            int result = em.createQuery("update Player p set p.team = :newTeam where p.team = :oldTeam")
                    .setParameter("oldTeam", team1)
                    .setParameter("newTeam", team2)
                    .executeUpdate();

            List<Player> allPlayers2 = em.createQuery("select p from Player p", Player.class).getResultList();

            System.out.println("==============================");
            System.out.println("result = " + result);
            System.out.println("==============================");

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void createPlayers(EntityManager em, Integer count, Team team) {
        for(int i = 1; i <= count; i++) {
            em.persist(new Player("PLAYER " + i + "(AUTO)", team));
        }
    }
}
