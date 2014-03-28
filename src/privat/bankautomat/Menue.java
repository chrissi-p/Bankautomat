package privat.bankautomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class Menue {
	
	private static Konto myKonto;
	private static boolean run;
	
	public static void start()
	{
		myKonto = new Konto(1);
		run = true;
		
		do{
			printMenue();
			try {
				choose();
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Falsches Zahlenformat");
			} catch (IOException e) {
				System.out.println("ERROR: Eingabe Fehler");
			}
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
	
	private static void choose() throws NumberFormatException, IOException
	{
		System.out.print("-> ");
		
		BufferedReader bR = new BufferedReader( new InputStreamReader( System.in ) );
		
		int wahl = Integer.parseInt(bR.readLine());

		switch(wahl)
		{
			case 1:
				myKonto.print();
				break;
				
			case 2:
				System.out.print("-> Wieviel möchten sie abheben: ");
				double auszahlen = Double.parseDouble(bR.readLine());
				if(myKonto.auszahlen(auszahlen)){
					System.out.println("Sie haben " + NumberFormat.getCurrencyInstance().format(auszahlen) + " ahgehoben");
					myKonto.print();	
				}else{
					System.out.println("Der Betrag von " + NumberFormat.getCurrencyInstance().format(auszahlen) + " konnte nicht abgehoben werden.");
				}
				break;
				
			case 3:
				System.out.print("-> Wieviel möchten sie einzahlen: ");
				double einzahlen = Double.parseDouble(bR.readLine());
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
	
	private static void admin() throws IOException
	{
		System.out.println("**** Adminmenue ****");
		System.out.println("Kontonummer: " + myKonto.getNummer());
		System.out.println("1. Ändere Besitzer (" + myKonto.getBesitzer() + ")");
		System.out.println("2. Ändere Limit (" + myKonto.getLimit() + ")");
		System.out.println("3. Ändere Zinssatz (" + myKonto.getZinssatz() + ")");
		System.out.println("4. Zinsen berechnen");
		System.out.println("5. Zurück zum Menü");
		System.out.print("-> ");
		
		BufferedReader bR = new BufferedReader( new InputStreamReader( System.in ) );
		
		int wahl = 0;
		
		boolean ok = true;
		do{
			ok = true;
			try {
				wahl = Integer.parseInt(bR.readLine());
			} catch (NumberFormatException e) {
				System.err.println("ERROR: Falsches Zahlenformat");
				ok = false;
			} catch (IOException e) {
				System.err.println("ERROR: Eingabefehler");
			}
		} while(!ok);

		
		switch(wahl)
		{
			case 1:
				if(myKonto.setBesitzer(bR.readLine())){
					System.out.println("-> Der Kontobesitzer wurde in \"" + myKonto.getBesitzer() + "\" geändert.");
				}else{
					System.err.println("-> Der Besitzer konnte nicht geändert werden.");
				}
				break;
				
			case 2:
				if(myKonto.setLimit(Double.parseDouble(bR.readLine()))){
					System.out.println("-> Das Limit wurde auf " + NumberFormat.getCurrencyInstance().format(myKonto.getLimit()) + " geändert.");
				}else{
					System.err.println("-> Das Limit konnte nicht geändert werden.");
				}
				break;
				
			case 3:
				if(myKonto.setZinssatz(Double.parseDouble(bR.readLine()))){
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
