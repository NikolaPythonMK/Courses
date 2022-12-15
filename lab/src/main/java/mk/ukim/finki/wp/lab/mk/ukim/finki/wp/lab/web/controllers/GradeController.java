package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.GradeRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;
    private final CourseService courseService;


    public GradeController(GradeService gradeService, CourseService courseService) {
        this.gradeService = gradeService;
        this.courseService = courseService;
    }

    @GetMapping
    public String getAddGradePage(HttpServletRequest req, Model model){
        Long courseId = (Long) req.getSession().getAttribute("courseId");
        model.addAttribute("students", courseService.listStudentsByCourse(courseId));
        model.addAttribute("course", courseService.findCourseById(courseId));
        return "add-grade";
    }

    @PostMapping("/add")
    public String addGradeToStudent(@RequestParam String selectedUsername,
                                    @RequestParam Character grade,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp,
                                    HttpServletRequest req){
        Long courseId = (Long) req.getSession().getAttribute("courseId");
        gradeService.addGrade(selectedUsername, courseId, grade, timestamp);
        return "redirect:/StudentEnrollmentSummary";
    }

}
