import java.util.Random;

/**
 * Klasa odpowiadaj�ca za powo�anie planszy do gry. <br><br>
 * Plansza sk�ada si� z dwuwymiarowej tablicy element�w 
 * klasy Element. Tablica ta jest o 2 wi�ksza, ni� potrzeba 
 * do gry, ze wzgl�du na mechanizm rozstawiania statk�w.
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
	 * Konstruktor pobiera dane od jakiego miejsca ma zacz�� rysowa� 
	 * i przypisuje je do odpowiednich zmiennych, a nast�pnie wywo�uje 
	 * funkcj� setBoard().
	 * 
	 * @param pixelA - odleg�o�� od lewej kraw�dzi ekranu (w pikselach).
	 * @param pixelB - odleg�o�� od g�rnej kraw�dzi ekranu (w pikselach.
	 */
	public Board(int pixelA, int pixelB){
		this.a = pixelA;
		this.b = pixelB;
		setBoard();
	}

	/**
	 * Funkcja pozwala na wy�wietlenie warto�ci o odleg�o�ci od lewej 
	 * kraw�dzi ekranu b�dz g�rnej kraw�dzi.
	 * 
	 * @param AorB decyzja kt�r� z warto�ci wy�wietli�.
	 * @return a b�dz b (w zale�no�ci od decyzji).
	 */
	public int getPixel(char AorB){
		if (AorB == 'b' || AorB == 'B') return this.b;
		else return this.a;
	}
	
	/**
	 * Wype�nia ca�� tablic� nowymi elementami typu 0 
	 * (informacja, �e nic tam nie ma/woda).
	 */
	public void setBoard(){
		for (int i = 0; i < 12; ++i)
			for (int j = 0; j < 12; ++j) 
					this.board[j][i] = new Element('0', 0);
	}
	
	/**
	 * Metoda testowa rysuj�ca plansz� w konsoli. <br>
	 * Nie ma ona znaczenia dla gry i jest u�ywana jedynie do test�w dla programisty.
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
	 * Ustawia ramk� dla planszy, czyli wype�nia graniczne 
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
