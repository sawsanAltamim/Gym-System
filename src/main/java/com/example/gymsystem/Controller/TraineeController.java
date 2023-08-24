package com.example.gymsystem.Controller;


import com.example.gymsystem.Api.ApiResponse;
import com.example.gymsystem.Service.TraineeService;
import com.example.gymsystem.Table.Trainee;
import jakarta.validation.Valid;
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
    @PostMapping("/{trainee_id}/add_trainee")
    public ResponseEntity addTraineeToManager(@PathVariable Integer trainee_id ,@RequestBody @Valid Trainee trainee){
        traineeService.addTraineeToManager(trainee_id,trainee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Add Trainee"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTrainee(@RequestBody @Valid Trainee trainee, @PathVariable Integer id){
        traineeService.updateTrainee(id, trainee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Update Trainee"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainee(@PathVariable Integer id){
        traineeService.deleteTrainee(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delete Trainee"));
    }
    @GetMapping("/{manager_id}/package")
    public ResponseEntity getTraineePackage(@PathVariable Integer manager_id) {
        String packageName = traineeService.getTraineePackage(manager_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Trainee's package: " + packageName));
    }
    @GetMapping("/check")
    public ResponseEntity LoginPasswordEmail( String password, String email , @RequestBody @Valid Trainee trainee ){
        traineeService.checkPasswordEmail(trainee.getPassword(),trainee.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Login"));
    }
}

