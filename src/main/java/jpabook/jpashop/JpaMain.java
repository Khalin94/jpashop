package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jpabook.jpashop.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

            // order는 연관관계에 주인이 아니기 때문에 db값에는 영향이 없다. 객체지향적으로 만들어주고 바로 order에서 OrderItem값을 가지고 올 수 있다.
            order.getOrderItem().add(orderItem);

//            em.flush();
//            em.clear();

            Order order1 = em.find(Order.class, order.getId());
            List<OrderItem> orderItem1 = order1.getOrderItem();

            // 양방향 관계 시 flush, clear 없이 데이터를 가지고 오면 order1의 OrderItem에 값이 없기 때문에 가지고 올 수 없다. (값들은 1차 캐시에만 들어 있기 때문)
            // 방법1) flush, clear
            // 방법2) persist 후 List에 add
            // 방법3) 편의 메소드 만들기
            System.out.println("==================");
            for (OrderItem orderItem2 : orderItem1) {
                System.out.println(orderItem2.getItem().getPrice());
            }
            System.out.println("==================");

            /*
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

             */

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
