package com.example.gymsystem.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private String numberPhone;
    @Email
    private String email;
    private String specialty;

    @ManyToOne
    @JoinColumn(name = "manager_id",referencedColumnName = "id")
    @JsonIgnore
    private Manager manager;

   /* @OneToOne
    @MapsId
    @JsonIgnore
    private Subscription subscription;
*/



}
