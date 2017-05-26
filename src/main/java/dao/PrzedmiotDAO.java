package dao;

import entity.Przedmiot;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author michlu
 * @sience 15.05.2017
 */
public class PrzedmiotDAO {

    private final static String CREATE = "INSERT INTO PRZEDMIOT (IDP, NAZWA_PRZEDMIOTU) SELECT ?, ? FROM dual WHERE NOT EXISTS (SELECT 1 FROM PRZEDMIOT WHERE IDP=?)";
    private final static String READ = "SELECT IDP, NAZWA_PRZEDMIOTU FROM PRZEDMIOT WHERE IDP = ?";
    private final static String UPDATE = "UPDATE PRZEDMIOT SET IDP=?, NAZWA_PRZEDMIOTU=? WHERE IDP = ?";
    private final static String DELETE = "DELETE FROM PRZEDMIOT WHERE IDP=?";

    public boolean create(Przedmiot przedmiot) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(CREATE);
            prepStmt.setInt(1, przedmiot.getIdp());
            prepStmt.setString(2, przedmiot.getNazwa());
            prepStmt.setInt(3, przedmiot.getIdp());
            int rowsAffected = prepStmt.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, null, conn);
        }
        return result;
    }

    public Przedmiot read(int idp) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        Przedmiot resultPrzedmiot = null;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(READ);
            prepStmt.setInt(1, idp);
            resultSet = prepStmt.executeQuery();
            if(resultSet.next()) {
                resultPrzedmiot = new Przedmiot();
                resultPrzedmiot.setIdp(resultSet.getInt("idp"));
                resultPrzedmiot.setNazwa(resultSet.getString("nazwa_przedmiotu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, resultSet, conn);
        }
        return resultPrzedmiot;
    }

    public boolean update(Przedmiot przedmiot) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(UPDATE);
            prepStmt.setInt(1, przedmiot.getIdp());
            prepStmt.setString(2, przedmiot.getNazwa());
            prepStmt.setInt(4, przedmiot.getIdp());
            int rowsAffected = prepStmt.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, null, conn);
        }
        return result;
    }

    public boolean delete(Przedmiot przedmiot) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(DELETE);
            prepStmt.setInt(1, przedmiot.getIdp());
            int rowsAffected = prepStmt.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, null, conn);
        }
        return result;
    }

    private void releaseResources(PreparedStatement prepStmt, ResultSet res, Connection conn) {
        try {
            if (prepStmt != null && !prepStmt.isClosed()) {
                prepStmt.close();
            }
            if (res != null && !res.isClosed()) {
                res.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
