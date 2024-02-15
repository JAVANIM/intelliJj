package com.boot.thymeleaf_20214.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Board {
//    private long id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
//    @Size(min=2, max=30)
    @Size(min=2, max=30,message = "제목은 2자이상 30자 이하 입니다.")
    private String title;
    private String content;
}
