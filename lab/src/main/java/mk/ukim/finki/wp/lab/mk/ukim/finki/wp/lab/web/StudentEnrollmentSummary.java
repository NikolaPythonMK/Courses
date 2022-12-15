//package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.web;
//
//import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.GradeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "StudentEnrollmentSummary", urlPatterns = "/StudentEnrollmentSummary")
//public class StudentEnrollmentSummary extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final CourseService courseService;
//    private final GradeService gradeService;
//
//    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine,
//                                    CourseService courseService,
//                                    GradeService gradeService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.courseService = courseService;
//        this.gradeService = gradeService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        String username = req.getParameter("size");
//        Long courseId = (Long) req.getSession().getAttribute("courseId");
//        if(username == null){
//            context.setVariable("students", courseService.listStudentsByCourse(courseId));
//            context.setVariable("course", courseService.findCourseById(courseId));
//            springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
//        }
//        courseService.addStudentInCourse(username, courseId);
//        context.setVariable("students", courseService.listStudentsByCourse(courseId));
//        context.setVariable("course", courseService.findCourseById(courseId));
//        context.setVariable("graded", gradeService.findAll());
//        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
//    }
//}
