package eg.edu.alexu.csd.oop.game;
import eg.edu.alexu.csd.oop.game.MainGui.MainFrame;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
	
	public static void main(String[] args)  {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");
 		SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
	}
}

