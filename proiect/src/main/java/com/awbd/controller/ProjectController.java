package com.awbd.controller;

import com.awbd.model.Employee;
import com.awbd.model.Project;
import com.awbd.model.Team;
import com.awbd.service.ProjectService;
import com.awbd.service.TeamService;
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
public class ProjectController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ProjectService projectService;

    public String loadProjectPageData(ModelAndView model) {
        return this.findPaginated(1, "projName", "asc", model);
    }

    @RequestMapping("/projPage")
    public ModelAndView viewProjPage( ) {

        ModelAndView mv = new ModelAndView("proj_page");
        List<Project> projectList = projectService.getAllProjects();
        mv.addObject(projectList);
        loadProjectPageData(mv);
        return mv;
    }

    @GetMapping("/showNewProjForm")
    public String showNewProjectForm(Model model) {
        // create model attribute to bind form data

        Page<Project> page = projectService.findPaginated(1, 5,"projName", "asc");
        List<Project> listProjects = page.getContent();

        Project project= new Project();
        model.addAttribute("project", project);
        model.addAttribute("listProjects", listProjects);
        model.addAttribute("listTeamsFromProject", project.getTeams());
        return "new_proj";
    }


    @GetMapping("/pageP/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;



        Page<Project> page = projectService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Project> listProjects = page.getContent();

        List<Set<Team>> teamSets = new ArrayList<>();

        for(Project project: listProjects)
            teamSets.add(project.getTeams());


        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listProjects", listProjects);
        model.addObject("teamSets", teamSets);

        return "index";
    }

    @RequestMapping(value = "/addTeamToProject/{teamid}/{projectid}")
    public String addTeamToProject(@PathVariable (value = "teamid") long teamid, @PathVariable("projectid") long projectid){
        Team teamObj = teamService.getTeamById(teamid);
        Project project = projectService.getProjectById(projectid);
        projectService.addTeamToProject(project,teamObj);
        return "redirect:/showFormForUpdateProj/"+projectid;
    }

    @RequestMapping(value = "/removeTeamFromProject/{teamid}/{projectid}")
    public String removeTeamFromProject(@PathVariable (value = "teamid") long teamid, @PathVariable("projectid") long projectid){
        Team teamObj = teamService.getTeamById(teamid);
        Project project = projectService.getProjectById(projectid);
        projectService.removeTeamFromProject(project,teamObj);
        return "redirect:/showFormForUpdateProj/"+projectid;
    }

    @PostMapping("/saveProj")
    public String saveProj(@ModelAttribute("project") Project project) {
        // save project to database
        projectService.saveProject(project);
        return "redirect:/showFormForUpdateProj/"+project.getProjid();
    }

    @GetMapping("/showFormForUpdateProj/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get proj from the service
        Project project = projectService.getProjectById(id);

        Page<Team> page = teamService.findPaginated(1, 5,"teamName", "asc");
        List<Team> listTeams = new ArrayList<Team>(page.getContent());
        Set<Team> projTeams = project.getTeams();

        listTeams.removeIf(projTeams::contains);       //so the same team isn't added twice


        model.addAttribute("project", project);
        model.addAttribute("listTeams", listTeams);
        model.addAttribute("listTeamsFromProject", projTeams);
        return "update_proj";
    }

    @GetMapping("/deleteProj/{id}")
    public String deleteProj(@PathVariable (value = "id") long id) {

        // call delete team method
        this.projectService.deleteProjectById(id);
        return "redirect:/projPage";
    }
}
