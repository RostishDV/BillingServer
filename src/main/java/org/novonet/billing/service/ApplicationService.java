package org.novonet.billing.service;

import org.novonet.billing.models.Application;
import org.novonet.billing.repo.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService extends
        AbstractService<Application, ApplicationRepository>{

    public ApplicationService(ApplicationRepository repository) {
        super(repository);
    }

    public Application save(Application application){
        return repository.save(application);
    }
}
