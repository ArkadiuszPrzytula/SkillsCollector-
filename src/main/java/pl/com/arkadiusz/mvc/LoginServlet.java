package pl.com.arkadiusz.mvc;


import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.User;
import pl.com.arkadiusz.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("sFactory"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", new User());
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        List<User> validUser = userDao.getAllUserNameAndPassword(user.getUserName(), user.getPassword());
        if (validUser.size() == 1) {
            req.getSession().invalidate();
            req.getSession().setAttribute("user", validUser.get(0));
            resp.sendRedirect("/user/skills");
        } else {
            if (userDao.isUsernameAvailable(user.getUserName())) {
                user.setUserName(null);
                user.setPassword(null);
                req.setAttribute("user", user);
                req.setAttribute("error", "User not exist");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            } else {
                user.setUserName(null);
                user.setPassword(null);
                req.setAttribute("user", user);
                req.setAttribute("error", "wrong password");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

            }

        }
    }
}
