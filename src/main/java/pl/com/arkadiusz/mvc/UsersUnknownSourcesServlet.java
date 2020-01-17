package pl.com.arkadiusz.mvc;

import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.Source;
import pl.com.arkadiusz.model.User;
import pl.com.arkadiusz.model.dao.SourceDao;
import pl.com.arkadiusz.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns =  "/user/unknown-sources")
public class UsersUnknownSourcesServlet extends HttpServlet {
    UserDao userDao;
    SourceDao sourceDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("sFactory"));
        sourceDao = new SourceDao((SessionFactory) getServletContext().getAttribute("sFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Source> sources = userDao.userSources(user);
        List<Source> allSources = sourceDao.allSources();
        allSources.removeIf(sources::contains);


        req.setAttribute("sources", allSources);
        req.getRequestDispatcher("/WEB-INF/views/unknownSources.jsp").forward(req, resp);
    }


}
