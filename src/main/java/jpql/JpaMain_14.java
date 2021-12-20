package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_14 {

    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = enf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Team teamC = new Team();
            teamC.setName("팀C");
            em.persist(teamC);


            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.changeTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.changeTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.changeTeam(teamB);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            member4.changeTeam(teamC);
            em.persist(member4);

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            em.clear();

            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("findMember = " + findMember.getAge());

//            System.out.println("resultCount = " + resultCount);
//
//            System.out.println("member1 = " + member1.getAge());
//            System.out.println("member2 = " + member2.getAge());
//            System.out.println("member3 = " + member3.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        enf.close();

    }
}
