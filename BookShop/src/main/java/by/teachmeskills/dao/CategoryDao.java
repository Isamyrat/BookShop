package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Category;
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
        if(CategoryDao.getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Category category = session.load(Category.class, id);
                session.delete(category);
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
    }

    public List<Category> getCategoryByName(String name) {
        List<Category> categories = null;
        if(CategoryDao.getINSTANCE().existsName(name)) {
            try (Session session = FACTORY.openSession()) {
                session.getTransaction();
                Query<Category> query = session.createQuery("select c from Category c where c.name=:name", Category.class)
                        .setParameter("name", name);
                categories = query.list();
            }
        }
        return categories;
    }

    public Category getCategoryById(Long id) {
        Category category= null;
        if(CategoryDao.getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.getTransaction();
                Query<Category> query = session.createQuery("select c from Category c where c.id=:id", Category.class)
                        .setParameter("id", id);
                category = query.getSingleResult();
            }
        }
        return category;
    }

    public List<Book> getAllBookForCategory(String nameCategory) {
        List<Book> books = null;
        if(CategoryDao.getINSTANCE().existsName(nameCategory)) {
            try (Session session = FACTORY.openSession()) {
                session.getTransaction();
                Query<Book> query = session.createQuery("select b from Book b join b.category c where  c.name=:name", Book.class)
                        .setParameter("name", nameCategory);
                books = query.list();
            }
        }
        return books;
    }
    public Boolean existsName(String name) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select c from Category c where name = :name");
        query.setParameter("name", name);
        if (query.uniqueResult() == null) {
            System.out.println("This category not exist");
        }
        return (query.uniqueResult() != null);
    }

    public Boolean existId(Long id) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select c from Category c where c.id = :id");
        query.setParameter("id", id);
        if (query.uniqueResult() == null) {
            System.out.println("This category not exist");
        }
        return (query.uniqueResult() != null);
    }

    public static CategoryDao getINSTANCE() {
        return INSTANCE;
    }
}
