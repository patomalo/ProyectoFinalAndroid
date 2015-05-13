package edu.math;

import android.util.Log;

public class Operacion {
	
	private String TAG = GetString.class.getSimpleName();
	private String[] s = new String[4]; 
	
	public String[] comenzarSumRes(String sa,String a, String sb,String b,String key) {
		fillEmptyArray(s, 4);
		int res = 0;
		String[] v1 = new String[2];
		String[] v2 = new String[2];
		String letter = "";
		Log.i(TAG, "comenzamos con las operaciones de dos: "+sa+" "+a+" "+sb+" "+b);
		v1 = separate(a);
		v2 = separate(b);
		
		Log.w(TAG, "SumRes - SEPARADO: "+v1[0]+" "+v1[1]+" "+v2[0]+" "+v2[1]);
		// checking variables
		s[3] = this.checkSumResSameVariable(v1[1], v2[1], key);
		if(!s[3].equals("")) {
			if(sa != "" && sa == "-") v1[0] = sa + v1[0];
			if(sb.equals("+")) res = sumar(v1[0],v2[0]);
			else if(sb.equals("-")) res = restar(v1[0],v2[0]);
			//verify, if the result is negative
			if(res < 0){
				s[0]="-";s[1]="OP";
				res=res*(-1);
			} else {
				//s[0]="";s[1]="";
				s[0]="+";s[1]="OP";
			}
			s[2]=""+res+v2[1];
		}
		Log.i(TAG, "SOLUCION de los ES: "+res);
		return s;
	}
	
	public int sumar(String a,String b) {
		Log.i(TAG, "SUMA: "+a+" "+b);
		int aa,bb;
		aa = Integer.parseInt(a);
		bb = Integer.parseInt(b);
		return aa+bb; 
	}
	
	public int restar(String a,String b) {
		Log.i(TAG, "RESTA: "+a+" "+b);
		int aa,bb;
		aa = Integer.parseInt(a);
		bb = Integer.parseInt(b);
		return aa-bb;
	}
	
	public String checkSumResSameVariable(String a,String b, String key) {
		String res="";
		if(key.equals("V1")){
			res="V1";
		}
		if(key.equals("V2")) {
			if(a.equals(b)) {
				res="V2";
			}
		}
		if(key.equals("V3")) {
			if(a.equals(b)) {
				res="V3";
			}
			if((a.substring(0, 1).equals( b.substring(1, 2) )) && 
			   (a.substring(1, 2).equals( b.substring(0, 1) ))) {
				res="V3";
			}
		}
		if(key.equals("V4")) {
			if(a.equals(b)) {
				res="V4";
			}
		}
		Log.i(TAG, "SumRes - Same variable:"+res);
		return res;
	}
	public String[] multiplicar(String sa,String a, String sb,String b) {
		String sol[] = new String[4];
		return sol;
	}
	public String[] dividir(String sa,String a, String sb,String b) {
		String sol[] = new String[4];
		return sol;
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
	
	//multiplicar o dividir
	public String[] multiDiv(String[] env) {
		fillEmptyArray(s, 4);
		String[] v1 = new String[2];
		String[] v2 = new String[2];
		Log.i(TAG, "INICIO DE MULTIPLICACION O DIV: "+env[0]+" "+env[1]+" "+env[2]+" "+env[3]+" "+env[4]+" "+env[5]+" "+env[6]);
		
		//regla de signos
		if(env[0].equals(""))env[0] = "+";
		if(env[4].equals(""))env[4] = "+";
		
		if(env[0].equals("+") && env[4].equals("+"))s[0]="+";
		else if(env[0].equals("+") && env[4].equals("-"))s[0]="-";
		else if(env[0].equals("-") && env[4].equals("+"))s[0]="-";
		else if(env[0].equals("-") && env[4].equals("-"))s[0]="+";
		
		//if(s[0].equals("+")){s[0]="";s[1] ="";}
		s[1]="OP";
		
		if(env[2].equals("V1")) {v1[0] = env[1];v1[1] = "";}
		else v1 = separate(env[1]);
		if(env[6].equals("V1")) {v2[0] = env[5];v2[1] = "";}
		else v2 = separate(env[5]);
		
		Log.i(TAG, "after separate is: "+v1[0]+" "+v1[1]+" "+v2[0]+" "+v2[1]);
		
		if(v1[0].equals(""))v1[0] = "1";
		if(v2[0].equals(""))v2[0] = "1";
		if(env[3].equals("*")) {
			int aa = Integer.parseInt(v1[0]);
			int bb = Integer.parseInt(v2[0]);
			s[2] = ""+(aa*bb);
			String[] mm = new String[2];
			mm = multilLetras(v1[1], v2[1],env[2], env[6]);
			Log.i(TAG, "AFTER multiLetra "+mm[0]+" "+mm[1]);
			s[2] = s[2]+mm[0];
			s[3] = mm[1];
		} else {
			//here I will divide
		}
		return s;
	}
	
	/**
	 * Mutiplica los string 
	 * Es importante cuando se realiza multiplicacion entre dos valores 
	 * @param a: primer valor
	 * @param b: segundo valor
	 * @param ba: base del primer valor 
	 * @param bb: base del segundo valor
	 * Ej: valor: [V1,V2,V3,V4]
	 * @return retorna un String con la mutiplicacion ya echa
	 */
	public String[] multilLetras(String a, String b, String ba, String bb) {
		String[] salida = new String[2];
		if(a.equals("")){
			salida[0]=b;
			salida[1]=bb;
		} else {
			if(b.equals("")) {
				salida[0] = a;
				salida[1] = ba;
			}
		}
		return salida;
	}
	
	/**
	 * Separa el Numero de su variable 3x : [3,x]  
	 * @param String el value
	 * @return Un array, del string separado
	 */
	public String[] separate(String value) {
		String[] div = new String[2];
		div[0]="";
		div[1]="";
		String d;
		for(int i=0;i<value.length();i++) {
			d=value.substring(i,i+1);
			if(isNumber(d))	div[0] = div[0]+d;
			else if(d.equals(".") || d.equals(",")) div[0] = div[0]+d;
			else {div[1]= value.substring(i, value.length());break;}
		}
		return div;
	}
	
	/**
	 * Verify if string is a number
	 * @param string
	 * @return true if the string is a number
	 */
	public boolean isNumber(String s) {
		char c = s.charAt(0);
		int as = c;
		if(as >= 48 && as <= 57) {
			return true;
		}
		else return false;
	}
}
