package com.example.gymsystem.Repostory;

import com.example.gymsystem.Table.Subscription;
import com.example.gymsystem.Table.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer> {
    Trainee findTraineeById(Integer Id);

    boolean existsByEmailAndPassword(String password, String email);
}