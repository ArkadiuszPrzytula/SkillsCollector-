package pl.com.arkadiusz.mvc;

import org.hibernate.SessionFactory;
import pl.com.arkadiusz.model.User;
import pl.com.arkadiusz.model.dao.SourceDao;
import pl.com.arkadiusz.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sources/confirm")
public class SourceConfirmServlet extends HttpServlet {

    UserDao userDao;
    SourceDao sourceDao;

    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("sFactory"));
        sourceDao = new SourceDao((SessionFactory) getServletContext().getAttribute("sFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sourceId", req.getParameter("sourceId"));
        req.setAttribute("sourceName", req.getParameter("sourceName"));
        req.getRequestDispatcher("/WEB-INF/views/skillLearnConfirm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Long sourceId = Long.parseLong(req.getParameter("sourceId"));

        userDao.userLearnSource(user.getUserName(), sourceId);
        resp.sendRedirect("/user/sources");
    }
}
