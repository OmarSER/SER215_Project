package hangman_Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameBoard extends JFrame{
	//General comment
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

	
	//Default constructor
	public GameBoard()
	{
		width = 500;
		height = 500;
		maxWrong = 6;
		maxWordLength = 15;
		
		//Default dir for sample images
		hangmanImageDir = letterImageDirectory = "images/";
		hangmanType = letterType = ".png";
		hangmanBaseName = "hangman";
		
		setTitle("HangMan");
		setSize(width, height);
		setResizable(false);
		addCloseWindowListener();
		
		initialize();
	}
	
	//Initialize game board 
	private void initialize()
	{
		numWrong = 0;
		
		correct = new JLabel("Selected Word: ");
		wrong = new JLabel("Wrong: " + numWrong);
		selectedWord = new String();
		wordHidden = new StringBuilder();
		
		getWord();
		addTextPanel();
		addLetterGrid();
		addHangman();
		
		//Center board
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2 - 200);
		setVisible(true);
	}
	
	private void addCloseWindowListener()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				int prompt = JOptionPane.showConfirmDialog(null, "Are you sure?", "Quit?", JOptionPane.YES_NO_OPTION);
				if (prompt == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
	}
	
	//Adds hang man panel
	private void addHangman() {
		// TODO Auto-generated method stub
		JPanel hangmanPanel = new JPanel();
		gameHangman = new Hangman(hangmanBaseName, hangmanImageDir, hangmanType);
		hangmanPanel.add(gameHangman);
		add(hangmanPanel, BorderLayout.CENTER);
	}
	
	//Adds grid to the bottom of the screen
	private void addLetterGrid() {
		// TODO Auto-generated method stub
		gameGrid = new LetterGrid(selectedWord, letterImageDirectory, letterType);
		gameGrid.attachListeners(new TileListener());
		add(gameGrid, BorderLayout.SOUTH);
	}
	
	//Adds  correct and incorrect labels
	private void addTextPanel() {
		// TODO Auto-generated method stub
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 2));
		textPanel.add(correct);
		textPanel.add(wrong);
		
		add(textPanel, BorderLayout.NORTH);
	}

	private void getWord() {
		// TODO Auto-generated method stub
		String[] option = {"Play", "Exit"};
		JPanel wordPanel = new JPanel();
		JLabel wordLabel = new JLabel("Enter the word that you would like to play: ");
		JTextField wordText = new JTextField(maxWordLength);
		wordPanel.add(wordLabel);
		wordPanel.add(wordText);
		
		int confirm = -1;
		while(selectedWord.isEmpty())
		{
			confirm = JOptionPane.showOptionDialog(null, wordPanel, "Enter Word", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
			if(confirm == 0)
			{
				selectedWord = wordText.getText();
				
				//Checking to see that the selectedWord is made up of only char
				//and is not larger than the max length
				if(!selectedWord.matches("[a-zA-Z]+") || selectedWord.length() > maxWordLength)
				{
					JOptionPane.showMessageDialog(null,  "Word must be 15 characters long and only contain letters.", "Invalid Word", JOptionPane.ERROR_MESSAGE);
					selectedWord = "";
				}
			}
			else if(confirm == 1)
				System.exit(0);
		}
		
		wordHidden.append(selectedWord.replaceAll(".", "*"));
		correct.setText(correct.getText() + wordHidden.toString());
	}
	
	//Asks if the player whats to play another game
	private void newGameDialog()
	{
		int dialogResult = JOptionPane.showConfirmDialog(null, "The Word that was Selected was: " + selectedWord + "\nWould you like to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION)
			initialize();
		else
			System.exit(0);
	}
	
	private class TileListener implements MouseListener
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			Object source = e.getSource();
			if(source instanceof LetterTile)
			{
				char c = ' ';
				int index = 0;
				boolean updated = false;
				
				LetterTile tilePressed = (LetterTile) source;
				c = tilePressed.guess();
				
				while((index = selectedWord.toLowerCase().indexOf(c, index)) != -1)
				{
					wordHidden.setCharAt(index,  selectedWord.charAt(index));
					index++;
					updated = true;
				}
				
				if(updated)
				{
					correct.setText("word: " + wordHidden.toString());
					if(wordHidden.toString().equals(selectedWord))
					{
						gameGrid.removeListeners();
						gameHangman.winImage();
						newGameDialog();
					}
				}
				else
				{
					wrong.setText("Incorrect: " + ++numWrong);
					if(numWrong >= maxWrong)
					{
						gameHangman.loseImage();
						gameGrid.removeListeners();
						newGameDialog();
					}
					else
						gameHangman.nextImage(numWrong);
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

}
