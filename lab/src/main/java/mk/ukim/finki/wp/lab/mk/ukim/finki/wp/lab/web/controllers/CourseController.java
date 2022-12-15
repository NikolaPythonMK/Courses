package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.enums.Type;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursePage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("courses", courseService.listAll());
        return "listCourses";
    }

    @GetMapping("/add-form")
    public String getAddCoursePage(Model model){
            model.addAttribute("teachers", teacherService.findAll());
            return "add-course";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        if(id == null || courseService.findCourseById(id) == null){
            return "redirect:/courses?error=CourseWasNotFound";
        }
        model.addAttribute("course", courseService.findCourseById(id));
        model.addAttribute("teachers", teacherService.findAll());
        return "add-course";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam(required = false) Long id,
                             @RequestParam(required = false) Type type,
                             @RequestParam String name, @RequestParam String desc, @RequestParam Long teacherId){
        Optional<Course> course = courseService.addCourse(name, desc, teacherId, id, type);
        if(course.isPresent()){
            return "redirect:/courses";
        }
        else{
            return "redirect:/courses?error=TheCourseCannotBeSaved";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

}
