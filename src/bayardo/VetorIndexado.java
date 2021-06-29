package bayardo;

public class VetorIndexado {

    public Vetor vetor;

    public double MaxWPrime;

    int sizePrime;

    public double peso;

    public VetorIndexado(Vetor vetor, double peso) {
        this.vetor = vetor;
        this.peso = peso;
    }

    public VetorIndexado(Vetor vetor, double peso, int sizePrime, double MaxWPrime) {
        this.vetor = vetor;
        this.peso = peso;
        this.sizePrime = sizePrime;
        this.MaxWPrime = MaxWPrime;
    }

    @Override
    public String toString() {
        return "\nVetorIndexado :" + "Peso: [" + peso + "] " + vetor;
    }

}