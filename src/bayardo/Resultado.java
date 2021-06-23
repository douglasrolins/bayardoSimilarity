package bayardo;

public class Resultado {
	int v1,v2;
	double similaridade;

	public Resultado(int v1, int v2, double similaridade) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.similaridade = similaridade;
	}

	@Override
	public String toString() {
		return "Resultado [v=" + v1 + " com v=" + v2 + ", similaridade= " + similaridade + "]\n";
	}
}
