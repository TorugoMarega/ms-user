package com.ms.user.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teste")
@Data
public class AppTest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
