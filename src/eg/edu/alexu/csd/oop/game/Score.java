package eg.edu.alexu.csd.oop.game;

public class Score {
    private int score;
    public Score(){
        score=0;
    }
    public void increase(){
        score++;
    }
    public void decrease(){
        if(score>0)score--;
    }
    public int getScore(){
        return score;
    }
}
