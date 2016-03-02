package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import modele.SalleBD;


public class ServiceR�servation implements Runnable{

	private Socket client;
	private boolean fini = false;
	
	public ServiceR�servation(Socket accept) {
		client = accept;
	}

	
	public void run() {
		System.err.println("Nouveau client : " + this.client.getInetAddress());
		
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			while (!fini) { 
				out.println("Voici les salles disponibles : ##" + SalleBD.afficherSalles() + "##Veuillez choisir le num�ro de la salle que vous souhaitez r�server:");
				out.flush();
				int choixSalle = Integer.parseInt(in.readLine());
//				out.println(salleChoisie(choixSalle));
				out.flush();
//				char c = in.readLine().charAt(0);
//				if (c == 'o'){
					out.println("Veuillez renseigner votre nom de famille.");
					out.flush();
					String nom = in.readLine();
//					reservation(choixSalle , nom);
//					out.println(recapitulatif(choixSalle));
//					enregistrement(choixSalle);
					fini = true;
//				} else {
//					
//				}
				
				
			}
			client.close();
		} catch (IOException e) {}	
		
		
	}
	
//	public String salleChoisie(int i){
//		return "Est ce bien la salle n� " + lesSalles.get(i).getNumSalle() + " ayant "+ lesSalles.get(i).getNbPlaces() + " places ? (o/n)";
//	}
//	
//	public void reservation (int i, String name){
//		lesSalles.get(i).ReserverSalle(name);
//	}
//	
//	
//	public String recapitulatif(int i){
//		return "R�capitulatif : ## - Num�ro de salle : "+ lesSalles.get(i).getNumSalle() + "## - Nombre de place : " + lesSalles.get(i).getNbPlaces() + "## - R�serv�e par : " + lesSalles.get(i).getReservant();
//	}
//	
//	public void enregistrement(int num){
//		 try {
//			FileWriter fw = new FileWriter("SallesR�serv�es.txt", true);
//			BufferedWriter bw = new BufferedWriter (fw) ;
//			PrintWriter pw = new PrintWriter (bw) ;
//			pw.println(lesSalles.get(num).getNumSalle()+";"+lesSalles.get(num).getNbPlaces()+";"+lesSalles.get(num).getReservant()+"\n");
//		} catch (IOException e) {
//			System.out.println("Erreur dans la cr�ation du fichier");
//		} 
//	}
	
	public void lancer(){
		(new Thread(this)).start();		
	}
	

}
