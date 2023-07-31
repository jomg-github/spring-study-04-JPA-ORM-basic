package jpabasic01.jpa01.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_ITEM")
@Getter @Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private Integer price;

    private Integer stockQuantity;
}
