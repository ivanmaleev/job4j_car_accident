package ru.job4j.accident.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accidentrule")
@Data
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }
}