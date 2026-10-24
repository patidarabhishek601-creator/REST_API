package com.patel.LearningRestAPIs.controller;

import com.patel.LearningRestAPIs.dto.AddStudentRequestDto;
import com.patel.LearningRestAPIs.dto.StudentDto;

import com.patel.LearningRestAPIs.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor


@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;



    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));


    }





    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(
                                                            @RequestBody  @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,@RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id,updates));
    }
}
