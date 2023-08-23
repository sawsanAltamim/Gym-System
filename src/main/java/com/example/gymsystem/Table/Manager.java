package com.example.gymsystem.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String number;

    @Pattern(regexp = "^[A-Za-z\\s]{1,}[0-9\\s]{1,}", message = "Please enter a valid password")
    private String password;

    @NotNull
    @PositiveOrZero
    private Integer numberTrainees;

    @NotNull
    @PositiveOrZero
    private Integer coachNumber;

    @OneToMany(mappedBy = "manager")
    private Set<Coach> coachSet;

    @OneToMany(mappedBy = "manager")
    private Set<Trainee> traineeSet;

}
