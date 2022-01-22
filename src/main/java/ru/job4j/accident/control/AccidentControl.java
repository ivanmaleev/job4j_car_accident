package ru.job4j.accident.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class AccidentControl {

    private final AccidentService accidentService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAccidentTypes());
        model.addAttribute("rules", accidentService.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidentService.save(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentService.getAccidentTypes());
        model.addAttribute("typeid", accident.getType().getId());
        model.addAttribute("rules", accidentService.getRules());
        model.addAttribute("accidentrules", new ArrayList<>(accident.getRules()));
        return "accident/update";
    }
}