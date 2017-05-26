package controller;

import dao.*;
import entity.*;
import util.DBInit;

import java.util.Scanner;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class Controller {
    Scanner scn = new Scanner(System.in);

    UczenDAO uczenDAO = new UczenDAO();
    NauczycielDAO nauczycielDAO = new NauczycielDAO();
    OcenaDAO ocenaDAO = new OcenaDAO();
    PrzedmiotDAO przedmiotDAO = new PrzedmiotDAO();
    OcenianieDAO ocenianieDAO = new OcenianieDAO();


    public void start(){
        boolean newloop = true;
        while(true){
            if(newloop){
                System.out.println("+------------------------------------------------------------------------------------");
                System.out.println("|  Dostępne komendy: ");
                System.out.println("|  init - ładuje podstawowe dane do bazy");
                System.out.println("|  quit - kończy program");
                System.out.println("|  add  - dodaje ocene");
                System.out.println("+------------------------------------------------------------------------------------");
                newloop = false;
            }

            String inputText = scn.nextLine();


            if(inputText.equals("init")){
                if(!DBInit.isInitDate()){
                    DBInit.initData();
                }
                else{
                    System.out.println("|  Controller: DBInit - dane zostały już załadowane");
                }
            }

            if(inputText.equals("quit")){
                System.out.println("|  Controller: KONIEC PROGRAMU");
                break;
            }

            if(inputText.equals("add")){
                System.out.println("|  Controller: DODAJE OCENE!");
                Ocenianie ocenianie = new Ocenianie();

                ocenianie.setRodzajOceny(inputRodzajOceny());
                ocenianie.setIdo(inputIdo());
                ocenianie.setIdp(inputIdp());
                ocenianie.setIdn(inputIdn());
                ocenianie.setIdu(inputIdu());

                ocenianieDAO.create(ocenianie);
                System.out.println("+------------------------------------------------------------------------------------");
                System.out.println("|");
                System.out.println("|  DODANO: " + ocenianie.toString());
                System.out.println("|");
                System.out.println("|  Rodzaj oceny: " + (ocenianie.getRodzajOceny().equals("C") ? "cząstkowa" : "semestralna"));
                System.out.println("|  Ocena: " + ocenaDAO.read(ocenianie.getIdo()).getWartosOpisowa());
                System.out.println("|  Przedmiot: " + przedmiotDAO.read(ocenianie.getIdp()).getNazwa());
                Nauczyciel nauczyciel = nauczycielDAO.read(ocenianie.getIdn());
                System.out.println("|  Nauczyciel: " + nauczyciel.getNazwisko() + " " + nauczyciel.getImie());
                Uczen uczen = uczenDAO.read(ocenianie.getIdu());
                System.out.println("|  Uczeń: " + uczen.getNazwisko() + " " + uczen.getImie());
                System.out.println("+------------------------------------------------------------------------------------");
                continue;
            }

            newloop = true;
        }
    }


    public String inputRodzajOceny(){
        System.out.println("+------------------------------------------------------------------------------------");
        String result;
        while(true){
            System.out.println("|  Controller: PODAJ RODZAJ OCENY [ \"C\" - cząstkowa, \"S\" - semestralna ]");

            while (!scn.hasNext("[sScC]")) {
                System.out.println("|  Error: NIE POPRAWNE DANE - (wymagane: S albo C)");
                scn.next();
            }
            result = scn.next().toUpperCase();
            break;

        }
        System.out.println("|  Controller: RODZAJ OCENY: " + result);
        return result;
    }

    private int inputIdo() {
        System.out.println("+------------------------------------------------------------------------------------");
        int ido;
        Ocena ocena;

        while(true){
            System.out.println("|  Controller: PODAJ IDO [ identyfikator oceny ]");

            while (!scn.hasNextInt()) {
                System.out.println("|  Error: NIE POPRAWNE DANE - (wymagane dane liczbowe)");
                scn.next();
            }
            ido = scn.nextInt();

            try{
                ocena = ocenaDAO.read(ido);
                System.out.println("|  Controller: WYBRANO OCENE - " + ocena.toString());
                break;
            }
            catch (NullPointerException ex){
                System.out.println("|  Error: NIE POPRAWNE DANE - (nie ma w bazie oceny o takim ID)");
                continue;
            }
        }
        return ido;
    }

    private int inputIdu() {
        System.out.println("+------------------------------------------------------------------------------------");
        int idu;
        Uczen uczen;
        while(true){
            System.out.println("|  Controller: PODAJ IDU [ identyfikator ucznia ]");

            while (!scn.hasNextInt()) {
                System.out.println("|  Error: NIE POPRAWNE DANE - (wymagane dane liczbowe)");
                scn.next();
            }
            idu = scn.nextInt();

            try{
                uczen = uczenDAO.read(idu);
                System.out.println("|  Controller: WYBRANO UCZNIA - " + uczen.toString());
                break;
            }
            catch (NullPointerException ex){
                System.out.println("|  Error: NIE POPRAWNE DANE - (nie ma w bazie ucznia o takim ID)");
                continue;
            }
        }
        return idu;
    }

    private int inputIdn() {
        System.out.println("+------------------------------------------------------------------------------------");
        int idn;
        Nauczyciel nauczyciel;
        while(true){
            System.out.println("|  Controller: PODAJ IDN [ identyfikator nauczyciela ]");

            while (!scn.hasNextInt()) {
                System.out.println("|  Error: NIE POPRAWNE DANE - (wymagane dane liczbowe)");
                scn.next();
            }
            idn = scn.nextInt();

            try{
                nauczyciel = nauczycielDAO.read(idn);
                System.out.println("|  Controller: WYBRANO NAUCZYCIELA - " + nauczyciel.toString());
                break;
            }
            catch (NullPointerException ex){
                System.out.println("|  Error: NIE POPRAWNE DANE - (nie ma w bazie nauczyciela o takim ID)");
                continue;
            }
        }
        return idn;
    }

    private int inputIdp() {
        System.out.println("+------------------------------------------------------------------------------------");
        int idp;
        Przedmiot przedmiot;
        while(true){
            System.out.println("|  Controller: PODAJ IDP [ identyfikator przedmiotu ]");

            while (!scn.hasNextInt()) {
                System.out.println("|  Error: NIE POPRAWNE DANE - (wymagane dane liczbowe)");
                scn.next();
            }
            idp = scn.nextInt();

            try{
                przedmiot = przedmiotDAO.read(idp);
                System.out.println("|  Controller: WYBRANO PRZEDMIOT - " + przedmiot.toString());
                break;
            }
            catch (NullPointerException ex){
                System.out.println("|  Error: NIE POPRAWNE DANE - (nie ma w bazie przedmiotu o takim ID)");
                continue;
            }
        }
        return idp;
    }
}
