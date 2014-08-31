import java.math.BigInteger;
public class Rational {
	private BigInteger numerator, denominator;
	
	public Rational(){
		this(new BigInteger("0"),new BigInteger("1"));
	}
	public Rational(BigInteger a, BigInteger b){
		BigInteger gcd = gcd(a,b);
		BigInteger zero = new BigInteger("0");
		BigInteger nagetiveOne = new BigInteger("-1");
		if(b.compareTo(zero)<0){
			a=a.multiply(nagetiveOne);
			b=b.multiply(nagetiveOne);
		}
		numerator = a.divide(gcd);
		denominator = b.divide(gcd);
	}
	public static BigInteger gcd(BigInteger n, BigInteger d){
		BigInteger n1;
		BigInteger n2;
		if((n.compareTo(new BigInteger("0")))<0)
			n1 = n.multiply(new BigInteger("-1"));
		else
			n1 = new BigInteger(n.toString());
		if((d.compareTo(new BigInteger("0")))<0)
			n2 = d.multiply(new BigInteger("-1"));
		else
			n2 = new BigInteger(d.toString());
		BigInteger gcd = new BigInteger("1");
		for(BigInteger k=new BigInteger("1"); k.compareTo(n1)<=0 && k.compareTo(n2)<=0; 
			k=k.add(new BigInteger("1"))){
			if(n1.remainder(k).equals(new BigInteger("0"))&&n2.remainder(k).equals(new BigInteger("0"))){
				gcd = null;
				gcd = new BigInteger(k.toString());
			}
		}
		return gcd;
	}
	public BigInteger getNumberator(){
		return numerator;
	}
	public BigInteger getDenominator(){
		return denominator;
	}
	
	public Rational add(Rational secondRational){
		BigInteger tmp1_n = numerator.multiply(secondRational.getDenominator());
		BigInteger tmp2_n = denominator.multiply(secondRational.getNumberator());
		BigInteger n = tmp1_n.add(tmp2_n);
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational(n, d);
	}
	public Rational subtract(Rational secondRational){
		BigInteger tmp1_n = numerator.multiply(secondRational.getDenominator());
		BigInteger tmp2_n = denominator.multiply(secondRational.getNumberator());
		BigInteger n = tmp1_n.subtract(tmp2_n);
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational(n, d);
	}
	public Rational multiply(Rational secondRational){
		BigInteger n = numerator.multiply(secondRational.getNumberator());
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational(n, d);
	}public Rational divide(Rational secondRational){
		BigInteger n = numerator.multiply(secondRational.getDenominator());
		BigInteger d = denominator.multiply(secondRational.getNumberator());
		return new Rational(n, d);
	}
	
	public String toString(){
		if(denominator.equals(new BigInteger("0")))
			return numerator.toString();
		else
			return numerator.toString()+"/"+denominator.toString();
	}
	public boolean equals(Object parm1){
		if((this.subtract((Rational)(parm1))).getNumberator().equals(new BigInteger("0")))
				return true;
		else
			return false;
	}
	public static void main(String args[]){
		BigInteger a = new BigInteger("-20");
		BigInteger b = new BigInteger("-12");
		Rational ac = new Rational(a, b);
		System.out.println(ac.toString());
	}
}
