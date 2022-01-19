package by.viraz84.myrentcar.servlet.filter;

import by.viraz84.myrentcar.model.Users;
import by.viraz84.myrentcar.model.enam.UserRole;
import by.viraz84.myrentcar.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter("/login")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");


        if(UserService.getInstance().userIsExist(login,password)){
            Users user = UserService.getInstance().login(login, password);
            UserRole role = user.getUserRole();

            req.getSession().setAttribute("user",user);
            req.getSession().setAttribute("userRole",role);
            forwardToPage(req, res, role);
        } else {
            forwardToPage(req, res, UserRole.UNKNOWN);
        }
    }

    private void forwardToPage(HttpServletRequest req, HttpServletResponse res, UserRole userRole)
            throws ServletException, IOException {
        if (userRole.equals(UserRole.ADMIN)) {
           req.getRequestDispatcher("/admin").forward(req,res);
        } else if (userRole.equals(UserRole.USER)) {
            req.getRequestDispatcher("/mainUser").forward(req,res);
        } else {
            req.setAttribute("not_found", true);
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
