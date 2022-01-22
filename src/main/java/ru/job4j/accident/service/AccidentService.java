package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentService {

    public Collection<Accident> findAllAccidents();

    public Collection<AccidentType> getAccidentTypes();

    public Collection<Rule> getRules();

    public Accident findById(int id);

    public void save(Accident accident, String[] ids);
}
