package jpabasic01.jpa01.practice.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberDTO {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
