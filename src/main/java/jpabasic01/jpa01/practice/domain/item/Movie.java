package jpabasic01.jpa01.practice.domain.item;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MOVIE")
@Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
