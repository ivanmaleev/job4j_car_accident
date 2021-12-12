package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService {
    public HashMap<Integer, Accident> findAllAccidents() {
        AccidentMem mem = new AccidentMem();
        return mem.getAccidents();
    }
}
