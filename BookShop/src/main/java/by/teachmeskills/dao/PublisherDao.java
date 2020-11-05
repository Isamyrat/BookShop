package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Publisher;
import by.teachmeskills.service.CheckId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public final class PublisherDao {
    private static final PublisherDao INSTANCE = new PublisherDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static PublisherDao getINSTANCE() {
        return INSTANCE;
    }

    public Serializable savePublisher(String name) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = 0;
            Publisher publisher = new Publisher(name);
            id = session.save(publisher);
            session.getTransaction().commit();
            FACTORY.close();
            return id;
        }
    }

    public void deletePublish(Byte id) {
        if (getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                    Publisher publisher = session.load(Publisher.class, id);
                    session.delete(publisher);
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
    }

    public Publisher getPublisher(String name) {
        Publisher publisher = null;
        if (getINSTANCE().existsName(name)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query<Publisher> query = session.createQuery("select p from Publisher p where p.name=:name", Publisher.class)
                        .setParameter("name", name);
                publisher = query.getSingleResult();
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
        return publisher;
    }

    public List<Book> getAllBookForPublisher(String namePublisher) {
        List<Book> books = null;
        if (INSTANCE.existsName(namePublisher)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query<Book> query = session.createQuery("select b from Book b join b.publisher p where p.name=:name", Book.class)
                        .setParameter("name", namePublisher);
                books = query.list();
            }
        }
        return books;
    }




    public Boolean existsName(String name) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select p from Publisher p where name = :n");
        query.setParameter("n", name);
        if (query.uniqueResult() == null){
            System.out.println("This publisher not exist");
        }
        return (query.uniqueResult() != null);
    }

    public Boolean existId(Byte id) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select p from Publisher p where p.id = :id");
        query.setParameter("id", id);
        if (query.uniqueResult() == null){
            System.out.println("This publisher not exist");
        }
        return (query.uniqueResult() != null);
    }

}
