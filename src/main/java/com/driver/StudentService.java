package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student){

        studentRepository.addStudentToDB(student);
    }

    public void addTeacher(Teacher teacher){

        studentRepository.addTeacherToDB(teacher);
    }

    public void addTeacherStudentPair(String studentName, String teacherName) {

        studentRepository.addPairToDB(studentName, teacherName);
    }

    public Student getStudent(String studentName){

        return studentRepository.getStudentByName(studentName);
    }

    public Teacher getTeacher(String teacherName){

        return studentRepository.getTeacherByName(teacherName);
    }

    public List<String> getStudentsByTeacherName(String teacherName){

        return studentRepository.getStudentList(teacherName);
    }

    public List<String> getAllStudentsList() {

        return studentRepository.getAllStudents();
    }

    public void deleteTeacher(String teacherName) {

        studentRepository.deleteTeacherFromDb(teacherName);
    }

    public void deleteAllTeachers() {

        studentRepository.deleteAllTeachersFromDb();
    }
}
