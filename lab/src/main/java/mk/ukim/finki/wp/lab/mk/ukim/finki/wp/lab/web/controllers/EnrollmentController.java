package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.EnrollmentService;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final StudentService studentService;

    public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService, StudentService studentService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public String getEnrollmentPage(@RequestParam(required = false) String part, Model model){
        if(part != null && !part.isEmpty()){
            model.addAttribute("courses", enrollmentService.searchInCourse(part));
            model.addAttribute("students", enrollmentService.searchInStudent(part));
        }
        else{
            model.addAttribute("courses", courseService.listAll());
            model.addAttribute("students", studentService.listAll());
        }
        return "enrollment";
    }
}
