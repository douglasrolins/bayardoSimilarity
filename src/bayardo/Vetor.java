package bayardo;

import java.util.List;

public class Vetor implements Comparable<Vetor> {

    public List<Feature> lista;

    public int id;

    public double maxw;

    public Vetor(int id, List<Feature> lista, double maxw) {
        this.id = id;
        this.lista = lista;
        this.maxw = maxw;
    }

    @Override
    public String toString() {
        return "Vetor : " + "ID : [" + id + "] MaxW: [" + maxw + "] >:" + lista + "]\n";
    }

    @Override
    public int compareTo(Vetor aux) {
        return this.maxw > aux.maxw ? -1 : maxw == aux.maxw ? 0 : 1;
    }

}