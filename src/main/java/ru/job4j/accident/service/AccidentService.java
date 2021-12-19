package ru.job4j.accident.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentRepository mem;
    private AccidentTypeRepository typeMem;
    private RuleRepository ruleMem;

    public AccidentService(AccidentRepository mem, AccidentTypeRepository typeMem,
                           RuleRepository ruleMem) {
        this.mem = mem;
        this.typeMem = typeMem;
        this.ruleMem = ruleMem;
    }

    public Collection<Accident> findAllAccidents() {
        return mem.findAll();
    }

    public Collection<AccidentType> getAccidentTypes() {
        return typeMem.findAll();
    }

    public Collection<Rule> getRules() {
        return ruleMem.findAll();
    }

    public Accident findById(int id) {
        Accident accident = mem.findById(id).orElse(null);
        return accident;
    }

    public void save(Accident accident, String[] ids) {
        for (String id : ids) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(id));
            accident.addRule(rule);
        }
        mem.save(accident);
    }
}
