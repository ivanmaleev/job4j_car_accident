package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private AtomicInteger id = new AtomicInteger(0);
    private Map<Integer, Accident> accidents;
    private Map<Integer, AccidentType> accidentTypes;
    private Map<Integer, Rule> rules;

    public AccidentMem() {
        rules = new HashMap<>();
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
        accidentTypes = new HashMap<>();
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
        accidents = new HashMap<>();
        Accident ac1 = new Accident(0, "ДТП", "Нарушение 1",
                "пр.Мира, 3", accidentTypes.get(1));
        ac1.addRule(findRuleById(1));
        ac1.addRule(findRuleById(2));
        Accident ac2 = new Accident(0, "ДТП2", "Нарушение 2",
                "ул.К.Маркса, 32", accidentTypes.get(2));
        ac2.addRule(findRuleById(3));
        ac2.addRule(findRuleById(2));
        Accident ac3 = new Accident(0, "ДТП3", "Нарушение 2",
                "пр.Иванова, 6", accidentTypes.get(3));
        ac3.addRule(findRuleById(3));
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

    public Collection<AccidentType> getAccidentTypes() {
        return accidentTypes.values();
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
