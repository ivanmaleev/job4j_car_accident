package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {
    @Override
    Collection<Rule> findAll();
}
