package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accident")
@Data
@NoArgsConstructor
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;
    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accident_accidentrule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rules_id"))
    private Set<Rule> rules = new HashSet<>();

    public Accident(int id, String name, String text, String address,
                    AccidentType accidentType) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = accidentType;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
}