package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain_Ex {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /** 양방향 연관관계를 사용할 경우
             * 양방향 연관관계는 만들지 않는 편이 가장 좋으나, 조회의 용이성(JPQL등) 및 비즈니스 로직의 이유로 필요시 양방향 맵핑
             * **/
//            Order order = new Order();
//            order.addOrderItem(new OrderItem());

            /**
             * 양방향 연관관계를 만들지 않아도 가능하다.
             * 단방향 연관관계만으로도 충분히 모든 개발이 가능하다.
             */
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);
            em.flush();
            em.clear();

            Book book = new Book();
            book.setName("헨젤과 그레텔");
            book.setAuthor("Shawn");

            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
