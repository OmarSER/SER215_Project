package hangman_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;

import project_HangMan.Hangman;
import project_HangMan.LetterGrid;

public class GameBoard extends JFrame{
	
	//Game board width
	private final int width;
		
	//Game board height
	private final int height;
		
	//Max num of guesses
	private final int maxWrong;
	
	//Max word length
	private final int maxWordLength;
	
	//Hangman images dir
	private final String hangmanImageDir;
	
	//Image type
	private final String hangmanType;
	
	//Base name
	private final String hangmanBaseName;
	
	//Letter dir
	private final String letterImageDirectory;
	
	//Letter image type
	private final String letterType;
	
	//Letter grid containing the letters
	private LetterGrid gameGrid;
	
	//Image holder
	private Hangman gameHangman;
	
	private int numWrong;
	
	//Display hidden letter guessed
	private JLabel correct;
		
	//Displays number of incorrect tries
	private JLabel wrong;
	
	//Word to be guessed
	private String selectedWord;
	
	//StringBuilder (hide the words until guessed correctly)
	private StringBuilder wordHidden;

}
