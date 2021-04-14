package com.awbd.controller;

import com.awbd.model.Info;
import com.awbd.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class InfoController {

    @Autowired
    private InfoService infoService;

    public String loadInfosPageData(ModelAndView model) {
        return findPaginated(1, "infoid", "asc", model);
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
        model.addAttribute("info", info);
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

        // set info as a model attribute to pre-populate the form
        model.addAttribute("info", info);
        return "update_info";
    }

    @GetMapping("/deleteInfo/{id}")
    public String deleteInfo(@PathVariable (value = "id") long id) {

        // call delete info method
        this.infoService.deleteInfoById(id);
        return "redirect:/infoPage";
    }


    @GetMapping("/pageI/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;

        Page<Info> page = infoService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Info> listInfos = page.getContent();

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listInfos", listInfos);

        return "index";
    }
}
