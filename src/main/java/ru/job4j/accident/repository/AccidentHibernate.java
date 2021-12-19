package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/*@Repository*/
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T query(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            createAccident(accident);
        } else {
            updateAccident(accident);
        }
        return accident;
    }

    private Accident updateAccident(Accident accident) {
        return query(session -> {
            session.update(accident);
            return accident;
        });
    }

    private Accident createAccident(Accident accident) {
        return query(session -> {
            session.save(accident);
            return accident;
        });
    }

    public List<Accident> findAll() {
        return query(session -> session.createQuery(
                "from Accident ac "
                        + " fetch all properties")
                .list());
    }

    public Collection<AccidentType> getAccidentTypes() {
        return query(session -> session.createQuery(
                "from AccidentType")
                .list());
    }

    public Collection<Rule> getRules() {
        return query(session -> session.createQuery(
                "from Rule")
                .list());
    }

    public Accident findById(int id) {
        return query(session -> {
            Query<Accident> query = session.createQuery(
                    "from Accident ac "
                            + "fetch all properties "
                            + "where ac.id = : id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }

    public Rule findRuleById(int id) {
        return query(session -> {
            Query<Rule> query = session.createQuery(
                    "from Rule where id = : id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }
}