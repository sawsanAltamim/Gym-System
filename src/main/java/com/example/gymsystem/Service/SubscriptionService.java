package com.example.gymsystem.Service;

import com.example.gymsystem.Api.ApiException;
import com.example.gymsystem.Repostory.SubscriptionRepository;
import com.example.gymsystem.Repostory.TraineeRepository;
import com.example.gymsystem.Table.Manager;
import com.example.gymsystem.Table.Subscription;
import com.example.gymsystem.Table.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final TraineeRepository traineeRepository;


    public List<Subscription> getAllSubscription(){
        return subscriptionRepository.findAll();
    }
    public void addSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }
    public void updateSubscription(Integer id,Subscription subscription){
        Subscription subscription1= subscriptionRepository.findSubscriptionById(id);

        if (subscription1 ==null){
            throw new ApiException("not found id");
        }
        subscription1.setPrice(subscription1.getPrice());
        subscription1.setPackages(subscription.getPackages());
        subscription1.setGymType(subscription.getGymType());
        subscription1.setDateStart(subscription.getDateStart());
        subscription1.setDateEnd(subscription.getDateEnd());
        subscriptionRepository.save(subscription1);

    }
    public void deleteSubscription(Integer id){
      Subscription subscription=subscriptionRepository.findSubscriptionById(id);
        if (subscription ==null){
            throw new ApiException("not found");
        }
        subscriptionRepository.delete(subscription);
    }

    // مو متاكده
    public void assigSubscriptionTrainee(Integer trainee_id, Integer subscription_id) {
        Trainee trainee = traineeRepository.findTraineeById(trainee_id);
        Subscription subscription = subscriptionRepository.findSubscriptionById(subscription_id);

        if (trainee == null || subscription == null) {
            throw new ApiException("ID Trainee or subscription not found");
        }

        trainee.setSubscription(subscription);
        traineeRepository.save(trainee);
    }
}



