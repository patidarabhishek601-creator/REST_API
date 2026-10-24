package com.patel.LearningRestAPIs.service.impl;

import com.patel.LearningRestAPIs.dto.AddStudentRequestDto;
import com.patel.LearningRestAPIs.dto.StudentDto;
import com.patel.LearningRestAPIs.entity.Student;
import com.patel.LearningRestAPIs.repository.StudentRepository;
import com.patel.LearningRestAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.modelmapper.Converters.Collection.map;

@Service
@RequiredArgsConstructor

public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students=studentRepository.findAll();
        return students
                .stream()

                .map(student->modelMapper.map(student,StudentDto.class))
        .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {

            Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("student not found"+id));
            return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent=modelMapper.map(addStudentRequestDto,Student.class);
        Student student=studentRepository.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("student does not exits by id"+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("student not found"+id));
         modelMapper.map(addStudentRequestDto,student);
      student=   studentRepository.save(student);
      return modelMapper.map(student,StudentDto.class);


    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("student not found"+id));
        updates.forEach((field,value) ->{
            switch (field) {
                case "name":student.setName((String) value);
                break;
                case "email":student.setEmail((String)value);
                default:
                    throw new IllegalArgumentException("field is not found");
            }
        });

        Student saveStudent =   studentRepository.save(student);
        return modelMapper.map(saveStudent,StudentDto.class);
    }
}
