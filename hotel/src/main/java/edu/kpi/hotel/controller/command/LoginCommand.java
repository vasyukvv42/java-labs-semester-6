package edu.kpi.hotel.controller.command;

import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.SignInFailedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var clientService = getClientService(req);

        var usernameOrEmail = req.getParameter("usernameOrEmail");
        var password = req.getParameter("password");

        User user;

        try {
            user = clientService.login(usernameOrEmail, password);
        } catch (SignInFailedException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        } catch (AccessDeniedException e) {
            resp.sendError(403, "Access denied");
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath());
    }
}
