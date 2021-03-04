package org.novonet.billing.repo;

import org.junit.jupiter.api.Test;
import org.novonet.billing.BillingApplication;
import org.novonet.billing.models.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApplicationRepositoryTest {
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Test
    public void saveNewApplicationTest(){
        Application application = new Application();
        application.setSubscriberId(1);
        application.setStatus("new");
        application.setTitle("installation");
        application.setDescription("install hardware");

        applicationRepository.save(application);
        long actualId = application.getId();
        long expectedId = 1;
        assertEquals(expectedId, actualId);
    }
}
