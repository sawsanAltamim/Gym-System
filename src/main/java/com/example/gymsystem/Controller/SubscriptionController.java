package com.example.gymsystem.Controller;

import com.example.gymsystem.Api.ApiResponse;
import com.example.gymsystem.Service.SubscriptionService;
import com.example.gymsystem.Table.Subscription;
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
    public ResponseEntity getAllSubscription(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getAllSubscription());
    }
    @PostMapping("/add")
    public ResponseEntity addSubscription(@RequestBody Subscription subscription){
        subscriptionService.addSubscription(subscription);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Add Subscription"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateSubscription(@RequestBody Subscription subscription, @PathVariable Integer id){
        subscriptionService.updateSubscription(id,subscription);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Update Subscription"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSubscription(@PathVariable Integer id){
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delete Subscription"));
    }

    @PutMapping("/{trainee_id}/assig/{subscription_id}")
    public ResponseEntity assigSubscriptionTrainee(@PathVariable Integer trainee_id, @PathVariable Integer subscription_id) {
        subscriptionService.assigSubscriptionTrainee(trainee_id,subscription_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assig done"));
    }

}
