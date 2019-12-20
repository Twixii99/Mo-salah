package eg.edu.alexu.csd.oop.game.snapshot;

import eg.edu.alexu.csd.oop.game.Levels.Level;

public class Memento {
    private Level level;

    public Memento(Level level){
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }


}
