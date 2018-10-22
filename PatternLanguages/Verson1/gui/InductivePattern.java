package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import compositePattern.PatternComponent;
import compositePattern.Play;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class InductivePattern extends JFrame {
	
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private JPanel buttonPane;
	private DefaultListModel<String> modelP;
	private DefaultListModel<String> modelPL;
	private JTextField textFieldName;
	private JTextField textFieldTemplate;
	private JTextArea  textAreaContext;
	private JTextArea  textAreaForces;
	private JTextArea  textAreaSolution;
	private JButton cancelButton;

	private EditPattern newWindow;
	private PatternComponent patternLanguage;
	private Play objectMain;
	private PatternComponent pattern;	
	private int position;
	@SuppressWarnings("unused")
	private JList<String> list;
	


	public InductivePattern(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent newPattern,
			DefaultListModel<String> newModelPL, DefaultListModel<String> newModelP) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Added Code
		patternLanguage = newPatternLanguage;
		objectMain = newObjectMain;
		pattern = newPattern;
		modelPL = newModelPL;
		modelP = newModelP;
		
		list = new JList<String>(modelP);
		
		initialize();
		initializeSave();
	}
	

	public InductivePattern(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent edittedPattern, 
					DefaultListModel<String> newModelPL, DefaultListModel<String> newModelP, int newPosition) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
				// Added Code
			patternLanguage = newPatternLanguage;
			objectMain = newObjectMain;
			pattern = edittedPattern; // edit pattern
			modelPL = newModelPL;
			modelP = newModelP;
			position = newPosition;
			
			list = new JList<String>(modelP);
			
			initialize();
			initializeUpdate();
		}
	
	public void initialize() {
		
		this.setTitle("Pattern Languages");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("C:Verson1\\icon.png");
		this.setIconImage(img);
		
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 784, 528);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblName.setBounds(10, 10,  100, 20);
			contentPanel.add(lblName);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textFieldName.setBounds(100, 10, 300, 25);
			contentPanel.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			JLabel lblTemplate = new JLabel("Template:");
			lblTemplate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTemplate.setBounds(10, 50, 100, 20);
			contentPanel.add(lblTemplate);
		}
		{
			textFieldTemplate = new JTextField();
			textFieldTemplate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textFieldTemplate.setBounds(100, 50, 300, 25);
			textFieldTemplate.setColumns(10);	
			textFieldTemplate.setText("Inductive Mini Pattern");
			contentPanel.add(textFieldTemplate);
		}
		{
			JLabel lblContext = new JLabel("Context:");
			lblContext.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblContext.setBounds(10, 90, 100, 20);
			contentPanel.add(lblContext);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(100, 90, 600, 100);
			contentPanel.add(scrollPane);
			{
				textAreaContext = new JTextArea();
				textAreaContext.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textAreaContext);
			}
		}
		{
			JLabel lblForces = new JLabel("Forces:");
			lblForces.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblForces.setBounds(10, 210, 100, 14);
			contentPanel.add(lblForces);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(100, 210, 600, 100);
			contentPanel.add(scrollPane);
			{
				textAreaForces = new JTextArea();
				textAreaForces.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textAreaForces);
			}
		}
		{
			JLabel lblSolution = new JLabel("Solution:");
			lblSolution.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSolution.setBounds(10, 330, 100, 20);
			contentPanel.add(lblSolution);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(100, 330, 600, 100);
			contentPanel.add(scrollPane);
			{
				textAreaSolution = new JTextArea();
				textAreaSolution.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textAreaSolution);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 528, 784, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						newWindow = new EditPattern(objectMain, patternLanguage, modelPL,modelP, false);
						newWindow.setVisible(true);					
						dispose();
					}
				});
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
			}
		}
	}
	
	public void initializeSave() {
		{
			JButton saveButton = new JButton("Save");
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					// US4 -- editPattern
					// Read the contents
					String name 	= "";
					name = textFieldName.getText().toString().trim();
					if( name.equals("") ) {
						String st = "Please,give a name to the pattern!";
						JOptionPane.showMessageDialog(null, st);
						return;
					}
					String template = textFieldTemplate.getText();
					String context 	= textAreaContext.getText();
					String forces 	= textAreaForces.getText();
					String solution = textAreaSolution.getText();
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(context, 1);
					pattern.setContents(forces, 2);
					pattern.setContents(solution,3);
					
					// Add pattern name in the last seat! (GUI)
					int index = modelP.size();
					modelP.add(index, name);

					// Add pattern 
					patternLanguage.add(pattern);
					
					newWindow = new EditPattern(objectMain, patternLanguage, modelPL,modelP, false);
					newWindow.setVisible(true);
										
					dispose();
				}
			});
			
			buttonPane.add(saveButton);
			getRootPane().setDefaultButton(saveButton);
		}
	}

	public void initializeUpdate() {

		textFieldName.setText(pattern.getName());
		textFieldTemplate.setText(pattern.getComponentsList().get(0).getContents());
		textAreaContext.setText(pattern.getComponentsList().get(1).getContents());
		textAreaForces.setText(pattern.getComponentsList().get(2).getContents());	
		textAreaSolution.setText(pattern.getComponentsList().get(3).getContents());
		
		{
			JButton updateButton = new JButton("Update");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					// US4 -- editPattern
					// Read the contents
					String name 	= "";
					name = textFieldName.getText().toString().trim();
					if( name.equals("") ) {
						String st = "Please,give a name to the pattern!";
						JOptionPane.showMessageDialog(null, st);
						return;
					}
					String template = textFieldTemplate.getText();
					String context 	= textAreaContext.getText();
					String forces 	= textAreaForces.getText();
					String solution = textAreaSolution.getText();
					
					pattern.setName(name);
					
					pattern.setContents(template,0);
					pattern.setContents(context, 1);
					pattern.setContents(forces, 2);
					pattern.setContents(solution,3);
					
					// Add pattern name in the last seat! (GUI)									 
					modelP.set(position, name);
															
					// Add pattern 
					patternLanguage.set(position, pattern);
					
					newWindow = new EditPattern(objectMain, patternLanguage, modelPL,modelP, false);
					newWindow.setVisible(true);
					
					dispose();
				}
			});
			
			buttonPane.add(updateButton);
			getRootPane().setDefaultButton(updateButton);
		}
	}
	
}