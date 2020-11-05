package by.teachmeskills.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdersService {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static boolean getNumber(String phone){
        Pattern pattern = Pattern.compile("(\\+375)(?<code>25|29|33|44)(\\d{3})(\\d{2})(\\d{2})");
        Matcher matcher = pattern.matcher(phone);
        boolean result = matcher.matches();
        if(result== false){
            System.out.println("wrong phone number entered");
        }
       return result;
    }
}
