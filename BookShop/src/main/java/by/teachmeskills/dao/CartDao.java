package by.teachmeskills.dao;

import by.teachmeskills.model.Book;
import by.teachmeskills.model.Cart;
import by.teachmeskills.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class CartDao {
    private static final CartDao INSTANCE = new CartDao();
    SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();


    public Serializable newCart() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = 0;
            Cart cart = new Cart();
            id = session.save(cart);
            session.getTransaction().commit();
            session.close();
            return id;
        }
    }
    //переписат, удаляет все карты
    public void deleteCart(Long id) {
        if (CartDao.getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Cart cart = getINSTANCE().getCart(id);
                session.delete(cart);
                session.getTransaction().commit();
                session.close();
            }
        }
    }

    public void removeBookFromCart(Long cartId, Long bookId){
        if (getINSTANCE().existId(cartId) && BookDao.getINSTANCE().existId(bookId)) {
            try {
                Session session = FACTORY.openSession();
                session.beginTransaction();
                Query queryCart = session.createQuery("select c from Cart c where c.id =:id", Cart.class)
                        .setParameter("id", cartId);
                Cart updateCart = (Cart) queryCart.getSingleResult();
                Query queryBook = session.createQuery("select b from Book b where b.id =:id", Book.class)
                        .setParameter("id", bookId);
                Book bookForRemove = (Book) queryBook.getSingleResult();
                updateCart.getBooks().remove(bookForRemove);
                session.update(updateCart);
                session.getTransaction().commit();
            } finally {
                FACTORY.close();
            }
        }
    }


    public void saveBookToCart(Long cartId, Book book) {
        if (getINSTANCE().existId(cartId)){
            try {
                Session session = FACTORY.openSession();
                session.beginTransaction();
                Query query = session.createQuery("select c from Cart c where c.id =:id", Cart.class)
                        .setParameter("id", cartId);
                Cart updateCart = (Cart) query.getSingleResult();
                updateCart.getBooks().add(book);
                session.update(updateCart);
                session.getTransaction().commit();
            } finally {
                FACTORY.close();
            }
    }
}

    public void saveBookToCartById(Long cartId, Long bookId) {
        if (getINSTANCE().existId(cartId) && BookDao.getINSTANCE().existId(bookId)) {
            try {
                Session session = FACTORY.openSession();
                session.beginTransaction();
                Query queryCart = session.createQuery("select c from Cart c where c.id =:id", Cart.class)
                        .setParameter("id", cartId);
                Cart updateCart = (Cart) queryCart.getSingleResult();
                Query queryBook = session.createQuery("select b from Book b where b.id =:id", Book.class)
                        .setParameter("id", bookId);
                Book bookForInsert = (Book) queryBook.getSingleResult();
                updateCart.getBooks().add(bookForInsert);
                session.update(updateCart);
                session.getTransaction().commit();
                session.close();
            } finally {
              // FACTORY.close();
            }
        }
    }

    public List<Book> getBookByCartId(Long idCart) {
        List<Book> book = null;
        if (getINSTANCE().existId(idCart)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
//         Cart cart =session.get(Cart.class,idCart);
                Query query = session.createQuery("select c from Cart c where c.id =:id", Cart.class)
                        .setParameter("id", idCart);
                Cart cart=(Cart) query.getSingleResult();
                book = cart.getBooks();
                session.getTransaction().commit();

            }
        }
        return book;
    }

    public Cart getCart(Long id) {
        Cart cart = null;
        if (getINSTANCE().existId(id)) {
            try (Session session = FACTORY.openSession()) {
                session.beginTransaction();
                Query<Cart> query = session.createQuery("select c from Cart c where c.id=:id", Cart.class)
                        .setParameter("id", id);
                cart = query.getSingleResult();
                session.getTransaction().commit();
                session.close();
            } catch (NullPointerException | IllegalArgumentException e) {
                System.err.println("This cart not exist");
            }
        }
        return cart;
    }


    public List<Cart> getAllActiveCarts() {
        List<Cart> carts = null;
        try (Session session = FACTORY.openSession()) {
            session.getTransaction();
            Query<Cart> query = session.createQuery("from Cart");
            carts = query.list();
        }
        return carts;
    }

    public Boolean existId(Long id) {
        Session session = FACTORY.openSession();
        Query query = session.
                createQuery("select c from Cart c where c.id = :id");
        query.setParameter("id", id);
        if (query.uniqueResult() == null) {
            System.out.println("This cart not exist");
        }
        return (query.uniqueResult() != null);
    }

    public static CartDao getINSTANCE() {
        return INSTANCE;
    }
}
