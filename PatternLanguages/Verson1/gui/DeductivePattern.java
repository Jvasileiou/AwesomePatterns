package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

public class DeductivePattern extends JFrame {
	
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private JPanel buttonPane;
	private DefaultListModel<String> modelP;
	private DefaultListModel<String> modelPL;
	private JTextField textFieldName;
	private JTextField textFieldTemplate;
	private JTextArea textAreaProblem;
	private JTextArea  textAreaSolution;
	private JTextArea  textAreaBenefits;
	private JButton cancelButton;

	private EditPattern newWindow;
	private PatternComponent patternLanguage;
	private Play objectMain;
	private PatternComponent pattern;	
	private int position;
	@SuppressWarnings("unused")
	private JList<String> list;
	private JTextArea textAreaConsequences;
	

	public DeductivePattern(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent newPattern,
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
	
	public DeductivePattern(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent edittedPattern, 
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
		
		setBounds(100, 100, 800, 670);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 784, 590);
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
			textFieldName.setBounds(120, 10, 300, 25);
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
			textFieldTemplate.setBounds(120, 50, 300, 25);
			textFieldTemplate.setColumns(10);	
			textFieldTemplate.setText("Deductive Mini Pattern");
			contentPanel.add(textFieldTemplate);
		}
		{
			JLabel lblProblem = new JLabel("Problem:");
			lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblProblem.setBounds(10, 90, 100, 20);
			contentPanel.add(lblProblem);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(120, 90, 600, 100);
			contentPanel.add(scrollPane);
			{
				textAreaProblem = new JTextArea();
				textAreaProblem.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textAreaProblem);
			}
		}
		{
			JLabel lblSolution = new JLabel("Solution:");
			lblSolution.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSolution.setBounds(10, 220, 100, 20);
			contentPanel.add(lblSolution);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(120, 220, 600, 100);
			contentPanel.add(scrollPane);
			{
				textAreaSolution = new JTextArea();
				textAreaSolution.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textAreaSolution);
			}
		}
		{
			JLabel lblBenefits = new JLabel("Benefits:");
			lblBenefits.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblBenefits.setBounds(10, 350, 100, 20);
			contentPanel.add(lblBenefits);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(120, 350, 600, 100);
			contentPanel.add(scrollPane);
			{
				textAreaBenefits = new JTextArea();
				textAreaBenefits.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textAreaBenefits);
			}
		}
		
		JLabel lblConcequences = new JLabel("Consequences:");
		lblConcequences.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConcequences.setBounds(10, 480, 100, 20);
		contentPanel.add(lblConcequences);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 480, 600, 100);
		contentPanel.add(scrollPane);
		
		textAreaConsequences = new JTextArea();
		textAreaConsequences.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(textAreaConsequences);
		
		buttonPane = new JPanel();
		buttonPane.setBounds(0, 590, 784, 30);
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
		buttonPane.setLayout(fl_buttonPane);
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
					String problem 	= textAreaProblem.getText();
					String solution 	= textAreaSolution.getText();
					String benefits = textAreaBenefits.getText();
					String consequences = textAreaConsequences.getText();
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(problem, 1);
					pattern.setContents(solution, 2);
					pattern.setContents(benefits,3);
					pattern.setContents(consequences,4);
					
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
		textAreaProblem.setText(pattern.getComponentsList().get(1).getContents());
		textAreaSolution.setText(pattern.getComponentsList().get(2).getContents());	
		textAreaBenefits.setText(pattern.getComponentsList().get(3).getContents());
		textAreaConsequences.setText(pattern.getComponentsList().get(4).getContents());
	
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
					String problem 	= textAreaProblem.getText();
					String solution = textAreaSolution.getText();
					String benefits = textAreaBenefits.getText();
					String consequences = textAreaConsequences.getText();
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(problem, 1);
					pattern.setContents(solution, 2);
					pattern.setContents(benefits, 3);
					pattern.setContents(consequences,4);
					
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