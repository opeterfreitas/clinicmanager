package br.com.clinicmanager.users.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private String phoneNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public Person(String name, String cpf, String phoneNumber, User createdBy) {
        this.name = name;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Person(String cpf, String name, String phoneNumber, User createdBy, LocalDateTime createdAt) {
        this.cpf = cpf;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
