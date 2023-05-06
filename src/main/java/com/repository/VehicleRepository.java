package com.repository;

import com.entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private final String INSERT_QUERY = "INSERT INTO vehicle (mark, model, type, mileage, price, id_profile) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private final String SELECT_BY_ID_QUERY = "SELECT id, mark, model, type, mileage, price, id_profile " +
            "FROM vehicle WHERE id = ?;";
    private final String SELECT_BY_ID_PROFILE_QUERY = "SELECT id, mark, model, type, mileage, price, id_profile " +
            "FROM vehicle WHERE id_profile = ?;";
    private final String UPDATE_QUERY = "UPDATE vehicle " +
            "SET mark = ?, model = ?, type = ?, mileage = ?, price = ?, id_profile = ? WHERE id = ?";
    private final String DELETE_QUERY = "DELETE FROM vehicle WHERE id = ?;";

    public void insert(Vehicle vehicle) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = getStatementFromVehicle(vehicle, connection, INSERT_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicle getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getVehicleFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> getByIdProfile(long idProfile) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            List<Vehicle> vehicles = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_PROFILE_QUERY);
            statement.setLong(1, idProfile);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(getVehicleFromResultSet(resultSet));
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vehicle vehicle) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = getStatementFromVehicle(vehicle, connection, UPDATE_QUERY);
            statement.setLong(7, vehicle.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement getStatementFromVehicle(Vehicle vehicle, Connection connection, String stringQuery) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(stringQuery);
        statement.setString(1, vehicle.getMark());
        statement.setString(2, vehicle.getModel());
        statement.setString(3, vehicle.getType());
        statement.setInt(4, vehicle.getMileage());
        statement.setInt(5, vehicle.getPrice());
        statement.setLong(6, vehicle.getIdProfile());
        return statement;
    }

    private static Vehicle getVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getLong("id"));
        vehicle.setMark(resultSet.getString("mark"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setType(resultSet.getString("type"));
        vehicle.setMileage(resultSet.getInt("mileage"));
        vehicle.setPrice(resultSet.getInt("price"));
        vehicle.setIdProfile(resultSet.getLong("id_profile"));
        return vehicle;
    }
}
