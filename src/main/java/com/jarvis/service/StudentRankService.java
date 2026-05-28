package com.jarvis.service;

import com.jarvis.dto.Student;
import com.jarvis.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentRankService {

    private final StudentRepository studentRepository;

    //Always use constructor up in the DI, modern enterprise practice.
    //Make is immutable.
    //An mocking easy
    public StudentRankService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //pre-existing api of jpa repository used save.
    public Student saveStudent(Student student){
        //Why is it returning, does it return something when we save it?
        //Here, hibernate inserts guery generation.

        return studentRepository.save(student);
    }

    //Get unique identifier required
    public Student getStudentById(Long id) {
        Optional<Student> existOrNot = studentRepository.findById(id);
        return existOrNot.orElse(null);
    }

    //Delete -> unique identifier required
    public boolean deleteStudentById(Long id){
        Optional<Student> studentExist = studentRepository.findById(id);
        if(studentExist.isPresent()){
            studentRepository.delete(studentExist.get());
            return true;
        }
        return false;
    }

    //Update service -> put
    public Student updateStudent(Student student, long id){
        //Fetch existing body from the db
        Optional<Student> existingBody = studentRepository.findById(id);
        if(existingBody.isPresent()){
            existingBody.get().setStudentName(student.getStudentName());
            existingBody.get().setJeeRank(student.getJeeRank());
            //existingBody.get().setId(id); -> because the id already exist, we are doing line 45.
            return saveStudent(existingBody.get());
        }
        return null;
    }

    //Patch -> unique identifier required
    //need to validate other not requested variable
    public Student patchStudent(Student student,long id){
        //student is the request body here

        Optional<Student> existingDbStudent = studentRepository.findById(id);
        if(existingDbStudent.isPresent()){
            if(student.getStudentName()!=null){
                existingDbStudent.get().setStudentName(student.getStudentName());
            }
            if(student.getJeeRank()!=0){
                existingDbStudent.get().setJeeRank(student.getJeeRank());
            }
            return saveStudent(existingDbStudent.get());
        }
        return null;
    }
}
