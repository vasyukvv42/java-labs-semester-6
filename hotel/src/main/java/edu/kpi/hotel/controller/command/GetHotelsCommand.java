package edu.kpi.hotel.controller.command;

import edu.kpi.hotel.model.exception.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetHotelsCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var hotelService = getHotelService(req);

        try {
            var hotels = hotelService.getHotels();
            req.setAttribute("hotels", hotels);
        } catch (AccessDeniedException e) {
            resp.sendError(403, "Access denied");
            return;
        }

        req.getRequestDispatcher("/hotels.jsp").forward(req, resp);
    }
}
