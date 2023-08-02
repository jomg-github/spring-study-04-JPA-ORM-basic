package jpabasic01.jpa01.practice.domain.item;

import jpabasic01.jpa01.practice.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_ITEM")
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item extends BaseTimeEntity {
    @Id @GeneratedValue//(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private Integer price;

//    private Integer stockQuantity;
//
//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();
}
