package bayardo;

import java.util.*;

public class AllPairs1 {

    public static List<Resultado> allPairs1(List<Vetor> V, double T) {

        Map<String, List<VetorIndexado>> indexVector = new HashMap<>();
        List<Resultado> O = new ArrayList<>();
        List<VetorIndexado> I;
        VetorIndexado vetorIndexado;
        double b;
        for (Vetor x : V) {
            // addAll
            O.addAll(findMatches1(x, indexVector, T));
            b = 0.0;
            // idx = 0;
            for (Feature feature : x.lista) {
                b = b + feature.peso * feature.maxW;

                if (b >= T) {
                    I = indexVector.get(feature.value);
                    if (I == null) {
                        I = new LinkedList<>();
                    }
                    vetorIndexado = new VetorIndexado(x, feature.peso);
                    I.add(vetorIndexado);
                    indexVector.put(feature.value, I);
                    feature.peso = 0.0;
                }
            }
        }
        return O;
    }

    public static List<Resultado> findMatches1(Vetor X, Map<String, List<VetorIndexado>> I, double T) {

        Map<Integer, Double> A = new HashMap<>();
        List<Resultado> M = new ArrayList<>();
        List<VetorIndexado> listaVetorIndexado;
        double s;

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
            s = entry.getValue() + Dot.dot(X, I, entry.getKey());
            if (s >= T) {
                M.add(new Resultado(X.id, entry.getKey(), s));
            }
        }
        return M;
    }

    // Melhorias no AllPairs1: Classe DotPrime2 já realiza o cálculo da similaridade entre o vetor atual e os candidatos
    public static List<Resultado> allPairs1Prime(List<Vetor> V, double T) {

        Map<String, List<VetorIndexado>> indexVector = new HashMap<>();
        List<Resultado> O = new ArrayList<>();
        List<VetorIndexado> I;
        VetorIndexado vetorIndexado;
        double b;
        for (Vetor x : V) {
            O.addAll(findMatches1Prime(x, indexVector, T));
            b = 0.0;
            for (Feature feature : x.lista) {
                b = b + feature.peso * feature.maxW;

                if (b >= T) {
                    I = indexVector.get(feature.value);
                    if (I == null) {
                        I = new LinkedList<>();
                    }
                    vetorIndexado = new VetorIndexado(x, feature.peso);
                    I.add(vetorIndexado);
                    indexVector.put(feature.value, I);
                    feature.peso = 0.0;
                }

            }
        }
        return O;
    }

    public static List<Resultado> findMatches1Prime(Vetor X, Map<String, List<VetorIndexado>> I, double T) {

        Map<Integer, VetorCandidato> A = new HashMap<>();
        List<VetorIndexado> listaVetorIndexado;
        VetorCandidato vc;

        for (Feature feature : X.lista) {
            listaVetorIndexado = I.get(feature.value);
            if (listaVetorIndexado != null) {
                for (VetorIndexado vetorIndexado : listaVetorIndexado) {
                    double similarity = feature.peso * vetorIndexado.peso;
                    vc = A.get(vetorIndexado.vetor.id);

                    if (vc == null) {
                        vc = new VetorCandidato(vetorIndexado.vetor, similarity);
                        A.put(vetorIndexado.vetor.id, vc);
                    } else {
                        vc.score += similarity;
                    }
                }
            }
        }
        return Dot.dotPrime2(X, A.values(), T);
    }
}