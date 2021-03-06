package ru.job4j.accident.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class IndexControl {

    private final AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        List<Accident> res = new ArrayList<>();
        accidentService.findAllAccidents().forEach(res::add);
        model.addAttribute("accidents", res);
        model.addAttribute("username", principal.getName());
        return "index";
    }
}