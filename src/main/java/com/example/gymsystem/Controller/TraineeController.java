package com.example.gymsystem.Controller;


import com.example.gymsystem.Api.ApiResponse;
import com.example.gymsystem.Service.TraineeService;
import com.example.gymsystem.Table.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    @GetMapping("/get")
    public ResponseEntity getAllTrainee(){
        return ResponseEntity.status(HttpStatus.OK).body(traineeService.getAllTrainee());
    }
    @PostMapping("/add")
    public ResponseEntity addTrainee(@RequestBody Trainee trainee){
        traineeService.addTrainee(trainee);
        return ResponseEntity.status(HttpStatus.OK).body("Add Trainee");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTrainee(@RequestBody Trainee trainee, @PathVariable Integer id){
        traineeService.updateTrainee(id, trainee);
        return ResponseEntity.status(HttpStatus.OK).body("Update Trainee");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainee(@PathVariable Integer id){
        traineeService.deleteTrainee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Trainee");
    }

    @PutMapping("/{trainee_id}/assig_trainee/{manager_id}")
    public ResponseEntity assigManangerTrainee(@PathVariable Integer trainee_id , @PathVariable Integer manager_id ){
        traineeService.assigManangerTrainee(trainee_id,manager_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assig done"));
    }
}

