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

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("sFactory"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", new User());
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        if (userDao.isUsernameAvailable(user.getUserName())) {
            userDao.save(user);
            resp.sendRedirect("/login");
        } else {
            user.setUserName(null);
            user.setPassword(null);
            req.setAttribute("user", user);
            req.setAttribute("error", "UserName Is taken");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}
