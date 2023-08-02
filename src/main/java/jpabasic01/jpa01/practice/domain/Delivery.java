package jpabasic01.jpa01.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_DELIVERY")
@Getter @Setter
public class Delivery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;

    private String street;

    private String zipcode;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
