package awk.leihvorgang.persistence.impl;

import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;
import awk.leihvorgang.persistence.IAusleihvorgangDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AusleihvorgangDAO implements IAusleihvorgangDAO {

    private Connection connection;

    //SQL-Statements

    private static final String SELECT_ALL_AUSLEIHVORGAENGE = "SELECT * FROM ausleihvorgang";

    private static final String SELECT_AUSLEIHVORGANG_BY_ID = "SELECT * FROM ausleihvorgang WHERE ausleihvorgangId = ?";

    private static final String INSERT_AUSLEIHVORGANG = "INSERT INTO ausleihvorgang (ausleihvorgangId, fahrzeugId, teilnehmerId, startdatum, enddatum, abgeschlossen, storniert, gefahreneKilometer, rechnungId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_AUSLEIHVORGANG = "UPDATE ausleihvorgang SET fahrzeugId = ?, teilnehmerId = ?, startdatum = ?, enddatum = ?, abgeschlossen = ?, storniert = ?, gefahreneKilometer = ?, rechnungId = ? WHERE ausleihvorgangId = ?";

    private static final String STORNIERE_AUSLEIHVORGANG = "UPDATE ausleihvorgang SET storniert = ? WHERE ausleihvorgangId = ?";

    private static final String ABSCHLIESSE_AUSLEIHVORGANG = "UPDATE ausleihvorgang SET abgeschlossen = ? AND SET gefahreneKilometer = ? WHERE ausleihvorgangId = ?";

    public AusleihvorgangDAO(Connection connection) {
        this.connection = connection;
    }

    public AusleihvorgangDAO() {

    }

    @Override
    public List<AusleihvorgangTO> getAllAusleihvorgaenge() {
        List<AusleihvorgangTO> ausleihvorgaengeTO = new ArrayList<AusleihvorgangTO>();

        try {
            connection = Persistence.getConnection();
            ResultSet resultSet = Persistence.executeQueryStatement(connection, SELECT_ALL_AUSLEIHVORGAENGE);

            while (resultSet.next()) {
                Long ausleihvorgangId = resultSet.getLong("ausleihvorgangId");
                Long fahrzeugId = resultSet.getLong("fahrzeugId");
                Long teilnehmerId = resultSet.getLong("teilnehmerId");
                LocalDateTime startdatum = resultSet.getTimestamp("startdatum").toLocalDateTime();
                LocalDateTime enddatum = resultSet.getTimestamp("enddatum").toLocalDateTime();
                String abgeschlossen = resultSet.getString("abgeschlossen");
                String storniert = resultSet.getString("storniert");
                int gefahreneKilometer = resultSet.getInt("gefahreneKilometer");
                Long rechnungId = resultSet.getLong("rechnungId");

                AusleihvorgangTO ausleihvorgangTO = new AusleihvorgangTO(ausleihvorgangId, fahrzeugId, teilnehmerId, startdatum, enddatum, abgeschlossen, storniert, gefahreneKilometer, rechnungId);
                ausleihvorgaengeTO.add(ausleihvorgangTO);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) Persistence.closeConnection(connection);
        }

        return ausleihvorgaengeTO;
    }

    @Override
    public Ausleihvorgang getAusleihvorgangById(Long id) {
        Ausleihvorgang ausleihvorgang = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Persistence.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_AUSLEIHVORGANG_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long ausleihvorgangId = resultSet.getLong("ausleihvorgangId");
                Long fahrzeugId = resultSet.getLong("fahrzeugId");
                Long teilnehmerId = resultSet.getLong("teilnehmerId");
                LocalDateTime startdatum = resultSet.getTimestamp("startdatum").toLocalDateTime();
                LocalDateTime enddatum = resultSet.getTimestamp("enddatum").toLocalDateTime();
                String abgeschlossen = resultSet.getString("abgeschlossen");
                String storniert = resultSet.getString("storniert");
                int gefahreneKilometer = resultSet.getInt("gefahreneKilometer");
                Long rechnungId = resultSet.getLong("rechnungId");

                ausleihvorgang = new Ausleihvorgang(ausleihvorgangId, fahrzeugId, teilnehmerId, startdatum, enddatum, abgeschlossen, storniert, gefahreneKilometer, rechnungId);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (connection != null) Persistence.closeConnection(connection);
        }

        return ausleihvorgang;
    }



    @Override
    public void saveAusleihvorgang(Ausleihvorgang ausleihvorgang) {
        try {
            connection = Persistence.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUSLEIHVORGANG);
            preparedStatement.setLong(1, ausleihvorgang.getAusleihvorgangId());
            preparedStatement.setLong(2, ausleihvorgang.getFahrzeugId());
            preparedStatement.setLong(3, ausleihvorgang.getTeilnehmerId());

            // Verwende Timestamp für SQL-taugliche Datumsangaben
            Timestamp startdatum = Timestamp.valueOf(ausleihvorgang.getStartdatum());
            Timestamp enddatum = Timestamp.valueOf(ausleihvorgang.getEnddatum());

            preparedStatement.setTimestamp(4, startdatum);
            preparedStatement.setTimestamp(5, enddatum);

            preparedStatement.setString(6, ausleihvorgang.getAbgeschlossen());
            preparedStatement.setString(7, ausleihvorgang.getStorniert());
            preparedStatement.setInt(8, ausleihvorgang.getGefahreneKilometer());
            preparedStatement.setLong(9, ausleihvorgang.getRechnungId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) Persistence.closeConnection(connection);
        }
    }




    @Override
    public void updateAusleihvorgang(Ausleihvorgang ausleihvorgang) {
        try {
            connection = Persistence.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUSLEIHVORGANG);
            preparedStatement.setLong(1, ausleihvorgang.getFahrzeugId());
            preparedStatement.setLong(2, ausleihvorgang.getTeilnehmerId());

            Timestamp startdatum = Timestamp.valueOf(ausleihvorgang.getStartdatum());
            Timestamp enddatum = Timestamp.valueOf(ausleihvorgang.getEnddatum());

            preparedStatement.setTimestamp(3, startdatum);
            preparedStatement.setTimestamp(4, enddatum);
            preparedStatement.setString(5, ausleihvorgang.getAbgeschlossen());
            preparedStatement.setString(6, ausleihvorgang.getStorniert());
            preparedStatement.setInt(7, ausleihvorgang.getGefahreneKilometer());
            preparedStatement.setLong(8, ausleihvorgang.getRechnungId());
            preparedStatement.setLong(9, ausleihvorgang.getAusleihvorgangId());


            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) Persistence.closeConnection(connection);
        }

    }

    @Override
    public void storniereAusleihvorgang(Ausleihvorgang ausleihvorgang) {
        try {
            connection = Persistence.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(STORNIERE_AUSLEIHVORGANG);
            preparedStatement.setString(1, ausleihvorgang.getStorniert());
            preparedStatement.setLong(2, ausleihvorgang.getAusleihvorgangId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) Persistence.closeConnection(connection);
        }

    }

    @Override
    public void abschliesseAusleihvorgang(Ausleihvorgang ausleihvorgang) {
        try {
            connection = Persistence.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ABSCHLIESSE_AUSLEIHVORGANG);
            preparedStatement.setString(1, ausleihvorgang.getAbgeschlossen());
            preparedStatement.setInt(2, ausleihvorgang.getGefahreneKilometer());
            preparedStatement.setLong(3, ausleihvorgang.getAusleihvorgangId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) Persistence.closeConnection(connection);
        }

    }


    @Override
    public Long maxAusleihvorgangsnummer() {
        connection = Persistence.getConnection();
        ResultSet resultSet = null;
        try {
            resultSet = Persistence.executeQueryStatement(connection, "SELECT MAX(ausleihvorgangId) as max FROM ausleihvorgang");

            if (resultSet.next()) {
                return resultSet.getLong("max");
            } else {
                return 0L;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Schließe die Verbindung ordnungsgemäß, wenn sie nicht mehr benötigt wird.
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Hier könntest du die SQLException behandeln oder weiterleiten, je nach Anwendungsfall.
                e.printStackTrace();
            }
        }
    }
}
