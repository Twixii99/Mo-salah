//package eg.edu.alexu.csd.oop.game.MainGui;
//
//import eg.edu.alexu.csd.oop.game.GameObject;
//import eg.edu.alexu.csd.oop.game.GameWorld;
//import eg.edu.alexu.csd.oop.game.Levels.Level;
//import eg.edu.alexu.csd.oop.game.snapshot.CareTaker;
//import eg.edu.alexu.csd.oop.game.snapshot.Memento;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.concurrent.TimeUnit;
//
//public class ReplayFrame extends JPanel {
//    private int xAxis[];
//    private int yAxis[];
//
//    /** array of level images */
//    private BufferedImage[] bufferedImages;
//    /** main frame */
//    private JFrame replayContainer;
//    /** image */
//    private BufferedImage image;
//    /** input data */
//    private CareTaker careTaker;
//
//    public ReplayFrame(CareTaker careTaker) throws InterruptedException, IOException {
//        /** instantiate caretakers */
//        this.careTaker = careTaker;
//
//        /** initialize array of images */
//        bufferedImages = new BufferedImage[100];
//
//        xAxis = new int[100];
//        yAxis = new int[100];
//
//        /** set the frame settings */
//        this.replayContainer = new JFrame("REPLAY");
//        this.replayContainer.setSize(new Dimension(1350, 700));
//        this.replayContainer.setLocationRelativeTo(null);
//        this.replayContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.replayContainer.setResizable(false);
//        this.replayContainer.setLayout(null);
//
//        /** set the panel settings */
//        super.setBounds(0,0,1350, 700);
//        super.setLayout(null);
//        //super.setBackground(Color.RED);
//
//        /** adding panel to the frame */
//        this.replayContainer.add(this);
//
//        /** set Visibility */
//        this.replayContainer.setVisible(true);
//
//        /** starting showing data */
//        this.showDataVisually();
//    }
//
//    public boolean showDataVisually() throws InterruptedException {
//        for (Object o : this.careTaker.getList()) {
//            this.reciever((Memento)o);
//            TimeUnit.SECONDS.sleep(1);
//        }
//        return true;
//    }
//
//    public void reciever(Memento memento) {
//        Level level = memento.getLevel();
//        LinkedList<GameObject> compresedList = this.compress(level);
//        for (int i = 0; i < compresedList.size(); ++i) {
//            this.xAxis[i] = compresedList.get(i).getX();
//            this.yAxis[i] = compresedList.get(i).getY();
//            this.bufferedImages[i] = compresedList.get(i).getSpriteImages()[0];
//        }
//    }
//
//    private LinkedList<GameObject> compress(Level level) {
//        LinkedList<GameObject> temp = new LinkedList<>();
//        for (GameObject o : level.ConstantElement)
//            temp.add(o);
//        for(GameObject o : level.movable)
//            temp.add(o);
//        for (GameObject o : level.control)
//            temp.add(o);
//        return temp;
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        int i = 0;
//        while(bufferedImages[i++] != null)
//            g.drawImage(bufferedImages[i], xAxis[i], yAxis[i], this);
//    }
//}
package eg.edu.alexu.csd.oop.game.MainGui;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Levels.Level;
import eg.edu.alexu.csd.oop.game.snapshot.CareTaker;
import eg.edu.alexu.csd.oop.game.snapshot.Memento;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ReplayFrame extends JPanel {
    private int xAxis;
    private int yAxis;

//    public static void main(String[] args) {
//        new ReplayFrame();
//    }
    /** main frame */
    private JFrame replayContainer;
    /** image */
    private BufferedImage image;
    /** input data */
    private CareTaker careTaker;

    public ReplayFrame(CareTaker careTaker) throws InterruptedException, IOException {
        /** instantiate caretakers */
        this.careTaker = careTaker;

        /** set the frame settings */
        this.replayContainer = new JFrame("REPLAY");
        this.replayContainer.setSize(new Dimension(1350, 700));
        this.replayContainer.setLocationRelativeTo(null);
        this.replayContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.replayContainer.setResizable(false);
        this.replayContainer.setLayout(null);

        /** set the panel settings */
        super.setBounds(0,0,1350, 700);
        super.setLayout(null);
        //super.setBackground(Color.RED);

        /** adding panel to the frame */
        this.replayContainer.add(this);

        /** set Visibility */
        this.replayContainer.setVisible(true);

        /** starting showing data */
        this.showDataVisually();
    }

    public boolean showDataVisually() throws InterruptedException {
        for (Object o : this.careTaker.getList()) {
            this.reciever((Memento)o);
            TimeUnit.SECONDS.sleep(1000);
        }
        this.removeAll();
        return true;
    }

    public void reciever(Memento memento) {
        Level level = memento.getLevel();
        LinkedList<GameObject> compresedList = this.compress(level);
        for (GameObject gameObject : compresedList){
            this.xAxis = gameObject.getX();
            this.yAxis = gameObject.getY();
            this.image = gameObject.getSpriteImages()[0];
            paintComponent(this.getGraphics());
        }
    }

    private LinkedList<GameObject> compress(Level level) {
        LinkedList<GameObject> temp = new LinkedList<>();
        for (GameObject o : level.ConstantElement)
            temp.add(o);
        for(GameObject o : level.movable)
            temp.add(o);
        for (GameObject o : level.control)
            temp.add(o);
        return temp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, xAxis, yAxis, this);
    }

}