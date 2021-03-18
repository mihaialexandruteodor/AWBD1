package com.awbd.lab1b;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextLoadTestAnnotation {

    @Test
    public void contructorDI(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SubscriptionConfig.class);


        Subscription theSubscription = context.getBean("myBooksSubscription", Subscription.class);

        System.out.println(theSubscription.getPrice() + " " + theSubscription.getDescription());

        context.close();
    }


    @Test
    public void setterDI(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SubscriptionConfig.class);

        Subscription theSubscription = context.getBean("myMoviesSubscription", Subscription.class);

        System.out.println(theSubscription.getPrice() + " " + theSubscription.getDescription());

        context.close();
    }


    @Test
    public void fieldDITest(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.awbd.lab1b");
        Subscription theSubscription = context.getBean("mySportSubscription", Subscription.class);
        System.out.println(theSubscription.getPrice() + " "
                + theSubscription.getDescription());
        context.close();
    }


}
