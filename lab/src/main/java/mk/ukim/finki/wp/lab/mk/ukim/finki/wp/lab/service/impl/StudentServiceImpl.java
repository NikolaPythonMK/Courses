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
        return repository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return repository.findAllByNameEqualsOrSurnameEquals(text, text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(repository.findById(username).isPresent()) return null;
        Student student = new Student(username, password, name, surname);
        return repository.save(student);
    }

    public List<Student> searchBySubstring(String substring){
        if(substring == null || substring.isEmpty()){
           throw new NullPointerException();
        }
        return repository.findAllByNameContainingOrUsernameContainingOrSurnameContaining(substring, substring, substring);
    }
}
