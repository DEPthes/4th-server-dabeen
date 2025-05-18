package hellojpa;

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
            // ✅ 테스트용 데이터 먼저 삽입 (최초 1회 실행)
            for (int i = 1; i <= 10; i++) {
                Member member = new Member();
                member.setId((long) i);
                member.setName("Member" + i);
                em.persist(member);
            }

            // ✅ 전체 조회
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // ✅ 페이징 조회 (6번째부터 8개, 즉 6~13번째)
            List<Member> paged = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            System.out.println("------ Paged Results ------");
            for (Member member : paged) {
                System.out.println("paged member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 문제가 생기면 롤백
        } finally {
            em.close();
        }

        emf.close();
    }
}