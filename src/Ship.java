import java.util.Random;
/**
 * Klasa odpowiedzialna za stworzenie statku, który 
 * póiej zostanie umieszczony na planszy do gry.<br>
 * Statek powstaje na ma³ej tablicy elementów. 
 * Uk³ad statku (czy jest zawiniêty czy nie) jest losowy.
 * <br>
 * Mo¿liwe do postawienia statki:<br>
 * <ul>
 * 			<li>cztery jednomasztowce	(jeden sposób na rozmieszczenie)<br>
 * 			<li>trzy dwumasztowce		(dwa sopoby na rozmieszczenie)<br>
 * 			<li>dwa trzymasztowce		(szeœæ sposobów na rozmieszczenie)<br>
 * 			<li>jeden czteromasztowiec	(osiemnaœcie sposobów na rozmieszczenie)<br>
 * </ul>
 * <br>Zmienna type odpowiada zmiennej type dla elementu.
 * @author Karolina Niemira H5X1S1
 *
 */
public class Ship {
	private Element[][] ship;
	private char type;
	private int x, y; //rozmiar tablicy
	private int intType;
	private int id;
	private Random random = new Random(); //do kszta³tu
	
	/**
	 * Powo³uje do ¿ycia tablicê elementów. W zale¿noœci od 
	 * podanego typu tablica, gdzie znajdzie siê statek ma 
	 * inn¹ wielkoœæ. <br>
	 * Do wielkoœci dodawane s¹ równie¿ miejsca o stanie 'o', 
	 * które maj¹ zapobiec kolizjom statków.
	 * 
	 * @param type typ statku (np. 4 - czteromasztowiec)
	 */
	public Ship(char type){
		this.type = type;
		int arrange; //jak po³o¿ony bêdzie statek (poziomo czy pionowo)
		switch (this.type){
		case '1': 
			this.intType = 1;
			x = y =this.intType+3;
			this.ship = new Element[this.x][this.y];
			break;
			
		case '2': 
			this.intType = 2;
			arrange = random.nextInt(2);
			if (arrange == 0){
				this.x = this.intType+2;
				this.y = this.intType+1;
			}
			else {
				this.x = this.intType+1;
				this.y = this.intType+2;
			}
			this.ship = new Element[this.x][this.y];
			break;
			
		case '3': 
			this.intType = 3;
			arrange = random.nextInt(3);
			if (arrange == 0){
				this.x = this.intType+2;
				this.y = this.intType;
			}
			else {
				if (arrange == 1){
					this.x = this.intType;
					this.y = this.intType+2;
				} else {
					this.x = this.y = this.intType + 1;
				}
			}
			this.ship = new Element[this.x][this.y];
			break;
			
		case '4': 
			this.intType = 4;
			arrange = random.nextInt(4);
			switch (arrange){
			case 0:
				this.x = this.intType+2;
				this.y = this.intType-1;
				break;
			case 1:
				this.x = this.intType-1;
				this.y = this.intType+2;
				break;
			case 2:
				this.x = this.intType;
				this.y = this.intType+1;
				break;
			default:
				this.x = this.intType+1;
				this.y = this.intType;
			}
			this.ship = new Element[this.x][this.y];
			break;
		}
	}

