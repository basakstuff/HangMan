
import java.util.Arrays;
import java.util.Random;

public class Board {

	private Player player;

	private int selectedWordLength;
	private CharStack wordStack;
	private CharStack boardStack;
	private CharStack letterStack;
	private CharStack missingLetterStack;

	private WordStack animalStack;

	private char[] vowels = new char[] { 'a', 'e', 'i', 'o', 'u' };

	public Board(String[] words) {
		this.player = new Player();

		this.wordStack = new CharStack();
		this.boardStack = new CharStack();
		this.letterStack = new CharStack();
		this.missingLetterStack = new CharStack();

		// build letter stack
		for (int i = 97 + 26 - 1; i >= 97; i--) {
			this.letterStack.push(((char) i));
		}

		// build animal stack
		this.animalStack = new WordStack();
		for (String word : words) {
			animalStack.push(word);
		}

		// choose an animal from animals
		Random random = new Random();
		String chosenWord = animalStack.at(random.nextInt(words.length));
		selectedWordLength = chosenWord.length();

		chosenWord = reverseString(chosenWord);
		for (char c : chosenWord.toCharArray()) {
			wordStack.push(c);
			boardStack.push('-');
		}
	}

	public Player getPlayer() {
		return player;
	}

	public boolean tick() {
		return tick(null);
	}

	private boolean tick(String move) {
		if (move == null) {
			printState();
			move = player.play();
		}

		if (move.equals("joker")) {

			if (player.hasJoker()) {
				Random random = new Random();

				String randomSelected = String.valueOf(wordStack.at(random.nextInt(selectedWordLength)));
				while (randomSelected.equals("-")) {
					randomSelected = String.valueOf(wordStack.at(random.nextInt(selectedWordLength)));
				}

				tick(randomSelected);

				player.setHasJoker(false);
			} else {
				System.out.println("You have already used your joker!");
			}

		} else {
			char moveChr = move.toCharArray()[0];

			int toRemove = letterStack.search(moveChr);
			if (toRemove != -1)
				letterStack.removeAt(toRemove);

			// search for letter
			if (wordStack.search(moveChr) != -1) {

				CharStack tmpWord = new CharStack();
				CharStack tmpBoard = new CharStack();

				while (!wordStack.empty()) {
					char wordChr = wordStack.pop();
					char boardChr = boardStack.pop();

					if (wordChr == moveChr) {
						boardChr = wordChr;
						wordChr = '-';
					}

					tmpWord.push(wordChr);
					tmpBoard.push(boardChr);
				}

				while (!tmpWord.empty()) {
					wordStack.push(tmpWord.pop());
					boardStack.push(tmpBoard.pop());
				}

			} else if (missingLetterStack.search(moveChr) == -1) {
				missingLetterStack.push(moveChr);

				if (isVowel(moveChr)) {
					player.decrementScoreBy(3);
				} else {
					player.decrementScoreBy(2);
				}
			} else {
				System.out.println("You have already tried letter " + moveChr + "!");
			}
		}

		return boardStack.search('-') != -1 && player.getScore() != 0;
	}

	public void printState() {
		System.out.println(String.format("Word: %s\tMisses: %-16s Score: %d\t%s", boardStack.toString().toUpperCase(),
				reverseString(missingLetterStack.toString().toUpperCase()), player.getScore(),
				letterStack.toString().toUpperCase()));
	}

	private boolean isVowel(char c) {
		return Arrays.binarySearch(vowels, c) != -1;
	}

	private static String reverseString(String str) {
		return (new StringBuilder(str)).reverse().toString();
	}

}
