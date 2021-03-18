package com.awbd.lab5;


import com.awbd.lab5.domain.Currency;
import com.awbd.lab5.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findProduct() {

        Product productFound = entityManager.find(Product.class, 1L);

        assertEquals(productFound.getCode(), "PCEZ");
    }

    @Test
    public void updateProduct() {

        Product productFound = entityManager.find(Product.class, 1L);
        productFound.setCurrency(Currency.EUR);

        entityManager.persist(productFound);
        entityManager.flush();

    }

    @Test
    public void findCurrency() {

        Product productFound = entityManager.find(Product.class, 1L);
        assertEquals(productFound.getCurrency(),Currency.EUR);

    }



}

