/**
	 * Odpowiada za utworzenie jednego elementu tablicy (jeden ma�y kwadrat)
	 * na kt�rym umieszczane b�d� elementy statk�w. Ka�dy element ma sw�j:<br>
	 * <ul>
	 * 		<li>typ (1,2,3,4 - rodzaj statku; 0 - brak statku), <br>
	 * 		<li>stan, <br>
	 * 		<li>id, <br>
	 * 		<li>priorytet strza�u (u�ywane dla gracza Komputer), <br>
	 * 		<li>informacj�, czy element statku p�ywa, czy nic tu nie ma.
	 * </ul>
	 * @author Karolina Niemira H5X1S1
	 * 
*/
public class Element {
	
	private int type;
	private char state;
	private int id = 0;
	private int priority = 0;
	private int destroy = 0;
	private int apo = 0;
	static final int HEIGHT = 30;
	static final int WIDTH = 30;
	
	/**
	 * Konstruktor odpowiada za stworzenie elementu. Dzi�ki podanym
	 * informacjom okre�la typ statku.
	 * 
	 * @param state warto�� stanu elementu dla zmiennej state
	 * @param id warto�� dla zmiennej id
	 */
	public Element(char state, int id){
		
		this.id = id;
		this.state = state;
		switch (this.state){
		case '1':
			this.type = 1;
			break;
		case '2':
			this.type = 2;
			break;
		case '3':
			this.type = 3;
			break;
		case '4':
			this.type = 4;
			break;
		default:
			this.type = 0;
		}
		this.destroy = 1;
	}

	/**
	 * Pobiera zawarto�� zmiennej apo (informacja o elemencie specjalnym 
	 * z mo�liwo�ci� zniszczenia wszystkich statk�w)
	 * @return apo 
	 */
	public int getApo(){
		return this.apo;
	}
	
	/**
	 * Zmienia warto�� zmiennej apo (element specjalny z mo�liwo�ci� 
	 * zniszczenia wszystkich statk�w)
	 * @param apo
	 */
	public void changeApo(int apo){
		this.apo = apo;
	}
	/**
	 * Zmienia warto�� zmiennej id elementu.
	 * @param id nowa warto�� dla zmiennej id.
	 */
	public void changeID(int id){
		this.id = id;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej id elementu.
	 * @return id
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej priority elementu (u�ywane dla 
	 * gracza Komputer).
	 * @return priority
	 */
	public int getPriority(){
		return this.priority;
	}
	
	/**
	 * Zmienia warto�� zmiennej priority elementu (u�ywane dla 
	 * gracza Komputer).
	 * @param priority - nowa warto�� dla zmiennej priority
	 */
	public void changePriority(int priority){
		this.priority = priority;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej state elementu. Mo�liwe warto�ci: <br>
	 * <ul>
	 * 		<li>1,2,3,4 - statek; 
	 * 		<li>0 - woda; 
	 * 		<li>x - nast�pi� trafiony strza�; 
	 * 		<li>* - nast�pi� spud�owany strza�; 
	 * 		<li>o - pole, gdzie nie mo�e stan�� statek, by nie dotyka� innego; 
	 * 		<li>N - ramka (nie mo�e by� tu statku, ani nie mo�na strzela�). 
	 * </ul>
	 * @return state
	 */
	public char getState(){
		return this.state;
	}
	
	/**
	 * Zmienia warto�� zmiennej state elementu.
	 * @param state - nowa warto�� dla zmiennej state
	 */
	public void changeState(char state){
		this.state = state;
	}
	
	/**
	 * Zwraca aktualn� zmiennej warto�� type elementu.
	 * @return type
	 */
	public int getType(){
		return this.type;
	}
	
	/**
	 * Zmienia warto�� zmiennej destroy elementu. Je�li  zmienna change jest 
	 * wi�ksza od zera, to zawsze przypisze jeden, w przeciwnym 
	 * wypadku ustawia 0. 
	 * @param change warto�� wed�ug kt�rej zostanie ustawiona nowa warto�� zmiennej destroy.
	 */
	public void changeDestroy(int change){
		if (change > 0) this.destroy = 1;
		else this.destroy = 0;
	}
	
	/**
	 * Zwraca aktualn� warto�� zmiennej destroy elementu.
	 * @return destroy
	 */
	public int getDestroy(){
		return this.destroy;
	}
}
