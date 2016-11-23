package hangman_Project;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class Hangman extends JLabel{
	private final int prefferedWidth;
	private final int prefferedHeight;
	
	//base name for images
	private final String baseName;
	
	private final String directoryImage;
	private final String typeImage;
	private String path;
	private BufferedImage image;
}