	/**
	 * ustawia statek na planszy w losowym miejscu.
	 * @param board plansza, na której ma siê znalezæ utworzony statek.
	 * @param id - id statku (unikatowe). Potrzebne dla inteligencji gracza Komputer.
	 */
	public void setShip(Board board, int id){
		int a = random.nextInt(10)+1; //Zakres [0,9]
		int b = random.nextInt(10)+1;
		int i, j;
		//  u³o¿enie statku na planszy 
		switch (this.type){
		case '1':
			shipType1(id);
			while(!checkCollision(a, b, board)){
				a = random.nextInt(10)+1;
				b = random.nextInt(10)+1;
			}
			for (i=a-1; i <= a+1; i++){
				for(j=b-1; j <= b+1; j++)
					board.board[i][j] = this.ship[i-a+1][j-b+1];
			}
			
			break;
		case '2':
			shipType2(id);
			while(!checkCollision(a, b, board)){
				a = random.nextInt(10)+1;
				b = random.nextInt(10)+1;
			}
			for (i=a; i < a+this.x; i++){
				for(j=b; j < b+this.y; j++)
					board.board[i][j] = this.ship[i-a][j-b];
			}
			
			break;
		case '3':
			shipType3(id);
			while(!checkCollision(a, b, board)){
				a = random.nextInt(10)+1;
				b = random.nextInt(10)+1;
			}
			for (i=a; i < a+this.x; i++){
				for(j=b; j < b+this.y; j++)
					board.board[i][j] = this.ship[i-a][j-b];
			}
			break;
		case '4':
			shipType4(id);
			while(!checkCollision(a, b, board)){
				a = random.nextInt(10)+1;
				b = random.nextInt(10)+1;
			}
			for (i=a; i < a+this.x; i++){
				for(j=b; j < b+this.y; j++)
					board.board[i][j] = this.ship[i-a][j-b];
			}
			break;
		default:
			System.out.println("B³¹d przy setShip");
		}
	}
	
	/**
	 * Utworzenie jednomasztowca.
	 * @param id id statku (unikatowe)
	 */
	private void shipType1(int id){
		int i, j;
		for (i = 0; i < this.x; i++){
			for (j = 0; j < this.y; j++)
				this.ship[i][j] = new Element('o',0);
		}
		this.ship[1][1] = new Element('1',id);
	}
	
	/**
	 * Utworzenie dwumasztowca. Uk³ad statku jest losowy.
	 * @param id id statku (unikatowe)
	 */
	private void shipType2(int id){
		int i, j;
		for (i = 0; i < this.x; i++){
			for (j = 0; j < this.y; j++)
				this.ship[i][j] = new Element('o',0);
		}
		this.ship[1][1] = new Element('2',id);

		if(this.x > this.y) {
			this.ship[2][1] = new Element('2',id);
		}
		else {
			this.ship[1][2] = new Element('2',id);
		}
		
	}
	
	/**
	 * Utworzenie trzymasztowca. Uk³ad statku jest losowy.
	 * @param id id statku (unikatowe)
	 */
	private void shipType3(int id){
		int i, j;

		for (i = 0; i < this.x; i++){
			for (j = 0; j < this.y; j++)
				this.ship[i][j] = new Element('o',0);
		}
		
		if(this.x == this.y-2){
			for (int k = 1; k <= 3; k++){
				this.ship[1][k] = new Element('3',id);
			}
		} else {
			if (this.y == this.x-2){
				for (int k = 1; k <= 3; k++){
					this.ship[k][1] = new Element('3',id);
				}
			} else {
				int tmp = random.nextInt(4)+1;
				for (i = 1; i < this.x-1; i++){
					for (j = 1; j < this.y-1; j++)
						this.ship[i][j] = new Element('3',id);
				}
				switch (tmp){
				case 1: this.ship[1][1] = new Element('o',0); break;
				case 2: this.ship[1][2] = new Element('o',0); break;
				case 3: this.ship[2][1] = new Element('o',0); break;
				case 4: this.ship[2][2] = new Element('o',0); break;
				}
			}
		}
	}

