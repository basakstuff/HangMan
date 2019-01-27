

public class Main {

    public static void main(String[] args) {

        String[] words = {
                "monkey", "penguin", "dolphin", 
                "umbrellabird", "wildebeest", "tarantula",
                "chimpanzee", "ewe", "chipmunk",
                "warthog", "doe", "wildcat",
                "hartebeest", "camel"
        };

        Board board = new Board(words);

        while (board.tick()) ;

        board.printState();
        if (board.getPlayer().getScore() == 0) {
            System.out.println("You lost!");
        } else {
            System.out.println("You won!");
        }

    }
}
