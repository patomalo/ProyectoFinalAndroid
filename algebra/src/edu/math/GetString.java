package edu.math;

import android.content.Context;
import android.util.Log;


public class GetString {
	
	private String TAG = GetString.class.getSimpleName();
	private String issue;
	private String[] steps = new String[20];
	Context context;
	basicMath bM;
	
	private String[] value = new String[200];
	private String[] tipo = new String[200];
	
	public String[] start(String getIssue) {
		bM = new basicMath();
		steps[0] = "IT'S WRONG";
		issue = getIssue.trim();
		inni();
		int count = countOperations();
		Log.i(TAG, "COMENZAMOS CON LA RESOLUCION");
		clean(issue);
		separar(issue);
		//printing logs
		for(int i = 0;i< 200 ;i++){
			if(value[i] != "")Log.v(TAG, value[i]);
		}
		Log.i(TAG, "INTERMEDIO");
		for(int i = 0;i< 200 ;i++){
			if(tipo[i] != "")Log.v(TAG, tipo[i]);
		}
		//-------------
		
		if(isEquals(issue)) {
			Log.i(TAG, "LA OPERACION TIENE UN IGUAL ");
			steps[0] = " SEGUIMOS TRABAJANDO\n EN LA OPERACION :(";
		} else {
			if(count == 1){
				if(!isLetter(issue)){
					Log.i(TAG, "ES SOLO UNA OPERACION ALGEBRAICA");
					steps[2] = Double.toString(bM.operate2num(issue));
				} else {
					steps = bM.basicStart(value, tipo);
				}
			}
			else {
				steps[0] = "SEGUIMOS TRABAJANDO\n EN EL PROBLEMA :(";
				Log.i(TAG, "TIENE MAS DE 1 OPERACION");
				steps=bM.basicStart(value,tipo);
			}
		}
		
		Log.d(TAG, "YA VAMOS A ENVIAR LOS PASOS A Operation");
		return steps;
	}
	
	/**
	 * This method count, how many operation has it to do
	 * @return count
	 */
	public int countOperations() {
		int count = 0,i,r;
		String[] ope = {"+","-","*","/"};
		for(int j=0; j<ope.length;j++){
			i=0;
			while(i < issue.length()){
				r = issue.indexOf(ope[j], i);
				if(r != -1){
					count++;
					i = r+1; 
				} else {
					break;
				}
			}
		}
		Log.i(TAG, "CONOTAMOS LAS OPERACIONES "+count);
		return count;
	}
	
	/**
	 * This step clean the string  with the spaces;
	 */
	public void clean(String value) {
			value.replace(" ", "");
	}
	
	
	
	/**
	 * Verify if string is a number
	 * @param string
	 * @return true if the string is a number
	 */
	public boolean isNumber(String s) {
		char c = s.charAt(0);
		int as = (int) c;
		if(as >= 48 && as <= 57) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Verify if string is a letter
	 * @param string
	 * @return true if the string is a letter
	 */
	public boolean isLetter(String s) {
		char c = s.charAt(0);
		int as = (int) c;
		if((as>=65 && as<=90)||(as>=97 && as<=122)) {
			return true;
		}
		else return false;
	}
	
	public boolean isStringLetters(String s) {
		String ind;
		for(int i = 0; i < s.length(); i++) {
			ind = s.substring(i, i+1);
			if(isLetter(ind))return true;
		}
		return false;
	}
	/**
	 * Verifica si es q existe Igual
	 * @param s
	 * @return true if the string has "="
	 */
	public boolean isEquals(String s) {
		String ind;
		for(int i=0;i< s.length();i++) {
			ind = s.substring(i, i+1);
			if(ind.trim().equals("=")) return true;
		}
		return false;
	}
	
	/**
	 * Verifica que existe un parentesis
	 * @param string
	 * @return true si existe un parentesis
	 */
	public boolean isParentesis(String s) {
		String ind;
		for(int i=0;i< s.length();i++) {
			ind = s.substring(i, i+1);
			if(ind.trim().equals("(")) return true;
			if(ind.trim().equals("{")) return true;
			if(ind.trim().equals("[")) return true;
		}
		return false;
	}
	
	/**
	 * LLENA LOS CAMPOS DE VALUE Y TIPO CON ESPACIOS
	 */
	public void inni() {
		for(int i = 0;i < 200; i++) {
			value[i] = "";
		}
		for(int i = 0;i < 200; i++) {
			tipo[i] = "";
		}
	}
	
	 /**
	  * realiza la separacion del texto
	  * @param string
	  */
	public void separar(String s){
		String d;
		int base = 0;
		
		for(int i=0;i<s.length();i++) {
			d = s.substring(i, i+1);
			
			if(d.equals("(")) {
				value[base] = d;
				tipo[base] = "SI1";
				base++;
			} else {
				if(d.equals("[")) {
					value[base] = d;
					tipo[base] = "SI2";
					base++;
				} else {
					if(d.equals("{")) {
						value[base] = d;
						tipo[base] = "SI3";
						base++;
					} else {
						if(d.equals(")")) {
							value[base] = d;
							tipo[base] = "SF1";
							base++;
						} else {
							if(d.equals("]")) {
								value[base] = d;
								tipo[base] = "SF2";
								base++;
							} else {
								if(d.equals("}")) {
									value[base] = d;
									tipo[base] = "SF3";
									base++;
								} else {
									if(d.equals("+") || d.equals("-") || d.equals("*") || d.equals("/")) {
										value[base] = d;
										tipo[base] = "OP";
										base++;
									} else {
										//this part become v1 v2 v3
										if(isNumber(d)) {
											if(base == 0) {
												value[base] = d;
												tipo[base] = "V1";
												base++;
											} else {
												if(tipo[base-1].equals("V1") || tipo[base-1].equals("V4")) {
													value[base-1] = value[base-1] + d;
												} else {
													if(tipo[base-1].equals("V2")) {
														value[base-1] = value[base-1] + d;
														tipo[base-1] = "V4";
													} else {
														value[base] = d;
														tipo[base] = "V1";
														base++;
													}
												}
											}
										} else {
											if(isLetter(d)) {
												if(base == 0) {
													value[base] = d;
													tipo[base] = "V2";
													base++;
												} else {
													if(tipo[base-1].equals("V1")) {
														value[base-1] = value[base-1] + d;
														tipo[base-1] = "V2";
													} else {
														if(tipo[base-1].equals("V2")) {
															value[base-1] = value[base-1] + d;
															tipo[base-1] = "V3";
														} else {
															value[base] = d;
															tipo[base] = "V2";
															base++;
														}
													}
												}
											} else {
												 if(d.equals(".") || d.equals(",")) {
													 value[base-1] = value[base-1] + d;
												 }
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Log.i(TAG, "FIN DE LA SEPARACION");
	}
}