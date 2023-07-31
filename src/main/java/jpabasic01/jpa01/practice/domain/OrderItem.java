package jpabasic01.jpa01.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_ORDER_ITEM")
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    private Integer orderPrice;

    private Integer count;
}
