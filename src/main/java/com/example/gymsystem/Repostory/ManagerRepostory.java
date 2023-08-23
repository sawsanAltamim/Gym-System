package com.example.gymsystem.Repostory;

import com.example.gymsystem.Table.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepostory extends JpaRepository<Manager, Integer> {

    Manager findManagerById(Integer id);
}
