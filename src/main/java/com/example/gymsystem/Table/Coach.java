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
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty
    @Column(unique=true, columnDefinition = "varchar(10) not null")
    private String numberPhone;

    @Email
    @NotEmpty
    @Column(unique=true)
    private String email;


    @ManyToOne
    @JoinColumn(name = "manager_id",referencedColumnName = "id")
    @JsonIgnore
    private Manager manager;

    @OneToMany(mappedBy = "coach")
    private Set<Subscription> subscriptions ;

}
