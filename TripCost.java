package project3;

import java.text.DecimalFormat;

import javafx.scene.control.TextField;

public final class TripCost {
	
	private double d;
	private double gc;
	private double gm;
	private double num;
	private double hc;
	private double fc;
	private double a;
	private double GasPrice;
	private DecimalFormat Decimal= new DecimalFormat("#.00");


 public TripCost  (TextField D, TextField GC, TextField GM, TextField Num, TextField HC, TextField FC, TextField A) {

	 d= Double.parseDouble(D.getText());
	 gc= Double.parseDouble(GC.getText());
	 gm= Double.parseDouble(GM.getText());
	 num= Double.parseDouble(Num.getText());
	 hc= Double.parseDouble(HC.getText());
	 fc= Double.parseDouble(FC.getText());
	 a= Double.parseDouble(A.getText());
}



final public String total(){
	
	GasPrice= (d/gm) * gc;
	
	double t=(GasPrice +  (hc + fc ) * num + a);
	String Total= Decimal.format(t);
	return "$ " + Total;

	
}
final public void KM() {
	
	double km= 0.621;
	d = km * d;	

	}


final public void DL() {
	
	double gl= 3.785;
	gc = gl * gc;	

}

final public void MG() {
	
	double mg= 2.352;
	gm= mg * gm;

	
}
final public void NV() {
	d= 1;
	gc=0;
	gm=1;
}
}
