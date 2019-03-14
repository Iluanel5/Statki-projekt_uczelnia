import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Klasa tworz¹ca panel i obs³uguj¹ca ca³¹ grê. Obs³uguje równie¿ 
 * klikniêcie myszy oraz przyciski.
 * @author Karolina Niemira H5X1S1
 * 
 */
public class GamePanel extends JPanel implements MouseListener, ActionListener{
	private Graphics2D gr;
	private int x, y; //tu klikniêto;
	static final int pixelA = 50;
	static final int pixelB = 80;
	static final char[] letters = {'A','B','C','D','E','F','G','H','I','J'};
	private Board board1;
	private Board board2;
	private Player[] player = new Player[2];
	private int err=0;
	private int round, playerType = 0;
	private int flag[] = {4, 0, 4};
	private JButton button1, button2, button3;
	JRadioButton RButton1, RButton2;
	private Random rand = new Random();
	
	/**
	 * Konstruktor jedynie dodaje obs³ugê przycisku myszy do gry 
	 * oraz wywo³uje metodê buttons().
	 */
	public GamePanel(){
		addMouseListener(this);
		buttons();
	}
	
	/**
	 * Odpowiada za utworzenie wszelkich parametrów dla wszystkich przycisków.
	 */
	private void buttons(){
		this.button2 = new JButton("Wróæ do menu");
		this.button1 = new JButton("Graj");
		this.button3 = new JButton("Reset");
		this.RButton1 = new JRadioButton("Gracz vs Komputer");
	    this.RButton1.setMnemonic(KeyEvent.VK_B);
	    this.RButton1.setActionCommand("GvsK");
	    this.RButton2 = new JRadioButton("Gracz vs Gracz");
	    this.RButton2.setMnemonic(KeyEvent.VK_B);
	    this.RButton2.setActionCommand("GvsG");
		setLayout(null);
		this.button2.addActionListener(this);
		this.button2.setBounds(320, 500, 120, 30);
		this.button1.addActionListener(this);
		this.button1.setBounds(300, 400, 200, 50);
		this.button3.addActionListener(this);
		this.button3.setBounds(320, 450, 120, 30);
		this.RButton1.addActionListener(this);
		this.RButton1.setFont(new Font("Arial", Font.CENTER_BASELINE,18));
		this.RButton1.setBackground(Color.WHITE);
		this.RButton1.setBounds(100, 100, 200, 30);
		this.RButton2.addActionListener(this);
		this.RButton2.setFont(new Font("Arial", Font.CENTER_BASELINE,18));
		this.RButton2.setBackground(Color.WHITE);
		this.RButton2.setBounds(100, 150, 200, 30);
	    
	    RButton1.setSelected(true);
	    
	}
	
	/**
	 * Odpowiada za Rysowanie i wyœwietlanie elementów Panelu.<br>
	 * Wyœwietlana zawartoœæ zale¿y od wartoœci flagi, które zostan¹ ustawione 
	 * po przyciœniêciu przycisków.
	 */
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		this.gr = g2d;
		this.gr.setColor(Color.WHITE);
		this.gr.fillRect(0, 0, getWidth(), getHeight());
		this.gr.setColor(Color.black);
		
