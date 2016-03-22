package model;

import java.util.List;

import exception.UserNotFoundException;
import persistence.*;


/**
 * Classe g�n�rale de l'application en charge de d�l�guer les diff�rentes actions possibles sur le mod�le
 */
public class Dispatcher {
	
	private static Dispatcher instance = new Dispatcher();
	private static UserDAO compteDao = UserDAO.getInstance();
	private static ReservationDAO reservationDao = ReservationDAO.getInstance();
	private static RoomDAO volDao = RoomDAO.getInstance();

	/**
	 * R�cup�rer un utilisateur � partir de son pseudo et de son mot de passe
	 * 
	 * @param pseudo Login suppos� du compte
	 * @param password Mot de passe suppos� du compte
	 * @return le compte correspondant aux param�tres
	 * @throws UserNotFoundException si aucun utilisateur ne poss�de cette combinaison
	 */
	public User getUser(String pseudo, String password)
		 throws  UserNotFoundException{
		User user = compteDao.findUser(pseudo, password);
		return user;
	}
	
	/**
	 * R�cup�rer une r�servation � partir de son num�ro unique
	 * 
	 * @param nbReservation Num�ro de la r�servation
	 * @return la r�servation correspondante au num�ro
	 * @throws NotFoundException si la r�servation ou ses diff�rentes composantes (salle, utilisateur) sont introuvables
	 */
	public Reservation getReservation(int nbReservation) throws NotFoundException {
		return reservationDao.trouverReservation(nbReservation);
	}
	
	/**
	 * R�cup�rer l'ensemble des r�servation d'un utilisateur
	 * 
	 * @param user l'utilisateur dont on recherche les r�servation
	 * @return une liste de r�servation
	 */
	public List<Reservation> getReservations(User user) {
		return reservationDao.recupererReservations(user);
	}
	
	/**
	 * R�cup�rer une salle � partir de son num�ro 
	 * 
	 * @param nbRoom le num�ro du la salle
	 * @return la salle correspondante
	 * @throws IntrouvableException si le vol correspondant est introuvable
	 */
	public Room getRoom(int nbRoom) throws IntrouvableException {
		return volDao.findRoom(nbRoom);
	}
	
	/**
	 * R�cup�rer un un vol � partir d'une r�servation
	 * 
	 * @param reservation la r�servation dont on recherche le vol
	 * @return la salle correspondante � la r�servation
	 * @throws IntrouvableException si le vol 
	 */
	public Room getRoom(Reservation reservation) throws IntrouvableException {
		return volDao.findRoom(reservation);
	}
	
	/**
	 * R�cup�rer l'ensemble des v suivant certains crit�res
	 * 
	 * 
	 * @param villeDestination la ville de destination du vol
	 * @param dateDebut le vol doit avoir lieu apr�s cette date (format jj/mm/yyyy)
	 * @param nbPlace le nombre de place du vol (format jj/mm/yyyy)
	 * @return une liste de vols
	 */
	public List<Room> getVols(String villeDestination, String dateDebut, int nbPlace){
		return volDao.recupererVols(villeDestination, dateDebut, nbPlace);
	}
	
	/**
	 * Ajouter un comtpe
	 * 
	 * @param login le login choisi pour le compte
	 * @param mdp le mot de passe choisi pour le compte
	 * @param nom le nom choisi pour le compte
	 * @param prenom le prenom choisi pour le compte
	 * @param le role du compte (utilisateur ou admin)
	 * @return le compte ainsi ajout�
	 * @throws CompteExisteDejaException si un voyageur correspondant au login existe d�j�
	 */
	public User ajouterCompte(String login, String mdp, String nom, String prenom, String role) 
			throws CompteExisteDejaException {
		User compte = new User(login, mdp, nom, prenom,role );
		compteDao.addUser(compte);
		return compte;
	}
	
	/**
	 * Ajouter un Vol
	 * 
	 * @param String destination de la r�servation
	 * @param String dateDebut de la r�servation
	 * @param int nombrePlaces de la r�servation
	 * @param float prix de la r�servation
	 */
	public Room ajouterVol(String destination, String dateDebut, int nombrePlaces, float prix) {
		Room vol = new Room(5, destination, dateDebut, nombrePlaces, prix);
		volDao.addRoom(destination, dateDebut, nombrePlaces, prix);
		return vol;
	}
	
	/**
	 * Ajouter une r�servation
	 * 
	 * @param vol vol de la r�servation
	 * @param compte comtpe futur de la r�servation
	 */
	public void ajouterReservation(Room vol, User compte, int nbPlaces) {
		reservationDao.ajouterReservation(vol, compte, nbPlaces);
	}

	/**
	 * Supprimer une r�servation
	 * 
	 * @param reservation la r�servation � supprimer
	 */
	public void removeReservation(Reservation reservation) {
		reservationDao.supprimerReservation(reservation);
	}
	
	/**
	 * R�cup�rer l'instance unique de SNGF (Singleton)
	 * 
	 * @return l'instance
	 */
	public static Dispatcher getInstance() {
		return instance;
	}

}