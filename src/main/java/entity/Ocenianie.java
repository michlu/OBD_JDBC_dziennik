package entity;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class Ocenianie {
    String rodzajOceny;
    int ido;
    int idp;
    int idn;
    int idu;


    public String getRodzajOceny() {
        return rodzajOceny;
    }

    public void setRodzajOceny(String rodzajOceny) {
        this.rodzajOceny = rodzajOceny;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getIdn() {
        return idn;
    }

    public void setIdn(int idn) {
        this.idn = idn;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    @Override
    public String toString() {
        return "Ocenianie{" +
                "rodzajOceny='" + rodzajOceny + '\'' +
                ", ido=" + ido +
                ", idp=" + idp +
                ", idn=" + idn +
                ", idu=" + idu +
                '}';
    }

}
