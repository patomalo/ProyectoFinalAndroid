package edu.sfsu.cs.orange.ocr;



import edu.math.GetString;
import edu.sfsu.cs.orange.ocr.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Operation extends Activity{
	
	private String TAG = Operation.class.getSimpleName();
	TextView ope;
	String[] steps = new String[20];
	String issue;
	
	TextView sp1;
	TextView sp2;
	TextView sp3;
	TextView sp4;
	TextView sp5;
	TextView sp6;
	TextView sp7;
	TextView sp8;
	TextView sp9;
	TextView sp10;
	TextView sp11;
	TextView sp12;
	TextView sp13;
	TextView sp14;
	TextView sp15;
	
	TextView sol1;
	TextView sol2;
	TextView sol3;
	TextView sol4;
	TextView sol5;
	TextView sol6;
	TextView sol7;
	TextView sol8;
	TextView sol9;
	TextView sol10;
	TextView sol11;
	TextView sol12;
	TextView sol13;
	TextView sol14;
	TextView sol15;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mathresult);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		issue = bundle.getString("mathProb");
		ope = (TextView) findViewById(R.id.mainProblem);
		
		//steps
		sp1 = (TextView) findViewById(R.id.step1);
		sp2 = (TextView) findViewById(R.id.step2);
		sp3 = (TextView) findViewById(R.id.step3);
		sp4 = (TextView) findViewById(R.id.step4);
		sp5 = (TextView) findViewById(R.id.step5);
		sp6 = (TextView) findViewById(R.id.step6);
		sp7 = (TextView) findViewById(R.id.step7);
		sp8 = (TextView) findViewById(R.id.step8);
		sp9 = (TextView) findViewById(R.id.step9);
		sp10 = (TextView) findViewById(R.id.step10);
		sp11 = (TextView) findViewById(R.id.step11);
		sp12 = (TextView) findViewById(R.id.step12);
		sp13 = (TextView) findViewById(R.id.step13);
		sp14 = (TextView) findViewById(R.id.step14);
		sp15 = (TextView) findViewById(R.id.step15);
		//solve fields
		sol1 = (TextView) findViewById(R.id.solv1);
		sol2 = (TextView) findViewById(R.id.solv2);
		sol3 = (TextView) findViewById(R.id.solv3);
		sol4 = (TextView) findViewById(R.id.solv4);
		sol5 = (TextView) findViewById(R.id.solv5);
		sol6 = (TextView) findViewById(R.id.solv6);
		sol7 = (TextView) findViewById(R.id.solv7);
		sol8 = (TextView) findViewById(R.id.solv8);
		sol9 = (TextView) findViewById(R.id.solv9);
		sol10 = (TextView) findViewById(R.id.solv10);
		sol11 = (TextView) findViewById(R.id.solv11);
		sol12 = (TextView) findViewById(R.id.solv12);
		sol13 = (TextView) findViewById(R.id.solv13);
		sol14 = (TextView) findViewById(R.id.solv14);
		sol15 = (TextView) findViewById(R.id.solv15);
		
		GetString getString = new GetString();
		steps = getString.start(issue);
		Log.d(TAG, "ANTES Q SE TIRE "+issue);
		ope.setText(""+issue);
		Log.w(TAG, "HASTA AQUI TODO BIEN: "+steps.length);
		if(steps[0] != "") {sp1.setText("PASO 1:");sol1.setText(steps[0]);}
		else {sp1.setText("");sol1.setText("");}
		
		if(steps[1] != "") {sp2.setText("PASO 2:");sol2.setText(steps[1]);}
		else {sp2.setText("");sol2.setText("");}
		
		if(steps[2] != "") {sp3.setText("PASO 3:");sol3.setText(steps[2]);}
		else {sp3.setText("");sol3.setText("");}
		
		if(steps[3] != "") {sp4.setText("PASO 4:");sol4.setText(steps[3]);}
		else {sp4.setText("");sol4.setText("");}
		
		if(steps[4] != "") {sp5.setText("PASO 5:");sol5.setText(steps[4]);}
		else {sp5.setText("");sol5.setText("");}
		
		if(steps[5] != "") {sp6.setText("PASO 6:");sol6.setText(steps[5]);}
		else {sp6.setText("");sol6.setText("");}
		
		if(steps[6] != "") {sp7.setText("PASO 7:");sol7.setText(steps[6]);}
		else {sp7.setText("");sol7.setText("");}
		
		if(steps[7] != "") {sp8.setText("PASO 8:");sol8.setText(steps[7]);}
		else {sp8.setText("");sol8.setText("");}
		
		if(steps[8] != "") {sp9.setText("PASO 9:");sol9.setText(steps[8]);}
		else {sp9.setText("");sol9.setText("");}
		
		if(steps[9] != "") {sp10.setText("PASO 10:");sol10.setText(steps[9]);}
		else {sp10.setText("");sol10.setText("");}
		
		if(steps[10] != "") {sp11.setText("PASO 11:");sol11.setText(steps[10]);}
		else {sp11.setText("");sol11.setText("");}
		
		if(steps[11] != "") {sp12.setText("PASO 12:");sol12.setText(steps[11]);}
		else {sp12.setText("");sol12.setText("");}
		
		if(steps[12] != "") {sp13.setText("PASO 13:");sol13.setText(steps[12]);}
		else {sp13.setText("");sol13.setText("");}
		
		if(steps[13] != "") {sp14.setText("PASO 14:");sol14.setText(steps[13]);}
		else {sp14.setText("");sol14.setText("");}
		
		if(steps[14] != "") {sp15.setText("PASO 15:");sol15.setText(steps[14]);}
		else {sp15.setText("");sol15.setText("");}
		
	}
}
