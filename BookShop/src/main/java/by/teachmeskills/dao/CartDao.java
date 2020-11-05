package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Cart;
import by.teachmeskills.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;

public class CartDao {
    private static final CartDao INSTANCE = new CartDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static CartDao getINSTANCE() {
        return INSTANCE;
    }


    public Serializable saveCart(Long id) {
        Serializable s = 0;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Cart cart = new Cart(id);
            s = session.save(cart);
            session.getTransaction().commit();
        }finally {
            FACTORY.close();}
        return s;
        }



    public void saveBookToCart(Cart cart, Book book) {
        try {
            Session session = FACTORY.openSession();
            session.beginTransaction();
            Serializable s = 0;
            cart.getBooks().add(book);
            session.update(cart);
            session.getTransaction().commit();
        }finally {
            FACTORY.close();
        }
    }

    public Cart getCart(Long id) {
        Cart cart = null;
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query<Cart> query = session.createQuery("select c from Cart c where c.id=:id", Cart.class)
                        .setParameter("id", id);
                cart = query.getSingleResult();
                session.getTransaction().commit();
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This cart not exist");
            } finally {
                FACTORY.close();
            }
        return cart;
    }


}
