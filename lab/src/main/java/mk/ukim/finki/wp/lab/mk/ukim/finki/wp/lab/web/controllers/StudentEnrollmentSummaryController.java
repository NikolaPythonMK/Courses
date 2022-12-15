package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/StudentEnrollmentSummary")
public class StudentEnrollmentSummaryController {

    private final CourseService courseService;
    private final GradeService gradeService;

    public StudentEnrollmentSummaryController(CourseService courseService, GradeService gradeService) {
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String studentInCoursePage(@RequestParam(required = false) String username,
                                      HttpServletRequest req, Model model)  {
        Long courseId = (Long) req.getSession().getAttribute("courseId");
        if(username != null){
            courseService.addStudentInCourse(username, courseId);
        }
        model.addAttribute("students", courseService.listStudentsByCourse(courseId));
        model.addAttribute("course", courseService.findCourseById(courseId));
        model.addAttribute("graded", gradeService.findAll());
        return "StudentsInCourse";
    }


}
