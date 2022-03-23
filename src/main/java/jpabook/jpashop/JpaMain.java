package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jpabook.jpashop.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.ORDER);
            order.setMember(member);
            em.persist(order);

            Item item = new Item();
            item.setName("item1");
            item.setPrice(1000);
            item.setStockQuantity(5);
            em.persist(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setOrderPrice(2000);
            orderItem.setCount(2);
            em.persist(orderItem);

            OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
            Item item1 = findOrderItem.getItem();
            Order order1 = findOrderItem.getOrder();
            Member member1 = order1.getMember();

            System.out.println("=====================");
            System.out.println(findOrderItem.getItem().getPrice() + " : " + findOrderItem.getOrderPrice());
            System.out.println(item1.getStockQuantity());
            System.out.println(order1.getMember().getStreet());
            System.out.println(member1.getZipcode());
            System.out.println("=====================");

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
