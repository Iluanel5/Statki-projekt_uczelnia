import java.awt.EventQueue;

/**
 * G��wna funkcja powo�uj�ca i uruchamiaj�ca widziane okno.<br> 
 * Program dotyczy gry w statki.<br>
 * <br>
 * @author Karolina Niemira H5X1S1
 */
public class Main {

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
                
            }
        });
    }

}
