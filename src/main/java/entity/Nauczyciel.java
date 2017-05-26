package entity;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class Nauczyciel {
    int idn;
    String nazwisko;
    String imie;

    public Nauczyciel() {
    }

    public Nauczyciel(int idn, String nazwisko, String imie) {
        this.idn = idn;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }

    public int getIdn() {
        return idn;
    }

    public void setIdn(int idn) {
        this.idn = idn;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public String toString() {
        return "Nauczyciel{" +
                "idn=" + idn +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                '}';
    }
}
