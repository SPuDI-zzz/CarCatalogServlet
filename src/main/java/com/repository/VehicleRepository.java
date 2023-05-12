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
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setStatementFromVehicle(statement, vehicle);
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
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setStatementFromVehicle(statement, vehicle);
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

    private void setStatementFromVehicle(PreparedStatement statement, Vehicle vehicle) throws SQLException {
        statement.setString(1, vehicle.getMark());
        statement.setString(2, vehicle.getModel());
        statement.setString(3, vehicle.getType());
        statement.setInt(4, vehicle.getMileage());
        statement.setInt(5, vehicle.getPrice());
        statement.setLong(6, vehicle.getIdProfile());
    }

    private Vehicle getVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        return Vehicle.builder()
                .id(resultSet.getLong("id"))
                .mark(resultSet.getString("mark"))
                .model(resultSet.getString("model"))
                .type(resultSet.getString("type"))
                .mileage(resultSet.getInt("mileage"))
                .price(resultSet.getInt("price"))
                .idProfile(resultSet.getLong("id_profile"))
                .build();
    }
}
