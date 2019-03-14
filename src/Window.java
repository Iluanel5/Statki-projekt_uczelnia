import javax.swing.JFrame;

/**
 * Klasa odpowiada za utworzenie g��wnego okna gry (ramki).<br>
 * Wysoko�� i szeroko�� jest ustawiona statycznie.
 * 
 * @author Karolina Niemira H5X1S1
 */
public class Window extends JFrame {
	
	static final int HEIGHT = 600;
	static final int WIDTH = 800;
	
	/**
	 * Konstruktor ustawia podstwowe parametry okienka (ramki) dla 
	 * ca�o�ci gry, a na koniec dodaje nowy GamePanel.
	 */
	public Window(){
		super("Cyfrobrody - v.4.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);//okienko na �rodku ekranu
        setResizable(false);		//blokada zmiany rozmiaru
        add(new GamePanel());
        setVisible(true);
	}
}
