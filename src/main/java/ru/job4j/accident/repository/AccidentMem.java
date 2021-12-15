package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;
    private AtomicInteger id = new AtomicInteger(0);

    public AccidentMem() {
        accidents = new HashMap<>();
        Accident ac1 = new Accident(0, "ДТП", "Нарушение 1", "пр.Мира, 3");
        Accident ac2 = new Accident(0, "ДТП2", "Нарушение 2", "ул.К.Маркса, 32");
        Accident ac3 = new Accident(0, "ДТП3", "Нарушение 2", "пр.Иванова, 6");
        save(ac1);
        save(ac2);
        save(ac3);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }
}
