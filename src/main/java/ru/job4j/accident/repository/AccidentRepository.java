package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Override
    Collection<Accident> findAll();

//    @Query("from Accident ac "
//            + "join fetch ac.type "
//            + "join fetch ac.rules "
//            + "where ac.id = : id")
//    Optional<Accident> findById(@Param("id") int id);
}
