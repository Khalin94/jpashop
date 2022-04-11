package jpabook.jpashop;

import jpabook.jpashop.domain.Book;

import javax.persistence.*;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Book book = new Book();
            book.setAuthor("kim");
            book.setIsbn("1ng903nfka893n");
            book.setCreatedBy("lee");
            book.setCreatedDate(LocalDateTime.now());
            book.setPrice(10_000);
            book.setStockQuantity(5);

            em.persist(book);

            em.flush();
            em.clear();


            Book book1 = em.find(Book.class, book.getId());

            book1.toString();

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
