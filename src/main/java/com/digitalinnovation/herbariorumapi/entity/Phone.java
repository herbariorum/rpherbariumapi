package com.digitalinnovation.herbariorumapi.entity;


import com.digitalinnovation.herbariorumapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data  //getter e setter
@Builder //padrão de projetos
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