		if (this.flag[0] != 4){
			
			add(this.button2);
			add(this.button3);
			
			if(this.playerType == 0){
				paintMyBoard(this.board1);
				paintOpponentBoard(this.board2);
			} else {
				if (this.round == 1){
					paintMyBoard(this.board1);
					paintOpponentBoard(this.board2);
				}
				if (this.round == 2){
					paintMyBoard(this.board2);
					paintOpponentBoard(this.board1);
				}
			}
				
		
			g2d.setColor(Color.lightGray);
			this.gr.fillRect(pixelA, 400, 250, 70);
			this.gr.fillRect(pixelA+420, 400, 260, 100);
			//this.board1.drawBoardTest();
		
			g2d.setColor(Color.black);
			this.gr.setFont(new Font("TimesRoman", Font.BOLD+Font.ITALIC,20));
			this.gr.drawString("Kapitan Cyfrobrody v4.0", 30, 30);

			this.gr.setFont(new Font("Arial", Font.CENTER_BASELINE,15));
			this.gr.drawString("Punktacja", pixelA+440+70, 425);
			this.gr.drawString(this.player[0].getName()+": "+this.player[0].viewScore(), pixelA+440, 455);
			this.gr.drawString(this.player[1].getName()+": "+this.player[1].viewScore(), pixelA+440, 485);
			
			if (checkEndGame()){
				this.flag[0] = 10;
			}
	  	
			switch (this.flag[0]){
			case 0:
				this.gr.drawString("Pud³o", pixelA+20, 455);
				
				if(this.err == 0){
	  				if(this.round == 1)
	  					this.round = 2;
	  				else
	  					this.round = 1;
				}
				if (this.playerType != 0 ) {
					this.flag[0] = 5;
					repaint(); 
				}
				break;
			case 1:
				if (this.round == 2) this.flag[2] = 1;
				this.gr.drawString("Trafiony p³ywa", pixelA+20, 455);
				break;
			case 2:
				if (this.round == 2) this.flag[2] = 2;
				this.gr.drawString("Trafiony zatopiony", pixelA+20, 455);
	  		
				break;
			case 3:
				if (this.round == 2) this.flag[2] = 3;
				this.gr.drawString("Nie mo¿esz tu strzeliæ", pixelA+20, 455);
				break;
			case 10:
				this.gr.drawString("Wygrywa "+getWinner(), pixelA+20, 455);
				this.player[0].changeId(3);
				this.player[1].changeId(3);
				break;
			}
			
			this.gr.drawString("Teraz aktywny: "+this.player[round-1].getName(), pixelA+20, 425);
	  	
			if (this.err != 0){
				this.err = 0;
			}
	  	
			if(this.round == 2 && this.player[1].getId() == 0)
				playAsComputer(this.board1);
			
		} else {
			//ButtonGroup group = new ButtonGroup();
			this.gr.setFont(new Font("Arial", Font.CENTER_BASELINE,20));
			this.gr.drawString("Wybierz tryb gry", 80, 60);
			add(this.RButton1);
			add(this.RButton2);

			add(this.button1);
		}	
	}
	
	/**
	 * Pobiera imie gracza, który wygra³ grê.
	 * @return imie gracza, który zwyciê¿y³.
	 */
	private String getWinner(){
		if (this.player[0].viewScore() > this.player[1].viewScore()) 
			return this.player[0].getName();
		else 
			return this.player[1].getName();

	}
	
	/**
	 * Sprawdza, czy gra ju¿ siê zakoñczy³a.
	 *		Punkty:<br>
	 * <ul>
	 * 			<li>+1 za trafienie, ale statek wci¹¿ p³ywa<br>
	 * 			<li>+2 za zatopienie<br>
	 * </ul>
	 * @return true b¹dz false, w zale¿noœci czy liczba punktów gracza wynios³a minimum 30.
	 */
	private boolean checkEndGame(){
		boolean answer = false;
		int playerScore = 0, minScore = 30;
		if (this.player[0].viewScore() > this.player[1].viewScore()) playerScore = this.player[0].viewScore();
		else 
			if (this.player[0].viewScore() < this.player[1].viewScore()) playerScore = this.player[1].viewScore();
		
		if (playerScore >= minScore) answer = true;
		
		return answer;
	}
	
	/**
	 * Po zatopieniu statku zostaje on otoczony polami '*', 
	 * czyli polami pustego trafienia, aby pokazaæ, i¿ statek 
	 * zosta³ zatopiony.
	 * @param board plansza na której zatopiono statek
	 * @param a zmienna do okreslenia miejsca ostatniego strza³u
	 * @param b zmienna do okreslenia miejsca ostatniego strza³u
	 */
	private void destroy(Board board, int a, int b){
		
		for (int i = 1; i < 11; i++){
			for (int j = 1; j < 11; j++){
				if (board.board[i][j].getID() == board.board[a][b].getID()){
					for (int k = i-1; k <= i+1; k++)
						for (int l = j-1; l <= j+1; l++)
							if (board.board[k][l].getState() == 'o') {
								board.board[k][l].changeState('*');
								board.board[k][l].changePriority(0);
							}
				}
			}
		}
	}
	
	/**
	 * Powo³anie do istnienia wszystkich wymaganych statków. 
	 * £¹cznie zostaje ich powo³anych 10 o id od 1 do 10.
	 * Na koniec losowany jest jeden element specjalny, posiadaj¹cy 
	 * mo¿liwoœæ zniszczenia wszystkich statków.
	 * @param board plansza, na której pojawi¹ siê statki
	 */
	private void setShips(Board board){
		int i = 0, j = 1;
		
		//Czteromasztowce
		Ship ship4 = new Ship('4');
		ship4.setShip(board, j);
		j++;
		
		//Trzymasztowce
				Ship ship3[] = {new Ship('3'), new Ship('3')};
				for (i=0; i<2; ++i)
					ship3[i].setShip(board, j+i);
		j+=2;
		//Dwumasztowce
		Ship ship2[] = {new Ship('2'), new Ship('2'), new Ship('2')};
		for (i=0; i<3; ++i)
			ship2[i].setShip(board, j+i);
		j+=3;
		
		//Jednomasztowce
		Ship ship1[] = {new Ship('1'),new Ship('1'),new Ship('1'),new Ship('1')};
		for (i=0; i<4; ++i)
			ship1[i].setShip(board,j+i);
		j+=4;
		
		int tmp1 = rand.nextInt(10)+1;
		int tmp2 = rand.nextInt(10)+1;
		int k = 1;
		do{
		if(board.board[tmp1][tmp2].getType() != 0){
			System.out.println("["+tmp1+"]["+tmp2+"] niszczy");
			board.board[tmp1][tmp2].changeApo(1);
			k = 0;
		} else {
			tmp1 = rand.nextInt(10)+1;
			tmp2 = rand.nextInt(10)+1;
		}
		}while(k != 0);
		
		board.setFrame();
	}

	/**
	 * Uruchamia siê trafieniu w element statku, który posiada mo¿liwoœæ zniszczenia 
	 * wszystkich i automatyczn¹ wygran¹.
	 * @param board plansza przeciwnika
	 */
	private void destroyAll(Board board){
		for(int i = 1; i<11; i++){
			for(int j = 1; j<11; j++){
				if(board.board[i][j].getType() != 0){
					destroy(board, i, j);
					board.board[i][j].changeState('x');
					board.board[i][j].changePriority(0);
					this.player[this.round-1].addAllScore(30);
				}
			}
		}
	}
	/**
	 * Maluje jawn¹ planszê z kolorowymi statkami. Ró¿owa kropka 
	 * to element specjalny niszcz¹cy wszystkie statki.
	 * @param board plansza, która ma zostaæ namalowana
	 */
	private void paintMyBoard(Board board){
		this.gr.setFont(new Font("Arial", Font.CENTER_BASELINE,12));
		
		int width = board.board[1][1].WIDTH;
		int height = board.board[1][1].HEIGHT;
		for (int i = 1; i < 11; ++i){
			this.gr.drawString(" "+letters[i-1]+" ", board.getPixel('a')+(width*(i-1))+(width/2-8), board.getPixel('b') - 10);
			for (int j = 1; j < 11; ++j){
				this.gr.drawString(" "+j+" ", board.getPixel('a') - 20, board.getPixel('b')+(height*(j-1))+(height/2+8));
				this.gr.drawRect(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);
				
				switch (board.board[i][j].getType()){
				case 1: 
					this.gr.setColor(Color.cyan);
					this.gr.fillRect(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);

					break;
				case 2: 
					this.gr.setColor(Color.green);
					this.gr.fillRect(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);
					break;
				case 3: 
					this.gr.setColor(Color.orange);
					this.gr.fillRect(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);
					break;
				case 4: 
					this.gr.setColor(Color.red);
					this.gr.fillRect(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);
					break;
				}
				if (board.board[i][j].getApo() == 1) {
					this.gr.setColor(Color.pink);
					this.gr.fillOval(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);
				}
				this.gr.setColor(Color.black);
				if(board.board[i][j].getState() == 'x'){
					
					this.gr.drawLine(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), board.getPixel('a')+((i-1)*width)+width, board.getPixel('b')+((j-1)*height)+height);
					this.gr.drawLine(board.getPixel('a')+((i-1)*width)+width, board.getPixel('b')+((j-1)*height), board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height)+height);
	
				}
				if (board.board[i][j].getState() == '*'){
					this.gr.fillOval(board.getPixel('a')+((i-1)*width)+10, board.getPixel('b')+((j-1)*height)+10, width-20, height-20);
				}
			}
		}
	}

	/**
	 * Maluje planszê z ukrytymi statkami.
	 * @param board plansza, która ma zostaæ namalowana
	 */
	private void paintOpponentBoard(Board board){
		this.gr.setFont(new Font("Arial", Font.CENTER_BASELINE,12));
		
		int width = board.board[1][1].WIDTH;
		int height = board.board[1][1].HEIGHT;
		for (int i = 1; i < 11; ++i){
			gr.setColor(Color.black);
			this.gr.drawString(" "+letters[i-1]+" ", board.getPixel('a')+(width*(i-1))+(width/2-8), board.getPixel('b') - 10);
			for (int j = 1; j < 11; ++j){
				gr.setColor(Color.black);
				this.gr.drawString(" "+j+" ", board.getPixel('a') - 20, board.getPixel('b')+(height*(j-1))+(height/2+8));
				this.gr.drawRect(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), width, height);
				if(board.board[i][j].getState() == 'x'){
					gr.setColor(Color.black);
					this.gr.drawLine(board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height), board.getPixel('a')+((i-1)*width)+width, board.getPixel('b')+((j-1)*height)+height);
					this.gr.drawLine(board.getPixel('a')+((i-1)*width)+width, board.getPixel('b')+((j-1)*height), board.getPixel('a')+((i-1)*width), board.getPixel('b')+((j-1)*height)+height);
				}
				if (board.board[i][j].getState() == '*'){
					gr.setColor(Color.black);
					this.gr.fillOval(board.getPixel('a')+((i-1)*width)+10, board.getPixel('b')+((j-1)*height)+10, width-20, height-20);
				}
			}
		}
	}

	/**
	 * Sprawdza, czy statek zosta³ zatopiony
	 * @param board plansza, na której nale¿y sprawdziæ
	 * @param x zmienna okreœlaj¹ca po³o¿enie ostatniego trafionego elementu statku
	 * @param y zmienna okreœlaj¹ca po³o¿enie ostatniego trafionego elementu statku
	 * @return true b¹dz false w zale¿noœci czy statek zaton¹³
	 */
	private boolean checkFloats(Board board, int x, int y){
		boolean answer = true;
		for (int i = 1; i < 11; i++){
			for (int j = 1; j < 11; j++){
				if(board.board[x][y].getID() == board.board[i][j].getID() && board.board[i][j].getDestroy() != 0)
					answer = false;	
			}
		}
		return answer;
	}

	/**
	 * Sprawdza ca³e klikniête pole. To tu okreœlane jest, czy statek zosta³ trafiony,
	 * czy klikniête miejsce nie jest poza aktywn¹ plansz¹ i ca³a obs³uga strza³u.
	 * @param board plansza, na której bêd¹ sprawdzane parametry strza³u (plansza przeciwnika aktywnego gracza)
	 */
	private void checkType(Board board){
		int a=0,b=0;
		try {
			
			if (this.round == 2){
				if (this.x >= pixelA && this.x <= pixelA+300) a = (this.x-pixelA)/30+1;
				else throw new Exceptions(1);
				if (this.y >= pixelB && this.y <= pixelB+300) b = (this.y-pixelB)/30+1;
				else throw new Exceptions(1);
			}
			
			if (this.round == 1){
				if (this.x >= 400+pixelA && this.x <= pixelA+700) a = ((this.x-400-pixelA)/30)+1;
				else throw new Exceptions(1);
				if (this.y >= pixelB && this.y <= pixelB+300) b = ((this.y-pixelB)/30)+1;
				else throw new Exceptions(1);
			}

			if(board.board[a][b].getState() !='o' && board.board[a][b].getState() !='0'){
				if (board.board[a][b].getState() !='x' && board.board[a][b].getState() !='*'){
					board.board[a][b].changeDestroy(board.board[a][b].getDestroy()-1);
					//System.out.println("Trafiony: "+board.board[a][b].type);
					board.board[a][b].changeState('x');
					board.board[a][b].changePriority(0);
					if(board.board[a][b].getApo() == 1) destroyAll(board);
					else{
					if (!checkFloats(board, a, b)){
						this.player[this.round-1].addScore(1);
						this.flag[0] = 1;
						if (board.board[a-1][b].getType() != 'N')
							board.board[a-1][b].changePriority(1);
						if (board.board[a+1][b].getType() != 'N')
							board.board[a+1][b].changePriority(1);
						if (board.board[a][b-1].getType() != 'N')
							board.board[a][b-1].changePriority(1);
						if (board.board[a][b+1].getType() != 'N')
							board.board[a][b+1].changePriority(1);
					} else {
						this.player[this.round-1].addScore(2);
						this.flag[0] = 2;
						destroy(board, a, b);
					}
					}
					repaint();
				} else throw new Exceptions(2);
			} else {
				board.board[a][b].changeState('*');
				board.board[a][b].changePriority(0);
				this.flag[0] = 0;
				
			}
		}catch(Exceptions e){ ++this.err; this.flag[0] = 3; }
		repaint();
	}
	
	/**
	 * Sprawdzenie priorytetu sprza³u. Komputer sprawdza czy 
	 * ma jakieœ pola o wiêkszym priorytecie strza³u.
	 * @param board - plansza przeciwnika aktywnego gracza
	 * @return true b¹dz false w zale¿noœci od istnienia priorytetu.
	 */
	private boolean checkPriority(Board board){
		
		for(int i = 1; i < 11; i++){
			for(int j = 1; j < 11; j++){
				if(board.board[i][j].getPriority() != 0) return true;
			}
		}
		return false;
	}
	
	/**
	 * Inteligencja gracza Komputer. Gracz Komputer strzela w losowe 
	 * miejsce. Jeœli trafi, to cztery s¹siaduj¹ce pola od trafienia, 
	 * gdzie do tej pory nie by³o ¿adnego strza³u otrzymuj¹ wiêkszy 
	 * priorytet ostrza³u.<br><br>
	 * Jeœli istnieje na planszy jakikolwiek priorytet ostrza³u, to 
	 * Komputer strzela w jedno z miejsc z priorytetem wiêkszym od zera.
	 * W przeciwnym wypadku Komputer przeprowadza losowy ostrza³ na planszy.
	 * @param board plansza przeciwnika
	 */
	private void playAsComputer(Board board){
		Random random = new Random();
		
		
		if(!checkPriority(board)){
			this.x = random.nextInt(300)+pixelA;
			this.y = random.nextInt(300)+pixelB;
			
		} else {
			for (int i = 1; i < 11; i++){
				for (int j = 1; j < 11; j++){
					if(board.board[i][j].getPriority() != 0){
						this.x = (i-1)*30+pixelA;
						this.y = (j-1)*30+pixelB;
						board.board[i][j].changePriority(0);
						i = 11;
						j = 11;
					}
				}
			}	
		}
		checkType(board);
	}
	
	/**
	 * Odpowiada za zresetowanie gry. Powo³uje nowych graczy, nowe 
	 * plansze, nowe statki, zeruje punktacjê i rozpoczyna grê na nowo.
	 */
	private void resetGame(){
		this.round = 1;
		this.err = 0;
		this.player[0] = new Player("Gracz 1", 1); 
		this.player[1] = new Player("Gracz 2", this.playerType);
		this.board1 = new Board(pixelA, pixelB);
		this.board2 = new Board(pixelA+400, pixelB);
		
		setShips(this.board1);
		setShips(this.board2);
	}

	/**
	 * Obs³uga naciœniêcia przycisku myszy.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		
		this.x = arg0.getX();
		this.y = arg0.getY();
		
		if(this.round == 1)
			checkType(this.board2);
		else
			if (this.round == 2 && this.player[1].getId() == 2){
				checkType(this.board1);
			}
		
	}
	
	
	/**
	 * Obs³uga przycisków.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		if(source == this.RButton1){
			RButton2.setSelected(false);
			this.playerType = 0;
		}
		
		if(source == this.RButton2){
			RButton1.setSelected(false);
			this.playerType = 2;
		}
		
		if(source == this.button1){
			this.flag[0] = 5;
			resetGame();

			remove(this.button1);
			remove(this.RButton1);
			remove(this.RButton2);
			//System.out.println("siemanko butt 1");
			repaint();
		}
		
		if(source == this.button2){
			this.flag[0] = 4;
			remove(this.button2);
			remove(this.button3);
			
			repaint();
		}
		
		if(source == this.button3){
			this.flag[0] = 5;
			resetGame();
			
			repaint();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0)  {}
	@Override
	public void mouseEntered(MouseEvent arg0)  {}
	@Override
	public void mouseExited(MouseEvent arg0)   {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
