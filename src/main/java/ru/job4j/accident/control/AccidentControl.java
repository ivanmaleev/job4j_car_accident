package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAccidentTypes());
        model.addAttribute("rules", accidents.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for (String id : ids) {
            accident.addRule(accidents.findRuleById(Integer.parseInt(id)));
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Accident accident = accidents.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidents.getAccidentTypes());
        model.addAttribute("typeid", accident.getType().getId());
        return "accident/update";
    }
}