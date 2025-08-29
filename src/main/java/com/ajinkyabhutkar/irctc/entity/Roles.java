package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "irctc_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
