package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;

public class OrderItemDao {
    public static final OrderItemDao INSTANCE = new OrderItemDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public Serializable saveOrderItem(Long idBook,Long idOrders){
        Serializable id =0;
        try(Session session=FACTORY.openSession()){
            session.getTransaction();
            Book book=BookDao.getINSTANCE().getBookById(idBook);
            OrderItem orderItem =OrderItem.builder()
                    .book(book)
                    .order(OrdersDao.getINSTANCE().getOrdersById(idOrders))
                    .price(book.getPrice())
                    .build();
            id=session.save(orderItem);
        }
        return id;
    }
    public void deleteOrderItem(Long id){
        if(OrderItemDao.getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                OrderItem orderItem = session.load(OrderItem.class, id);
                session.delete(orderItem);
                session.beginTransaction();
                session.getTransaction().commit();
                FACTORY.close();
            }catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This orderItem not exist");
            }finally{
                FACTORY.close();
            }
        }
    }
    public OrderItem getOrderItemById(Long id){
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            OrderItem orderItem = null;
            try {
                Query<OrderItem> query = session.createQuery("select oi from OrderItem oi where oi.id=:id", OrderItem.class)
                        .setParameter("id", id);
                orderItem = query.getSingleResult();
                session.getTransaction().commit();
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This orders not exist");
            }finally{
                FACTORY.close();
            }
            return orderItem;
        }
    }

    public Boolean existId(Long id) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select o from OrderItem o where o.id = :id");
        query.setParameter("id", id);
        if (query.uniqueResult() == null) {
            System.out.println("This orders not exist");
        }
        return (query.uniqueResult() != null);
    }



    public static OrderItemDao getINSTANCE() {
        return INSTANCE;
    }
}
