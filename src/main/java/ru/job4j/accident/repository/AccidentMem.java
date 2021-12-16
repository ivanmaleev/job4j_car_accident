package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;
    private AtomicInteger id = new AtomicInteger(0);
    private HashMap<Integer, AccidentType> accidentTypes;

    public AccidentMem() {
        accidentTypes = new HashMap<>();
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
        accidents = new HashMap<>();
        Accident ac1 = new Accident(0, "ДТП", "Нарушение 1",
                "пр.Мира, 3", accidentTypes.get(1));
        Accident ac2 = new Accident(0, "ДТП2", "Нарушение 2",
                "ул.К.Маркса, 32", accidentTypes.get(2));
        Accident ac3 = new Accident(0, "ДТП3", "Нарушение 2",
                "пр.Иванова, 6", accidentTypes.get(3));
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
        if (accident.getType().getName() == null) {
            accident.setType(findAccidentTypeById(accident.getType().getId()));
        }
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }
}
