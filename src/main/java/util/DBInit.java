package util;

import dao.NauczycielDAO;
import dao.OcenaDAO;
import dao.PrzedmiotDAO;
import dao.UczenDAO;
import entity.Nauczyciel;
import entity.Ocena;
import entity.Przedmiot;
import entity.Uczen;

import java.sql.*;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class DBInit {

    private static boolean initDate;

    private final static String[] createTabeles = {
            "CREATE TABLE przedmiot (idp integer not null, nazwa_przedmiotu varchar2(20) not null)",
            "CREATE TABLE nauczyciel (idn integer not null, nazwisko_nauczyciela varchar2(30) not null, imie_nauczyciela varchar2(20) not null)",
            "CREATE TABLE uczen (idu integer not null, nazwisko_ucznia varchar2(30) not null, imie_ucznia varchar2(20) not null)",
            "CREATE TABLE ocena (ido integer not null, wartosc_opisowa varchar2(20) not null, wartosc_numeryczna float not null)",
            "CREATE TABLE ocenianie (id integer not null, rodzaj_oceny char(1) not null, ido integer not null, idp integer not null, idn integer not null, idu integer not null)"
    };

    public static void initTables(){
        for (String create : createTabeles) {
            createTable(create);
        }
    }

    public static void createTable(String createTables){
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.createStatement();
            statement.execute(createTables);
            System.out.println("|  DBInit: " + createTables);

        } catch (SQLSyntaxErrorException e) {
            System.out.println("|  DBInit: Tabele o takich nazwach już istnieją");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(statement, conn);
        }
    }



    public static void initData(){
        UczenDAO uczenDao = new UczenDAO();
        NauczycielDAO nauczycielDAO = new NauczycielDAO();
        OcenaDAO ocenaDAO = new OcenaDAO();
        PrzedmiotDAO przedmiotDAO = new PrzedmiotDAO();
        System.out.println("|  DBInit: wczytywanie danych...");

        Uczen uczen1 = new Uczen(1, "Nowak", "Jan");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen1) ? "zapisano: "+uczen1.toString() : "nie zapisano: "+uczen1.toString()));

        Uczen uczen2 = new Uczen(2, "Marzok", "Paula");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen2) ? "zapisano: "+uczen2.toString() : "nie zapisano: "+uczen2.toString()));

        Uczen uczen3 = new Uczen(3, "Kowalski", "Kamil");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen3) ? "zapisano: "+uczen3.toString() : "nie zapisano: "+uczen3.toString()));

        Uczen uczen4 = new Uczen(4, "Dzięcioł", "Szymek");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen4) ? "zapisano: "+uczen4.toString() : "nie zapisano: "+uczen4.toString()));

        Uczen uczen5 = new Uczen(5, "Majewski", "Tomek");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen5) ? "zapisano: "+uczen5.toString() : "nie zapisano: "+uczen5.toString()));

        Uczen uczen6 = new Uczen(6, "Ogłoza", "Kamila");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen6) ? "zapisano: "+uczen6.toString() : "nie zapisano: "+uczen6.toString()));

        Uczen uczen7 = new Uczen(7, "Trela", "Sebastian");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen7) ? "zapisano: "+uczen7.toString() : "nie zapisano: "+uczen7.toString()));

        Uczen uczen8 = new Uczen(8, "Lewandowski", "Jacek");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen8) ? "zapisano: "+uczen8.toString() : "nie zapisano: "+uczen8.toString()));

        Uczen uczen9 = new Uczen(9, "Sokół", "Anna");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen9) ? "zapisano: "+uczen9.toString() : "nie zapisano: "+uczen9.toString()));

        Uczen uczen10 = new Uczen(10, "Rzeszowski", "Marek");
        System.out.println("|  DBInit: " + (uczenDao.create(uczen10) ? "zapisano: "+uczen10.toString() : "nie zapisano: "+uczen10.toString()));


        Nauczyciel nauczyciel1 = new Nauczyciel(1,"Szymczak", "Ryszard");
        System.out.println("|  DBInit: " + (nauczycielDAO.create(nauczyciel1) ? "zapisano: "+nauczyciel1.toString() : "nie zapisano: "+nauczyciel1.toString()));

        Nauczyciel nauczyciel2 = new Nauczyciel(2,"Spadło", "Krystian");
        System.out.println("|  DBInit: " + (nauczycielDAO.create(nauczyciel2) ? "zapisano: "+nauczyciel2.toString() : "nie zapisano: "+nauczyciel2.toString()));

        Nauczyciel nauczyciel3 = new Nauczyciel(3,"Krajcarz", "Monika");
        System.out.println("|  DBInit: " + (nauczycielDAO.create(nauczyciel3) ? "zapisano: "+nauczyciel3.toString() : "nie zapisano: "+nauczyciel3.toString()));

        Nauczyciel nauczyciel4 = new Nauczyciel(4,"Wiśniewski", "Dominik");
        System.out.println("|  DBInit: " + (nauczycielDAO.create(nauczyciel4) ? "zapisano: "+nauczyciel4.toString() : "nie zapisano: "+nauczyciel4.toString()));


        Ocena ocena1 = new Ocena(1,"niedostateczny", 1);
        System.out.println("|  DBInit: " + (ocenaDAO.create(ocena1) ? "zapisano: "+ocena1.toString() : "nie zapisano: "+ocena1.toString()));

        Ocena ocena2 = new Ocena(2,"dopuszczający", 2);
        System.out.println("|  DBInit: " + (ocenaDAO.create(ocena2) ? "zapisano: "+ocena2.toString() : "nie zapisano: "+ocena2.toString()));

        Ocena ocena3 = new Ocena(3,"dostateczny", 3);
        System.out.println("|  DBInit: " + (ocenaDAO.create(ocena3) ? "zapisano: "+ocena3.toString() : "nie zapisano: "+ocena3.toString()));

        Ocena ocena4 = new Ocena(4,"dobry", 4);
        System.out.println("|  DBInit: " + (ocenaDAO.create(ocena4) ? "zapisano: "+ocena4.toString() : "nie zapisano: "+ocena4.toString()));

        Ocena ocena5 = new Ocena(5,"bardzo dobry", 5);
        System.out.println("|  DBInit: " + (ocenaDAO.create(ocena5) ? "zapisano: "+ocena5.toString() : "nie zapisano: "+ocena5.toString()));

        Ocena ocena6 = new Ocena(6,"celujący", 6);
        System.out.println("|  DBInit: " + (ocenaDAO.create(ocena6) ? "zapisano: "+ocena6.toString() : "nie zapisano: "+ocena6.toString()));


        Przedmiot przedmiot1 = new Przedmiot(1,"Polski");
        System.out.println("|  DBInit: " + (przedmiotDAO.create(przedmiot1) ? "zapisano: "+przedmiot1.toString() : "nie zapisano: "+przedmiot1.toString()));

        Przedmiot przedmiot2 = new Przedmiot(2,"Biologia");
        System.out.println("|  DBInit: " + (przedmiotDAO.create(przedmiot2) ? "zapisano: "+przedmiot2.toString() : "nie zapisano: "+przedmiot2.toString()));

        Przedmiot przedmiot3 = new Przedmiot(3,"Geografia");
        System.out.println("|  DBInit: " + (przedmiotDAO.create(przedmiot3) ? "zapisano: "+przedmiot3.toString() : "nie zapisano: "+przedmiot3.toString()));

        Przedmiot przedmiot4 = new Przedmiot(4,"Historia");
        System.out.println("|  DBInit: " + (przedmiotDAO.create(przedmiot4) ? "zapisano: "+przedmiot4.toString() : "nie zapisano: "+przedmiot4.toString()));

        initDate = true;
    }

    private static void releaseResources(Statement statement, Connection conn) {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInitDate() {
        return initDate;
    }
}
