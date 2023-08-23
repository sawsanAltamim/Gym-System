package com.example.gymsystem.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20)  check(Packages='Mass' or Packages='Golden' or Packages='Silver')")
    private String packages;
  //  @Column(columnDefinition = "varchar(20)  check(Packages='Swimming' or Packages='iron' or Packages='fitness')")
    private String gymType;
    private Double price;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateEnd;

    @PositiveOrZero
    @NotNull
    private Integer maximumNumberSubscribers;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateStart;

    @OneToMany(mappedBy = "subscription")
    private Set<Trainee> traineeSet;


}
