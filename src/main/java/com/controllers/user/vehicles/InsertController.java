package com.controllers.user.vehicles;

import com.entity.Vehicle;
import com.repository.VehicleRepository;
import com.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/vehicles/new")
public class InsertController extends HttpServlet {
    private final VehicleService vehicleService = new VehicleService(new VehicleRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/vehicles/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = mapToVehicle(req);

        vehicleService.addVehicle(vehicle);

        resp.sendRedirect(req.getContextPath() + "/user/vehicles");
    }

    private Vehicle mapToVehicle(HttpServletRequest req) {
        return Vehicle.builder()
                .mark(req.getParameter("mark"))
                .model(req.getParameter("model"))
                .type(req.getParameter("type"))
                .mileage(Integer.parseInt(req.getParameter("mileage")))
                .price(Integer.parseInt(req.getParameter("price")))
                .idProfile((Long) (req.getSession().getAttribute("idProfile")))
                .build();
    }
}
