/**
	 * Odpowiada za utworzenie jednego elementu tablicy (jeden ma³y kwadrat)
	 * na którym umieszczane bêd¹ elementy statków. Ka¿dy element ma swój:<br>
	 * <ul>
	 * 		<li>typ (1,2,3,4 - rodzaj statku; 0 - brak statku), <br>
	 * 		<li>stan, <br>
	 * 		<li>id, <br>
	 * 		<li>priorytet strza³u (u¿ywane dla gracza Komputer), <br>
	 * 		<li>informacjê, czy element statku p³ywa, czy nic tu nie ma.
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
	 * Konstruktor odpowiada za stworzenie elementu. Dziêki podanym
	 * informacjom okreœla typ statku.
	 * 
	 * @param state wartoœæ stanu elementu dla zmiennej state
	 * @param id wartoœæ dla zmiennej id
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
	 * Pobiera zawartoœæ zmiennej apo (informacja o elemencie specjalnym 
	 * z mo¿liwoœci¹ zniszczenia wszystkich statków)
	 * @return apo 
	 */
	public int getApo(){
		return this.apo;
	}
	
	/**
	 * Zmienia wartoœæ zmiennej apo (element specjalny z mo¿liwoœci¹ 
	 * zniszczenia wszystkich statków)
	 * @param apo
	 */
	public void changeApo(int apo){
		this.apo = apo;
	}
	/**
	 * Zmienia wartoœæ zmiennej id elementu.
	 * @param id nowa wartoœæ dla zmiennej id.
	 */
	public void changeID(int id){
		this.id = id;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej id elementu.
	 * @return id
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej priority elementu (u¿ywane dla 
	 * gracza Komputer).
	 * @return priority
	 */
	public int getPriority(){
		return this.priority;
	}
	
	/**
	 * Zmienia wartoœæ zmiennej priority elementu (u¿ywane dla 
	 * gracza Komputer).
	 * @param priority - nowa wartoœæ dla zmiennej priority
	 */
	public void changePriority(int priority){
		this.priority = priority;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej state elementu. Mo¿liwe wartoœci: <br>
	 * <ul>
	 * 		<li>1,2,3,4 - statek; 
	 * 		<li>0 - woda; 
	 * 		<li>x - nast¹pi³ trafiony strza³; 
	 * 		<li>* - nast¹pi³ spud³owany strza³; 
	 * 		<li>o - pole, gdzie nie mo¿e stan¹æ statek, by nie dotykaæ innego; 
	 * 		<li>N - ramka (nie mo¿e byæ tu statku, ani nie mo¿na strzelaæ). 
	 * </ul>
	 * @return state
	 */
	public char getState(){
		return this.state;
	}
	
	/**
	 * Zmienia wartoœæ zmiennej state elementu.
	 * @param state - nowa wartoœæ dla zmiennej state
	 */
	public void changeState(char state){
		this.state = state;
	}
	
	/**
	 * Zwraca aktualn¹ zmiennej wartoœæ type elementu.
	 * @return type
	 */
	public int getType(){
		return this.type;
	}
	
	/**
	 * Zmienia wartoœæ zmiennej destroy elementu. Jeœli  zmienna change jest 
	 * wiêksza od zera, to zawsze przypisze jeden, w przeciwnym 
	 * wypadku ustawia 0. 
	 * @param change wartoœæ wed³ug której zostanie ustawiona nowa wartoœæ zmiennej destroy.
	 */
	public void changeDestroy(int change){
		if (change > 0) this.destroy = 1;
		else this.destroy = 0;
	}
	
	/**
	 * Zwraca aktualn¹ wartoœæ zmiennej destroy elementu.
	 * @return destroy
	 */
	public int getDestroy(){
		return this.destroy;
	}
}
