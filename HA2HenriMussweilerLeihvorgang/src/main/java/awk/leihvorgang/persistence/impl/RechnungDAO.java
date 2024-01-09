package awk.leihvorgang.persistence.impl;

import awk.leihvorgang.entity.internal.Rechnung;
import awk.leihvorgang.persistence.IRechungDAO;

import java.sql.*;
import java.time.LocalDateTime;

public class RechnungDAO implements IRechungDAO {

    private Connection connection;

    //SQL-Statements
    private static final String SAVE_RECHNUNG = "INSERT INTO rechnung (rechnungid, teilnehmerid, rechnungsdatum, gesamtsumme) VALUES (?, ?, ?, ?)";
    @Override
    public void saveRechnung(Rechnung rechnung) {
        try {
            connection = Persistence.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RECHNUNG);
            preparedStatement.setLong(1, maxRechnungsnummer() + 1);
            preparedStatement.setLong(2, rechnung.getTeilnehmerId());
            Timestamp rechnungsdatum = Timestamp.valueOf(rechnung.getRechnungsdatum());
            preparedStatement.setTimestamp(3, rechnungsdatum);
            preparedStatement.setDouble(4, rechnung.getGesamtsumme());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) Persistence.closeConnection(connection);
        }
    }

    @Override
    public Long maxRechnungsnummer() {
        connection = Persistence.getConnection();
        ResultSet resultSet = null;
        try {
            resultSet = Persistence.executeQueryStatement(connection, "SELECT MAX(rechnungId) as max FROM rechnung");

            if (resultSet.next()) {
                return resultSet.getLong("max");
            } else {
                return 0L;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
