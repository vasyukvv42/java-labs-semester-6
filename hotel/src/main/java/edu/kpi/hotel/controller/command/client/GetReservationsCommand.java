package edu.kpi.hotel.controller.command.client;

import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.model.exception.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetReservationsCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var reservationService = getReservationService(req);

        try {
            var reservations = reservationService.getReservationsByUser(getCurrentUser(req));
            req.setAttribute("reservations", reservations);
        } catch (AccessDeniedException e) {
            resp.sendError(403);
            return;
        }

        req.getRequestDispatcher("/reservations.jsp").forward(req, resp);
    }
}
