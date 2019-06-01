package edu.kpi.hotel.controller;

import edu.kpi.hotel.controller.command.*;
import edu.kpi.hotel.controller.command.admin.AcceptRoomRequest;
import edu.kpi.hotel.controller.command.admin.CreateHotelCommand;
import edu.kpi.hotel.controller.command.admin.CreateRoomCommand;
import edu.kpi.hotel.controller.command.client.CreateRoomRequestCommand;
import edu.kpi.hotel.controller.command.client.GetReservationsCommand;
import edu.kpi.hotel.controller.command.client.PayInvoiceCommand;
import edu.kpi.hotel.controller.command.client.RefillBalanceCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/app/*")
public class MainServlet extends HttpServlet {
    private Map<String, Command> getCommands = Map.ofEntries(
            Map.entry("/app/login", forward("/login.jsp")),
            Map.entry("/app/signup", forward("/signup.jsp")),
            Map.entry("/app/logout", new LogoutCommand()),
            Map.entry("/app/hotels", new GetHotelsCommand()),
            Map.entry("/app/requests", new GetRoomRequestsCommand()),
            Map.entry("/app/reservations", new GetReservationsCommand())
    );

    private Map<String, Command> postCommands = Map.ofEntries(
            Map.entry("/app/login", new LoginCommand()),
            Map.entry("/app/signup", new SignupCommand()),
            Map.entry("/app/logout", new LogoutCommand()),
            Map.entry("/app/refill-balance", new RefillBalanceCommand()),
            Map.entry("/app/hotels", new CreateHotelCommand()),
            Map.entry("/app/create-room", new CreateRoomCommand()),
            Map.entry("/app/create-room-request", new CreateRoomRequestCommand()),
            Map.entry("/app/accept-request", new AcceptRoomRequest()),
            Map.entry("/app/pay-invoice", new PayInvoiceCommand())
    );

    private Command defaultCommand = forward("/index.jsp");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var command = getCommand(req, getCommands);
        command.execute(req, resp);
    }

    private Command getCommand(HttpServletRequest req, Map<String, Command> commands) {
        var path = req.getRequestURI().substring(req.getContextPath().length());
        return commands.getOrDefault(path, defaultCommand);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var command = getCommand(req, postCommands);
        command.execute(req, resp);
    }

    private Command forward(String path) {
        return (req, resp) -> req.getRequestDispatcher(path).forward(req, resp);
    }

}
