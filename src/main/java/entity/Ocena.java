package entity;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
public class Ocena {
    int ido;
    String wartosOpisowa;
    float wartosNumeryczna;

    public Ocena() {
    }

    public Ocena(int ido, String wartosOpisowa, float wartosNumeryczna) {
        this.ido = ido;
        this.wartosOpisowa = wartosOpisowa;
        this.wartosNumeryczna = wartosNumeryczna;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public String getWartosOpisowa() {
        return wartosOpisowa;
    }

    public void setWartosOpisowa(String wartosOpisowa) {
        this.wartosOpisowa = wartosOpisowa;
    }

    public float getWartosNumeryczna() {
        return wartosNumeryczna;
    }

    public void setWartosNumeryczna(float wartosNumeryczna) {
        this.wartosNumeryczna = wartosNumeryczna;
    }

    @Override
    public String toString() {
        return "Ocena{" +
                "ido=" + ido +
                ", wartosOpisowa='" + wartosOpisowa + '\'' +
                ", wartosNumeryczna=" + wartosNumeryczna +
                '}';
    }
}
