package bayardo;

public class Feature implements Comparable<Feature> {

    public Double peso;
    public Integer freq;
    public String value; //Identificador da dimensão
    public Double maxW;

    public Feature(String value, Double peso, Integer freq, double maxW) {
        super();
        this.freq = freq;
        this.value = value;
        this.peso = peso;
        this.maxW = maxW;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((freq == null) ? 0 : freq.hashCode());
        result = prime * result + ((peso == null) ? 0 : peso.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feature other = (Feature) obj;
        if (freq == null) {
            if (other.freq != null)
                return false;
        } else if (!freq.equals(other.freq))
            return false;
        if (peso == null) {
            if (other.peso != null)
                return false;
        } else if (!peso.equals(other.peso))
            return false;
        if (value == null) {
            return other.value == null;
        } else return value.equals(other.value);
    }

    @Override
    public int compareTo(Feature aux) {
        if (this.freq < aux.freq) return 1;
        else if (this.freq > aux.freq) return -1;
        else return this.value.compareTo(aux.value);
    }

    @Override
    public String toString() {
        return "\nFeature [MaxW =" + maxW + ", Peso =" + peso + ", Freq=" + freq + ", Value=" + value + "]\n";
    }
}