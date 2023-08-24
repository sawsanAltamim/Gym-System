package com.example.gymsystem.Service;

import com.example.gymsystem.Api.ApiException;
import com.example.gymsystem.Repostory.CoachRepository;
import com.example.gymsystem.Repostory.ManagerRepostory;
import com.example.gymsystem.Table.Coach;
import com.example.gymsystem.Table.Manager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;
    private final ManagerRepostory managerRepostory;

    public List<Coach> getAllCoach() {
        return coachRepository.findAll();
    }

    public void addCoachToManager(Integer manager_id, Coach coach) {
        Manager manager = managerRepostory.findManagerById(manager_id);
        if (manager == null) {
            throw new ApiException("Manager not found");
        }

        manager.setCoachNumber(manager.getCoachNumber() + 1);
        coach.setManager(manager);

        managerRepostory.save(manager);
        coachRepository.save(coach);
    }

    public void updateCoach(Integer id,Coach coach){
       Coach  coach1= coachRepository.findCoachById(id);

        if (coach1 ==null){
            throw new ApiException("not found id");
        }
        coach1.setEmail(coach.getEmail());
        coach1.setNumberPhone(coach.getNumberPhone());
        coach1.setName(coach.getName());
        coachRepository.save(coach1);

    }

    public void deleteCoach(Integer id){
        Coach coach =coachRepository.findCoachById(id);
        if (coach ==null){
            throw new ApiException("not found");
        }
        Manager manager = coach.getManager();
        if (manager != null) {
            manager.setCoachNumber(manager.getCoachNumber() - 1); // Decrement coachNumber
            managerRepostory.save(manager); // Save manager changes
        }
        coachRepository.delete(coach);
    }
    public void assigManangerCoach(Integer coach_id , Integer manager_id){
        Coach coach = coachRepository.findCoachById(coach_id);
        Manager manager = managerRepostory.findManagerById(manager_id);

        if(coach == null || manager == null){
            throw new ApiException("ID coach or manager not found");
        }
       /* manager.getCoachSet().add(coach);*/
        coach.setManager(manager);
        coachRepository.save(coach);
    }
}
