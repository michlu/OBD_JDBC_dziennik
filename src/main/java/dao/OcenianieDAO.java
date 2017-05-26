package dao;

import entity.Ocenianie;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class OcenianieDAO {
    private final static String CREATE = "INSERT INTO OCENIANIE (RODZAJ_OCENY, IDO, IDP, IDN, IDU) VALUES (?, ?, ?, ?, ?)";


    public boolean create(Ocenianie ocenianie) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(CREATE);
            prepStmt.setString(1, ocenianie.getRodzajOceny());
            prepStmt.setInt(2, ocenianie.getIdo());
            prepStmt.setInt(3, ocenianie.getIdp());
            prepStmt.setInt(4, ocenianie.getIdn());
            prepStmt.setInt(5, ocenianie.getIdu());
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
