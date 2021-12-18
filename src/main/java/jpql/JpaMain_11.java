package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_11 {

    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = enf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            for (int i = 0; i < 100; i++) {
                Team team = new Team();
                team.setName("팀" + i);
                em.persist(team);

                Member member = new Member();
                member.setUsername("회원" + i);
                member.changeTeam(team);
                em.persist(member);
            }
//            Team teamA = new Team();
//            teamA.setName("팀A");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("팀B");
//            em.persist(teamB);
//
//            Team teamC = new Team();
//            teamC.setName("팀C");
//            em.persist(teamC);


//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.changeTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.changeTeam(teamA);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUsername("회원3");
//            member3.changeTeam(teamB);
//            em.persist(member3);
//
//            Member member4 = new Member();
//            member4.setUsername("회원4");
//            member4.changeTeam(teamC);
//            em.persist(member4);

            em.flush();
            em.clear();

//            String query = "select t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//
//            System.out.println("===========");
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | members = " + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }

            String query = "select t from Team t";
            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(100)
                    .getResultList();

            System.out.println("===========");
            for (Team team : result) {
                System.out.println("team = " + team.getName() + " | members = " + team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        enf.close();

    }
}
