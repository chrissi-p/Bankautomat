package privat.bankautomat;

import java.text.NumberFormat;


public class Konto {

	private int nummer;
	private String besitzer;
	private double saldo;
	private double limit;
	private double zinssatz;
	
	Konto(int neueNummer)
	{
		if(neueNummer > 99999 && neueNummer <1000000)
		{
			nummer = neueNummer;
		}
		else
		{
			nummer = 111111;
		}
		setBesitzer("unbekannt");
		saldo = 0.0;
		limit = 0.0;
		zinssatz = 3.0;
	}
	
	public int getNummer()
	{
		return nummer;
	}
	
	public boolean setBesitzer(String neuerBesitzer)
	{
		if(!neuerBesitzer.isEmpty())
		{
			besitzer = neuerBesitzer;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String getBesitzer()
	{
		return besitzer;
	}
	
	public double getSaldo()
	{
		return saldo;
	}
	
	public boolean setLimit(double neuesLimit)
	{
		if(neuesLimit <= 0.0)
		{
			limit = neuesLimit;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public double getLimit()
	{
		return limit;
	}
	
	public boolean setZinssatz(double neuerZinssatz)
	{
		if(neuerZinssatz >= 0.0)
		{
			zinssatz = neuerZinssatz;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public double getZinssatz()
	{
		return zinssatz;
	}
	
	public boolean einzahlen(double betrag)
	{
		if(betrag >= 0.0)
		{
			saldo += betrag;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean auszahlen(double betrag)
	{
		if(betrag >= 0.0 && saldo-betrag >= limit)
		{
			saldo -=betrag;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void berechneZinsen()
	{
		saldo = saldo + saldo/100*zinssatz;
	}
	
	public void print()
	{
		System.out.println("Kontostand: " + NumberFormat.getCurrencyInstance().format(saldo));
	}
	
	public void printInfo()
	{
		System.out.println("Besitzer: " + besitzer);
		System.out.println("Kontonummer: " + nummer);
		System.out.println("Kontostand: " + NumberFormat.getCurrencyInstance().format(saldo));
		System.out.println("Limit: " + NumberFormat.getCurrencyInstance().format(limit));
		System.out.println("Zinssatz: " + zinssatz + " %");
	}
}
