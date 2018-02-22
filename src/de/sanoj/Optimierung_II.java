package de.sanoj;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Optimierung_II {

	double[] xi = { 0, 1, 2, 3, 4, 5 };
	double[] mi = { 0.1, 1.3, 4.2, 8.5, 15, 24 };
	double k;
	double min, max;
	double delta;
	
	double minfehler;
	double mink;

	double f(double x) {
		return k * (x * x);
	}

	public Optimierung_II() {
		delta = 0.01;
		min = 0;
		max = 2;
		minfehler = 1000;
		mink = min;
	}
	
	public void start(){
		for(double i = min; i <= max; i += delta){
			k = i;
			double tmpa = summe(xi.length);
			System.out.println("K: "+k);
			System.out.println("Fehler: "+Math.sqrt(tmpa/xi.length));
			System.out.println();
			if(tmpa < minfehler){
				minfehler = tmpa;
				mink = k;
			}
		}
		System.out.println("Effizientestes K = "+mink);
		System.out.println("Fehler bei effizientestem K = "+Math.sqrt(minfehler/xi.length));
	}
	
	private double summe(int max){
		double tmpn = 0.0;
		for(int i2 = 0; i2 < max; i2++){
			tmpn += Math.pow((f(xi[i2]) - mi[i2]), 2);
			System.out.println("Fehlertemp(i="+(i2+1)+"): "+Math.pow((f(xi[i2]) - mi[i2]), 2));
		}
		return tmpn;
	}

	public static void main(String[] args) {
//		Optimierung_I optimierung = new Optimierung_I();
//		optimierung.start();
		new Optimierung_II().start();
		
//		play(null, null);
	}
	static Clip clip;
	
	private static void play(Object codeBase, String fileName) {
        try {
            clip = AudioSystem.getClip();  //<---
            File file = new File(fileName + ".wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
