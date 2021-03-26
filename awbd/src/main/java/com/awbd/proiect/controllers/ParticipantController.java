package com.awbd.lab7.controllers;

import com.awbd.lab7.domain.Participant;
import com.awbd.lab7.exceptions.ResourceNotFoundException;
import com.awbd.lab7.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParticipantController {
    @Autowired
    ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/participant/info/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("participant",
                participantService.findById(Long.valueOf(id)));
        return "infoparticipant";
    }

    @RequestMapping("/participant/new")
    public String newFilm(Model model) {
        model.addAttribute("participant", new Participant());
        return "participantform";
    }

    @PostMapping("/participant")
    public String saveOrUpdate(@ModelAttribute Participant participant){
        Participant savedParticipant = participantService.save(participant);
        return "redirect:/participant/info/" + savedParticipant.getId();
    }

    /* GlobalExceptionHandler.class
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handlerNotFoundException(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("exception",exception);
        modelAndView.setViewName("notfound");
        return modelAndView;
    }
    */
}
