package org.daniel.quickstart;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Developer extends PanacheEntity {

    @Column(unique = true)
    public String name;

    @Column
    public Integer age;

}
