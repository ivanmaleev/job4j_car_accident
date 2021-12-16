package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(accidents.findAccidentTypeById(1));
        types.add(accidents.findAccidentTypeById(2));
        types.add(accidents.findAccidentTypeById(3));
        model.addAttribute("types", types);
        List<Rule> rules = new ArrayList<>();
        rules.add(accidents.findRuleById(1));
        rules.add(accidents.findRuleById(2));
        rules.add(accidents.findRuleById(3));
        model.addAttribute("rules", rules);
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
        model.addAttribute("accident", accidents.findById(id));
        return "accident/update";
    }
}