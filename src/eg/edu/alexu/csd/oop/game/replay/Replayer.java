package eg.edu.alexu.csd.oop.game.replay;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;
import eg.edu.alexu.csd.oop.game.Levels.Level;
import eg.edu.alexu.csd.oop.game.snapshot.CareTaker;
import eg.edu.alexu.csd.oop.game.snapshot.Memento;

public class Replayer  {
    public Replayer(CareTaker careTaker){
        for (Object level : careTaker.getList()) {
            printx((Memento) level);
            //levelDrawer((Memento) level);
        }
    }

    private void printx(Memento level) {
        Level lx = level.getLevel();
        System.out.println(lx.control.size() + "\t" + lx.movable.size());
    }

    private void levelDrawer(Memento level){
        System.out.println("level ----- \n\n");
        printLevelDetails(level.getLevel());
    }

    private void printLevelDetails(Level lev){
        System.out.println("\nconst");
        for(GameObject c : lev.ConstantElement){
            System.out.println(c.getClass().getName());
            System.out.println("x: " + c.getX() + " y: " + c.getY());
        }
        System.out.println("\ncontrol");
        for(GameObject c : lev.control){
            System.out.println(c.getClass().getName());
            System.out.println("x: " + c.getX() + " y: " + c.getY());
        }
        System.out.println("\nmovable");
        for(GameObject c : lev.movable){
            System.out.println(c.getClass().getName());
            System.out.println("x: " + c.getX() + " y: " + c.getY());
        }
    }

}
