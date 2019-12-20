package eg.edu.alexu.csd.oop.game.MainGui;

import eg.edu.alexu.csd.oop.game.GameWorld;
import eg.edu.alexu.csd.oop.game.Levels.*;
import eg.edu.alexu.csd.oop.game.replay.Replayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class NewOptions {
//    public static void main(String[] args) {
//        new NewOptions();
//    }

    /** frame for new Options */
    private JFrame newOptionsFrame;
    /** panel of contents */
    private JPanel panel;
    /** Starting button */
    private MainButtons start;
    /** text field */
    private JTextField text;
    /** combo box contains levels */
    private static JComboBox<String> levels;
    /** name and Score of the player */
    private String metaData;
    /** replay Button */
    private MainButtons replay;

    public NewOptions() {
        this.panel = new JPanel();
        this.text = new JTextField();
        this.levels = new JComboBox<>(new String[]{"level1", "level2", "level3", "level4", "level5"});
        this.start = new MainButtons("START");
        this.replay = new MainButtons("REPLAY");
        this.newOptionsFrame = new JFrame("New Game");
        /** set data of the frame */
        this.newOptionsFrame.setSize(900, 500);
        this.newOptionsFrame.setResizable(false);
        this.newOptionsFrame.setLocationRelativeTo(null);
        this.newOptionsFrame.setLayout(null);
        this.newOptionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.newOptionsFrame.setContentPane(new JLabel(new ImageIcon("photos/bg.jpg")));
        /** set data of the panel */
        this.panel.setBackground(new Color(0,0,0,150));
        this.panel.setBounds(50, 220, 800, 175);
        this.panel.setLayout(null);
        /** textField settings */
        this.text.setBounds(100,20,600,25);
        this.text.setText("Enter name");
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.text.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        this.text.setForeground(Color.BLACK);
        this.text.setEnabled(false);
        this.text.setBorder(null);
        this.text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                text.setForeground(Color.BLACK);
                text.setBackground(Color.WHITE);
                text.setEnabled(true);
                text.setText("");
            }
        });
        /** set settings for levels */
        this.levels.setBounds(125,60,550,25);
        this.levels.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 14));
        this.levels.setForeground(Color.BLACK);
        /** set new start button settings */
        this.start.setBounds(450, 100, 200, 50);
        this.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                xnotify();
                newOptionsFrame.setVisible(false);
            }
        });
        /** set replay button settings */
        this.replay.setBounds(150, 100, 200, 50);
        this.replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new ReplayFrame(GameWorld.getCareTaker());
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /** adding components */
        this.panel.add(this.text);
        this.panel.add(this.levels);
        this.panel.add(this.replay);
        this.panel.add(this.start);
        this.newOptionsFrame.add(this.panel);
        /** set visibility */
        this.newOptionsFrame.setVisible(true);
    }

    public static void xnotify() {
        MainFrame.updateForNewGame();
    }

    public static Level getNewLevel() {
        String choosedLevel = levels.getSelectedItem().toString();
        if(choosedLevel.equals("level1"))
            return new Level1();
        else if(choosedLevel.equals("level2"))
            return new Level2();
        else if(choosedLevel.equals("level3"))
            return new Level3();
        else
            return new Level4();
    }
}
