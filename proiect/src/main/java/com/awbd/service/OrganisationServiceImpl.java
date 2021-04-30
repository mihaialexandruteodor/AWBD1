package com.awbd.service;

import com.awbd.model.Client;
import com.awbd.model.Organisation;
import com.awbd.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    @Override
    public void saveOrganisation(Organisation organisation) {
        organisationRepository.save(organisation);
    }

    @Override
    public Organisation getOrganisationById(long id) {
        Optional<Organisation> optional = organisationRepository.findById(id);
        Organisation organisation = null;
        if (optional.isPresent()) {
            organisation = optional.get();
        } else {
            throw new RuntimeException(" Organisation not found for id :: " + id);
        }
        return organisation;
    }

    @Override
    public void deleteOrganisationById(long id) {
        organisationRepository.deleteById(id);
    }

    @Override
    public Page<Organisation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return organisationRepository.findAll(pageable);
    }

    @Override
    public void addClient(Organisation organisation, Client client) {
        organisation.getClientList().add(client);
        organisationRepository.save(organisation);
    }

    @Override
    public void RemoveClient(Organisation organisation, Client client) {
        organisation.getClientList().remove(client);
        organisationRepository.save(organisation);
    }


}
