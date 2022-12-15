package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.web;


import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchStudent", urlPatterns = "/SearchStudent")
public class SearchStudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;


    public SearchStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String substring = req.getParameter("search-input");
        List<Student> students;
        try{
            students = studentService.searchBySubstring(substring);
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("students", students);
            springTemplateEngine.process("listStudents.html", context, resp.getWriter());
        }
        catch (NullPointerException e){
            resp.sendRedirect("/listStudent");
        }
    }

}
