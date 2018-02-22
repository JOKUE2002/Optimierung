package de.sanoj;

public class Vektor {

	private double k1, k2, k3;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "k1: " + k2 + "\nk2: " + k2 + "\nk3: " + k3;
	}

	public Vektor() {
		k1 = 0;
		k2 = 0;
		k3 = 0;
	}

	public Vektor(double k1, double k2, double k3) {
		this.k1 = k1;
		this.k2 = k2;
		this.k3 = k3;
	}

	public double getBetrag() {
		return Math.sqrt((k1 * k1) + (k2 * k2) + (k3 * k3));
	}

	public double getK1() {
		return k1;
	}

	public double getK2() {
		return k2;
	}

	public double getK3() {
		return k3;
	}

	public void setK1(double k1) {
		this.k1 = k1;
	}

	public void setK2(double k2) {
		this.k2 = k2;
	}

	public void setK3(double k3) {
		this.k3 = k3;
	}

}
