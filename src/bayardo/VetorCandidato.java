package bayardo;

public class VetorCandidato {

    public Vetor vetor;

    public double score;

    public int ysize;

    public double maxw;

    public VetorCandidato(Vetor vetor, double score) {
        this.vetor = vetor;
        this.score = score;
    }

    public VetorCandidato(Vetor vetor, double score, int ysize, double maxw) {
        this.vetor = vetor;
        this.score = score;
        this.ysize = ysize;
        this.maxw = maxw;
    }

    @Override
    public String toString() {
        return "\nVetorIndexado :" + "Score: [" + score + "] " + vetor;
    }

}