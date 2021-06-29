package bayardo;

import java.util.*;

public class AllPairs2 {

    public static List<Resultado> allPairs2(List<Vetor> V, double T) {
        Map<String, List<VetorIndexado>> indexVector = new HashMap<>();
        List<Resultado> O = new ArrayList<>();
        List<VetorIndexado> I;
        VetorIndexado vetorIndexado;
        double b;
        for (Vetor x : V) {
            O.addAll(findMatches2(x, indexVector, T));
            b = 0.0;
            int ysize = 0;
            double ymaxw = 0;
            for (Feature feature : x.lista) {
                b = b + Math.min(feature.maxW, x.maxw) * feature.peso; // New

                if (b >= T) {
                    I = indexVector.get(feature.value);
                    if (I == null) {
                        I = new LinkedList<>();
                    }

                    //vetorIndexado = new VetorIndexado(x, feature.peso);
                    vetorIndexado = new VetorIndexado(x, feature.peso,ysize,ymaxw);
                    I.add(vetorIndexado);
                    indexVector.put(feature.value, I);
                    feature.peso = 0.0;
                }
                else {
                    ysize++;
                    ymaxw = feature.peso;
                }

            }
        }
        return O;
    }

    public static List<Resultado> findMatches2(Vetor X, Map<String, List<VetorIndexado>> I, double T) {

        Map<Integer, VetorCandidato> A = new HashMap<>();
        List<VetorIndexado> listaVetorIndexado;
        VetorCandidato vc;
        VetorIndexado vetorIndexado;

        // Remscore - Line 3
        double remscore = 0.0;
        for (Feature feature : X.lista) {
            remscore = (feature.peso * feature.maxW);
        }
        // End Remscore

        // Minsize - Line 4
        int minsize = (int) Math.floor(T / X.maxw);


        //Collections.sort(X.lista, Collections.reverseOrder());

        for (Feature feature : X.lista) {

            listaVetorIndexado = I.get(feature.value);
            if (listaVetorIndexado != null) {

                //Remove y,w from the I while |y| < minsize - Line 6
                Iterator<VetorIndexado> v = listaVetorIndexado.iterator();
                while (v.hasNext()) {
                    vetorIndexado = v.next();
                    if (vetorIndexado.vetor.lista.size() < minsize) {
                        v.remove();
                    }
                }
                //End Remove Iterator

                for (VetorIndexado vi : listaVetorIndexado) {

                    if (vi.peso != 0.0 || remscore >= T) { // Line 8
                        double similarity = feature.peso * vi.peso;
                        vc = A.get(vi.vetor.id);

                        if (vc == null) {
                            vc = new VetorCandidato(vi.vetor, similarity,vi.sizePrime,vi.MaxWPrime);

                         //   if (vi.peso + (Math.min(vi.vetor.lista.size(), X.lista.size()) * X.maxw * vi.vetor.maxw) >= T) { // Line 12
                                A.put(vi.vetor.id, vc);
                          //  }
                        } else {
                            vc.score += similarity;
                        }
                    }
                }
            }
            remscore = remscore - feature.peso * feature.maxW; // Line 10
        }
        return Dot.dotPrime2(X, A.values(), T);
    }
}