package by.teachmeskills.dao;

import by.teachmeskills.model.Orders;
import by.teachmeskills.service.OrdersService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrdersDao {
    private static final OrdersDao INSTANCE = new OrdersDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public Serializable saveOrdersDao(LocalDateTime l, String name, String t_pay, String phone, int price, boolean paid) {
        Serializable id=0;
        if(OrdersService.getNumber(phone)) {
           try (Session session = FACTORY.openSession()) {
               session.getTransaction();
               Orders orders = Orders.builder()
                       .time_of_order(l)
                       .user_name(name)
                       .type_of_payment(t_pay)
                       .user_phone(phone)
                       .order_price(price)
                       .paid_up(paid)
                       .build();
               id = session.save(orders);
               FACTORY.close();
           }
       }
        return id;
    }

    public void deleteOrders(Long id){
        if(OrdersDao.getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Orders orders = session.load(Orders.class, id);
                session.delete(orders);
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
    }

    public Orders getOrdersById(Long id){
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Orders orders = null;
                try {
                    Query<Orders> query = session.createQuery("select o from Orders o where o.id=:id", Orders.class)
                            .setParameter("id", id);
                    orders = query.getSingleResult();
                    session.getTransaction().commit();
                } catch (NullPointerException | IllegalArgumentException e) {
                    System.err.println("This orders not exist");
                }finally{
                    FACTORY.close();
                }
                return orders;
            }

        }

    public Boolean existId(Long id) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select ord from Orders ord where ord.id = :id");
        query.setParameter("id", id);
        if (query.uniqueResult() == null) {
            System.out.println("This orders not exist");
        }
        return (query.uniqueResult() != null);
    }

    public static OrdersDao getINSTANCE() {
        return INSTANCE;
    }
}

