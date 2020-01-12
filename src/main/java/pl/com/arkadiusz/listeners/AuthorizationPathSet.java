package pl.com.arkadiusz.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebListener
public class AuthorizationPathSet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Set<String> unprotectedPatches = new HashSet<>();
        unprotectedPatches.add("/register");
        unprotectedPatches.add("/login");
        sce.getServletContext().setAttribute("unprotectedPatches", unprotectedPatches);

    }
}
