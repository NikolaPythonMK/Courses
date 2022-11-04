package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repo) {
        this.repository = repo;
    }

    @Override
    public List<Student> listAll() {
        return repository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return repository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        Student student = repository.findAllStudents().stream()
                .filter(i -> i.getSurname().equals(username)).findFirst().orElse(null);

        boolean validName = name != null && !name.isEmpty();
        boolean validSurname = surname != null && !surname.isEmpty();

        if(student == null && validName && validSurname){
            Student savedStudent = new Student(username, password, name, surname);
            StudentRepository.students.add(savedStudent);
            return savedStudent;
        }
        return null;
    }
}
