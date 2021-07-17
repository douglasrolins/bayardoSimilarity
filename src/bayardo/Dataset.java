package bayardo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dataset {


    public static List<Vetor> readXMLtoListVector(File file) {

        List<Vetor> V = new ArrayList<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("vetor");

            int id;
            double maxwV;

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                id = Integer.parseInt(eElement.getAttribute("id"));
                maxwV = Double.parseDouble(eElement.getAttribute("maxw"));
                int qtdFeatures = eElement.getElementsByTagName("feature").getLength();

                List<Feature> f = new ArrayList<>();
                for (int j=0; j < qtdFeatures; j++) {

                    String value = eElement.getElementsByTagName("value").item(j).getTextContent();
                    Double peso = Double.valueOf(eElement.getElementsByTagName("peso").item(j).getTextContent());
                    Integer freq = Integer.valueOf(eElement.getElementsByTagName("freq").item(j).getTextContent());
                    double maxwF = Double.parseDouble(eElement.getElementsByTagName("maxW").item(j).getTextContent());

                    f.add(new Feature(value,peso,freq,maxwF));

                }
                Collections.sort(f);
                Vetor v = new Vetor(id,f,maxwV);
                V.add(v);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return V;
    }

    //Dataset Fig 1 - Article An Efficient Similarity Join Algorithm with Cosine Similarity Predicate, Dongjoo Lee
    public static List<Vetor> DatasetInline(){
        List<Feature> l1 = new ArrayList<>();
        l1.add(new Feature("A", 0.46,  3, 0.46));
        l1.add(new Feature("B", 0.31, 4, 0.55)); // 1
        l1.add(new Feature("E", 0.31, 4, 0.37)); // 2
        l1.add(new Feature("H", 0.31, 4, 0.61)); // 3
        l1.add(new Feature("J", 0.62, 3, 0.62));
        l1.add(new Feature("K", 0.31, 3, 0.5));
        l1.add(new Feature("O", 0.15, 3, 0.37));
        Collections.sort(l1);
        Vetor v1 = new Vetor(1, l1, 0.62);

        List<Feature> l2 = new ArrayList<>();
        l2.add(new Feature("A", 0.46, 3, 0.46));
        l2.add(new Feature("B", 0.31, 4, 0.55)); // 2
        l2.add(new Feature("D", 0.31, 3, 0.31));
        l2.add(new Feature("E", 0.31, 4, 0.37)); // 1
        l2.add(new Feature("H", 0.31, 4, 0.61)); // 3
        l2.add(new Feature("J", 0.62, 3, 0.62));
        l2.add(new Feature("O", 0.15, 3, 0.37));
        Collections.sort(l2);
        Vetor v2 = new Vetor(2, l2, 0.62);

        List<Feature> l3 = new ArrayList<>();
        l3.add(new Feature("B", 0.33, 4, 0.55));
        l3.add(new Feature("D", 0.17, 3, 0.31));
        l3.add(new Feature("F", 0.33, 2, 0.33));
        l3.add(new Feature("G", 0.33, 1, 0.33));
        l3.add(new Feature("I", 0.33, 2, 0.33));
        l3.add(new Feature("J", 0.50, 3, 0.62));
        l3.add(new Feature("K", 0.50, 3, 0.5));
        l3.add(new Feature("M", 0.17, 2, 0.18));
        Collections.sort(l3);
        Vetor v3 = new Vetor(3, l3, 0.50);

        List<Feature> l4 = new ArrayList<>();
        l4.add(new Feature("B", 0.55, 4, 0.55));
        l4.add(new Feature("C", 0.18, 2, 0.30));
        l4.add(new Feature("D", 0.18, 3, 0.31));
        l4.add(new Feature("E", 0.37, 4, 0.37));
        l4.add(new Feature("F", 0.18, 2, 0.33));
        l4.add(new Feature("H", 0.37, 4, 0.61));
        l4.add(new Feature("I", 0.18, 2, 0.33));
        l4.add(new Feature("K", 0.37, 3, 0.5));
        l4.add(new Feature("M", 0.18, 2, 0.18));
        l4.add(new Feature("O", 0.37, 3, 0.37));
        Collections.sort(l4);
        Vetor v4 = new Vetor(4, l4, 0.55);

        List<Feature> l5 = new ArrayList<>();
        l5.add(new Feature("A", 0.15, 3, 0.46));
        l5.add(new Feature("C", 0.30, 2, 0.30));
        l5.add(new Feature("E", 0.30, 4, 0.37));
        l5.add(new Feature("H", 0.61, 4, 0.61));
        l5.add(new Feature("L", 0.46, 1, 0.46));
        l5.add(new Feature("N", 0.46, 1, 0.46));
        Collections.sort(l5);
        Vetor v5 = new Vetor(5, l5, 0.61);

        List<Vetor> V = new ArrayList<>();
        V.add(v1);
        V.add(v2);
        V.add(v3);
        V.add(v4);
        V.add(v5);
        return V;
    }

    public static void printXML(File file) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("vetor");
            System.out.println("----------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                Element eElement = (Element) nNode;
                String id = eElement.getAttribute("id");
                String maxW = eElement.getAttribute("maxw");
                System.out.println("Vetor id: " + id);
                System.out.println("MaxW: " + maxW);
                System.out.println("FEATURES:");
                System.out.println("----------------------------");
                int qtdFeatures = eElement.getElementsByTagName("feature").getLength();
                for (int j=0; j<qtdFeatures; j++) {
                    System.out.println("Valor : " + eElement.getElementsByTagName("value").item(j).getTextContent());
                    System.out.println("Peso : " + eElement.getElementsByTagName("peso").item(j).getTextContent());
                    System.out.println("Frequencia : " + eElement.getElementsByTagName("freq").item(j).getTextContent());
                    System.out.println("MaxW : " + eElement.getElementsByTagName("maxW").item(j).getTextContent());
                    System.out.println("----------------------------");
                }
                System.out.println("----------------------------");
                System.out.println("----------------------------");
                System.out.println("----------------------------");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}