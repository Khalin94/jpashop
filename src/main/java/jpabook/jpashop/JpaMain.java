package jpabook.jpashop;

import jpabook.jpashop.domain.Member;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setName("hello");
            member.setCity("LA");
            member.setStreet("45Ave");

            em.persist(member);

            tx.commit();
        }catch (TransactionRequiredException e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
