package edu.math;

import java.util.Arrays;

import android.util.Log;

public class basicMath {
	private String TAG = basicMath.class.getSimpleName();
	
	public String Equ="NA";
	public String[] va = new String[200];
	public String[] key = new String[200];
	public String[] solu = new String[4];
	
	public String[] s = new String[20];
	Operacion oper = new Operacion();
	Parentesis pare = new Parentesis();

	/**
	 * Inicio de Math
	 * @param valores
	 * @param key
	 * @return Steps....los pasos q deberia seguir :O
	 */
	public String[] basicStart(String[] vars, String[] keys) {
		
		fillEmptyArray(s, 20);
		
		//seran los nuevos llenando de blanco
		fillEmptyArray(va, 200);
		fillEmptyArray(key, 200);
		fillEmptyArray(solu, 4);
		
		//clasifing
		Equ = clasificarEq(vars,keys);
		
		//miselanea
		int[] pIO = new int[2];//pos de parentesis In y Out 
		
		if(Equ.equals("Eq1")) {
			Log.i(TAG, "ECUACION TIPO 1");
			boolean next;
			//creating all the steps
			for(int i=0;i<s.length;i++) {
				fillEmptyArray(solu, 4);
				next = opSumaResta(vars,keys,i);
				if(next == false)break;
				vars = Arrays.copyOf(va,va.length);
				keys = Arrays.copyOf(key, key.length);
				
			}
		} else{
			if(Equ.equals("Eq2")) {
				Log.i(TAG, "ECUACION TIPO 2");
				boolean next;
				for(int i = 0;i<s.length;i++) {
					fillEmptyArray(solu, 4);
					next = opMultiDivAll(vars,keys,i);
					if(next == false)break;
					vars = Arrays.copyOf(va,va.length);
					keys = Arrays.copyOf(key, key.length);
				}
			} else {
				Log.i(TAG, "ECUACION TIPO 3");
				boolean next;
				for(int i = 0;i<s.length;i++) {
					fillEmptyArray(solu, 4);
					next = opMultiDivAll(vars,keys,i);
					if(next == false){// Don't change to (!next) xq creo q se jode x esta cagada 
						fillEmptyArray(solu, 4);
						next = opSumaResta(vars,keys,i);
						//if(next == false) break;
						if(next == false) {
							//despejando parentesis
							if(pare.isParentesis(vars)) {
								pIO = pare.getInOutParentesis(vars, keys);
								if(pIO[0] != pIO[1]) {
									next = opDespejarPar(pIO,vars,keys,i);
								}
							} else {
								break;
							}
							
						}
					}
					vars = Arrays.copyOf(va,va.length);
					keys = Arrays.copyOf(key, key.length);
				}
			}
		}
		//print steps
		for(int i=0;i<s.length;i++) {
			Log.w(TAG, "steps "+i+1+": "+s[i]);
		}
		return s;
	}
	
	/**
	 * Calcula suma o resta de numeros naturales
	 * @param vars: los valores
	 * @param keys: tipo de string 
	 * @param step: el paso en la q se va a guardar
	 * @return true si se uso el paso 
	 */
	public boolean opSumaResta(String[] vars,String[] keys,int step) {
		//inicios
		int pib=0;
		fillEmptyArray(va, 200);
		fillEmptyArray(key, 200);
		String[] v1 = new String[2];
		String[] v2 = new String[2];
		//looking for operation and  saving the new operation
		va[0] = vars[0];
		key[0] = keys[0];
		for(int i=1;i<keys.length;i++) {//comenzamos desde 1 para no tomar como operacion al 0 si es q es negativo
			//IT'LL NOT WORK WITH THIS EXCEPTION "- 3 * - 4"
			if(keys[i].trim().equals("OP")) {
				Log.d(TAG, "VALIDAMOS: "+keys[i-1]+"  "+keys[i+1]);
				//vamos a evaluar suma y resta no+
				if(keys[i-1].equals(keys[i+1])) {
					Log.d(TAG, "PASAMOS LA VALIDACION: "+keys[i-1]+"  "+keys[i+1]);
					//verificar similitud
					v1 = oper.separate(vars[i-1]);
					v2 = oper.separate(vars[i+1]);
					String same= oper.checkSumResSameVariable(v1[1], v2[1], keys[i+1]);
					if(!same.equals("")) {
						//borrar en el nuevo guardado
						va[i-1] = "";key[i-1] = "";
						//verificar signo
						if(i-1 != 0) {
							if(keys[i-2].equals("OP")) {
								Log.w(TAG, "TIENE SIGNO");
								solu = oper.comenzarSumRes(vars[i-2],vars[i-1],vars[i],vars[i+1],keys[i-1]);
								va[i-2]="";key[i-2]="";
								vars[i-2]="";vars[i-1]="";vars[i]="";vars[i+1]="";
							} else {
								Log.w(TAG, "NO tiene SIGNO");
								solu = oper.comenzarSumRes("", vars[i-1], vars[i], vars[i+1],keys[i-1]);
								vars[i-1]="";vars[i]=""; vars[i+1]="";
							}
						} else {
							Log.w(TAG, "NO tiene SIGNO");
							solu = oper.comenzarSumRes("", vars[i-1], vars[i], vars[i+1],keys[i-1]);
							vars[i-1]="";vars[i]=""; vars[i+1]="";
						}
						pib = i;
						break;
					}
				}
			}
			va[i] = vars[i];
			key[i] = keys[i];
		}
		Log.i(TAG, "GOING TO SAVE: SumRes "+solu[0]+solu[1]+solu[2]+solu[3]+" "+step);
		//SAVING A STEPS
		return saveSteps(pib,vars,keys,step);
		
	}
	private boolean saveSteps(int pib,String[] vars,String[] keys, int step) {
		int pib2 = 0;
		Log.d(TAG, "ver solucion: "+solu[0]+" "+solu[1]+" "+solu[2]+" "+solu[3]);
		if(solu[0].equals("") && solu[1].equals("") && solu[2].equals("") && solu[3].equals(""))return false;
		Log.i(TAG, "save steps: IS  NOT FALSE");
		//continuamos con el llenado
		//llenamos la solucion
		for(int i=0;i<va.length;i++) {
			if(va[i] == ""){
				if(solu[0].equals("")) {
					va[i] = solu[2]; key[i] = solu[3];
					pib2=i;
				} else {
					va[i] = solu[0]; key[i] = solu[1];
					va[i+1] = solu[2]; key[i+1] = solu[3];
					pib2=i+1;
				}
				break;
			}
		}
		//llenamos con el resto 
		for(int i = pib; i <vars.length;i++) {
			if(vars[i] != "") {
				pib2++;
				va[pib2] = vars[i];
				key[pib2] = keys[i];
			}
		}
		//done
		s[step] = convertArraytoString(va);
		Log.d(TAG, "AHORA SI ESTE ES EL RESULTADO: "+s[step]);
		return true;
	}
	
