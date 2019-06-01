package edu.kpi.hotel.controller.command.client;

import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.controller.command.GetHotelsCommand;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.NotEnoughBalanceException;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayInvoiceCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var reservationService = getReservationService(req);

        var reservationId = new ObjectId(req.getParameter("reservationId"));

        try {
            var reservation = reservationService.getReservationById(reservationId).get();
            reservationService.payInvoice(getCurrentUser(req), reservation);
        } catch (AccessDeniedException e) {
            resp.sendError(403);
            return;
        } catch (NullPointerException e) {
            resp.sendError(404);
            return;
        } catch (NotEnoughBalanceException | IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
        }

        var reservationsCommand = new GetReservationsCommand();
        reservationsCommand.execute(req, resp);
    }
}
