package com.example.gymsystem.Controller;

import com.example.gymsystem.Api.ApiResponse;
import com.example.gymsystem.Service.CoachService;
import com.example.gymsystem.Table.Coach;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coach")
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/get")
    public ResponseEntity getAllCoach(){
        return ResponseEntity.status(200).body(coachService.getAllCoach());
    }
    @PostMapping("/add")
    public ResponseEntity addCoach(@RequestBody Coach coach){
        coachService.addCoach(coach);
        return ResponseEntity.status(200).body("Add Coach");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCoach(@RequestBody Coach coach, @PathVariable Integer id){
        coachService.updateCoach(id,coach);
        return ResponseEntity.status(200).body("Update Coach");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoach(@PathVariable Integer id){
       coachService.deleteCoach(id);
        return ResponseEntity.status(200).body("Delete Coach");
    }

    @PutMapping("/{coach_id}/assig_coach/{manager_id}")
    public ResponseEntity assigManangerCoach(@PathVariable Integer coach_id , @PathVariable Integer manager_id ){
        coachService.assigManangerCoach(coach_id,manager_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assig done"));
    }

}
