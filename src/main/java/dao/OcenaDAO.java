package dao;

import entity.Ocena;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class OcenaDAO {

    private final static String CREATE = "INSERT INTO OCENA (IDO, WARTOSC_OPISOWA, WARTOSC_NUMERYCZNA) SELECT ?, ?, ? FROM dual WHERE NOT EXISTS (SELECT 1 FROM OCENA WHERE IDO=?)";
    private final static String READ = "SELECT IDO, WARTOSC_OPISOWA, WARTOSC_NUMERYCZNA FROM OCENA WHERE IDO = ?";
    private final static String UPDATE = "UPDATE OCENA SET IDO=?, WARTOSC_OPISOWA=?, WARTOSC_NUMERYCZNA=? WHERE IDO = ?";
    private final static String DELETE = "DELETE FROM OCENA WHERE IDO=?";

    public boolean create(Ocena ocena) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(CREATE);
            prepStmt.setInt(1, ocena.getIdo());
            prepStmt.setString(2, ocena.getWartosOpisowa());
            prepStmt.setFloat(3, ocena.getWartosNumeryczna());
            prepStmt.setInt(4, ocena.getIdo());
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

    public Ocena read(int ido) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        Ocena resultOcena = null;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(READ);
            prepStmt.setInt(1, ido);
            resultSet = prepStmt.executeQuery();
            if(resultSet.next()) {
                resultOcena = new Ocena();
                resultOcena.setIdo(resultSet.getInt("ido"));
                resultOcena.setWartosOpisowa(resultSet.getString("wartosc_opisowa"));
                resultOcena.setWartosNumeryczna(resultSet.getFloat("wartosc_numeryczna"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, resultSet, conn);
        }
        return resultOcena;
    }

    public boolean update(Ocena ocena) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(UPDATE);
            prepStmt.setInt(1, ocena.getIdo());
            prepStmt.setString(2, ocena.getWartosOpisowa());
            prepStmt.setFloat(3, ocena.getWartosNumeryczna());
            prepStmt.setInt(4, ocena.getIdo());
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

    public boolean delete(Ocena ocena) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(DELETE);
            prepStmt.setInt(1, ocena.getIdo());
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
