package com.sparta.springjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Entity 가 붙은 클래스는 JPA가 관리하는것.
@Entity
//값을 가져올때 써야하기 때문에 @Getter를 추가
@Getter
//기본 생성자 만들어주는 어노테이션
@NoArgsConstructor
//order는 예약어
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //다대일 구조(ManyToOne)
    @ManyToOne
    //join
    @JoinColumn(name = "food_id")
    private Food food;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Orders(Food food, Member member) {
        this.food = food;
        this.member = member;
    }
}
