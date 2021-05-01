package com.awbd.controller;

import com.awbd.model.Employee;
import com.awbd.model.Team;
import com.awbd.service.EmployeeService;
import com.awbd.service.TeamService;
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
public class TeamController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TeamService teamService;

    public String loadTeamPageData(ModelAndView model) {
        return this.findPaginated(1, "teamName", "asc", model);
    }

    @RequestMapping("/teamPage")
    public ModelAndView viewTeamssPage( ) {

        ModelAndView mv = new ModelAndView("team_page");
        List<Team> teamList = teamService.getAllTeams();
        mv.addObject(teamList);
        loadTeamPageData(mv);
        return mv;
    }


    @GetMapping("/showNewTeamForm")
    public String showNewTeamForm(Model model) {
        // create model attribute to bind form data

        Page<Employee> page = employeeService.findPaginated(1, 5,"firstName", "asc");
        List<Employee> listEmployees = page.getContent();

        Team team= new Team();
        model.addAttribute("team", team);
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listEmployeesFromTeam", team.getEmployeeList());
        return "new_team";
    }

    @GetMapping("/pageT/{pageNo}")
    public String findPaginated(@Valid @PathVariable (value = "pageNo") int pageNo,
                                @Valid @RequestParam("sortField") String sortField,
                                @Valid @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;



        Page<Team> page = teamService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Team> listTeams = page.getContent();

        List<Set<Employee>> employeeSets = new ArrayList<>();

        for(Team team : listTeams)
            employeeSets.add(team.getEmployeeList());


        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listTeams", listTeams);
        model.addObject("employeeSets", employeeSets);
        return "index";
    }



    @RequestMapping(value = "/addEmployeeToTeam/{id}/{team}")
    public String addEmployeeToTeam(@Valid @PathVariable (value = "id") long id, @Valid @PathVariable("team") long team){
        Team teamObj = teamService.getTeamById(team);
        Employee employee = employeeService.getEmployeeById(id);
        teamService.addEmployeeToTeam(teamObj,employee);
        return "redirect:/showFormForUpdateTeam/"+team;
    }

    @RequestMapping(value = "/removeEmployeeFromTeam/{id}/{team}")
    public String removeEmployeeFromTeam(@Valid @PathVariable (value = "id") long id, @Valid @PathVariable("team") long team){
        Team teamObj = teamService.getTeamById(team);
        Employee employee = employeeService.getEmployeeById(id);
        teamService.removeEmployeeFromTeam(teamObj,employee);
        return "redirect:/showFormForUpdateTeam/"+team;
    }

    @PostMapping("/saveTeam")
    public String saveTeam(@Valid @ModelAttribute("team") Team team) {
        // save employee to database
        teamService.saveTeam(team);
        return "redirect:/showFormForUpdateTeam/"+team.getId();
    }

    @GetMapping("/showFormForUpdateTeam/{id}")
    public String showFormForUpdate(@Valid @PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        Team team = teamService.getTeamById(id);

        Page<Employee> page = employeeService.findPaginated(1, 5,"firstName", "asc");
        List<Employee> listEmployees = new ArrayList<Employee>(page.getContent());
        Set<Employee> teamEmployees = team.getEmployeeList();

        listEmployees.removeIf(teamEmployees::contains);       //so the same employee isn't added twice

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("team", team);
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listEmployeesFromTeam", teamEmployees);
        return "update_team";
    }

    @GetMapping("/deleteTeam/{id}")
    public String deleteTeam(@Valid @PathVariable (value = "id") long id) {

        // call delete team method
        this.teamService.deleteTeamById(id);
        return "redirect:/teamPage";
    }
}
