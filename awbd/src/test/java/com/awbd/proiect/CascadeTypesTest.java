/*package com.awbd.proiect;

import com.awbd.proiect.domain.Currency;
import com.awbd.proiect.domain.Participant;
import com.awbd.proiect.domain.Product;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class CascadeTypesTest {

    @Autowired
    private EntityManager entityManager;


    @Ignore
    @Test
    public void saveParticipant() {

        Participant participant = new Participant();
        participant.setFirstName("Will");
        participant.setLastName("Snow");

        Product product = new Product();
        product.setName("Impression, Sunrise");
        product.setReservePrice(300D);
        product.setCode("PMON");
        product.setSeller(participant);

        participant.setProducts(Arrays.asList(product));

        entityManager.persist(participant);
        entityManager.flush();
        entityManager.clear();

    }


    @Test
    public void updateParticipant() {
        Product product = entityManager.find(Product.class, 4L);
        Participant participant = product.getSeller();
        participant.setFirstName("William");
        participant.getProducts().forEach(prod -> {
            prod.setCurrency(Currency.USD);
        });
        entityManager.merge(participant);
        entityManager.flush();
    }


    @Test
    public void orphanRemoval() {
        Product product = entityManager.find(Product.class, 1L);
        product.setInfo(null);
        entityManager.persist(product);
        entityManager.flush();


    }


    @ParameterizedTest
    @ValueSource(longs = {2, 3})
    public void orphanRemoval(long id){
        Product product = entityManager.find(Product.class, id);
        product.setInfo(null);
        entityManager.persist(product);
        entityManager.flush();
    }

}*/