package org.novonet.billing.service;

import org.novonet.billing.models.Service;
import org.novonet.billing.repo.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService extends AbstractService<Service, ServiceRepository> {
    public ServiceService(ServiceRepository repository) {
        super(repository);
    }

    public Service save(Service service) throws EntryWithSameNameAlreadyExist {
        if (repository.findByName(service.getName()).isPresent()){
            throw new EntryWithSameNameAlreadyExist(service.getName());
        }
        return repository.save(service);
    }
}
