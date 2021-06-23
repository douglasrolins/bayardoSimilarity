package bayardo;

import java.util.*;

public class Dot {

    public static double dot(Vetor X, Map<String, List<VetorIndexado>> lista2, int id) {
        double dotProduct = 0.0;

        for (Map.Entry<String, List<VetorIndexado>> entry : lista2.entrySet()) {
            for (VetorIndexado aux : entry.getValue()) {
                if (id == aux.vetor.id) {
                    List<Feature> tempLista = aux.vetor.lista;
                    for (int i = 0; i < tempLista.size() && tempLista.get(i).peso != 0.0; i++) {
                        List<Feature> tempLista2 = X.lista;
                        for (Feature feature : tempLista2) {
                            if (feature.value.equals(tempLista.get(i).value)) {
                                dotProduct += tempLista.get(i).peso * feature.peso;
                                break;
                            }
                        }
                    }
                    return dotProduct;
                }
            }
        }
        return dotProduct;
    }

    // Melhorias no método dotPrime: Utiliza Sort Merge ou Hash, fazendo que a complexidade seja O(n) ao invés de O(n^2) como em dot
    @SuppressWarnings("unused")
    public static double dotPrime(Vetor x, Vetor y) {
        double dotProduct = 0.0;

        ////// Hash
        Map<String, Double> mapX = new HashMap<>(x.lista.size());
        for (Feature f : x.lista) {
            mapX.put(f.value, f.peso);
        }
        for (Feature f : y.lista) {
            if (f.peso != 0.0) {
                Double peso = mapX.get(f.value);
                if (peso != null) {
                    dotProduct += f.peso * peso;
                }
            }
        }
        //End Hash

		/* Sort Merge
		List<Feature> tempLista = Y.lista;
		int size = tempLista.size();

		for (int i = 0; i < size && tempLista.get(i).peso != 0.0; i++)
		{
			List<Feature> tempLista2 = X.lista;
			for (int j = 0; j < tempLista2.size(); j++)
			{
				if (tempLista2.get(j).value == tempLista.get(i).value)
				{
					dotProduct += tempLista.get(i).peso * tempLista2.get(j).peso;
					break;
				}
			}
		}
		End Sort Merge */

        return dotProduct;
    }


    // Classe DotPrime2 já realiza o cálculo da similaridade entre o vetor atual e os candidatos e já retorna o mapa de resultados
    public static List<Resultado> dotPrime2(Vetor x, Collection<VetorCandidato> candidatos, double t) {

        List<Resultado> m = new ArrayList<>();

        Map<String, Double> mapX = new HashMap<>(x.lista.size());
        for (Feature f : x.lista) {
            mapX.put(f.value, f.peso);
        }
        for (VetorCandidato vc : candidatos) {
            double dotProduct = vc.score;

            for (Feature f : vc.vetor.lista) {
                if (f.peso != 0.0) {
                    Double peso = mapX.get(f.value);
                    if (peso != null) {
                        dotProduct += f.peso * peso;
                    }
                }
            }
            if (dotProduct >= t) {
                m.add(new Resultado(x.id, vc.vetor.id, dotProduct));
            }
        }
        return m;
    }
}