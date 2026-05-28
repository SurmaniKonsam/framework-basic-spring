package com.jarvis.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jarvis.dto.Student;
import com.jarvis.service.StudentRankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentRankController {

    private final StudentRankService studentRankService;

    public StudentRankController(StudentRankService studentRankService){
        this.studentRankService = studentRankService;
    }

    /**
     * For quick api response
     * Use HashMap.
     */
    /*
    @PostMapping("/register")
    public Map<String,Object> savedStudent(@RequestBody Student student){
        Student std = studentRankService.saveStudent(student);
        Map<String,Object> savedResponse = new HashMap<>();
        List<Object> returnedResponse = new ArrayList<>();
        savedResponse.put("Student",std);
        savedResponse.put("Message","Student Registered Successfully : "+HttpStatus.OK);

        String message = "HttpResponse : "+HttpStatus.OK+" | Message : "+"Student Registered Successfully";
        savedResponse.put("Message",message);
        //returnedResponse.add(message);
        //String objectAdded = "Student : "+std;
        //returnedResponse.add(objectAdded);
        savedResponse.put("Student",std);

        return savedResponse;
    }
    */

    //Now comes the production grade practice.
    /**
     * use registerResponse, we will discuss what registerResponse really is.
     * We will also see, how registerResponse sits on the back of ResponseEntity
     * and after development we will see, what ResponseEntity really do, how is it related to
     * registerResponse.
     */

    /*
        RegisterResponse is automatically taken care of by spring compiler.
        It returns -> record object, i know its an immutable object, we will see what it does?
     */
    public record APIResponse(@JsonProperty("Message124324") String message,
                                   @JsonProperty("Student") Student student){
        //no need of getter and setter property
        //it will take care of itself.
        //We will study few more JsonProperty

    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> savedStudent(@RequestBody Student student){
        //Object is returned here
        Student std = studentRankService.saveStudent(student);
        APIResponse apiResponse = new APIResponse("Registered Successfully",std);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    //time for get now. I will design this api by myself.
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentData(@PathVariable Long id){
        Student std = studentRankService.getStudentById(id);
        if(std==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        }else{
            APIResponse apiResponse = new APIResponse("Fetched Successfully",std);
            //return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
            //or
            return ResponseEntity.ok(apiResponse);
        }
        //we can debug here as well but, postman is the better client to do that
    }


    /**
     * Be very mindful, that controller is first stage of penetration so be mindful to have the
     * returned response from the JPARepository handled carefully, just like we did here:
     * return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        boolean deleted = studentRankService.deleteStudentById(id);
        if(deleted){
            /**
             * Let's reduce long code ResponseEntity.status(HttpStatus.OK).body(DTOResponse);
             * to one single code
             * ResponseEntity.ok(DTOResponse);
             */
            return ResponseEntity.ok("Student deleted successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateStudent(@RequestBody Student student
            ,@PathVariable long id){
        //in controller only response entity, and service calling will be done.
        Student updateResponse = studentRankService.updateStudent(student,id);
        APIResponse apiResponse;
        if(updateResponse==null){
            apiResponse = new APIResponse("Student Not found of id : "+id,null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        apiResponse = new APIResponse("Updated Successfully",updateResponse);
        return ResponseEntity.ok(apiResponse);
    }


    /**
     * final -> Patch -> used primarily for, updating specific field out of existing object from
     * the db, for the unique identifier or primary key entity identified.
     */
    @PatchMapping("/patch/{id}")
    public ResponseEntity<APIResponse> patchStudent(@RequestBody Student student,
                                                    @PathVariable long id){
        //get the existing body first
        Student updatedStudent = studentRankService.patchStudent(student,id);
        APIResponse apiResponse;
        if(updatedStudent==null){
            apiResponse = new APIResponse("Student not found",updatedStudent);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }else{
            apiResponse = new APIResponse("Student patched successfully",updatedStudent);
            return ResponseEntity.ok(apiResponse);
        }
    }



}
