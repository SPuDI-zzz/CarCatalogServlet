package com.controllers.user.vehicles;

import com.repository.VehicleRepository;
import com.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/vehicles/remove")
public class DeleteController extends HttpServlet {
    private final VehicleService vehicleService = new VehicleService(new VehicleRepository());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idVehicle = Long.parseLong(req.getParameter("idVehicle"));
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        if (vehicleService.canModifyVehicle(idProfile, idVehicle)) {
            vehicleService.removeVehicle(idVehicle);
        }

        resp.sendRedirect(req.getContextPath() + "/user/vehicles");
    }
}
