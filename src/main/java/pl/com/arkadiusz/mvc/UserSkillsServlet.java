package pl.com.arkadiusz.mvc;

import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.Skill;
import pl.com.arkadiusz.model.User;
import pl.com.arkadiusz.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/users/skills")
public class UserSkillsServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("sFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        List<Skill> skills = userDao.userSkills(user);

        Map<String, Integer> userSkills = skills.stream().collect(Collectors.toMap(
                s -> s.getName(),
                i -> 1,
                (v, v2) -> v + v2
        ));

        req.setAttribute("skills", userSkills.entrySet());
        req.getRequestDispatcher("/WEB-INF/views/skills.jsp").forward(req, resp);

    }

}
