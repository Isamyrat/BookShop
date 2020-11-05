package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.OrderItem;
import by.teachmeskills.model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;

public class OrderItemDao {
    public static final OrderItemDao INSTANCE = new OrderItemDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public Serializable saveOrderItem(String name,Long i){
        Serializable id =0;
        try(Session session=FACTORY.openSession()){
            session.getTransaction();
            Book book= (Book) BookDao.getINSTANCE().getBookFromName(name);
            OrderItem orderItem =OrderItem.builder()
                    .book(book)
                    .order(OrdersDao.getINSTANCE().getOrders(i))
                    .price(book.getPrice())
                    .build();
            id=session.save(orderItem);
        }
        return id;
    }

    public OrderItem getOrderItem(Long id){
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





    public static OrderItemDao getINSTANCE() {
        return INSTANCE;
    }
}
