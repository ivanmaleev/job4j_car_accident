package ru.job4j.accident.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.*;
import java.util.*;

/*@Repository*/
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(
                        "insert into accident (name, text, address, type_id) "
                                + "values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, accident.getName());
                statement.setString(2, accident.getText());
                statement.setString(3, accident.getAddress());
                statement.setInt(4, accident.getType().getId());
                return statement;
            }
        }, holder);
        int id = (Integer) holder.getKeys().get("id");
        accident.setId(id);
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_accidentrule (accident_id, rules_id) "
                            + "values (?,?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    public List<Accident> findAll() {
        return (List<Accident>) jdbc.query("select ac.id, ac.name, ac.text, ac.address, "
                        + "at.id as at_id, at.name as at_name, r.id as r_id, r.name as r_name "
                        + "from accident ac "
                        + "left join accidenttype at on ac.type_id = at.id "
                        + "left join accident_accidentrule ar on ac.id = ar.accident_id "
                        + "join accidentrule r on ar.rules_id = r.id",
                new AccidentResultExtractor());
    }

    public Collection<AccidentType> getAccidentTypes() {
        return jdbc.query("select id, name from accidenttype",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select id, name from accidentrule",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    public Accident findById(int id) {
        ArrayList<Accident> arrayList = (ArrayList<Accident>) jdbc.query(
                "select ac.id, ac.name, ac.text, ac.address, "
                        + "at.id as at_id, at.name as at_name, r.id as r_id, r.name as r_name "
                        + "from accident ac "
                        + "left join accidenttype at on ac.type_id = at.id "
                        + "left join accident_accidentrule ar on ac.id = ar.accident_id "
                        + "join accidentrule r on ar.rules_id = r.id "
                        + "where ac.id = ?", new AccidentResultExtractor(), id);
        return arrayList.stream().findAny().orElse(null);
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select id, name "
                + "from accidentrule "
                + "where id = ?", new BeanPropertyRowMapper<>(Rule.class), id);
    }

    public class AccidentRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Accident accident = new BeanPropertyRowMapper<>(Accident.class)
                    .mapRow(resultSet, i);
            AccidentType accidentType = new AccidentType();
            accidentType.setId(resultSet.getInt("at_id"));
            accidentType.setName(resultSet.getString("at_name"));
            accident.setType(accidentType);
            return accident;
        }
    }

    public class AccidentResultExtractor implements ResultSetExtractor {
        @Override
        public ArrayList<Accident> extractData(ResultSet resultSet)
                throws SQLException, DataAccessException {

            Map<Integer, Accident> accidentMap = new HashMap<>();
            Map<Integer, AccidentType> accidentTypeMap = new HashMap<>();
            Map<Integer, Rule> ruleMap = new HashMap<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Accident accident = accidentMap.get(id);
                if (accident == null) {
                    accident = new Accident();
                    accident.setId(id);
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("text"));
                    accident.setAddress(resultSet.getString("address"));
                    accidentMap.put(id, accident);
                }

                int atId = resultSet.getInt("at_id");
                AccidentType accidentType = accidentTypeMap.get(atId);
                if (accidentType == null) {
                    accidentType = new AccidentType();
                    accidentType.setId(atId);
                    accidentType.setName(resultSet.getString("at_name"));
                    accidentTypeMap.put(atId, accidentType);
                }

                accident.setType(accidentType);
                int rId = resultSet.getInt("r_id");
                Rule rule = ruleMap.get(rId);
                if (rule == null) {
                    rule = new Rule();
                    rule.setId(rId);
                    rule.setName(resultSet.getString("r_name"));
                    ruleMap.put(rId, rule);
                }
                accident.addRule(rule);
            }
            return new ArrayList<>(accidentMap.values());
        }
    }
}