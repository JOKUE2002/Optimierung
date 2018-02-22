package de.sanoj;

import java.util.ArrayList;

public class Optimierung_II {

	double[] xi = { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 }; // Gegebene X-Werte
	double[] mi = { 80, 50, 31, 16, 5, 3, 5, 16, 31, 50, 80 }; // Gegebene
																// Y-Werte
	double min, max; // Minimal und Maximalwerte, in denen
	double delta = 0.001;
	ArrayList<Vektor> prevk = new ArrayList<Vektor>();

	Vektor k = new Vektor(1,1,1);
	double minfehler; // Kleinster Fehler
	Vektor mink; // Vektor, bei dem der Fehler am kleinsten ist.
	boolean first = true; // true -> Erste runde; false -> nicht erste runde

	// TODO: Fill in f
	double f(double x, Vektor k) {
		return k.getK1() * Math.pow(x, 2) + k.getK2() * x + k.getK3();
	}

	public Optimierung_II() {
		min = 0;
		max = 2;
		minfehler = 1000;
	}
	
	double prelambda;

	public void start() {
		for (int i = 0; i < 100000; i++) {

			
			double lambda = Lambda(k);
			
			if(first){
				prelambda = lambda;
				first = false;
			}else{
				if(prevk.get(i-1).getBetrag() - k.getBetrag() <= delta && i > 100){
					System.out.println("FINISHED AFTER "+i+" ITERATIONS!");
					System.out.println("PreLambda: "+prelambda);
					System.out.println("Lambda: "+lambda);
					break;
				}
				System.out.println(prevk.get(i-1).getBetrag() - k.getBetrag());
				System.out.println();
			}

			prevk.add(k);

			double a = k.getK1() - lambda * gradF(k).getK1();
			double b = k.getK2() - lambda * gradF(k).getK2();
			double c = k.getK3() - lambda * gradF(k).getK3();
			k = new Vektor(a,b,c);
			System.out.println(k);
		}
		System.out.println(k);
	}

	private double Lambda(Vektor k) {
		double tmp = delta;
		double min = 1000;// min tmp
		double min2 = 0; // Lambda bei min tmp
		boolean first = true;
		for (; tmp < 100; tmp += delta) {
			double a = k.getK1() - gradF(k).getK1();
			double b = k.getK2() - gradF(k).getK2();
			double c = k.getK3() - gradF(k).getK3();
			Vektor klambda = new Vektor(a, b, c);
			double tmp2 = F(klambda);
			if (tmp2 < min || first) {
				min = tmp2;
				min2 = tmp;
				first = false;
			}
		}

		return min2;
	}

	private double F(Vektor k) {
		double tmpn = 0.0;
		for (int i2 = 0; i2 < mi.length; i2++) {
			tmpn += Math.pow((f(xi[i2], k) - mi[i2]), 2);
//			System.out.println("Fehlertemp(i=" + (i2 + 1) + "): " + Math.pow((f(xi[i2], k) - mi[i2]), 2));
		}
		return tmpn;
	}

	private Vektor gradF(Vektor k) {
		double a = (F(new Vektor(k.getK1() + delta, k.getK2(), k.getK3())) - F(k)) / delta;
		double b = (F(new Vektor(k.getK1(), k.getK2() + delta, k.getK3())) - F(k)) / delta;
		double c = (F(new Vektor(k.getK1(), k.getK2(), k.getK3() + delta)) - F(k)) / delta;
		return new Vektor(a, b, c);
	}

	public static void main(String[] args) {
		new Optimierung_II().start();
	}
}
