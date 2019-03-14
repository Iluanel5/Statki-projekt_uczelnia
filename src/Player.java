import java.util.Random;

/**
 * Klasa odpowiadaj�ca za wszelkie informacje odno�cie gracza.<br>
 * Na pocz�tku gry ka�dy z graczy posiada 0 punkt�w.
 * @author Karolina Niemira H5X1S1
 *
 */
public class Player {
	private String name = "Gracz";
	private int id = 0;
	private int score = 0;
	
	/**
	 * Konstruktor tworzy gracza. Je�eli jego id wynosi 0, 
	 * to znak, i� jednym z graczy b�dzie komputer.
	 * 
	 * @param name nazwa dla gracza
	 * @param id identyfikator gracza
	 */
	public Player(String name, int id){
		this.id = id;
		if(id == 0)
			this.name = "Komputer";
		else
			this.name = name;	
	}
	
	/**
	 * Zmienia warto�� zimennej score. Do poprzedniej warto�ci dodaje 
	 * podan� warto��.
	 * @param scoreToAdd ilo�� dodatkowych punkt�w
	 */
	public void addScore(int scoreToAdd){
		this.score += scoreToAdd;
	}
	/**
	 * Przypisuje ca�kowicie now� liczb� punkt�w
	 * @param scoreToAdd
	 */
	public void addAllScore(int scoreToAdd){
		this.score = scoreToAdd;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej score (podgl�d punkt�w).
	 * @return score
	 */
	public int viewScore(){
		return this.score;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej name.
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej id.
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Zmienia warto�� zimennej id.
	 * @param id nowa warto�� dla zmiennej id
	 */
	public void changeId(int id){
		this.id = id;
	}

}