	private boolean opMultiDivAll(String[] vars, String[] keys, int step) {
		//inicios
		int pib=0;
		fillEmptyArray(va, 200);
		fillEmptyArray(key, 200);
		String[] env = new String[7]; 
		//looking for operation and  saving the new operation
		va[0] = vars[0];
		key[0] = keys[0];
		for(int i=1;i<keys.length;i++) {//comenzamos desde 1 para no tomar como operacion al 0 si es q es negativo
			if(keys[i].trim().equals("OP")) {
				
				if(vars[i].trim().equals("*") || vars[i].trim().equals("/")) {
					Log.d(TAG, " PASAMOS LA VALIDACION");
					if(i-1 != 0) {
						if(keys[i-2].equals("OP")) {
							Log.w(TAG, "1er valor TIENE SIGNO");
							env[0] = va[i-2];
							va[i-2]="";key[i-2]="";
							vars[i-2]="";
						} else {
							Log.w(TAG, "1er valor NO TIENE SIGNO");
							env[0] = "";
						}
					} else {
						Log.w(TAG, "1er valor NO TIENE SIGNO");
						env[0] = "";
					}
					if(keys[i+1].equals("OP")) {
						Log.w(TAG, "2do valor TIENE SIGNO");
						env[4] = vars[i+1];
						env[5] = vars[i+2];
						env[6] = keys[i+2];
						vars[i+1]="";vars[i+2]="";
					} else {
						Log.w(TAG, "2do valor NO TIENE SIGNO");
						env[4] = "";
						env[5] = vars[i+1];
						env[6] = keys[i+1];
						vars[i+1]="";
					}
					//envios
					env[1]=vars[i-1];env[2]=keys[i-1];
					env[3]=vars[i];
					//borrados
					va[i-1] = "";key[i-1] = "";vars[i-1]="";
					vars[i]="";
					solu = oper.multiDiv(env);
					pib = i;
					break;
				}
			}
			va[i] = vars[i];
			key[i] = keys[i];
		}
		Log.i(TAG, "GOING TO SAVE: MultiDiv "+solu[0]+solu[1]+solu[2]+solu[3]+" "+step);
		//SAVING A STEPS
		return saveSteps(pib, vars, keys, step);
	}
	
	
	public boolean opDespejarPar(int[] p, String[] vars, String[] keys, int step) {
		Log.i(TAG, "despejar parentesis con un + o - adelante");
		
		
		//inicios
//		int pib=0;
		fillEmptyArray(va, 200);
		fillEmptyArray(key, 200);
		String ss,tp;
		//looking for operation and  saving the new operation
		
		if(p[0] != 0) {
			if(keys[p[0]-1].equals("OP") ){
				if( vars[p[0]-1].equals("-")) tp = "-";
				else tp = "+";
			} else tp = "+";
		} else tp = "+";
		
		int pv = 0;
		for(int i=0;i<keys.length;i++) {
			if(!keys[i].equals("")) {
				va[pv]=vars[i];
				key[pv]=keys[i];
				
				if(i == p[0]) {
					if(p[0] != 0) {
						if(keys[i-1].equals("OP")) {
							if(keys[i+1].equals("OP")){
								ss = pare.multiSignos(vars[i-1], vars[i+1]);
								va[pv-1] = ss;
								keys[pv+1]="";
							} else {
								ss = pare.multiSignos(vars[i-1], "+");
								va[pv-1] = ss;
							}
						} else {
							if(keys[pv+1].equals("OP")) {
								ss = pare.multiSignos("+", keys[i+1]);
								va[pv] = ss;key[pv]="OP";
								pv++;
							} else {
								ss = pare.multiSignos("+", "+");
								va[pv]=ss;key[pv]="OP";
								pv++;
							}
						}
					} else {
						if(keys[pv+1].equals("OP")) {
							ss = pare.multiSignos("+", keys[i+1]);
							va[pv] = ss;key[pv]="OP";
							pv++;
						} else {
							ss = pare.multiSignos("+", "+");
							va[pv]=ss;key[pv]="OP";
							pv++;
						}
					}
				} else {
					if(i>p[0] && i<p[1]){
						if(keys[i].equals("OP")){
							ss = pare.multiSignos(vars[i], tp);
							va[pv] = ss;
						}
					}
					if(i != p[1]){
						pv++;
					} else {
						va[pv]="";key[pv]="";
					}
				}
			}
		}
		Log.i(TAG, "PRINT LO REALIZADO");
		//print
		for(int i = 0;i< 200 ;i++){
			if(va[i] != "")Log.v(TAG, va[i]);
		}
		Log.i(TAG, "INTERMEDIO");
		for(int i = 0;i< 200 ;i++){
			if(key[i] != "")Log.v(TAG, key[i]);
		}
		s[step] = convertArraytoString(va);
		//-----
		return false;
	}
	
