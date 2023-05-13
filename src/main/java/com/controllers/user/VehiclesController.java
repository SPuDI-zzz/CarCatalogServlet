package com.controllers.user;

import com.repository.VehicleRepository;
import com.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/vehicles")
public class VehiclesController extends HttpServlet {
    private final VehicleService vehicleService = new VehicleService(new VehicleRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        req.setAttribute("vehicleList", vehicleService.getVehicleList(idProfile));

        req.getRequestDispatcher("/WEB-INF/user/vehicles.jsp").forward(req, resp);
    }
}