	/**
	 * Utworzenie zawiniêtego czteromasztowca. Uk³ad statku jest losowy.
	 * @param id id statku (unikatowe)
	 */
	private void shipType4a(int id){
		int a, b, style, rand = random.nextInt(8)+1;
		if (this.x == this.y-1){
			
			style = 1;
		} else {
			
			style = 2;
		}
		for (int i = 1; i < this.x-1; i++){
			
			for (int j = 1; j < this.y-1; j++)
				this.ship[i][j] = new Element('4',id);
		}
		
		switch (rand){
		case 1: 
			if (style == 1){
			this.ship[1][1] = new Element('o',0);
			this.ship[1][3] = new Element('o',0);
			} else {
				this.ship[1][1] = new Element('o',0);
				this.ship[3][1] = new Element('o',0);
			}
			break;
		case 2: 
			if (style == 1){
			this.ship[1][2] = new Element('o',0);
			this.ship[1][3] = new Element('o',0);
			} else {
				this.ship[2][1] = new Element('o',0);
				this.ship[3][1] = new Element('o',0);
			}
			break;
		case 3: 
			if (style == 1){
			this.ship[1][1] = new Element('o',0);
			this.ship[1][2] = new Element('o',0);
			} else {
				this.ship[1][1] = new Element('o',0);
				this.ship[2][1] = new Element('o',0);
			}
			break;
		case 4: 
			if (style == 1){
			this.ship[1][1] = new Element('o',0);
			this.ship[2][3] = new Element('o',0);
			} else {
				this.ship[1][1] = new Element('o',0);
				this.ship[3][2] = new Element('o',0);
			}
			break;
		case 5: 
			if (style == 1){
			this.ship[1][3] = new Element('o',0);
			this.ship[2][1] = new Element('o',0);
			} else {
				this.ship[3][1] = new Element('o',0);
				this.ship[1][2] = new Element('o',0);
			}
			break;
		case 6: 
			if (style == 1){
			this.ship[2][3] = new Element('o',0);
			this.ship[2][1] = new Element('o',0);
			} else {
				this.ship[3][2] = new Element('o',0);
				this.ship[1][2] = new Element('o',0);
			}
			break;
		case 7: 
			if (style == 1){
			this.ship[2][3] = new Element('o',0);
			this.ship[2][2] = new Element('o',0);
			} else {
				this.ship[3][2] = new Element('o',0);
				this.ship[2][2] = new Element('o',0);
			}
			break;
		case 8: 
			if (style == 1){
			this.ship[2][2] = new Element('o',0);
			this.ship[2][1] = new Element('o',0);
			} else {
				this.ship[2][2] = new Element('o',0);
				this.ship[1][2] = new Element('o',0);
			}
			break;
		default:
			System.out.println("B³¹d przy tworzeniu klasa Ship");
		}
		
		
	}
	
	/**
	 * Utworzenie czteromasztowca. Uk³ad statku jest losowy.
	 * @param id id statku (unikatowe)
	 */
	private void shipType4(int id){
		int i, j;

		for (i = 0; i < this.x; i++){
			for (j = 0; j < this.y; j++)
				this.ship[i][j] = new Element('o',0);
		}
		
		if(this.x == this.y-3){
			for (int k = 1; k <= 4; k++){
				this.ship[1][k] = new Element('4',id);
			}
		}else{
			if(this.y == this.x-3){
				for (int k = 1; k <= 4; k++){
					this.ship[k][1] = new Element('4',id);
				}
			} else {
				shipType4a(id);
			}
		}
	}
	
	/**
	 * Sprawdzenie kolizji w danym miejscu (a,b) na planszy. 
	 * @param a okreslenie miejsca do sprawdzenia.
	 * @param b okreslenie miejsca do sprawdzenia.
	 * @param board plansza, na której jest sprawdzana kolizja.
	 * @return true b¹dz false w zale¿noœci od tego czy jest mo¿liwa kolizja.
	 */
	private boolean checkCollision(int a, int b, Board board){
		boolean answer = true;
		int i, j;
		switch (this.type){
		case '1':
			for (i=a-1; i <= a+1; i++){
				for(j=b-1; j <= b+1; j++){
					if(board.board[i][j].getState() != '0' && board.board[i][j].getState() != 'N' && board.board[i][j].getState() != 'o') 
						answer = false;
				}
			}
			break;
		default:
			if (a+this.x > board.SIZE || b+this.y > board.SIZE) answer = false;
			else{
				for (i=a; i < a+this.x; i++){
					for(j=b; j < b+this.y; j++){
						if(board.board[i][j].getState() != '0' && board.board[i][j].getState() != 'N' && board.board[i][j].getState() != 'o') 
							answer = false;
					}
				}
			}
		}
		return answer;
	}
}
