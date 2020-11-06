package by.teachmeskills;

import by.teachmeskills.dao.BookDao;
import by.teachmeskills.dao.CartDao;
import by.teachmeskills.model.Book;
import by.teachmeskills.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test {
    public static void main(String[] args) {
//Book bookForInsert = BookDao.getINSTANCE().getBookById(1l);
//        Book bookForInsert2 = BookDao.getINSTANCE().getBookById(2l);
//CartDao.getINSTANCE().saveBookToCart(1l,bookForInsert);
        CartDao.getINSTANCE().saveBookToCartById(11l,1l);



//       Cart gettCart =  CartDao.getINSTANCE().getCart(20l);
//        System.out.println("CCCCCCCCC " + gettCart.getId());
//       CartDao.getINSTANCE().deleteCart(20l);
//       List<Cart> carts = CartDao.getINSTANCE().getAllActiveCarts();
//     carts.stream().forEach(cart -> System.out.println(cart.getId()));
      //  System.out.println(gettCart.getId());
//        CartDao.getINSTANCE().newCart();
//        CartDao.getINSTANCE().newCart();
//        CartDao.getINSTANCE().deleteCart(14l);
//        CartDao.getINSTANCE().deleteCart(15l);
//        CartDao.getINSTANCE().deleteCart(16l);
       // Cart cart = CartDao.getINSTANCE().getCart(1l);

 //       Book book = BookDao.getINSTANCE().getBookById(1l);
       // System.out.println("Cart id is: " +cart.getId());

        //CartDao.getINSTANCE().saveCart(3l);

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
