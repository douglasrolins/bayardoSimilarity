package bayardo;

public class VetorIndexado {

    public Vetor vetor;

   // public double MaxWPrime;

    public double peso;

    public VetorIndexado(Vetor vetor, double peso) {
        this.vetor = vetor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "\nVetorIndexado :" + "Peso: [" + peso + "] " + vetor;
    }

}
