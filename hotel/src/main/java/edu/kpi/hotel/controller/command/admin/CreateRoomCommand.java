package edu.kpi.hotel.controller.command.admin;

import dev.morphia.VerboseJSR303ConstraintViolationException;
import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.controller.command.GetHotelsCommand;
import edu.kpi.hotel.model.exception.AccessDeniedException;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class CreateRoomCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var hotelService = getHotelService(req);

        var hotelId = new ObjectId(req.getParameter("hotelId"));
        var roomNumber = Integer.valueOf(req.getParameter("number"));
        var cost = BigDecimal.valueOf(Double.valueOf(req.getParameter("cost")));
        var maxPeople = Integer.valueOf(req.getParameter("maxPeople"));

        try {
            var hotel = hotelService.getHotelById(hotelId).get();
            hotelService.createRoom(hotel, roomNumber, maxPeople, cost);
        } catch (AccessDeniedException e) {
            resp.sendError(403, "Access denied");
            return;
        } catch (VerboseJSR303ConstraintViolationException e) {
            req.setAttribute("errorMessage", e.getMessage());
        }

        var getHotelsCommand = new GetHotelsCommand();
        getHotelsCommand.execute(req, resp);    }
}
