package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;

    public AccidentMem() {
        accidents = new HashMap<>();
        Accident ac1 = new Accident(1, "ДТП", "Нарушение 1", "пр.Мира, 3");
        Accident ac2 = new Accident(2, "ДТП2", "Нарушение 2", "ул.К.Маркса, 32");
        Accident ac3 = new Accident(3, "ДТП3", "Нарушение 2", "пр.Иванова, 6");
        accidents.put(ac1.getId(), ac1);
        accidents.put(ac2.getId(), ac2);
        accidents.put(ac3.getId(), ac3);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }
}
