
import java.util.Scanner;

public class Player {

    private Scanner scanner;

    private int score;
    private boolean hasJoker;

    public Player() {
        this.score = 12;
        this.hasJoker = true;

        scanner = new Scanner(System.in);
    }

    public String play() {
        System.out.print("Guess: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public void decrementScoreBy(int x) {
        this.score -= x;
    }

    public void incrementScoreBy(int x) {
        this.score += x;
    }

    public void decrementScore() {
        this.score--;
    }

    public void incrementScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean hasJoker() {
        return hasJoker;
    }

    public void setHasJoker(boolean hasJoker) {
        this.hasJoker = hasJoker;
    }
}
