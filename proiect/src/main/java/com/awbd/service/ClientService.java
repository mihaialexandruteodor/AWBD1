package com.awbd.service;

import com.awbd.model.Client;
import com.awbd.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    void saveClient(Client client);
    Client getClientById(long id);
    void deleteClientById(long id);
    Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    void ChangeProject(Client client, Project project);
    void RemoveProject(Client client);
}
