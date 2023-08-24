package com.example.gymsystem.Service;

import com.example.gymsystem.Api.ApiException;
import com.example.gymsystem.Repostory.CoachRepository;
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
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final TraineeRepository traineeRepository;
    private final CoachRepository coachRepository;


    public List<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    public void addSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public void updateSubscription(Integer id, Subscription subscription) {
        Subscription subscription1 = subscriptionRepository.findSubscriptionById(id);

        if (subscription1 == null) {
            throw new ApiException("not found id");
        }
        subscription1.setPrice(subscription1.getPrice());
        subscription1.setPackages(subscription.getPackages());
        subscription1.setGymType(subscription.getGymType());
        subscription1.setDateStart(subscription.getDateStart());
        subscription1.setDateEnd(subscription.getDateEnd());
        subscriptionRepository.save(subscription1);

    }

    public void deleteSubscription(Integer id) {
        Subscription subscription = subscriptionRepository.findSubscriptionById(id);
        if (subscription == null) {
            throw new ApiException("not found");
        }
        subscriptionRepository.delete(subscription);
    }

    public void subscriptionsDateValid(Integer subscriptions_id) {
        Subscription subscription = subscriptionRepository.findSubscriptionById(subscriptions_id);
        if (subscription == null) {
            throw new ApiException("Subscription not found");
        }

        LocalDate currentDate = LocalDate.now();
        if (subscription.getDateEnd().isBefore(currentDate)) {
            throw new ApiException("Subscription is not valid");
        }
    }

    public void subscriptions(Integer trainee_id, Integer subscription_id) {
        Trainee trainee = traineeRepository.findTraineeById(trainee_id);
        if (trainee == null) {
            throw new ApiException("Trainee not found");
        }
        Subscription subscription = subscriptionRepository.findSubscriptionById(subscription_id);
        if (subscription == null) {
            throw new ApiException("Subscription not found");
        }
        double balance = trainee.getBalance();
        double preiceSubscriptions = subscription.getPrice();
        if (balance < preiceSubscriptions) {
            throw new ApiException("Insufficient balance");

        }
        if (trainee.getState().equals("subscriber")) {
            throw new ApiException("You already have a subscription");
        }

        if (subscription.getMaximumNumberSubscribers() == 0) {
            throw new ApiException("Subscription plan is full");
        }

        subscriptionsDateValid(subscription.getId()); ////Checks if the subscription is valid for trainee or not

        boolean hasDiscountCoupon = trainee.getDiscountCoupon();
        if (hasDiscountCoupon == true) {
            preiceSubscriptions *= 0.9;
            trainee.setBalance(balance - preiceSubscriptions);
            trainee.setDiscountCoupon(false);
            traineeRepository.save(trainee);
        } else {
            trainee.setBalance(balance - preiceSubscriptions);
            traineeRepository.save(trainee);
        }

        subscription.setMaximumNumberSubscribers(subscription.getMaximumNumberSubscribers() - 1);
        subscriptionRepository.save(subscription);

        trainee.setSubscription(subscription);
        trainee.setState("subscriber");
        traineeRepository.save(trainee);
    }

    public void assigSubscriptionTrainee(Integer trainee_id, Integer subscription_id) {
        Trainee trainee = traineeRepository.findTraineeById(trainee_id);
        Subscription subscription = subscriptionRepository.findSubscriptionById(subscription_id);

        if (trainee == null || subscription == null) {
            throw new ApiException("ID Trainee or subscription not found");
        }

        trainee.setSubscription(subscription);
        traineeRepository.save(trainee);
    }

    public void assigSubscriptinCoach(Integer coach_id , Integer subscription_id){
        Coach coach = coachRepository.findCoachById(coach_id);
        Subscription subscription = subscriptionRepository.findSubscriptionById(subscription_id);

        if(coach == null || subscription == null){
            throw new ApiException("ID coach or subscription not found");
        }
        coach.getSubscriptions().add(subscription);
        subscription.setCoach(coach);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(Integer subscription_id, Integer trainee_id) {
        Subscription subscription = subscriptionRepository.findSubscriptionById(subscription_id);
        if (subscription == null) {
            throw new ApiException("Subscription not found");
        }
        Trainee trainee = traineeRepository.findTraineeById(trainee_id);
        if (trainee == null) {
            throw new ApiException(" Trainee not found");
        }
        if (trainee.getState().equals("Not_subscribed")) {
            throw new ApiException("You really don't have a subscription");
        }

        trainee.setSubscription(subscription);
        trainee.setState("Not_subscribed");
        traineeRepository.save(trainee);
    }

    public List<Subscription> SearchPackages(String packages){
        List<Subscription> subscription=subscriptionRepository.findSubscriptionByPackages(packages);
        if (subscription == null) {
            throw new ApiException(" Packages not found");
        }
        return subscription;
    }
}



