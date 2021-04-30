package com.awbd.service;

import com.awbd.model.Organisation;
import com.awbd.model.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganisationService {
    List<Organisation> getAllOrganisations();
    void saveOrganisation(Organisation organisation);
    Organisation getOrganisationById(long id);
    void deleteOrganisationById(long id);
    Page<Organisation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    void addClient(Organisation organisation, Client client);
    void RemoveClient(Organisation organisation, Client client);
}
