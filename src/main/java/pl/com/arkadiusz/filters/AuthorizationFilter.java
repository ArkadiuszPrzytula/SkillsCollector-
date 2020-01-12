package pl.com.arkadiusz.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String servletPath = req.getServletPath();
          Set<String> unprotectedPatches= (Set<String>) getServletContext().getAttribute("unprotectedPatches");
        if (unprotectedPatches.contains(servletPath)){
            chain.doFilter(req,res);
        }else {
            if (req.getSession().getAttribute("user")==null){
                res.sendRedirect("/login");
            }else {
                chain.doFilter(req,res);
            }
        }
    }
}
