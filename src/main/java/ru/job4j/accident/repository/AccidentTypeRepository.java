package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
    @Override
    Collection<AccidentType> findAll();
}
