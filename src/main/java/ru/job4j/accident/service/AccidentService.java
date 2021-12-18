package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentHibernate mem;

    public AccidentService(AccidentHibernate mem) {
        this.mem = mem;
    }

    public Collection<Accident> findAllAccidents() {
        return mem.getAccidents();
    }

    public Collection<AccidentType> getAccidentTypes() {
        return mem.getAccidentTypes();
    }

    public Collection<Rule> getRules() {
        return mem.getRules();
    }

    public Accident findById(int id) {
        return mem.findById(id);
    }

    public void save(Accident accident, String[] ids) {
        for (String id : ids) {
            accident.addRule(mem.findRuleById(Integer.parseInt(id)));
        }
        mem.save(accident);
    }
}
