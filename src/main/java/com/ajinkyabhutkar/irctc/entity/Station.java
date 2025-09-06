package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "irctc_stations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;
    private String name;
    private String city;
    private String state;
    private LocalDateTime createtime=LocalDateTime.now();


}
