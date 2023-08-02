package jpabasic01.jpa01.practice.domain.item;


import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Table(name = "TB_ALBUM")
public class Album extends Item {
    private String artist;
}
