package com.awbd.lab1b;

import com.awbd.lab1b.Subscription;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextLoadTest {

    @Test
    public void contructorDI(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextCS.xml");

        Subscription theSubscription = context.getBean("myBooksSubscription", Subscription.class);

        System.out.println(theSubscription.getPrice() + " " + theSubscription.getDescription());

        context.close();
    }


    @Test
    public void setterDI(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextCS.xml");

        Subscription theSubscription = context.getBean("myMoviesSubscription", Subscription.class);

        System.out.println(theSubscription.getPrice() + " " + theSubscription.getDescription());

        context.close();
    }


    @Test
    public void fieldDITest(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextCS.xml");
        Subscription theSubscription = context.getBean("mySportSubscription", Subscription.class);
        System.out.println(theSubscription.getPrice() + " "
                + theSubscription.getDescription());
        context.close();
    }


}
