package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String,Student> studentDB = new HashMap<>();
    Map<String,Teacher> teacherDB = new HashMap<>();
    Map<String, List<String>> teacherStudentList = new HashMap<>();
    public void addStudentToDB(Student student){

        String name = student.getName();
        studentDB.put(name,student);
        return;
    }

    public void addTeacherToDB(Teacher teacher){

        String name = teacher.getName();
        teacherDB.put(name,teacher);
        return;
    }

    public void addPairToDB(String studentName, String teacherName) {

        if(studentDB.containsKey(studentName) && teacherDB.containsKey(teacherName)) {

            if (!teacherStudentList.containsKey(teacherName)) {
                List<String> studentList = new ArrayList<>();
                studentList.add(studentName);
                teacherStudentList.put(teacherName, studentList);
            } else {
                List<String> studentList = teacherStudentList.get(teacherName);
                studentList.add(studentName);
                teacherStudentList.put(teacherName, studentList);
            }
            return;
        }
    }

    public Student getStudentByName(String studentName){

        if(studentDB.containsKey(studentName)){
            return studentDB.get(studentName);
        }
        return null;
    }

    public Teacher getTeacherByName(String teacherName){

        if(teacherDB.containsKey(teacherName)) {
            return teacherDB.get(teacherName);
        }
        return null;
    }

    public List<String> getStudentList(String teacherName){

        if(teacherStudentList.containsKey(teacherName)){
            return teacherStudentList.get(teacherName);
        }
        return null;
    }

    public List<String> getAllStudents() {

        List<String> studentList = new ArrayList<>();
        for(String name: studentDB.keySet()){
            studentList.add(name);
        }
        return studentList;
    }

    public void deleteTeacherFromDb(String teacherName) {

        if(teacherDB.containsKey(teacherName)){
            if(teacherStudentList.containsKey(teacherName)) {
                List<String> studentList = teacherStudentList.get(teacherName);
                for (String student : studentList) {
                    if (studentDB.containsKey(student)) {
                        studentDB.remove(student);
                    }
                }
                teacherDB.remove(teacherName);
                teacherStudentList.remove(teacherName);
                return;
            }
        }
    }

    public void deleteAllTeachersFromDb() {

        for(String teacherName: teacherDB.keySet()){
            if(teacherStudentList.containsKey(teacherName)){
                List<String> studentList = teacherStudentList.get(teacherName);
                for(String student : studentList){
                    if(studentDB.containsKey(student)){
                        studentDB.remove(student);
                    }
                }
            }
        }
       teacherStudentList.clear();
       teacherDB.clear();
    }
}
