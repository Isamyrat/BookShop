package by.teachmeskills.model;

import by.teachmeskills.dao.BookDao;
import by.teachmeskills.dao.PublisherDao;
import org.junit.Test;

import java.io.Serializable;

import static junit.framework.TestCase.assertNotNull;

public class BookDaoTest {
    @Test
    public void getBookId(){
//        System.out.println(BookDao.getINSTANCE().existId(3L));
       System.out.println(PublisherDao.getINSTANCE().existId((byte)4));

    }
//    public void saveBook(){
//        String name="Harry Porter and the Philosophers Stone";
//        String author="Joanne Kathleen Rowling";
//        String desc ="Harry Potter and the Philosopher's Stone is a fantasy novel written by" +
//                " British author J. K. Rowling. The first novel in the Harry Potter series and" +
//                " Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his" +
//                " magical heritage on his eleventh birthday, when he receives a letter of " +
//                "acceptance to Hogwarts School of Witchcraft and Wizardry.";
//        String nameP="mahao";
//        String nameC="dramas";
//
//       Serializable id= BookDao.getINSTANCE().saveBook(name,author,2.5,desc,1997,25,nameP,nameC);
//
//        String name="Harry Porter and the Chamber of Secrets";
//        String author="Joanne Kathleen Rowling";
//        String desc ="Harry Potter and the Chamber of Secrets is a fantasy novel written by British author J. K. " +
//                "Rowling and the second novel in the Harry Potter series. The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, " +
//                "during which a series of messages on the walls of the school's corridors warn that the has been opened and that the"+
//         "would kill all pupils who do not come from all-magical families." ;
//        String nameP="mahao";
//        String nameC="dramas";
//
//        Serializable id= BookDao.getINSTANCE().saveBook(name,author,3.8,desc,1998,12,nameP,nameC);
//        assertNotNull(id);

//        }
    }

