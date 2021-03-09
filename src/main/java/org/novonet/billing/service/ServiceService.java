package org.novonet.billing.service;

import org.novonet.billing.models.Service;
import org.novonet.billing.repo.ServiceRepository;

public class ServiceService extends AbstractService<Service, ServiceRepository> {
    public ServiceService(ServiceRepository repository) {
        super(repository);
    }
}
