/**
 * Klasa dla rzucanych wyj�tk�w.
 * 
 * @author Karolina Niemira H5X1S1
 *
 */
public class Exceptions extends Exception {
	Exceptions(int a){
		switch (a){
		case 1:
			System.out.println("Wyszed�e� poza plansz�!");
			break;
		case 2:
			System.out.println("Tu ju� strzela�e�!");
			break;
		default:
			System.out.println("B��d");
		}
	}
}