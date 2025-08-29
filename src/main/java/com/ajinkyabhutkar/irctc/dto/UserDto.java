package com.ajinkyabhutkar.irctc.dto;

import com.ajinkyabhutkar.irctc.entity.Booking;
import com.ajinkyabhutkar.irctc.entity.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private LocalDateTime createTime=LocalDateTime.now();

    private List<RolesDto> roles=new ArrayList<>();

}
