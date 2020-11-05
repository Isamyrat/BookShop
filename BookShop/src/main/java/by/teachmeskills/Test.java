package by.teachmeskills;

import by.teachmeskills.dao.BookDao;
import by.teachmeskills.dao.CategoryDao;
import by.teachmeskills.dao.PublisherDao;
import by.teachmeskills.model.Book;
import by.teachmeskills.model.Publisher;
import by.teachmeskills.service.BookService;
import by.teachmeskills.service.CheckId;
import by.teachmeskills.service.OrdersService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
//            List<Book> bookList = null;
////              bookList=CategoryDao.getINSTANCE().getAllBookForCategory("dramas");
//            bookList = PublisherDao.getINSTANCE().getAllBookForPublisher("mahao");
////            System.out.println(PublisherDao.getINSTANCE().getPublisher("mahao"));
//
//            System.out.println(bookList.toString());
//            for (Book b :bookList) {
//                System.out.println(b.toString());
//                System.out.println();
//            }
//            Book book= BookDao.getINSTANCE().getBook("Harry Porter and the Philosophers Stone");
            Book book = BookDao.getINSTANCE().getBookFromId(2L);
            System.out.println(book.toString());
            session.getTransaction().commit();
            FACTORY.close();
        }
    }
}
