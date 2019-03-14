import java.util.Random;

/**
 * Klasa odpowiadaj¹ca za wszelkie informacje odnoœcie gracza.<br>
 * Na pocz¹tku gry ka¿dy z graczy posiada 0 punktów.
 * @author Karolina Niemira H5X1S1
 *
 */
public class Player {
	private String name = "Gracz";
	private int id = 0;
	private int score = 0;
	
	/**
	 * Konstruktor tworzy gracza. Je¿eli jego id wynosi 0, 
	 * to znak, i¿ jednym z graczy bêdzie komputer.
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
	 * Zmienia wartoœæ zimennej score. Do poprzedniej wartoœci dodaje 
	 * podan¹ wartoœæ.
	 * @param scoreToAdd iloœæ dodatkowych punktów
	 */
	public void addScore(int scoreToAdd){
		this.score += scoreToAdd;
	}
	/**
	 * Przypisuje ca³kowicie now¹ liczbê punktów
	 * @param scoreToAdd
	 */
	public void addAllScore(int scoreToAdd){
		this.score = scoreToAdd;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej score (podgl¹d punktów).
	 * @return score
	 */
	public int viewScore(){
		return this.score;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej name.
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej id.
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Zmienia wartoœæ zimennej id.
	 * @param id nowa wartoœæ dla zmiennej id
	 */
	public void changeId(int id){
		this.id = id;
	}

}
