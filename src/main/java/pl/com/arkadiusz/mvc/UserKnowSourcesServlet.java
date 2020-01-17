package pl.com.arkadiusz.mvc;

import org.hibernate.SessionFactory;

import pl.com.arkadiusz.model.Source;
import pl.com.arkadiusz.model.User;
import pl.com.arkadiusz.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/user/sources")
public class UserKnowSourcesServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("sFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Source> sources = userDao.userSources(user);
        req.setAttribute("sources", sources);
        req.getRequestDispatcher("/WEB-INF/views/sources.jsp").forward(req, resp);
    }
}
