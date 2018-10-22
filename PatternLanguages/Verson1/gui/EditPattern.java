package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import compositePattern.Pattern;
import compositePattern.PatternComponent;
import compositePattern.PatternComposite;
import compositePattern.Play;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class EditPattern extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblPatterns;
	private JButton addNewButton,homeButton,editButton,removeButton;
	
	private DefaultListModel<String> modelP = new DefaultListModel<String>();;
	private JList<String> list;	
	private DefaultListModel<String> modelPL; 
	private SelectTemplate newWindow;	
	private PatternComponent patternLanguage;	
	private Play objectMain;
	private JLabel lblAddANew;
	private JLabel lblEditAnExisting;
	private JLabel lblRemoveAnExisting;

	
	public EditPattern(Play newObjectMain,	PatternComponent newPatternLanguage,
			DefaultListModel<String> newModelPL) {	
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		objectMain = newObjectMain;
		patternLanguage = newPatternLanguage;
		modelPL = newModelPL;
		
		newPatternLanguage.getName();
		list = new JList<String>(modelP);
		
		initialize();
	}
	
	// Second constructor
	public EditPattern(Play newObjectMain, PatternComponent newPatternLanguage,DefaultListModel<String> newModelPL,
			DefaultListModel<String> newModelP,boolean comeFromStartWindow) {	
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			objectMain = newObjectMain;
			patternLanguage = newPatternLanguage;
			modelPL = newModelPL;
			modelP = newModelP;
			
			newPatternLanguage.getName();
			list = new JList<String>(modelP);
	
			if(comeFromStartWindow) {
				fillModel();
			}
			
			initialize();
			initialize2();
	}
	
	public void fillModel() {
		String nameOfPattern="";
		int index = 0;
		
		int size = patternLanguage.getSizeArrayList();
		
		for(int i = 0; i < size; i++) 
		{
			//nameOfPattern =
			nameOfPattern = patternLanguage.getComponentsList().get(i).getName();
			//(PatternComposite) patternLanguage.get
			index = modelP.size();
			modelP.add(index, nameOfPattern);
		}
	}
	
	public void initialize() {
		
		this.setTitle("Pattern Languages");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("C:Verson1\\icon.png");
		this.setIconImage(img);
		
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		{
			lblAddANew = new JLabel("Add a new Pattern tou your Pattern Language");
			lblAddANew.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblAddANew.setForeground(Color.LIGHT_GRAY);
			lblAddANew.setBackground(Color.LIGHT_GRAY);
			lblAddANew.setBounds(420, 69, 354, 14);
			getContentPane().add(lblAddANew);
		}
		{
			lblRemoveAnExisting = new JLabel("Remove an existing Pattern");
			lblRemoveAnExisting.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblRemoveAnExisting.setForeground(Color.LIGHT_GRAY);
			lblRemoveAnExisting.setBounds(420, 181, 354, 14);
			getContentPane().add(lblRemoveAnExisting);
		}
		{
			list.setBounds(10, 54, 300, 440);
			getContentPane().add(list);
		}
		{
			lblPatterns = new JLabel("Patterns");
			lblPatterns.setBounds(10, 7, 300, 14);
			getContentPane().add(lblPatterns);
		}
		{	
			
			addNewButton = new JButton("Add New");
			addNewButton.setBounds(320, 54, 90, 45);
			getContentPane().add(addNewButton);
			addNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					newWindow = new SelectTemplate(objectMain, patternLanguage, modelPL, modelP);					
					newWindow.setVisible(true);		
					dispose(); 
				}
			});			
		}
		{
			homeButton = new JButton("Home");			
			homeButton.setBounds(10, 505, 90, 45);
			getContentPane().add(homeButton);
	
			homeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					new Start(objectMain, modelPL);
					Start.setVisibility(true);
					dispose();
				}
			});
		}
		{
			removeButton = new JButton("Remove");			
			removeButton.setBounds(320, 166, 90, 45);
			getContentPane().add(removeButton);
			
			JLabel lblReturnToHome = new JLabel("Return to Home to Exit");
			lblReturnToHome.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblReturnToHome.setForeground(Color.LIGHT_GRAY);
			lblReturnToHome.setBounds(610, 536, 164, 14);
			getContentPane().add(lblReturnToHome);
	
			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					int index =-1;
					index=list.getSelectedIndex();
					
					if(index == -1) {
						String st = "Please,select a pattern to remove!";
						JOptionPane.showMessageDialog(null, st);
					}
					else {		
						Pattern patternRemoved = (Pattern) patternLanguage.getChild(index);

						// Remove pattern 
						objectMain.removePattern((PatternComposite) patternLanguage, patternRemoved);
					 
						// Remove pattern of list(GUI)
						modelP.removeElementAt(index);	
					}
				}
			});
		}
		
	}
	

	public void initialize2() {
		
		{
			lblEditAnExisting = new JLabel("Edit an existing Pattern");
			lblEditAnExisting.setForeground(Color.LIGHT_GRAY);
			lblEditAnExisting.setBounds(420, 125, 354, 14);
			getContentPane().add(lblEditAnExisting);
		}
		{
			editButton = new JButton("Edit");
			editButton.setBounds(320, 110, 90, 45);
			getContentPane().add(editButton);
				
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int position = -1;
					PatternComponent patternEditted;
					
					position = list.getSelectedIndex();
					
					// if not selected -- warning
					if(position == -1)
					{
						System.out.println("Select one Pattern");
						return;
					}	
				
					// We take the element that is in the position!
					patternEditted = patternLanguage.getChild(position);
												
					// The first is template! 
					String template = patternEditted.getComponentsList().get(0).getContents().trim();
					if(template.equals("Micro Pattern")) {
						
						MicroPattern newWindowMicro = new MicroPattern(objectMain, patternLanguage, patternEditted, 
								modelPL, modelP, position);
						//dispose();
						newWindowMicro.setVisible(true);
						
					}else if(template.equals("Inductive Mini Pattern")){
						InductivePattern newWindowInductive = new InductivePattern(objectMain, patternLanguage, patternEditted, 
								modelPL, modelP, position);
						//dispose();
						newWindowInductive.setVisible(true);
					}else if(template.equals("Deductive Mini Pattern")){
						DeductivePattern newWindowDeductive = new DeductivePattern(objectMain, patternLanguage, patternEditted, 
								modelPL, modelP, position);
						//dispose();
						newWindowDeductive.setVisible(true);
					}else if(template.equals("Gang of Four Pattern")){
						GangOfFourPattern newWindowGang = new GangOfFourPattern(objectMain, patternLanguage, patternEditted, 
								modelPL, modelP, position);
						//dispose();
						newWindowGang.setVisible(true);
					}else if(template.equals("System of Patterns")){
						SystemOfPatterns newWindowSystem = new SystemOfPatterns(objectMain, patternLanguage, patternEditted, 
								modelPL, modelP, position);
						//dispose();
						newWindowSystem.setVisible(true);
					}
					dispose();
				}
			});
		}
	}
}


