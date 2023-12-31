package jpabasic01.jpa01.practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
