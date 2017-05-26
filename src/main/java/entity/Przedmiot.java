package entity;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class Przedmiot {
    int idp;
    String nazwa;

    public Przedmiot() {
    }

    public Przedmiot(int idp, String nazwa) {
        this.idp = idp;
        this.nazwa = nazwa;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Przedmiot{" +
                "idp=" + idp +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
