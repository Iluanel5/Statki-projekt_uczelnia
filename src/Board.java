import java.util.Random;

/**
 * Klasa odpowiadaj¹ca za powo³anie planszy do gry. <br><br>
 * Plansza sk³ada siê z dwuwymiarowej tablicy elementów 
 * klasy Element. Tablica ta jest o 2 wiêksza, ni¿ potrzeba 
 * do gry, ze wzglêdu na mechanizm rozstawiania statków.
 * 
 * @author Karolina Niemira H5X1S1
 * 
 */
public class Board{
	static final int SIZE = 12;
	private int a, b;
	protected Element[][] board = new Element[SIZE][SIZE];
	Random random = new Random();
	
	/**
	 * Konstruktor pobiera dane od jakiego miejsca ma zacz¹æ rysowaæ 
	 * i przypisuje je do odpowiednich zmiennych, a nastêpnie wywo³uje 
	 * funkcjê setBoard().
	 * 
	 * @param pixelA - odleg³oœæ od lewej krawêdzi ekranu (w pikselach).
	 * @param pixelB - odleg³oœæ od górnej krawêdzi ekranu (w pikselach.
	 */
	public Board(int pixelA, int pixelB){
		this.a = pixelA;
		this.b = pixelB;
		setBoard();
	}

	/**
	 * Funkcja pozwala na wyœwietlenie wartoœci o odleg³oœci od lewej 
	 * krawêdzi ekranu b¹dz górnej krawêdzi.
	 * 
	 * @param AorB decyzja któr¹ z wartoœci wyœwietliæ.
	 * @return a b¹dz b (w zale¿noœci od decyzji).
	 */
	public int getPixel(char AorB){
		if (AorB == 'b' || AorB == 'B') return this.b;
		else return this.a;
	}
	
	/**
	 * Wype³nia ca³¹ tablicê nowymi elementami typu 0 
	 * (informacja, ¿e nic tam nie ma/woda).
	 */
	public void setBoard(){
		for (int i = 0; i < 12; ++i)
			for (int j = 0; j < 12; ++j) 
					this.board[j][i] = new Element('0', 0);
	}
	
	/**
	 * Metoda testowa rysuj¹ca planszê w konsoli. <br>
	 * Nie ma ona znaczenia dla gry i jest u¿ywana jedynie do testów dla programisty.
	 */
	public void drawBoardTest(){
		for (int i = 0; i < 12; ++i){
			System.out.println(" ");
			for (int j = 0; j < 12; ++j){
				System.out.print(this.board[j][i].getType()+" ");
			}
		}
	}
	
	/**
	 * Ustawia ramkê dla planszy, czyli wype³nia graniczne 
	 * pola elementami o stanie 'N'.<br>
	 * W grze jest niewidoczna.
	 */
	public void setFrame(){
		for (int i = 0; i < 12; ++i){
			for (int j = 0; j < 12; ++j){
				if(i==0 || j==0 || i==11 || j==11) this.board[j][i] = new Element('N', 0);
			}
		}
	}
}
