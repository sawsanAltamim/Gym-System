package com.example.gymsystem.Service;


import com.example.gymsystem.Api.ApiException;
import com.example.gymsystem.Repostory.ManagerRepostory;
import com.example.gymsystem.Repostory.SubscriptionRepository;
import com.example.gymsystem.Repostory.TraineeRepository;
import com.example.gymsystem.Table.Coach;
import com.example.gymsystem.Table.Manager;
import com.example.gymsystem.Table.Subscription;
import com.example.gymsystem.Table.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TraineeService {

    private final TraineeRepository traineeRepository;
    private final ManagerRepostory managerRepostory;
    private final SubscriptionRepository subscriptionRepository;

    public List<Trainee> getAllTrainee() {
        return traineeRepository.findAll();
    }

    public void addTraineeToManager(Integer manager_id, Trainee trainee) {
        Manager manager = managerRepostory.findManagerById(manager_id);
        if (manager == null) {
            throw new ApiException("Manager not found");
        }

        manager.setNumberTrainees(manager.getNumberTrainees() + 1);
        trainee.setManager(manager);

        managerRepostory.save(manager);
        traineeRepository.save(trainee);
    }

    public void updateTrainee(Integer id, Trainee trainee) {
        Trainee trainee1 = traineeRepository.findTraineeById(id);

        if (trainee1 == null) {
            throw new ApiException("not found id");
        }
        trainee1.setAge(trainee.getAge());
        trainee1.setName(trainee.getName());
        trainee1.setEmail(trainee.getEmail());
        trainee1.setNumber(trainee.getNumber());
        trainee1.setState(trainee.getState());
        traineeRepository.save(trainee1);

    }

    public void deleteTrainee(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        if (trainee == null) {
            throw new ApiException("not found");
        }
        Manager manager = trainee.getManager();
        if (manager != null) {
            manager.setNumberTrainees(manager.getNumberTrainees() - 1); // Decrement coachNumber
            managerRepostory.save(manager);
        }
        traineeRepository.delete(trainee);
    }

    public void assigManangerTrainee(Integer trainee_id, Integer manager_id) {
        Trainee trainee = traineeRepository.findTraineeById(trainee_id);
        Manager manager = managerRepostory.findManagerById(manager_id);

        if (trainee == null || manager == null) {
            throw new ApiException("ID Trainee or manager not found");
        }

        trainee.setManager(manager);
        traineeRepository.save(trainee);
    }

    public String getTraineePackage(Integer traineeId) {
        Trainee trainee = traineeRepository.findTraineeById(traineeId);
        if (trainee == null) {
            throw new ApiException("Trainee not found");
        }

        if (trainee.getSubscription() == null) {
            throw new ApiException("Trainee does not have a subscription");
        }

        return trainee.getSubscription().getPackages();
    }

    public boolean checkPasswordEmail(String password, String email){
        boolean trainee=traineeRepository.existsByEmailAndPassword(password,email);
        if (trainee==false){
            throw new ApiException("not found");
        }
        return true;
    }
}

