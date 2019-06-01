package edu.kpi.hotel.controller.command.client;

import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.model.exception.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class RefillBalanceCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var clientService = getClientService(req);

        var amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        try {
            clientService.refillBalance(getCurrentUser(req), amount);
        } catch (AccessDeniedException e) {
            resp.sendError(403);
            return;
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
