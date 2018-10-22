package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

import compositePattern.Decorator;
import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Play;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class Start{

	private static JFrame frame;
	private static DefaultListModel<String> modelPL = new DefaultListModel<String>();
	
	private EditPattern newWindowEditPattern;
	private JButton openButton,createButton,saveTxtButton,loadButton;
	private JPanel StartMenu;
	private JLabel lblPatternLanguages;
	private JButton decorateButton ;
	private JList<String> listPL;
	private Play objectMain = new Play();
	private SelectName newWindow;
	//private PatternComponent patternLanguage;
	//private DefaultListModel<String> modelP = new DefaultListModel<String>(); 
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Start(); 
					Start.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Start(Play newObjectMain,DefaultListModel<String> newModelPL) {
		objectMain = newObjectMain;
		modelPL = newModelPL;
		
		initialize();
	}
	
	public Start() {
		initialize();
	}
	
	private void initialize() {
		
		listPL = new JList<String>(modelPL);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		StartMenu = new JPanel();
		frame.getContentPane().add(StartMenu, "name_518402610114028");
		StartMenu.setLayout(null);	
		listPL.setBounds(10, 36, 300, 500);

		frame.setTitle("Pattern Languages");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("C:Verson1\\icon.png");
		frame.setIconImage(img);
		
		// Create Label
		lblPatternLanguages = new JLabel("Pattern Languages");
		lblPatternLanguages.setBounds(10, 11, 138, 14);
		
		// Create Button
		createButton = new JButton("Create");
		createButton.setBounds(322, 33, 90, 45);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	
				newWindow = new SelectName(objectMain, modelPL);
				newWindow.setVisible(true);
				//frame.setVisible(false);
			}
		});		
		
		//	CREATE LOAD BUTTON
		loadButton = new JButton("Load");
		loadButton.setBounds(322, 145, 90, 45);	
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// parent component of the dialog
				JFrame parentFrame = new JFrame();
				JFileChooser fileChooser = new JFileChooser();
						
				fileChooser.setDialogTitle("Specify a file to load");
						
				int userSelection = fileChooser.showOpenDialog(parentFrame);
					
				// If click on button SAVE
				if (userSelection == JFileChooser.APPROVE_OPTION)
				{
				    File fileToSave = fileChooser.getSelectedFile();
				    
				    String path =  fileToSave.getAbsolutePath();
					
				    final JTextField textField = getTexField(fileChooser);
				    
				    String text = textField.getText().toString();
				    
				    // Split meta-character dot
				    String[] tokens = text.split("\\.");
				  
				    if(tokens[0].equals(null)) 
				    	return;
				    
				    String name = tokens[0];
				    
					/////////////////////////////////////////////////////////////////////
					PatternComponent patternLang = null ;
					
					if(tokens[1].equals("txt")) // TXT 
					{ 	
						patternLang = new PatternComposite(name);	
					}
					else if(tokens[1].equals("tex"))  // LATEX
					{  
						String beginTag = "\\documentclass{article}\r\n" + "\\begin{document}\r\n" + "\\title{";
						String endTag = "\\maketitle";
						patternLang = new Decorator( name , beginTag , endTag );
					}else
					{
						String st = "The file is not in the right \nformat or there is pattern \nlanguage with the same name";
						JOptionPane.showMessageDialog(null, st);	
						return;
					}
					/////////////////////////////////////////////////////////////////////			    

				    patternLang.createPathToRead(path);   
				    
				    boolean result = objectMain.loadContentsOfPatternLanguage(patternLang);
				    if(result) {
				    	modelPL.add(modelPL.getSize(), name);
				    	Start.frame.dispose();
				    	new Start(objectMain, modelPL);
				    	Start.setVisibility(true);
				    	
				    }else {
				    	String st = "The file is not in the right \nformat";
						JOptionPane.showMessageDialog(null, st);
				    }
				    
				}		
			}
		});	
		
		// Add list and buttons
		StartMenu.add(listPL);
		StartMenu.add(lblPatternLanguages);
		StartMenu.add(createButton);
		StartMenu.add(loadButton);
		
		// Create Button
		saveTxtButton = new JButton("Save");
		saveTxtButton.setBounds(322, 201, 90, 45);
		saveTxtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int index = -1; 
				index = listPL.getSelectedIndex();
				
				if(index == -1) {
					String st = "Choose the pattern language\nyou want to save.";
					JOptionPane.showMessageDialog(null, st);
				}else {
							
					String namePL = listPL.getSelectedValue();				
				
					// If pattern language has one pattern ,at least
					if( checkIfPatternLanguageHasPattern(namePL) ) {
						// parent component of the dialog
						JFrame parentFrame = new JFrame();
						JFileChooser fileChooser = new JFileChooser();
						
						fileChooser.setSelectedFile(new File(fileChooser.getCurrentDirectory(), namePL + ".txt"));
						
						fileChooser.setDialogTitle("Specify a file to save");
						
						int userSelection = fileChooser.showSaveDialog(parentFrame);
						
						// If click on button SAVE
						if (userSelection == JFileChooser.APPROVE_OPTION)
						{
						    File fileToSave = fileChooser.getSelectedFile();
						    
						    String path =  fileToSave.getAbsolutePath();
						    
							PatternComponent recentPL = objectMain.getPatternLanguages().get(namePL);
							
							recentPL.createPathToWrite(path);
										
							objectMain.savePatternLanguage(recentPL);
						}
					}
					/*				
						final JTextField textField = getTexField(chooser);
					   	System.out.println("TEXT : " + textField.getText().toString());
					*/
				}		
			}
		});	
							
		openButton = new JButton("Open");
		openButton.setBounds(322, 89, 90, 45);
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{							
				String namePL = listPL.getSelectedValue();
				
				if(namePL == null) 
				{
					String st = "Choose a Pattern Language \nyou want to open.";
					JOptionPane.showMessageDialog(null, st);	
					return;
				}
				
				DefaultListModel<String> modelP = new DefaultListModel<String>(); 
				PatternComponent patternLanguage = objectMain.getPatternLanguages().get(namePL);
								
				// If there is not such pattern language in hash map
				if(patternLanguage == null) {
					String st = "There is not such a name \nthat is stored in disk";
					JOptionPane.showMessageDialog(null, st);	
					return;
				}
			
				// True - because we call it from Start object.
				newWindowEditPattern = new EditPattern(objectMain, patternLanguage, modelPL, modelP, true);
				newWindowEditPattern.setVisible(true);
				frame.dispose();
			}
		});
						
		decorateButton = new JButton("Decorate");
		decorateButton.setBounds(322, 257, 90, 45);
		decorateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String name = listPL.getSelectedValue();
				if(name == null) {
					String st = "Choose a Pattern Language\nyou want to decorate.";
					JOptionPane.showMessageDialog(null, st);
				}		
				
				PatternComponent patternLanguageDecorated = objectMain.getPatternLanguages().get(name);
				
				objectMain.decoratePatternLanguage( patternLanguageDecorated );
			}
		});
		
		StartMenu.add(saveTxtButton);
		StartMenu.add(openButton);
		StartMenu.add(decorateButton);
		
		JLabel lblCreateANew = new JLabel("Create a new Pattern Language");
		lblCreateANew.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCreateANew.setForeground(Color.LIGHT_GRAY);
		lblCreateANew.setBounds(421, 37, 341, 39);
		StartMenu.add(lblCreateANew);
		
		JLabel lblOpenAnExisting = new JLabel("Open an existing Pattern Language from the list on the left");
		lblOpenAnExisting.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblOpenAnExisting.setForeground(Color.LIGHT_GRAY);
		lblOpenAnExisting.setBounds(421, 104, 317, 14);
		StartMenu.add(lblOpenAnExisting);
		
		JLabel lblLoadAPattern = new JLabel("Load a Pattern Language from your disk");
		lblLoadAPattern.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoadAPattern.setForeground(Color.LIGHT_GRAY);
		lblLoadAPattern.setBounds(421, 160, 302, 14);
		StartMenu.add(lblLoadAPattern);
		
		JLabel lblSaveYourWork = new JLabel("Save your work on a text file");
		lblSaveYourWork.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaveYourWork.setForeground(Color.LIGHT_GRAY);
		lblSaveYourWork.setBounds(421, 216, 302, 14);
		StartMenu.add(lblSaveYourWork);
		
		JLabel lblTextAndLatex = new JLabel("Text and LaTex decoration for your Pattern Languages");
		lblTextAndLatex.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTextAndLatex.setForeground(Color.LIGHT_GRAY);
		lblTextAndLatex.setBounds(421, 272, 302, 14);
		StartMenu.add(lblTextAndLatex);
	}
	
	public boolean checkIfPatternLanguageHasPattern(String namePL) {
			
		PatternComponent recentPL = objectMain.getPatternLanguages().get(namePL);
		
		if(recentPL.getSizeArrayList() == 0) {
			String st = "The selected pattern language\nhas not any pattern to save!";
			JOptionPane.showMessageDialog(null, st);
			
			return false;
		}
		return true;
	}
	
	 public JTextField getTexField(Container container) {
         for (int i = 0; i < container.getComponentCount(); i++) {
             Component child = container.getComponent(i);
             if (child instanceof JTextField) {
                 return (JTextField) child;
             } else if (child instanceof Container) {
                 JTextField field = getTexField((Container) child);
                 if (field != null) {
                     return field;
                 }
             }
         }
         return null;
     }
	
	public static void setVisibility(boolean set){
		frame.setVisible(set);
	}

	public Play getObjectMain() {
		return objectMain;
	}

	public void setObjectMain(Play objectMain) {
		this.objectMain = objectMain;
	}

	public SelectName getNewWindow() {
		return newWindow;
	}

	public void setNewWindow(SelectName newWindow) {
		this.newWindow = newWindow;
	}
}
