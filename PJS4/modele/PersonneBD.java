package modele;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class PersonneBD extends PJS4BD{

	public static void sauverEnBasePersonne(String nom, String prenom, String pseudo, String mdp, String departement) {
		String requete = "INSERT INTO `PERSONNE` (`NomPers`, `PrenomPers`, `Pseudo`, `Mdp`, `Departement`) VALUES "
				+ "('"+ nom +"','" + prenom + "','" + pseudo + "','" + mdp + "','" + departement + "')";
		sql(requete);
	}

	public static boolean verifCompte(String nom, String mdp) {
		String requete = "SELECT * FROM personne WHERE NomPers = '" + nom + "' AND Mdp = '" + mdp + "'";
		
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
				System.out.println("Erreur : Utilisateur inconnu!");
				return false;
			} else {
				return true;
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
		
		return false;
	}
}
