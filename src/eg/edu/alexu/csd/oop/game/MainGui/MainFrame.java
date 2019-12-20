package eg.edu.alexu.csd.oop.game.MainGui;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameWorld;
import eg.edu.alexu.csd.oop.game.Levels.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame {

	/** image put as icon */
	private ImageIcon exitIcon = null;
	/** instence of OptionButtons */
	private OptionsButtons options = null;
	/** instence of NewGamePanel */
	private NewGamePanel newGame = null;
	/** menu instances data */
	private static JMenuBar menuBar = new JMenuBar();
	/** container of options */
	private JMenu menu;
	/** to begin a new game */
	private JMenuItem newMenuItem;
	/** to pause this game */
	private JMenuItem pauseMenuItem;
	/** Resume game */
	private JMenuItem resumeMenuItem;
	/** Begainning game */
	private static GameEngine.GameController gameController;
	/** initialize new frame for starting new game */
	private static NewOptions newOptions;

	public MainFrame() {
		super.setTitle("Circus Game");
		super.setSize(1200,700);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setIconImage(Toolkit.getDefaultToolkit().getImage("photos/circus.png"));
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		/** 
		*	there is some magic on this area I don't really know how it works 
		*/
		super.setLayout(new BorderLayout());
		super.setContentPane(new JLabel(new ImageIcon("photos/bg.jpg")));
		super.setLayout(new FlowLayout(0,450,175));

		this.options = OptionsButtons.getInstance();
		this.newGame = NewGamePanel.getInstance();
		this.addSettingsToButtons();
		super.add(options);

		super.setSize(1199, 699);
		super.setSize(1200, 700);

		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitIcon = new ImageIcon("photos/icons8-question-mark-64.png");    
				int check = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure to exit?", "EXIT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, exitIcon);
				if(check == JOptionPane.YES_OPTION)
					MainFrame.this.dispose();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("The starting main programm terminated succesfully...");
			}
		});

		this.menu = new JMenu("File");
		this.newMenuItem = new JMenuItem("New");
		this.menu.add(newMenuItem);
		this.menu.addSeparator();
		this.pauseMenuItem = new JMenuItem("Pause");
		this.menu.add(pauseMenuItem);
		this.menu.addSeparator();
		this.resumeMenuItem = new JMenuItem("Resume");
		this.menu.add(resumeMenuItem);
		this.menuBar.add(menu);
		this.setMenuSettings();
	}

	private void setMenuSettings() {
		this.newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				newOptions = new NewOptions();
			}
		});

		this.pauseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				gameController.pause();
			}
		});

		this.resumeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				gameController.resume();
			}
		});
	}

	private void addSettingsToButtons() {
		options.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				repaint();
				setLayout(null);
				add(newGame);
			}
		});

		newGame.getDoneButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField text = newGame.getTextField();
				String player = text.getText();
				if(player.equals("") || player.equals("Enter your name")) {
					text.setBackground(Color.RED);
					text.setText("PLEASE ENTER YOUR NAME");
					text.setEnabled(false);
				} else {
				dispose();
				String level = newGame.getSelectedLevel();
				Level lev;

				if (level.equals("Level1"))
					lev = new Level1();
				else if (level.equals("Level2"))
					lev = new Level2();
				else if (level.equals("Level3"))
					lev = new Level3();
				else
					lev = new Level4();
				gameController = GameEngine.start("Circus Game", new eg.edu.alexu.csd.oop.game.GameWorld(1350, 700,lev), menuBar, Color.WHITE);
			}}
		});
	}

	public static void updateForNewGame() {
		gameController.changeWorld(new eg.edu.alexu.csd.oop.game.GameWorld(1350, 700, newOptions.getNewLevel()));
		//gameController = GameEngine.start("Circus Game", new eg.edu.alexu.csd.oop.game.GameWorld(1350, 700,newOptions.getNewLevel()), menuBar, Color.WHITE);
	}
}