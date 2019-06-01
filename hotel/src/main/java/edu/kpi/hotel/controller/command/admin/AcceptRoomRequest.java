package edu.kpi.hotel.controller.command.admin;

import dev.morphia.VerboseJSR303ConstraintViolationException;
import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import edu.kpi.hotel.model.exception.ReservationConflictException;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

public class AcceptRoomRequest extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var roomRequestService = getRoomRequestService(req);
        var reservationService = getReservationService(req);

        var roomRequestId = new ObjectId(req.getParameter("roomRequestId"));
        var roomNumber = Integer.valueOf(req.getParameter("roomNumber"));

        try {
            var roomRequest = roomRequestService.getRoomRequestById(roomRequestId).get();
            reservationService.createReservation(
                    roomRequest.getUser(),
                    roomRequest.getHotel(),
                    roomNumber,
                    roomRequest.getReserveFrom(),
                    roomRequest.getReserveTo()
            );
            roomRequestService.acceptRoomRequest(roomRequest);
        } catch (AccessDeniedException | ReservationConflictException e) {
            resp.sendError(403);
            return;
        } catch (NoSuchElementException e) {
            resp.sendError(404);
            return;
        } catch (NullPointerException | VerboseJSR303ConstraintViolationException e) {
            resp.sendError(500);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/app/requests");
    }
}
