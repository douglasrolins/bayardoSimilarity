package bayardo;

import java.io.File;
import java.util.*;

public class AllPair {

    public static void main(String[] args) {

        List<Vetor> V = null;
        List<Resultado> listaResultado;

        // Set Threshold
        double t = 0.5;

        /// -------  Set dataset source ----------
        Scanner read = new Scanner(System.in);
        System.out.print("1 for Dataset inline or 2 for XML --> ");
        int source = read.nextInt();
        //int source = 2; // 1 for Dataset Inline or 2 for XML

        if (source == 1) {
            V = Dataset.DatasetInline();
        } else if (source == 2) {
            File file = new File("src/bayardo/dataset.xml");
            V = Dataset.readXMLtoListVector(file);

            // -- Print XML
           // Dataset.printXML(file);
        }

        // --------- End Set dataset source ----------


        ////////////////////
        ////////// ALLPAIRS0
        System.out.println("\t\tALL PAIRS 0\t\t");

        long startTime = System.nanoTime();
        listaResultado = AllPairs0.allPairs0(V, t);
        long totalTime = System.nanoTime() - startTime;

        System.out.println(listaResultado);
        System.out.println("Tempo de Execução AllPairs0: " + totalTime + " = " + totalTime / 1000000 + " ms");
        System.out.println("----");

        ////////////////////
        ////////// ALLPAIRS1

        // -- Load dataset
        if (source == 1) {
            V = Dataset.DatasetInline();
        } else if (source == 2) {
            File file = new File("src/bayardo/dataset.xml");
            V = Dataset.readXMLtoListVector(file);
        }
        // --------

        System.out.println("\t\tALL PAIRS 1\t\t");
        Collections.sort(V);

        startTime = System.nanoTime();
        listaResultado = AllPairs1.allPairs1(V, t);
        totalTime = System.nanoTime() - startTime;

        System.out.println(listaResultado);
        System.out.println("Tempo de Execução AllPairs1: " + totalTime + " = " + totalTime / 1000000 + " ms");
        System.out.println("----");

        ////////////////////
        ////////// ALLPAIRS1PRIME

        // -- Load dataset
        if (source == 1) {
            V = Dataset.DatasetInline();
        } else if (source == 2) {
            File file = new File("src/bayardo/dataset.xml");
            V = Dataset.readXMLtoListVector(file);
        }
        // --------

        System.out.println("\t\tALL PAIRS 1 PRIME\t\t");
        Collections.sort(V);

        startTime = System.nanoTime();
        listaResultado = AllPairs1.allPairs1Prime(V, t);
        totalTime = System.nanoTime() - startTime;

        System.out.println(listaResultado);
        System.out.println("Tempo de Execução AllPairs1Prime: " + totalTime + " = " + totalTime / 1000000 + " ms");
        System.out.println("----");


        ////////////////////
        ////////// ALLPAIRS2

        // -- Load dataset
        if (source == 1) {
            V = Dataset.DatasetInline();
        } else if (source == 2) {
            File file = new File("src/bayardo/dataset.xml");
            V = Dataset.readXMLtoListVector(file);
        }
        // --------

        System.out.println("\t\tALL PAIRS 2\t\t");
        V.sort(Collections.reverseOrder());

        startTime = System.nanoTime();
        listaResultado = AllPairs2.allPairs2(V, t);
        totalTime = System.nanoTime() - startTime;

        System.out.println(listaResultado);
        System.out.println("Tempo de Execução AllPairs2: " + totalTime + " = " + totalTime / 1000000 + " ms");
        System.out.println("----");

        // -- Print vector list
        //System.out.println(V);
    }
}