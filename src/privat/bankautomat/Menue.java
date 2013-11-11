package privat.bankautomat;

import java.text.NumberFormat;
import java.util.Scanner;

public class Menue {
	
	private static Konto myKonto;
	private static boolean run;
	
	public static void start()
	{
		myKonto = new Konto(1);
		run = true;
		
		do{
			printMenue();
			choose();
		}while(run);
		
		
	}
	
	private static void printMenue()
	{
		System.out.println("****** Menue ******");
		System.out.println("1. Aktueller Kontostand");
		System.out.println("2. Geld abheben");
		System.out.println("3. Geld einzahlen");
		System.out.println("4. Zeige Informationen");
		System.out.println("5. Administration");
		System.out.println("6. Beenden");
	}
	
	private static void choose()
	{
		System.out.print("-> ");
		
		Scanner s = new Scanner(System.in);
		int wahl = s.nextInt();
		
		switch(wahl)
		{
			case 1:
				myKonto.print();
				break;
				
			case 2:
				System.out.print("-> Wieviel möchten sie abheben: ");
				double auszahlen = s.nextDouble();
				if(myKonto.auszahlen(auszahlen)){
					System.out.println("Sie haben " + NumberFormat.getCurrencyInstance().format(auszahlen) + " ahgehoben");
					myKonto.print();	
				}else{
					System.out.println("Der Betrag von " + NumberFormat.getCurrencyInstance().format(auszahlen) + " konnte nicht abgehoben werden.");
				}
				break;
				
			case 3:
				System.out.print("-> Wieviel möchten sie einzahlen: ");
				double einzahlen = s.nextDouble();
				if(myKonto.einzahlen(einzahlen)){
					System.out.println("Sie haben " + NumberFormat.getCurrencyInstance().format(einzahlen) + " eingezahlt");
					myKonto.print();
				}else{
					System.out.println("Der Betrag von " + NumberFormat.getCurrencyInstance().format(einzahlen) + " konnte nicht eingezahlt werden.");
				}
				break;
				
			case 4:
				myKonto.printInfo();
				break;
				
			case 5:
				admin();
				break;
				
			case 6:
				exit();
				break;
				
			default:
				System.err.println("Keine gültige Auswahl" + wahl);
				break;
		}
	}
	
	private static void admin()
	{
		System.out.println("**** Adminmenue ****");
		System.out.println("Kontonummer: " + myKonto.getNummer());
		System.out.println("1. Ändere Besitzer (" + myKonto.getBesitzer() + ")");
		System.out.println("2. Ändere Limit (" + myKonto.getLimit() + ")");
		System.out.println("3. Ändere Zinssatz (" + myKonto.getZinssatz() + ")");
		System.out.println("4. Zinsen berechnen");
		System.out.println("5. Zurück zum Menü");
		System.out.print("-> ");
		
		Scanner s = new Scanner(System.in);
		String s_Wahl = s.nextLine();
		int wahl = Integer.parseInt(s_Wahl);
		
		switch(wahl)
		{
			case 1:
				if(myKonto.setBesitzer(s.nextLine())){
					System.out.println("-> Der Kontobesitzer wurde in \"" + myKonto.getBesitzer() + "\" geändert.");
				}else{
					System.err.println("-> Der Besitzer konnte nicht geändert werden.");
				}
				break;
				
			case 2:
				if(myKonto.setLimit(s.nextDouble())){
					System.out.println("-> Das Limit wurde auf " + NumberFormat.getCurrencyInstance().format(myKonto.getLimit()) + " geändert.");
				}else{
					System.err.println("-> Das Limit konnte nicht geändert werden.");
				}
				break;
				
			case 3:
				if(myKonto.setZinssatz(s.nextDouble())){
					System.out.println("-> Der Zinssatz wurde auf " + myKonto.getZinssatz() + "% geändert.");
				}
				break;
				
			case 4:
				myKonto.berechneZinsen();
				System.out.print("Die Zinsen wurden Berechnet: ");
				myKonto.print();
				break;
				
			case 5:
				return;
				
			default:
				System.err.println("Keine gültige Auswahl");
				break;
		}	
	}
	
	private static void exit()
	{
		run = false;
		System.out.println("-> Programm wurde beendet!");
	}
}
