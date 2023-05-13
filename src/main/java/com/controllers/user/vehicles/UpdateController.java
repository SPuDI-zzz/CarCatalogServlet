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

@WebServlet("/user/vehicles/edit")
public class UpdateController extends HttpServlet {
    private final VehicleService vehicleService = new VehicleService(new VehicleRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idVehicle = Long.parseLong(req.getParameter("idVehicle"));
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        if (vehicleService.canModifyVehicle(idProfile, idVehicle)) {
            req.setAttribute("vehicle", vehicleService.getVehicle(idVehicle));
            req.getRequestDispatcher("/WEB-INF/user/vehicles/edit.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/error/404.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = mapToVehicle(req);

        vehicleService.updateVehicle(vehicle);

        resp.sendRedirect(req.getContextPath() + "/user/vehicles");
    }

    private Vehicle mapToVehicle(HttpServletRequest req) {
        return Vehicle.builder()
                .id(Long.parseLong(req.getParameter("idVehicle")))
                .mark(req.getParameter("mark"))
                .model(req.getParameter("model"))
                .type(req.getParameter("type"))
                .mileage(Integer.parseInt(req.getParameter("mileage")))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }
}
