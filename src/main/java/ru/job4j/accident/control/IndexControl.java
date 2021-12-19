package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    private final AccidentService accidentService;

    public IndexControl(AccidentService accidents) {
        this.accidentService = accidents;
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        List<Accident> res = new ArrayList<>();
        accidentService.findAllAccidents().forEach(res::add);
        model.addAttribute("accidents", res);
        return "index";
    }
}