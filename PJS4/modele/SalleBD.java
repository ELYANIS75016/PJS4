package modele;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class SalleBD extends PJS4BD{

	public static void sauverEnBaseSalle(int etage, int salle, String date, int places) {
		String requete = "INSERT INTO `SALLE` (`NumEtage`, `NumSalle`, `HeureReserv`, `NbPlace`) VALUES "
				+ "('"+ etage +"','" + salle + "','" + date + "','" + places + "')";
		sql(requete);
	}
	
	public static String afficherSalles() {
		String requete = "SELECT * FROM SALLE";
		ResultSet rs =null;
		String salleString = "";
		
		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : r�cup�ration de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Cr�ation d'un statement
			st = cn.createStatement();

			// Etape 4 : ex�cution requ�te
			rs = st.executeQuery(requete);

			// Si r�cup donn�es alors �tapes 5 (parcours Resultset)
			if (!rs.next()) {
				System.out.println("Cette table est vide.");
			}
			rs.previous();
			
			while (rs.next()) {
				salleString += ("Num�ro de Salle : " + rs.getString("NumSalle") + 
				" - Etage : " + rs.getString("NumEtage") +
				" - NbPlace : " + rs.getString("NbPlace") + "##");
			}
			
		} catch (MySQLSyntaxErrorException e) {
			System.out.print("Cette table n'existe pas dans la BDD.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			sqlClose();
		}
		return salleString;
	}

}
