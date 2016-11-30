package hangman_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

//imported this for the image
import javax.swing.ImageIcon;

public class HangMan_Project {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangMan_Project window = new HangMan_Project();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HangMan_Project() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setFont(new Font("Castellar", Font.BOLD, 11));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBackground(Color.GRAY);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//action for opening a new window to play the game
				GameBoard newGame = new GameBoard();
			}
		});
		btnStart.setFont(new Font("Castellar", Font.BOLD, 15));
		btnStart.setBounds(176, 208, 99, 41);
		frame.getContentPane().add(btnStart);
		
		//Tutorial for beginners
		JButton btnNewButton = new JButton("Tutorial");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Castellar", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//-------------------------------------------------------------------------------------------------------------
				//This option opens a new window and displays the tutorial
				JOptionPane.showMessageDialog(null, "Hello player, this is a Hang Man tutorial, just in case you didn't know how to play.\n"
						+ "\n1. Play in a group of 2 or more\n"
						+ "\n2. One player will select a word to start off the game.\n"
						+ "\n3. Another player will select a letter of the alphabet.\n"
						+ "\n4. If the letter is contained in the word, the player gets another turn to guess the letter.\n"
						+ "\n5. If the letter is not contained in the word a body part of the hangman is added\n"
						+ "\n6. The game will continue until:\n"
						+ "\n     - The word is guessed correctly, WINNER!!!\n"
						+ "\n     - All the parts of the hangman are displayed, YOU LOSE!!!\n");
				
			}
		});
		btnNewButton.setBounds(318, 209, 106, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnHangMan = new JButton("Hang Man");
		btnHangMan.setBackground(Color.GRAY);
		btnHangMan.setFont(new Font("Castellar", Font.BOLD, 20));
		btnHangMan.setBounds(10, 11, 177, 46);
		frame.getContentPane().add(btnHangMan);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.BLACK);
		label.setIcon(new ImageIcon("C:\\Users\\FramedWand\\Desktop\\SER215_Project\\images\\home.png"));
		label.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(label);
	}
}

