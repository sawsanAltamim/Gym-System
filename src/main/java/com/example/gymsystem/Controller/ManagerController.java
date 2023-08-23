package com.example.gymsystem.Controller;

import com.example.gymsystem.Api.ApiResponse;
import com.example.gymsystem.Service.CoachService;
import com.example.gymsystem.Service.ManagerService;
import com.example.gymsystem.Service.TraineeService;
import com.example.gymsystem.Table.Manager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/get")
    public ResponseEntity getAllManager(){
        return ResponseEntity.status(HttpStatus.OK).body(managerService.getAllManager());
    }
    @PostMapping("/add")
    public ResponseEntity addManager(@RequestBody @Valid Manager manager){
        managerService.addManager(manager);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Manager added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAddress(@PathVariable Integer id, @RequestBody @Valid Manager manager){
        managerService.updateManager(id, manager);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Manager update successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id){
        managerService.deleteManager(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Manager deleted successfully"));
    }

}
