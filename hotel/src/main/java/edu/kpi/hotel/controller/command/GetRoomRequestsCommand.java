package edu.kpi.hotel.controller.command;

import edu.kpi.hotel.controller.command.AbstractCommand;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.exception.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRoomRequestsCommand extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var roomRequestService = getRoomRequestService(req);
        var hotelService = getHotelService(req);

        try {
            var user = getCurrentUser(req);
            List<RoomRequest> requests;
            var suitableRooms = new HashMap<RoomRequest, Map<Integer, Room>>();

            if (user.isAdministrator()) {
                requests = roomRequestService.getPendingRoomRequests();

                for (RoomRequest roomRequest : requests) {
                    suitableRooms.put(roomRequest, hotelService.getSuitableRooms(roomRequest));
                }
            }
            else
                requests = roomRequestService.getRoomRequestsByUser(user);

            req.setAttribute("roomRequests", requests);
            req.setAttribute("suitableRooms", suitableRooms);
        } catch (AccessDeniedException|NullPointerException e) {
            resp.sendError(403);
            return;
        }

        req.getRequestDispatcher("/requests.jsp").forward(req, resp);
    }
}
