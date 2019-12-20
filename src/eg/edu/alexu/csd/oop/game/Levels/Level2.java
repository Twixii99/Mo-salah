package eg.edu.alexu.csd.oop.game.Levels;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.ImageObject;

import java.util.LinkedList;

public class Level2 extends Level {


    @Override
    public int getNumOfRequiredBalls() {
        return 2;
    }


    @Override
    public int ShiftY() {
        return 2;
    }

    @Override
    public int getNumOfEnemy() {
        return 2;
    }

    @Override
    public int getNumOfballs() {
        return 6;
    }

    @Override
    public Level clone() {
        Level level = new Level2();
        LinkedList<GameObject> newConstants = new LinkedList<>();
        for (GameObject o : this.ConstantElement) newConstants.add(((ImageObject)o).clone());
        level.setConstantElement(newConstants);

        LinkedList<GameObject> newControl = new LinkedList<>();
        for (GameObject o : this.control) newControl.add(((ImageObject)o).clone());
        level.setControl(newControl);

        LinkedList<GameObject> newMovable = new LinkedList<>();
        for (GameObject o : this.movable) newMovable.add(((ImageObject)o).clone());
        level.setMovable(newMovable);

        level.setHeight(this.height);
        level.setWidth(this.width);
        return level;
    }


}
