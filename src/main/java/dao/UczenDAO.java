package dao;

import entity.Uczen;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class UczenDAO {

    private final static String CREATE = "INSERT INTO UCZEN (IDU, NAZWISKO_UCZNIA, IMIE_UCZNIA) SELECT ?, ?, ? FROM dual WHERE NOT EXISTS (SELECT 1 FROM UCZEN WHERE IDU=?)";
    private final static String READ = "SELECT idu, nazwisko_ucznia, imie_ucznia FROM uczen WHERE idu = ?";
    private final static String UPDATE = "UPDATE uczen SET idu=?, nazwisko_ucznia=?, imie_ucznia=? WHERE idu = ?";
    private final static String DELETE = "DELETE FROM uczen WHERE idu=?";

    public boolean create(Uczen uczen) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(CREATE);
            prepStmt.setInt(1, uczen.getIdu());
            prepStmt.setString(2, uczen.getNazwisko());
            prepStmt.setString(3, uczen.getImie());
            prepStmt.setInt(4, uczen.getIdu());
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

    public Uczen read(int idu) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        Uczen resultUczen = null;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(READ);
            prepStmt.setInt(1, idu);
            resultSet = prepStmt.executeQuery();
            if(resultSet.next()) {
                resultUczen = new Uczen();
                resultUczen.setIdu(resultSet.getInt("IDU"));
                resultUczen.setNazwisko(resultSet.getString("NAZWISKO_UCZNIA"));
                resultUczen.setImie(resultSet.getString("IMIE_UCZNIA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(prepStmt, resultSet, conn);
        }
        return resultUczen;
    }

    public boolean update(Uczen uczen) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(UPDATE);
            prepStmt.setInt(1, uczen.getIdu());
            prepStmt.setString(2, uczen.getNazwisko());
            prepStmt.setString(3, uczen.getImie());
            prepStmt.setInt(4, uczen.getIdu());
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

    public boolean delete(Uczen uczen) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        boolean result = false;
        try {
            conn = DBConnection.getConnection();
            prepStmt = conn.prepareStatement(DELETE);
            prepStmt.setInt(1, uczen.getIdu());
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
