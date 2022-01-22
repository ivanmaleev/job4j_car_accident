package ru.job4j.accident.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.service.AccidentService;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private AccidentRepository mem;
    private AccidentTypeRepository typeMem;
    private RuleRepository ruleMem;

    @Override
    public Collection<Accident> findAllAccidents() {
        return mem.findAll();
    }

    @Override
    public Collection<AccidentType> getAccidentTypes() {
        return typeMem.findAll();
    }

    @Override
    public Collection<Rule> getRules() {
        return ruleMem.findAll();
    }

    @Override
    public Accident findById(int id) {
        Accident accident = mem.findById(id).orElse(null);
        return accident;
    }

    @Override
    public void save(Accident accident, String[] ids) {
        for (String id : ids) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(id));
            accident.addRule(rule);
        }
        mem.save(accident);
    }
}
