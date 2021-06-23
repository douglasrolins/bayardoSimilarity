package bayardo;

import java.util.*;

public class AllPairs0 {

    public static List<Resultado> allPairs0(List<Vetor> V, double T) {
        Map<String, List<VetorIndexado>> indexVector = new HashMap<>();
        List<Resultado> O = new ArrayList<>();
        List<VetorIndexado> I;
        VetorIndexado vetorIndexado;

        for (Vetor x : V) {
            O.addAll(findMatches0(x, indexVector, T));
            for (Feature feature : x.lista) {
                I = indexVector.get(feature.value);
                if (I == null) {
                    I = new LinkedList<>();
                }
                vetorIndexado = new VetorIndexado(x, feature.peso);
                I.add(vetorIndexado);
                indexVector.put(feature.value, I);
            }
        }
        return O;
    }

    public static List<Resultado> findMatches0(Vetor X, Map<String, List<VetorIndexado>> I, double T) {
        Map<Integer, Double> A = new HashMap<>();
        List<Resultado> M = new ArrayList<>();
        List<VetorIndexado> listaVetorIndexado;

        for (Feature feature : X.lista) {
            listaVetorIndexado = I.get(feature.value);
            if (listaVetorIndexado != null) {
                for (VetorIndexado vetorIndexado : listaVetorIndexado) {
                    double similarity = feature.peso * vetorIndexado.peso;
                    Double oldSimilarity = A.get(vetorIndexado.vetor.id);
                    if (oldSimilarity == null) {
                        oldSimilarity = 0.0;
                    }
                    A.put(vetorIndexado.vetor.id, similarity + oldSimilarity);
                }
            }
        }
        for (Map.Entry<Integer, Double> entry : A.entrySet()) {
            if (entry.getValue() >= T) {
                M.add(new Resultado(X.id, entry.getKey(), entry.getValue()));
            }
        }
        return M;
    }
}