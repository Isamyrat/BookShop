package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Category;
import by.teachmeskills.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public final class BookDao {
    private static final BookDao INSTANCE = new BookDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static BookDao getINSTANCE() {
        return INSTANCE;
    }

    public Serializable saveBook(String name, String author, Double price, String describe,
                                 int year, int count, String namePub, Long idCategory) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Publisher publisher = PublisherDao.getINSTANCE().getPublisher(namePub);
            Category category = CategoryDao.getINSTANCE().getCategoryById(idCategory);
            Book book = Book.builder()
                    .name(name)
                    .author(author)
                    .price(price)
                    .describe(describe)
                    .year_of_publication(year)
                    .count_in_storage(count)
                    .publisher(publisher)
                    .category(category)
                    .build();
            Serializable id = session.save(book);
            return id;
        }
    }

    public void deleteBook(Long id) {
        if (getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                    Book book = session.load(Book.class, id);
                    session.delete(book);
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
    }


    public List<Book> getBookByName(String name) {
        List<Book> book = null;
        if (getINSTANCE().existsName(name)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query<Book> query = session.createQuery("select b from Book b where b.name=:name", Book.class)
                        .setParameter("name", name);
                book = query.list();
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
        return book;
    }

    public Book getBookById(Long id) {
        Book book = null;
        if (getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query<Book> query = session.createQuery("select b from Book b where b.id=:id", Book.class)
                        .setParameter("id", id);
                book = query.getSingleResult();
                session.getTransaction().commit();
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This book not exist");
            }
        }
        return book;
    }

    public void updateBook(Long id, Book book) {
        if (getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query query = session.createQuery("select b from Book b where b.id =:id", Book.class)
                        .setParameter("id", id);
                Book updateBook = (Book) query.getSingleResult();
                if(book.getName() != null){
                    updateBook.setName(book.getName());
                }
                if(book.getAuthor() != null){
                    updateBook.setAuthor(book.getAuthor());
                }
                if(book.getPrice() !=null){
                    updateBook.setPrice(book.getPrice());
                }
                if(book.getDescribe() !=null){
                    updateBook.setDescribe(book.getDescribe());
                }
                if(book.getYear_of_publication() != null){
                    updateBook.setYear_of_publication(book.getYear_of_publication());
                }
                if(book.getCount_in_storage() != null){
                    updateBook.setCount_in_storage(book.getCount_in_storage());
                }
                if(book.getCategory() !=null){
                    updateBook.setCategory(book.getCategory());
                }
                session.update(updateBook);
                session.getTransaction().commit();
                FACTORY.close();
            }
        }
    }

    public Boolean existsName(String name) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select b from Book b where name = :n");
        query.setParameter("n", name);
        if (query.uniqueResult() == null) {
            System.out.println("This book not exist");
        }
        return (query.uniqueResult() != null);
    }

    public Boolean existId(Long id) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select b from Book b where b.id = :id");
        query.setParameter("id", id);
        if (query.uniqueResult() == null) {
            System.out.println("This book not exist");
        }
        return (query.uniqueResult() != null);
    }
}