	/**
	 * clasifica q tipo de ecuacion para tener una mejor evaluacion 
	 * @param k = la llave key[]
	 * @return String, el tipo de evacion
	 */
	private String clasificarEq(String[] v, String[] k) {
		String cl="NA";
		int v1=0,v2=0,v3=0,v4=0;
		int o1=0,o2=0,o3=0,o4=0;
		//now we're going to evaluate counting the values
		for(int i = 0;i < k.length; i++) {
			if(k[i] != "") {
				if(k[i].equals("V1")) v1++;
				if(k[i].equals("V2")) v2++;
				if(k[i].equals("V3")) v3++;
				if(k[i].equals("V4")) v4++;
				
				if(v[i].equals("+")) o1++;
				if(v[i].equals("-")) o2++;
				if(v[i].equals("*")) o3++;
				if(v[i].equals("/")) o4++;
			}
		}
		if(v1>0 && v2==0 && v3==0 && v4==0 && (o1>0 || o2>0)) {
			cl = "Eq1";
		} else {
			if( o3>0 && o1==0 && o2==0 && o4==0 ) {
			//HARDCODEADO!!!
				cl = "Eq2";
			} else {
				cl = "Eq3";
			}
		}
		return cl;
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
	
	/**
	 * hace un swap entre el primer y el segundo string 
	 * @param a
	 * @param b
	 */
	private void swap2Str(String a, String b) {
		String t;
		t = a;a = b; b = t;
	}
	
	/**
	 * multiplica, suma, resta o divide dos numeros enteros
	 * @param string 
	 * @return la suma resta multiplicacion o division del numero
	 */
	public double operate2num(String val) {
		int in=-1,n1=0,n2;
		double solution=0;
		String pr,ope="";
		for(int i=0;i<val.length();i++) {
			pr = val.substring(i, i+1);
			Log.i(TAG, "LETRA: "+pr+" boolean: "+isNumber(pr));
			if(isNumber(pr)){
				if(in == -1) in = i;
			}
			else {
				n1 = Integer.parseInt(val.substring(in, i));
				in=-1;
				//save thee operation
				ope = pr;
			}
		}
		n2 = Integer.parseInt(val.substring(in, val.length()));
		if(ope.equals("+")) solution = n1+n2;
		else if(ope.equals("-")) solution = n1-n2;
		else if(ope.equals("*")) solution = n1*n2;
		else if(ope.equals("/")) solution = n1/n2;
		Log.i(TAG, n1+" operacion "+n2+" resultados: "+solution);
		return solution;
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
	
	/**
	 * Verify if string is a letter
	 * @param string
	 * @return true if the string is a letter
	 */
	public boolean isLetter(String s) {
		char c = s.charAt(0);
		int as = c;
		if((as>=65 && as<=90)||(as>=97 && as<=122)) {
			return true;
		}
		else return false;
	}
	
	public String convertArraytoString(String[] s) {
		String sol="";
		for(int i=0;i<s.length;i++) {
			if(s[i] != "") {
				sol+=s[i];
			}
		}
		return sol;
	}
}
