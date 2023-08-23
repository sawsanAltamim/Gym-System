package com.example.gymsystem.Repostory;

import com.example.gymsystem.Table.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer> {
    Trainee findTraineeById(Integer Id);
}