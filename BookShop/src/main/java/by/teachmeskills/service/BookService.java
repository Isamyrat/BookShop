package by.teachmeskills.service;



public final class BookService {
    private static final BookService INSTANCE=new BookService();




    public static BookService getINSTANCE() {
        return INSTANCE;
    }
}
