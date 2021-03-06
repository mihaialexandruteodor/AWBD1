package com.awbd.controller;

import com.awbd.model.*;
import com.awbd.model.Client;
import com.awbd.service.ClientService;
import com.awbd.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    public String loadOrganisationsPageData(ModelAndView model) {
        return findPaginated(1, "organisationName", "asc", model);
    }

    @RequestMapping("/organisationPage")
    public ModelAndView viewOrganisationPage( ) {

        ModelAndView mv = new ModelAndView("organisation_page");
        List<Organisation> organisationList = organisationService.getAllOrganisations();
        mv.addObject(organisationList);
        loadOrganisationsPageData(mv);
        return mv;
    }

    @GetMapping("/showNewOrganisationForm")
    public String showNewOrganisationForm(Model model) {
        // create model attribute to bind form data
        Organisation organisation = new Organisation();
        model.addAttribute("organisation", organisation);
        return "new_organisation";
    }

    @PostMapping("/saveOrganisation")
    public String saveOrganisation(@Valid @ModelAttribute("organisation") Organisation organisation) {
        // save organisation to database
        organisationService.saveOrganisation(organisation);
        return "redirect:/organisationPage";
    }

    @GetMapping("/deleteOrganisation/{id}")
    public String deleteOrganisation(@Valid @PathVariable (value = "id") long id) {

        organisationService.deleteOrganisationById(id);
        return "redirect:/organisationPage";
    }

    @GetMapping("/showFormForUpdateOrganisation/{id}")
    public String showFormForUpdate(@Valid @PathVariable( value = "id") long id, Model model) {

        // get organisation from the service
        Organisation organisation = organisationService.getOrganisationById(id);

        Page<Client> page = clientService.findPaginated(1, 5,"firstName", "asc");
        List<Client> listClients = new ArrayList<Client>(page.getContent());
        Set<Client> organisationClients = organisation.getClientList();

        listClients.removeIf(organisationClients::contains);       //so the same client isn't added twice

        model.addAttribute("organisation", organisation);
        model.addAttribute("listClients", listClients);
        model.addAttribute("listClientsFromOrganisation", organisationClients);
        return "update_organisation";
    }

    @GetMapping("/pageO/{pageNo}")
    public String findPaginated(@Valid @PathVariable (value = "pageNo") int pageNo,
                                @Valid @RequestParam("sortField") String sortField,
                                @Valid @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;



        Page<Organisation> page = organisationService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Organisation> listOrganisations = page.getContent();

        List<Set<Client>> clientSets = new ArrayList<>();

        for(Organisation team : listOrganisations)
            clientSets.add(team.getClientList());


        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listOrganisations", listOrganisations);
        model.addObject("clientSets", clientSets);
        return "index";
    }

    @RequestMapping(value = "/addClientToOrganisation/{id}/{organisation}")
    public String addEmployeeToTeam(@Valid @PathVariable (value = "id") long id, @Valid @PathVariable("organisation") long organisation){
        Client clientObj = clientService.getClientById(id);
        Organisation organisationObj = organisationService.getOrganisationById(organisation);
        organisationService.addClient(organisationObj, clientObj);
        return "redirect:/showFormForUpdateOrganisation/"+organisation;
    }

    @RequestMapping(value = "/removeClientFromOrganisation/{id}/{organisation}")
    public String removeClientFromOrganisation(@Valid @PathVariable (value = "id") long id, @Valid @PathVariable("organisation") long organisation){
        Client clientObj = clientService.getClientById(id);
        Organisation organisationObj = organisationService.getOrganisationById(organisation);
        organisationService.removeClient(organisationObj,clientObj);
        return "redirect:/showFormForUpdateOrganisation/"+organisation;
    }
}
