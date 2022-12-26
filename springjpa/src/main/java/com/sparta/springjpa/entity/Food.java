package com.sparta.springjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Entity 가 붙은 클래스는 JPA가 관리하는것.
@Entity
//값을 가져올때 써야하기 때문에 @Getter를 추가
@Getter
//기본 생성자 만들어주는 어노테이션
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)//null값을 허용하지 않음.
    private String foodName;
    @Column(nullable = false)//null값을 허용하지 않음.
    private int price;
    //음식 하나에 오더가 여러개(OneToMany)
    //연관관계를 만들기 위해 mappedBy = "food"
    @OneToMany(mappedBy = "food", fetch = FetchType.EAGER)
    //여러개의 오더를 받기위해 리스트 형식으로 받고 초기화
    private List<Orders> orders = new ArrayList<>();

    public Food(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }
}
