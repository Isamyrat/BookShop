package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Category;
import by.teachmeskills.service.CheckId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public final class CategoryDao {
    private static final CategoryDao INSTANCE = new CategoryDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();


    public Serializable saveCategory(String name) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = 0;
            Category category = new Category(name);
            id = session.save(category);
            session.getTransaction().commit();
            FACTORY.close();
            return id;
        }
    }

    public void deleteCategory(Long id) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            boolean result = CheckId.checkCategoryId(id);
            if (result) {
                Category category = session.load(Category.class, id);
                session.delete(category);
            } else {
                System.out.println("Category with this id was not found!!!!");
            }
            session.getTransaction().commit();
            FACTORY.close();
        }
    }

    public Category getCategory(String name) {
        Category category = null;
        try (Session session = FACTORY.openSession()) {
            session.getTransaction();
            Query<Category> query = session.createQuery("select c from Category c where c.name=:name", Category.class)
                    .setParameter("name", name);
            category = query.getSingleResult();
        }
        return category;
    }

    public List<Book> getAllBookForCategory(String nameCategory) {
        List<Book> books = null;
        try (Session session = FACTORY.openSession()) {
            session.getTransaction();
            Query<Book> query = session.createQuery("select b from Book b join b.category c where  c.name=:name", Book.class)
                    .setParameter("name", nameCategory);
            books = query.list();
        }
        return books;
    }

    public static CategoryDao getINSTANCE() {
        return INSTANCE;
    }
}
