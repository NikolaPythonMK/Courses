package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("Joe1234", "1234", "Joe", "Rogan"));
        students.add(new Student("Elin5322", "5322", "Elin", "Allison"));
        students.add(new Student("Louis1000", "1000", "Louis", "John"));
        students.add(new Student("Jon5443", "5443", "Jon", "Jones"));
        students.add(new Student("Jack3333", "3333", "Jack", "Harlow"));
    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(i -> i.getName().contains(text) || i.getSurname().contains(text)).toList();
    }

    public List<Student> searchBySubstring(String substring){
        return students.stream().filter(i -> i.getName().contains(substring) || i.getSurname().contains(substring)).toList();
    }

}
