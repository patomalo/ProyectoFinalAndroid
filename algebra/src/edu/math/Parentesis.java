package edu.math;

import android.util.Log;

public class Parentesis {
	
	private String TAG = Parentesis.class.getSimpleName();
	private int[] sol = new int[2];
	
	
	/**
	 * Verifica que existe un parentesis
	 * @param array
	 * @return true si existe un parentesis
	 */
	public boolean isParentesis(String[] s) {
		Log.d(TAG, "REVISANDO SI TIENE PARENTESIS");
		String ind;
		for(int i=0;i< s.length;i++) {
			ind = s[i];
			if(ind.trim().equals("(")) return true;
			if(ind.trim().equals("{")) return true;
			if(ind.trim().equals("[")) return true;
		}
		return false;
	}
	
	public int[] getInOutParentesis(String[] v, String[] k) {
		int in=0,out=0;
		Log.d(TAG, "sacando el I y Out de un parentesis");
		for(int i=0;i<k.length;i++) {
			if(!k[i].equals("")) {
				
				if(k[i].equals("SI1")) if(i>=in) in=i;
				if(k[i].equals("SI2")) if(i>=in) in=i;
				if(k[i].equals("SI3")) if(i>=in) in=i;
			}
		}
		for(int i = in;i<k.length;i++) {
			if(!k[i].equals("")) {
				if(k[in].equals("SI1")) {
					if(k[i].equals("SF1")) {out=i;break;}
				}
				if(k[in].equals("SI2")) {
					if(k[i].equals("SF2")) {out=i;break;}
				}
				if(k[in].equals("SI3")) {
					if(k[i].equals("SF3")) {out=i;break;}
				}
			}
		}
		sol[0]=in;sol[1]=out;
		return sol;
	}
	
	public String multiSignos(String a, String b) {
		String r ="";
		if(a.equals("+") && b.equals("+")) r = "+";
		if(a.equals("+") && b.equals("-")) r = "-";
		if(a.equals("-") && b.equals("+")) r = "-";
		if(a.equals("-") && b.equals("-")) r = "+";
		return r;
	}
	
	/**
	 * para llenar un array string, con campos vacios ej. ' "" '
	 * 
	 * @param s: es el array
	 * @param size: es el tamaño del array
	 */
	private void fillEmptyArray(String[] s,int size) {
		for(int i=0;i<size;i++) {
			s[i] = "";
		}
	}
}
