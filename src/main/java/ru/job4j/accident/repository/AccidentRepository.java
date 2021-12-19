package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Override
    Collection<Accident> findAll();

}
