package LindXdeep.calculator;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

	public Main() {
		JFrame calcWin = new JFrame("Calculator");
				calcWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				calcWin.setSize(400, 697);
				calcWin.setPreferredSize(new Dimension(calcWin.getWidth(), calcWin.getHeight()));
				calcWin.add(new Panel(calcWin));
				calcWin.setLocationRelativeTo(null);
				calcWin.setVisible(true);
				calcWin.pack();	
				calcWin.setResizable(false);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Main();
			}
		});
	}
}
