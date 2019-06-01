package edu.kpi.hotel.controller.command;

import com.mongodb.DuplicateKeyException;
import dev.morphia.VerboseJSR303ConstraintViolationException;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var clientService = getClientService(req);

        var username = req.getParameter("username");
        var email = req.getParameter("email");
        var password = req.getParameter("password");

        User user;

        try {
            user = clientService.createAccount(username, email, password);
        } catch (AccessDeniedException e) {
            resp.sendError(403, "Access denied");
            return;
        } catch (NullPointerException | VerboseJSR303ConstraintViolationException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        } catch (DuplicateKeyException e) {
            req.setAttribute("errorMessage", "Username or email already in use");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath());
    }

}
