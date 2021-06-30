package bayardo;

public class VetorIndexado {

    public Vetor vetor;

    public double maxw;

    int size;

    public double peso;

    public VetorIndexado(Vetor vetor, double peso) {
        this.vetor = vetor;
        this.peso = peso;
    }

    public VetorIndexado(Vetor vetor, double peso, int size, double maxw) {
        this.vetor = vetor;
        this.peso = peso;
        this.size = size;
        this.maxw = maxw;
    }

    @Override
    public String toString() {
        return "\nVetorIndexado: [Peso:" + peso + " Maxw:" + maxw + " Size:" + size + "]" + vetor;
    }

}