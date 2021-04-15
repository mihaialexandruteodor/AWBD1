package com.awbd.controller;

import com.awbd.model.Client;
import com.awbd.model.Info;
import com.awbd.model.Project;
import com.awbd.service.InfoService;
import com.awbd.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private ProjectService projectService;

    public String loadInfosPageData(ModelAndView model) {
        return findPaginated(1, "description",  "asc", model);
    }

    @RequestMapping("/infoPage")
    public ModelAndView viewInfosPage( ) {

        ModelAndView mv = new ModelAndView("info_page");
        List<Info> infoList = infoService.getAllInfos();
        mv.addObject(infoList);
        loadInfosPageData(mv);
        return mv;
    }

    @GetMapping("/showNewInfoForm")
    public String showNewInfoForm(Model model) {
        // create model attribute to bind form data
        Info info = new Info();

        Page<Project> page2 = projectService.findPaginated(1, 5,"projName", "asc");
        List<Project> listProjects = new ArrayList<Project>(page2.getContent());

        if(info.getInfoProject()!=null)
            listProjects.remove(info.getInfoProject());

        model.addAttribute("info", info);
        model.addAttribute("listProjects", listProjects);
        return "new_info";
    }

    @PostMapping("/saveInfo")
    public String saveInfo(@ModelAttribute("info") Info info) {
        // save info to database
        infoService.saveInfo(info);
        return "redirect:/infoPage";
    }

    @GetMapping("/showFormForUpdateInfo/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get info from the service
        Info info = infoService.getInfoById(id);

        Page<Project> page2 = projectService.findPaginated(1, 5,"projName", "asc");
        List<Project> listProjects = new ArrayList<Project>(page2.getContent());

        if(info.getInfoProject()!=null)
            listProjects.remove(info.getInfoProject());

        // set info as a model attribute to pre-populate the form
        model.addAttribute("info", info);
        model.addAttribute("listProjects", listProjects);
        return "update_info";
    }

    @GetMapping("/deleteInfo/{id}")
    public String deleteInfo(@PathVariable (value = "id") long id) {

        // call delete info method
        this.infoService.deleteInfoById(id);
        return "redirect:/infoPage";
    }


    @GetMapping("/pageI/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                                            @RequestParam("sortField") String sortField,
                                                            @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;

        Page<Info> page = infoService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Info> listInfos = page.getContent();

        Page<Project> page2 = projectService.findPaginated(1, 5,"projName", "asc");
        List<Project> listProjects = new ArrayList<Project>(page2.getContent());

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listInfos", listInfos);
        model.addObject("listProjects", listProjects);

        return "index";
    }

    @RequestMapping("/changeProjectInfo/{projid}/{infoid}")
    public String changeProject(@PathVariable (value = "projid") long projid, @PathVariable("infoid") long infoid){
        Project project = projectService.getProjectById(projid);
        Info info = infoService.getInfoById(infoid);
        infoService.ChangeProjectInfo(info,project);
        return "redirect:/showFormForUpdateInfo/"+infoid;
    }

    @RequestMapping("/removeProjectInfo/{infoid}")
    public String removeProject(@PathVariable("infoid") long infoid){
        Info info = infoService.getInfoById(infoid);
        infoService.RemoveProjectInfo(info);
        return "redirect:/showFormForUpdateInfo/"+infoid;
    }
}
