package com.ajinkyabhutkar.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "irctc_users")
//@Table(name = "irctc_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    private String phone;

    @Column(name = "createtime")
    private LocalDateTime createTime;

//    private UserRole userRole=UserRole.ROLE_NORMAL;

    @OneToMany(mappedBy = "user")
    private List<Booking> booking;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Roles> roles=new ArrayList<>();

}
