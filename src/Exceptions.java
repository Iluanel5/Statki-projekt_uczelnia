/**
 * Klasa dla rzucanych wyj¹tków.
 * 
 * @author Karolina Niemira H5X1S1
 *
 */
public class Exceptions extends Exception {
	Exceptions(int a){
		switch (a){
		case 1:
			System.out.println("Wyszed³eœ poza planszê!");
			break;
		case 2:
			System.out.println("Tu ju¿ strzela³eœ!");
			break;
		default:
			System.out.println("B³¹d");
		}
	}
}