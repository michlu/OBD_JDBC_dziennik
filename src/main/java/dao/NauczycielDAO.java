package dao;

import entity.Nauczyciel;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author michlu
 * @sience 15.05.2017
 */
public class NauczycielDAO {

    private final static String CREATE = "INSERT INTO NAUCZYCIEL (IDN, NAZWISKO_NAUCZYCIELA, IMIE_NAUCZYCIELA) SELECT ?, ?, ? FROM dual WHERE NOT EXISTS (SELECT 1 FROM NAUCZYCIEL WHERE IDN=?)";
    private final static String READ = "SELECT IDN, nazwisko_nauczyciela, imie_nauczyciela FROM NAUCZYCIEL WHERE idn = ?";
    private final static String UPDATE = "UPDATE NAUCZYCIEL SET idn=?, NAZWISKO_NAUCZYCIELA=?, IMIE_NAUCZYCIELA=? WHERE idn = ?";
    private final static String DELETE = "DELETE FROM NAUCZYCIEL WHERE idn=?";

    public boolean create(Nauczyciel nauczyciel) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(CREATE);
            prepStmt.setInt(1, nauczyciel.getIdn());
            prepStmt.setString(2, nauczyciel.getNazwisko());
            prepStmt.setString(3, nauczyciel.getImie());
            prepStmt.setInt(4, nauczyciel.getIdn());
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

    public Nauczyciel read(int idn) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        Nauczyciel resultNauczyciel = null;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(READ);
            prepStmt.setInt(1, idn);
            resultSet = prepStmt.executeQuery();
            if(resultSet.next()) {
                resultNauczyciel = new Nauczyciel();
                resultNauczyciel.setIdn(resultSet.getInt("idn"));
                resultNauczyciel.setNazwisko(resultSet.getString("nazwisko_nauczyciela"));
                resultNauczyciel.setImie(resultSet.getString("imie_nauczyciela"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, resultSet, conn);
        }
        return resultNauczyciel;
    }

    public boolean update(Nauczyciel nauczyciel) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(UPDATE);
            prepStmt.setInt(1, nauczyciel.getIdn());
            prepStmt.setString(2, nauczyciel.getNazwisko());
            prepStmt.setString(3, nauczyciel.getImie());
            prepStmt.setInt(4, nauczyciel.getIdn());
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

    public boolean delete(Nauczyciel nauczyciel) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(DELETE);
            prepStmt.setInt(1, nauczyciel.getIdn());
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
