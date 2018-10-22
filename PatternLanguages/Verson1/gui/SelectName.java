package gui;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import compositePattern.PatternComponent;
import compositePattern.Play;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class SelectName extends JDialog {

	private static final long serialVersionUID = 1L;
	private static int counter = 0 ;
	private JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblSelectName;
	private JButton nextButton,cancelButton;
	
	private EditPattern newWindow;
	private DefaultListModel<String> modelPL;
	private Play objectMain;
	private PatternComponent patternLanguage;
	private String nameNewPatternLanguage = "";	
	
	
	public SelectName(Play newObjectMain, DefaultListModel<String> newModelPL) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		objectMain = newObjectMain;
		modelPL = newModelPL;
		
		initialize();
		
	}
	
	public void initialize() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("C:Verson1\\icon.png");
		this.setIconImage(img);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		{
			lblSelectName = new JLabel("Select name for your Pattern Language");
			lblSelectName.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectName.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblSelectName.setBounds(10, 62, 414, 40);
			contentPanel.add(lblSelectName);
		}
		{
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setBounds(10, 100, 414, 45);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{		
			cancelButton = new JButton("Cancel");
			cancelButton.setBounds(10, 205, 90, 45);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			cancelButton.setActionCommand("cancel");
		}
		{
			nextButton = new JButton("Next");
			nextButton.setBounds(334, 205, 90, 45);
			contentPanel.add(nextButton);
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					nameNewPatternLanguage = textField.getText();
						
					if(nameNewPatternLanguage.equals("")) 
					{
						nameNewPatternLanguage = "PatternLanguage(" + counter +")" ; 
						
						counter++;
						String st = "You did not specify a name.\nThe default name is " + nameNewPatternLanguage ;
						JOptionPane.showMessageDialog(null, st);
					}
					
					// CREATE and ADD the Pattern Language in hashMapList
					patternLanguage = objectMain.createPatternLanguage(nameNewPatternLanguage);		
					
					// Add to the list of Pattern Language
					modelPL.add(modelPL.getSize(), nameNewPatternLanguage);
					
					newWindow = new EditPattern(objectMain, patternLanguage, modelPL);	
					
					newWindow.setVisible(true);
					Start.setVisibility(false);
					setVisible(false);	
				}															
			});
			nextButton.setActionCommand("Next");
			getRootPane().setDefaultButton(nextButton);
		}
	}


	public PatternComponent getPatternL() {
		return patternLanguage;
	}

}