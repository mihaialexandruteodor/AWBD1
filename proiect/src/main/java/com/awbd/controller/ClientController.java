package com.awbd.controller;

import com.awbd.model.Client;
import com.awbd.model.Employee;
import com.awbd.model.Project;
import com.awbd.service.ClientService;
import com.awbd.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProjectService projectService;

    public String loadClientsPageData(ModelAndView model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @RequestMapping("/clientPage")
    public ModelAndView viewClientsPage( ) {

        ModelAndView mv = new ModelAndView("client_page");
        List<Client> clientList = clientService.getAllClients();
        mv.addObject(clientList);
        loadClientsPageData(mv);
        return mv;
    }

    @GetMapping("/showNewClientForm")
    public String showNewClientForm(Model model) {
        // create model attribute to bind form data
        Client client = new Client();
        model.addAttribute("client", client);
        return "new_client";
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client) {
        // save client to database
        clientService.saveClient(client);
        return "redirect:/clientPage";
    }

    @GetMapping("/showFormForUpdateClient/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get client from the service
        Client client = clientService.getClientById(id);



        Page<Project> page2 = projectService.findPaginated(1, 5,"projName", "asc");
        List<Project> listProjects = new ArrayList<Project>(page2.getContent());
        Project clientProj = client.getClientProject();

        if(clientProj != null)
            listProjects.remove(clientProj);

        // set client as a model attribute to pre-populate the form
        model.addAttribute("client", client);
        model.addAttribute("listProjects", listProjects);

        return "update_client";
    }

    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable (value = "id") long id) {

        // call delete client method 
        this.clientService.deleteClientById(id);
        return "redirect:/clientPage";
    }


    @GetMapping("/pageC/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;

        Page<Client> page = clientService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Client> listClients = page.getContent();


        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listClients", listClients);

        return "index";
    }

    @RequestMapping("/changeProject/{projid}/{clientid}")
    public String changeProject(@PathVariable (value = "projid") long projid, @PathVariable("clientid") long clientid){
        Project project = projectService.getProjectById(projid);
        Client client = clientService.getClientById(clientid);
        clientService.ChangeProject(client,project);
        return "redirect:/showFormForUpdateClient/"+clientid;
    }

    @RequestMapping("/removeProject/{clientid}")
    public String removeProject(@PathVariable("clientid") long clientid){
        Client client = clientService.getClientById(clientid);
        clientService.RemoveProject(client);
        return "redirect:/showFormForUpdateClient/"+clientid;
    }
}
