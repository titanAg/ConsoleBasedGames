//Kyle Orcutt 300277486
import java.util.*;
import java.io.*;
public class Hangman {
	private static ArrayList<String> dictionary = new ArrayList<String>();
	private static ArrayList<String> names = new ArrayList<String>();
	private static ArrayList<Integer> scores = new ArrayList<Integer>();
	private static String randomWord;
	private static int score = 0;
	private static int lowest = 0;
	public static void main(String[] args) {
		ArrayList<String> incorrect = new ArrayList<String>();
		ArrayList<String> correct = new ArrayList<String>();
		boolean isGameOver = false;
		boolean isRightWord = false;
		int guessesLeft = 7;
		String guess = "";
		int correctCount = 0;
		try {
			Scanner input = new Scanner(System.in);
			readWordsFile("words");
			readHighscoresFile("highscores");
			System.out.println("              HANGMAN");
			System.out.println("===================================\n\n");
			while (isGameOver == false) {
				if (isRightWord == true) {
					score += ((30 * guessesLeft) + 100);
					System.out.println("Good job! You solved the word: " + randomWord);
					System.out.println("With a score of " + score);
					System.out.println("and " + guessesLeft + " guesses left!\n\n");
					guessesLeft = 7;
					correctCount = 0;
					incorrect.clear();
					correct.clear();
					pullRandomWord();
					isRightWord = false;
				} else {
					pullRandomWord();
				}
				if (guessesLeft == 0) {
					System.out.println("       Game Over");
			        lowest = scores.get(0);
			        for (int i = 0; i < scores.size()-1; i++) {
			            if (lowest > scores.get(i+1)) {
			                lowest = scores.get(i+1);
			            }
			        }
					if (score > lowest) {
						writeHighScore("highscores");
					}
					sortHighScores();
					displayScores();
					isGameOver = true;
				}
				while (guessesLeft > 0 && isRightWord == false) {

					System.out.print("Hidden Word:");
					for (int i = 0; i < randomWord.length(); i++) {
						correct.add(" _");
						System.out.print(correct.get(i));
					}
					System.out.print("\nIncorrect Guesses: ");
					for (int i = 0; i < incorrect.size(); i++) {
						System.out.print(incorrect.get(i));
						if (i < incorrect.size() - 1) {
							System.out.print(", ");
						}
					}
					System.out.println("\nGuesses Left: " + guessesLeft);
					System.out.println("Score: " + score);
					System.out.print("Enter next guess: ");
					guess = input.next();
					guess = guess.toUpperCase();
					while (guess.length() > 1 || !Character.isLetter(guess.charAt(0))) {
						System.out.println("Invalid guess, please enter a guess in the form of a single letter.");
						System.out.print("Enter next guess: ");
						guess = input.next();
						guess = guess.toUpperCase();
					}
					if (randomWord.contains(guess)) {
						if (correct.contains(guess) || correct.contains(" " + guess)) {
							System.out.println("You have already guessed this letter, try again.");
						} else {
							for (int i = 0; i < randomWord.length(); i++) {
								if (randomWord.charAt(i) == guess.charAt(0)) {
									correct.set(i, " " + guess);
									correctCount++;
									score += 10;
								}
							}
							if (randomWord.length() == correctCount)
								isRightWord = true;
						}
					} else {
						if (incorrect.contains(guess)) {
							System.out.println("You have already guessed this letter, try again.");
						} else {
							System.out.println("Sorry, there were no " + guess + "'s");
							incorrect.add(guess);
							Collections.sort(incorrect);
							guessesLeft--;
						}
					}
					System.out.println();
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}	
	public static void sortHighScores() {
		for (int i = 1; i < scores.size()-1; i++) {
			for (int k = 0; k < scores.size()-i; k++) {
				if (scores.get(k) < scores.get(k+1)) {
					int tempScore = scores.get(k);
					scores.set(k,scores.get(k+1));
					scores.set(k+1,tempScore);
					String tempName = names.get(k);
					names.set(k,names.get(k+1));
					names.set(k+1,tempName);
				}
			}
		}
	}
	public static void displayScores() {
		System.out.println("      High Scores");
				for (int i = 0; i < names.size(); i++) {
					System.out.print(i + 1 + "      " + names.get(i));
					String temp = names.get(i);
					for (int k = 12; k > temp.length(); k--) {
						System.out.print(" ");
					}
					System.out.print(scores.get(i));
					System.out.println();
				}
				System.out.println();
	}
	public static void writeHighScore(String filename) throws FileNotFoundException{
        File file = new File(filename);
        PrintWriter output = new PrintWriter(file);
        Scanner input = new Scanner(System.in);
        System.out.println("Awesome! You made the high score list!");
        System.out.print("Enter your name: ");
        String tempName = input.next();
        int index = scores.indexOf(lowest);
        names.set(index,tempName);
        scores.set(index,score);
        for (int i= 0; i < scores.size(); i++) {
                output.print(names.get(i) + " ");
                output.print(scores.get(i));
                output.println();	
        }
        output.close();
        input.close();
    }
	public static void readHighscoresFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			String playerName = input.next();
			int playerScore = input.nextInt();
			names.add(playerName);
			scores.add(playerScore);
		}
		input.close();
	}
	public static void readWordsFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			String word = input.next();
			dictionary.add(word);
		}
		input.close();
	}
	public static void pullRandomWord() {
		int wordSelector = (int) ((Math.random() * dictionary.size()));
		randomWord = dictionary.get(wordSelector);
		randomWord = randomWord.toUpperCase();
	}
}