package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import compositePattern.PatternComponent;
import compositePattern.Play;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class SystemOfPatterns extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JTextField textFieldTemplate;
	private JTextField textFieldAlsoKnownAs;
	private JTextArea textAreaExample;
	private JTextArea textAreaContext;
	private JTextArea textAreaProblem;
	private JTextArea textAreaSolution;
	private JTextArea textAreaStructure;
	private JTextArea textAreaDynamics;
	private JTextArea textAreaImplementation;
	private JTextArea textAreaExampleResolved;
	private JTextArea textAreaVariants;
	private JTextArea textAreaKnownUses;
	private JTextArea textAreaConsequences;
	private PatternComponent patternLanguage;
	private Play objectMain;
	private PatternComponent pattern;
	private DefaultListModel<String> modelPL;
	private DefaultListModel<String> modelP;
	@SuppressWarnings("unused")
	private JList<String> list;
	private JPanel buttonPane;
	private JFrame frame;
	private JPanel panel;
	private JButton cancelButton;
	private EditPattern newWindow;
	private int position;
	

	public SystemOfPatterns(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent newPattern,
			DefaultListModel<String> newModelPL, DefaultListModel<String> newModelP) {
		patternLanguage = newPatternLanguage;
		objectMain = newObjectMain;
		pattern = newPattern;
		modelPL = newModelPL;
		modelP = newModelP;
		
		list = new JList<String>(modelP);
		
		initialize();
		initializeSave();
	}

	
	public SystemOfPatterns(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent edittedPattern, 
			DefaultListModel<String> newModelPL, DefaultListModel<String> newModelP, int newPosition) {
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
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(700, 1350));
		panel.setMaximumSize(new Dimension(800, 1350));
		panel.setMinimumSize(new Dimension(700, 1350));
		 
		frame.setSize(new Dimension(800, 600));
		
		JScrollPane scrollPanel = new JScrollPane(panel);
		panel.setLayout(null);		
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 20, 100, 14);
		panel.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldName.setBounds(140, 20, 300, 25);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Temlate :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 50, 100, 14);
		panel.add(lblNewLabel_1);
		
		textFieldTemplate = new JTextField();
		textFieldTemplate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldTemplate.setBounds(140, 50, 300, 25);
		textFieldTemplate.setText("System of Patterns");
		panel.add(textFieldTemplate);
		textFieldTemplate.setColumns(10);
		
		JLabel lblNewLabelAKA = new JLabel("Also Known As :");
		lblNewLabelAKA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelAKA.setBounds(10, 80, 143, 14);
		panel.add(lblNewLabelAKA);
		
		textFieldAlsoKnownAs = new JTextField();
		textFieldAlsoKnownAs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldAlsoKnownAs.setBounds(140, 81, 300, 25);
		panel.add(textFieldAlsoKnownAs);
		textFieldAlsoKnownAs.setColumns(10);
		
		JLabel lblEx = new JLabel("Example :");
		lblEx.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEx.setBounds(10, 110, 100, 14);
		panel.add(lblEx);
		
		JScrollPane scrollPaneEx = new JScrollPane();
		scrollPaneEx.setBounds(140, 110, 600, 100);
		panel.add(scrollPaneEx);
		
		textAreaExample = new JTextArea();
		textAreaExample.setToolTipText("");
		textAreaExample.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneEx.setViewportView(textAreaExample);

		
		JLabel lblNewLabelCo = new JLabel("Context :");
		lblNewLabelCo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelCo.setBounds(10, 220, 100, 14);
		panel.add(lblNewLabelCo);
		JScrollPane scrollPaneCo = new JScrollPane();
		scrollPaneCo.setBounds(140, 220, 600, 100);
		panel.add(scrollPaneCo);
		
		textAreaContext = new JTextArea();
		textAreaContext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneCo.setViewportView(textAreaContext);

		
		JLabel lblNewLabelProb = new JLabel(" Problem :");
		lblNewLabelProb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelProb.setBounds(10, 330, 80, 14);
		panel.add(lblNewLabelProb);
		
		JScrollPane scrollPaneProb = new JScrollPane();
		scrollPaneProb.setBounds(141, 330, 600, 100);
		panel.add(scrollPaneProb);
		
		textAreaProblem = new JTextArea();
		textAreaProblem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneProb.setViewportView(textAreaProblem);
		
		JLabel lblNewLabelSol = new JLabel("Solution :");
		lblNewLabelSol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelSol.setBounds(10, 440, 100, 14);
		panel.add(lblNewLabelSol);
		
		JScrollPane scrollPaneSol = new JScrollPane();
		scrollPaneSol.setBounds(140, 440, 600, 100);
		panel.add(scrollPaneSol);
		
		textAreaSolution = new JTextArea();
		textAreaSolution.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneSol.setViewportView(textAreaSolution);
		
		
		JLabel lblNewLabelStruc = new JLabel("Stucture :");
		lblNewLabelStruc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelStruc.setBounds(10, 550, 100, 14);
		panel.add(lblNewLabelStruc);
		
		JScrollPane scrollPaneStruc = new JScrollPane();
		scrollPaneStruc.setBounds(140, 550, 600, 100);
		panel.add(scrollPaneStruc);
		
		textAreaStructure = new JTextArea();
		textAreaStructure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneStruc.setViewportView(textAreaStructure);
		
		JLabel lblNewLabelDym = new JLabel("Dynamics :");
		lblNewLabelDym.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelDym.setBounds(10, 660, 100, 14);
		panel.add(lblNewLabelDym);
		
		JScrollPane scrollPaneDym = new JScrollPane();
		scrollPaneDym.setBounds(140, 660, 600, 100);
		panel.add(scrollPaneDym);
		
		textAreaDynamics = new JTextArea();
		textAreaDynamics.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneDym.setViewportView(textAreaDynamics);
		
		JLabel lblNewLabelImp = new JLabel("Implementation :");
		lblNewLabelImp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelImp.setBounds(10,770, 100, 14);
		panel.add(lblNewLabelImp);
		
		JScrollPane scrollPaneImp = new JScrollPane();
		scrollPaneImp.setBounds(140, 770, 600, 100);
		panel.add(scrollPaneImp);
		
		textAreaImplementation = new JTextArea();
		textAreaImplementation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneImp.setViewportView(textAreaImplementation);
		
		JLabel lblNewLabelER = new JLabel("Example Resolved :");
		lblNewLabelER.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelER.setBounds(10,880, 120, 14);
		panel.add(lblNewLabelER);
		
		JScrollPane scrollPaneER = new JScrollPane();
		scrollPaneER.setBounds(140, 880, 600, 100);
		panel.add(scrollPaneER);
		
		textAreaExampleResolved = new JTextArea();
		textAreaExampleResolved.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneER.setViewportView(textAreaExampleResolved);	
		
		JLabel lblNewLabelVar = new JLabel("Variants :");
		lblNewLabelVar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelVar.setBounds(10, 990, 100, 14);
		panel.add(lblNewLabelVar);
		
		JScrollPane scrollPaneVar = new JScrollPane();
		scrollPaneVar.setBounds(140, 990, 600, 100);
		panel.add(scrollPaneVar);
		
		textAreaVariants = new JTextArea();
		textAreaVariants.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneVar.setViewportView(textAreaVariants);
		
		JLabel lblNewLabelKU = new JLabel("Known Uses :");
		lblNewLabelKU.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelKU.setBounds(10, 1100, 100, 14);
		panel.add(lblNewLabelKU);
		
		JScrollPane scrollPaneKU = new JScrollPane();
		scrollPaneKU.setBounds(140, 1100, 600, 100);
		panel.add(scrollPaneKU);
		
		textAreaKnownUses = new JTextArea();
		textAreaKnownUses.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneKU.setViewportView(textAreaKnownUses);

		JLabel lblNewLabelCon = new JLabel("Consequences :");
		lblNewLabelCon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelCon.setBounds(10, 1210, 100, 14);
		panel.add(lblNewLabelCon);
		
		JScrollPane scrollPaneCon = new JScrollPane();
		scrollPaneCon.setBounds(140, 1210, 600, 100);
		panel.add(scrollPaneCon);
		
		textAreaConsequences = new JTextArea();
		textAreaConsequences.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneCon.setViewportView(textAreaConsequences);
		
		
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			frame.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						newWindow = new EditPattern(objectMain, patternLanguage, modelPL,modelP, false);
						newWindow.setVisible(true);					
						frame.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setSize(new Dimension(700, 1000));
		frame.getContentPane().add(scrollPanel);

		frame.setVisible(true);
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
					String alsoKnownAs = textFieldAlsoKnownAs.getText();
					String example = textAreaExample.getText();
					String context = textAreaExample.getText();
					String problem 	= textAreaProblem.getText();
					String solution = textAreaSolution.getText();
					String structure = textAreaStructure.getText();
					String dynamics = textAreaDynamics.getText();
					String implementation = textAreaImplementation.getText();
					String exampleResolved = textAreaExampleResolved.getText();
					String variants = textAreaVariants.getText();
					String knownUses = textAreaKnownUses.getText();
					String consequences = textAreaConsequences.getText();
				
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(alsoKnownAs,1);
					pattern.setContents(example,2);
					pattern.setContents(context,3);
					pattern.setContents(problem,4);
					pattern.setContents(solution,5);
					pattern.setContents(structure,6);
					pattern.setContents(dynamics,7);
					pattern.setContents(implementation,8);
					pattern.setContents(exampleResolved,9);
					pattern.setContents(variants,10);
					pattern.setContents(knownUses,11);
					pattern.setContents(consequences,12);
					
					// Add pattern name in the last seat! (GUI)
					int index = modelP.size();
					modelP.add(index, name);

					// Add pattern 
					patternLanguage.add(pattern);
					
					newWindow = new EditPattern(objectMain, patternLanguage, modelPL,modelP, false);
					newWindow.setVisible(true);
					
					frame.dispose();
				}
			});
			buttonPane.add(saveButton);
			frame.getRootPane().setDefaultButton(saveButton);
		}
	}
	
	public void initializeUpdate() {
		
		textFieldName.setText(pattern.getName());
		textFieldTemplate.setText(pattern.getComponentsList().get(0).getContents());
		textFieldAlsoKnownAs.setText(pattern.getComponentsList().get(1).getContents());
		textAreaExample.setText(pattern.getComponentsList().get(3).getContents());
		textAreaProblem.setText(pattern.getComponentsList().get(4).getContents());
		textAreaSolution.setText(pattern.getComponentsList().get(5).getContents());
		textAreaStructure.setText(pattern.getComponentsList().get(6).getContents());
		textAreaDynamics.setText(pattern.getComponentsList().get(7).getContents());
		textAreaImplementation.setText(pattern.getComponentsList().get(8).getContents());
		textAreaExampleResolved.setText(pattern.getComponentsList().get(9).getContents());
		textAreaVariants.setText(pattern.getComponentsList().get(10).getContents());
		textAreaKnownUses.setText(pattern.getComponentsList().get(11).getContents());
		textAreaConsequences.setText(pattern.getComponentsList().get(12).getContents());
		
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
					String alsoKnownAs = textFieldAlsoKnownAs.getText();
					String example = textAreaExample.getText();
					String context = textAreaExample.getText();
					String problem 	= textAreaProblem.getText();
					String solution = textAreaSolution.getText();
					String structure = textAreaStructure.getText();
					String dynamics = textAreaDynamics.getText();
					String implementation = textAreaImplementation.getText();
					String exampleResolved = textAreaExampleResolved.getText();
					String variants = textAreaVariants.getText();
					String knownUses = textAreaKnownUses.getText();
					String consequences = textAreaConsequences.getText();
				
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(alsoKnownAs,1);
					pattern.setContents(example,2);
					pattern.setContents(context,3);
					pattern.setContents(problem,4);
					pattern.setContents(solution,5);
					pattern.setContents(structure,6);
					pattern.setContents(dynamics,7);
					pattern.setContents(implementation,8);
					pattern.setContents(exampleResolved,9);
					pattern.setContents(variants,10);
					pattern.setContents(knownUses,11);
					pattern.setContents(consequences,12);
										
					// Add pattern name in the last seat! (GUI)									 
					modelP.set(position, name);
															
					// Add pattern 
					patternLanguage.set(position, pattern);
					
					newWindow = new EditPattern(objectMain, patternLanguage, modelPL,modelP, false);
					newWindow.setVisible(true);
					
					frame.dispose();
				}
			});
			
			buttonPane.add(updateButton);
			getRootPane().setDefaultButton(updateButton);
		}
	}	
}