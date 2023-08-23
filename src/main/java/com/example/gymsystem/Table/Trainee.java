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
public class Trainee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Integer age;
    @Email
    private String email;
    @NotNull
    private String number;
    @NotEmpty
    @Column(columnDefinition = "varchar(20)  check(state='subscriber' or state='Not_subscribed')")
    private String state;
    @NotNull
    @PositiveOrZero
    private Double balance;

    private Boolean discountCoupon;

    @Pattern(regexp = "^[A-Za-z\\s]{1,}[0-9\\s]{1,}", message = "Please enter a valid password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "manager_id",referencedColumnName = "id")
    @JsonIgnore
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "trainee_id",referencedColumnName = "id")
    @JsonIgnore
    private Subscription subscription;


}
