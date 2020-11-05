package by.teachmeskills.model;

import by.teachmeskills.dao.CategoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;


public class CategoryDaoTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

//    @Test
//    public void saveCategory() {
//        try (Session session = FACTORY.openSession()) {
//           Serializable id = CategoryDao.getINSTANCE().saveCategory("classic");
//            assertNotNull(id);
//            FACTORY.close();
//        }
//    }
//
//    @Test
//    public void getAllBook(){
//
//       List<Book> books= CategoryDao.getINSTANCE().getAllBookForCategory("dramas");
//
//        for (Book book:books) {
//            System.out.println(book.toString());
//        }
//    }
}
