package edu.kpi.hotel.controller.command.client;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateRoomRequestCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var roomRequestService = getRoomRequestService(req);
        var hotelService = getHotelService(req);

        var hotelId = new ObjectId(req.getParameter("hotelId"));
        var maxCost = BigDecimal.valueOf(Double.valueOf(req.getParameter("maxCost")));
        var people = Integer.valueOf(req.getParameter("people"));

        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date reserveFrom;
        Date reserveTo;
        try {
            reserveFrom = dateFormat.parse(req.getParameter("from"));
            reserveTo = dateFormat.parse(req.getParameter("to"));
        } catch (ParseException e) {
            resp.sendError(400);
            return;
        }

        try {
            var hotel = hotelService.getHotelById(hotelId).get();
            roomRequestService.createRoomRequest(getCurrentUser(req), hotel, people, maxCost, reserveFrom, reserveTo);
        } catch (AccessDeniedException e) {
            resp.sendError(403);
            return;
        } catch (VerboseJSR303ConstraintViolationException | IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
        }

        var getHotelsCommand = new GetHotelsCommand();
        getHotelsCommand.execute(req, resp);
    }
}
