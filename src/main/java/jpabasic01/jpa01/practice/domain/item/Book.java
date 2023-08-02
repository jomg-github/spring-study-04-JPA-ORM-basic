package jpabasic01.jpa01.practice.domain.item;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_BOOK")
public class Book extends Item {
    private String author;
    private String isbn;
}
