package bayardo;

public class VetorCandidato {

    public Vetor vetor;

    public double score;

    public VetorCandidato(Vetor vetor, double score) {
        this.vetor = vetor;
        this.score = score;
    }

    @Override
    public String toString() {
        return "\nVetorIndexado :" + "Score: [" + score + "] " + vetor;
    }

}