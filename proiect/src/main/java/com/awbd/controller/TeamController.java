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

import java.util.List;

@Controller
public class TeamController {


    public String loadTeamPageData(ModelAndView model) {
        return this.findPaginated(1, "teamName", "asc", model);
    }

    @RequestMapping("/teamPage")
    public ModelAndView viewEmployeesPage( ) {

        ModelAndView mv = new ModelAndView("team_page");
        List<Team> teamList = teamService.getAllTeams();
        mv.addObject(teamList);
        loadTeamPageData(mv);
        return mv;
    }

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TeamService teamService;


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
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;



        Page<Team> page = teamService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Team> listTeams = page.getContent();

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listTeams", listTeams);
        return "index";
    }

   /* @PostMapping("/addEmployeeToTeam/{id,team}")
    public String addEmployeeToTeam(@PathVariable(value = "id") long id, @ModelAttribute("team") Team team) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        teamService.addEmployeeToTeam(team,employee);
        return "redirect:/teamPage";
    }*/

    @PostMapping("/saveTeam")
    public String saveTeam(@ModelAttribute("team") Team team) {
        // save employee to database
        teamService.saveTeam(team);
        return "redirect:/teamPage";
    }
}
