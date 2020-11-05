package by.teachmeskills;

import by.teachmeskills.dao.BookDao;
import by.teachmeskills.dao.CartDao;
import by.teachmeskills.model.Book;
import by.teachmeskills.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args) {

//        Cart cart = CartDao.getINSTANCE().getCart(1l);
//        System.out.println("Cart id is: " +cart.getId());
     //   CartDao.getINSTANCE().saveCart(2l);
CartDao.getINSTANCE().saveBookToCart();
   //     SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
//        try (Session session = FACTORY.openSession()) {
//            session.beginTransaction();
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
//            Book book = BookDao.getINSTANCE().getBookById(2L);
//            System.out.println(book.toString());
//            session.getTransaction().commit();
//            FACTORY.close();
//        }



//        Book updBook = Book.builder().year_of_publication(1928).build();
//        BookDao.getINSTANCE().updateBook(1l, updBook);
//BookDao.getINSTANCE().updateBook(1l,updBook);

    }
}
