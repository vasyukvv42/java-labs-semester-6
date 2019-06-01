package edu.kpi.hotel.controller.command.admin;

import dev.morphia.VerboseJSR303ConstraintViolationException;
import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.controller.command.GetHotelsCommand;
import edu.kpi.hotel.model.exception.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateHotelCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var hotelService = getHotelService(req);

        var name = req.getParameter("name");
        var country = req.getParameter("country");
        var city = req.getParameter("city");
        var address = req.getParameter("address");
        var rating = Integer.valueOf(req.getParameter("rating"));

        try {
            hotelService.createHotel(name, country, city, address, rating);
        } catch (AccessDeniedException e) {
            resp.sendError(403, "Access denied");
            return;
        } catch (VerboseJSR303ConstraintViolationException e) {
            req.setAttribute("errorMessage", e.getMessage());
        }

        var getHotelsCommand = new GetHotelsCommand();
        getHotelsCommand.execute(req, resp);
    }
}
