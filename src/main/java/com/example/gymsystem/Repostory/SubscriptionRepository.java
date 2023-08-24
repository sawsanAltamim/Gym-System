package com.example.gymsystem.Repostory;

import com.example.gymsystem.Table.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
    Subscription findSubscriptionById(Integer id);

    List<Subscription> findSubscriptionByPackages (String packages);
}