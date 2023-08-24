package com.example.gymsystem.Controller;

import com.example.gymsystem.Api.ApiResponse;
import com.example.gymsystem.Service.SubscriptionService;
import com.example.gymsystem.Table.Subscription;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;


    @GetMapping("/get")
    public ResponseEntity getAllSubscription() {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getAllSubscription());
    }

    @PostMapping("/add")
    public ResponseEntity addSubscription(@RequestBody @Valid Subscription subscription) {
        subscriptionService.addSubscription(subscription);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Add Subscription"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSubscription(@RequestBody @Valid Subscription subscription, @PathVariable Integer id) {
        subscriptionService.updateSubscription(id, subscription);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Update Subscription"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSubscription(@PathVariable Integer id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delete Subscription"));
    }

    @PutMapping("/{trainee_id}/assig/{subscription_id}")
    public ResponseEntity assigSubscriptionTrainee(@PathVariable Integer trainee_id, @PathVariable Integer subscription_id) {
        subscriptionService.assigSubscriptionTrainee(trainee_id, subscription_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assig done"));
    }

    @GetMapping("/verify/{subscriptions_id}")
    public ResponseEntity subscriptionsDateValid(@PathVariable Integer subscriptions_id) {
        subscriptionService.subscriptionsDateValid(subscriptions_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Subscription is valid"));
    }

    @GetMapping("/join_gym/{trainee_id}/{subscription_id}")
    public ResponseEntity subscriptions(@PathVariable Integer trainee_id, @PathVariable Integer subscription_id) {
        subscriptionService.subscriptions(trainee_id, subscription_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Subscription completed successfully"));


    }

    @GetMapping("/unsubscribe/{trainee_id}/{subscription_id}")
    public ResponseEntity unsubscribe(@PathVariable Integer trainee_id, @PathVariable Integer subscription_id) {
        subscriptionService.unsubscribe(trainee_id, subscription_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Subscription has been cancelled"));
    }

    @PutMapping("/{coach_id}/assig_subscriptin_coach/{subscription_id}")
    public ResponseEntity assigSubscriptinCoach(@PathVariable Integer coach_id, @PathVariable Integer subscription_id) {
        subscriptionService.assigSubscriptinCoach(coach_id, subscription_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assig done"));
    }

    @GetMapping("/Search/{packages}")
    public ResponseEntity SearchGymType(@PathVariable String packages) {
        return ResponseEntity.status(200).body(subscriptionService.SearchPackages(packages));
    }
}
