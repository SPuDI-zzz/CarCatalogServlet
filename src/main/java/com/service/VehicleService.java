package com.service;

import com.dto.VehicleDTO;
import com.entity.Vehicle;
import com.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Collection<VehicleDTO> getVehicleList(Long idProfile) {
        Collection<Vehicle> vehicles = vehicleRepository.getByIdProfile(idProfile);
        Collection<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(mapToVehicleDTO(vehicle));
        }
        return vehicleDTOS;
    }

    public VehicleDTO getVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.getById(id);
        return mapToVehicleDTO(vehicle);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.insert(vehicle);
    }

    public void removeVehicle(Long id) {
        vehicleRepository.delete(id);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.update(vehicle);
    }

    public boolean canModifyVehicle(Long idProfile, Long idVehicle){
        Vehicle vehicle = vehicleRepository.getById(idVehicle);
        if (isVehicleExists(vehicle)){
            return isIdProfilesEquals(idProfile, vehicle);
        }
        return false;
    }

    private boolean isIdProfilesEquals(Long idProfile, Vehicle vehicle) {
        return Objects.equals(vehicle.getIdProfile(), idProfile);
    }

    private boolean isVehicleExists(Vehicle vehicle) {
        return vehicle != null;
    }

    private VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getMark(),
                vehicle.getModel(),
                vehicle.getType(),
                vehicle.getMileage(),
                vehicle.getPrice(),
                vehicle.getIdProfile()
        );
    }


}
