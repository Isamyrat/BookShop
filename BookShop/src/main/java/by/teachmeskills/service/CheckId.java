package by.teachmeskills.service;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Category;
import by.teachmeskills.model.Orders;
import by.teachmeskills.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CheckId {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static boolean checkPublishId(Long id) {
        boolean result = false;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Publisher publisher = null;
            try {
                publisher = (Publisher) session.get(Publisher.class, id);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This publisher not exist");
            }
            if (publisher != null) {
                result = true;
            }
            session.getTransaction().commit();
            FACTORY.close();
        }
        return result;
    }

    public static boolean checkCategoryId(Long id) {
        boolean result = false;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Category category = null;
            try {
                category = (Category) session.get(Category.class, id);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This publisher not exist");
            }
            if (category != null) {
                result = true;
            }
            session.getTransaction().commit();
            FACTORY.close();
        }
        return result;
    }

    public static boolean checkBookId(Long id) {
        boolean result = false;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Book book = null;
            try {
                book = (Book) session.get(Book.class, id);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This publisher not exist");
            }
            if (book != null) {
                result = true;
            }
            session.getTransaction().commit();
            FACTORY.close();
        }
        return result;
    }
    public static boolean checkOrdersId(Long id) {
        boolean result = false;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Orders orders = null;
            try {
                orders = (Orders) session.get(Orders.class, id);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This publisher not exist");
            }
            if (orders != null) {
                result = true;
            }
            session.getTransaction().commit();
            FACTORY.close();
        }
        return result;
    }
}
