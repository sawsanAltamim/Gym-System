package com.example.gymsystem.Service;

import com.example.gymsystem.Api.ApiException;
import com.example.gymsystem.Repostory.CoachRepository;
import com.example.gymsystem.Repostory.ManagerRepostory;
import com.example.gymsystem.Table.Coach;
import com.example.gymsystem.Table.Manager;
import com.example.gymsystem.Table.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  ManagerService {
    private final ManagerRepostory managerRepostory;
    private final CoachRepository coachRepository;

    public List<Manager> getAllManager(){
        return managerRepostory.findAll();
    }

    public void addManager(Manager manager){
        managerRepostory.save(manager);
    }

    public void updateManager(Integer id,Manager manager){
        Manager manager1 = managerRepostory.findManagerById(id);
        if(manager1 == null){
            throw new ApiException("Manager not found");
        }
        manager1.setName(manager.getName());
        manager1.setEmail(manager.getEmail());
        manager1.setNumber(manager.getNumber());
        manager1.setPassword(manager.getPassword());
        manager1.setCoachNumber(manager.getCoachNumber());
        manager1.setNumberTrainees(manager.getNumberTrainees());

        managerRepostory.save(manager1);
    }

    public void deleteManager(Integer id){
        Manager manager =managerRepostory.findManagerById(id);
        if(manager == null){
            throw new ApiException("Manager not found");
        }
        managerRepostory.delete(manager);

    }
}
