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

public class GangOfFourPattern extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JTextField textFieldTemplate;
	private JTextField textFieldPatternClassification;
	private JTextArea textAreaIntent;
	private JTextArea textAreaAlsoKnownAs;
	private JTextArea textAreaMotivation;
	private JTextArea textAreaApplicability;
	private JTextArea textAreaStructure;
	private JTextArea textAreaParticipants;
	private JTextArea textAreaCollaborations;
	private JTextArea textAreaConsequences;
	private JTextArea textAreaImplementation;
	private JTextArea textAreaSampleCode;
	private JTextArea textAreaKnownUses;
	private JTextArea textAreaRelatedPatterns;
	private PatternComponent patternLanguage;
	private Play objectMain;
	private PatternComponent pattern;
	private DefaultListModel<String> modelPL;
	private DefaultListModel<String> modelP;
	@SuppressWarnings("unused")
	private JList<String> list;
	private JFrame frame;
	private JPanel panel;
	private JPanel buttonPane;
	private JButton cancelButton;
	private EditPattern newWindow;
	private int position;
	
	public GangOfFourPattern(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent newPattern,
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
	

	public GangOfFourPattern(Play newObjectMain, PatternComponent newPatternLanguage, PatternComponent edittedPattern, 
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
		
		panel.setPreferredSize(new Dimension(700, 1450));
		panel.setMaximumSize(new Dimension(800, 1450));
		panel.setMinimumSize(new Dimension(700, 1450));

		frame.setSize(new Dimension(800, 600));

		JScrollPane scrollPanel = new JScrollPane(panel);
		panel.setLayout(null);		
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 20, 100, 14);
		panel.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldName.setBounds(150, 20, 300, 25);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Temlate :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 50, 100, 20);
		panel.add(lblNewLabel_1);
		
		textFieldTemplate = new JTextField();
		textFieldTemplate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldTemplate.setBounds(150, 50, 300, 25);
		textFieldTemplate.setText("Gang of Four Pattern");
		panel.add(textFieldTemplate);
		textFieldTemplate.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Pattern Classification :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 85, 143, 14);
		panel.add(lblNewLabel_2);
		
		textFieldPatternClassification = new JTextField();
		textFieldPatternClassification.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldPatternClassification.setBounds(150, 81, 300, 25);
		panel.add(textFieldPatternClassification);
		textFieldPatternClassification.setColumns(10);
		
		JLabel lblIntent = new JLabel("Intent :");
		lblIntent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIntent.setBounds(10, 110, 100, 14);
		panel.add(lblIntent);
		
		JScrollPane scrollPaneInt = new JScrollPane();
		scrollPaneInt.setBounds(150, 110, 590, 100);
		panel.add(scrollPaneInt);
		
		textAreaIntent = new JTextArea();
		textAreaIntent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneInt.setViewportView(textAreaIntent);

		
		JLabel lblNewLabel_3 = new JLabel("Also Known As :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 220, 100, 14);
		panel.add(lblNewLabel_3);
		JScrollPane scrollPaneAka = new JScrollPane();
		scrollPaneAka.setBounds(150, 220, 590, 100);
		panel.add(scrollPaneAka);
		
		textAreaAlsoKnownAs = new JTextArea();
		textAreaAlsoKnownAs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneAka.setViewportView(textAreaAlsoKnownAs);

		
		JLabel lblNewLabelMotiv = new JLabel(" Motivation :");
		lblNewLabelMotiv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelMotiv.setBounds(10, 330, 80, 14);
		panel.add(lblNewLabelMotiv);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 330, 591, 100);
		panel.add(scrollPane);
		
		textAreaMotivation = new JTextArea();
		textAreaMotivation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(textAreaMotivation);
		
		JLabel lblNewLabelApplic = new JLabel("Applicability :");
		lblNewLabelApplic.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelApplic.setBounds(10, 440, 100, 14);
		panel.add(lblNewLabelApplic);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(150, 440, 590, 100);
		panel.add(scrollPane_1);
		
		textAreaApplicability = new JTextArea();
		textAreaApplicability.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane_1.setViewportView(textAreaApplicability);
		
		
		JLabel lblNewLabelStruc = new JLabel("Stucture :");
		lblNewLabelStruc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelStruc.setBounds(10, 550, 100, 14);
		panel.add(lblNewLabelStruc);
		
		JScrollPane scrollPaneStruc = new JScrollPane();
		scrollPaneStruc.setBounds(150, 550, 600, 100);
		panel.add(scrollPaneStruc);
		
		textAreaStructure = new JTextArea();
		textAreaStructure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneStruc.setViewportView(textAreaStructure);
		
		JLabel lblNewLabelParti = new JLabel("Participants :");
		lblNewLabelParti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelParti.setBounds(10, 660, 100, 14);
		panel.add(lblNewLabelParti);
		
		JScrollPane scrollPaneParti = new JScrollPane();
		scrollPaneParti.setBounds(150, 660, 600, 100);
		panel.add(scrollPaneParti);
		
		textAreaParticipants = new JTextArea();
		textAreaParticipants.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneParti.setViewportView(textAreaParticipants);
		
		JLabel lblNewLabelCola = new JLabel("Collaborations :");
		lblNewLabelCola.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelCola.setBounds(10, 770, 100, 14);
		panel.add(lblNewLabelCola);
		
		JScrollPane scrollPaneCola = new JScrollPane();
		scrollPaneCola.setBounds(150, 770, 600, 100);
		panel.add(scrollPaneCola);
		
		textAreaCollaborations = new JTextArea();
		textAreaCollaborations.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneCola.setViewportView(textAreaCollaborations);
		
		JLabel lblNewLabelCon = new JLabel("Consequences :");
		lblNewLabelCon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelCon.setBounds(10, 880, 100, 14);
		panel.add(lblNewLabelCon);
		
		JScrollPane scrollPaneCon = new JScrollPane();
		scrollPaneCon.setBounds(150, 880, 600, 100);
		panel.add(scrollPaneCon);
		
		textAreaConsequences = new JTextArea();
		textAreaConsequences.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneCon.setViewportView(textAreaConsequences);
		
		JLabel lblNewLabelImp = new JLabel("Implementation :");
		lblNewLabelImp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelImp.setBounds(10, 990, 100, 14);
		panel.add(lblNewLabelImp);
		
		JScrollPane scrollPaneImp = new JScrollPane();
		scrollPaneImp.setBounds(150, 990, 600, 100);
		panel.add(scrollPaneImp);
		
		textAreaImplementation = new JTextArea();
		textAreaImplementation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneImp.setViewportView(textAreaImplementation);
		
		JLabel lblNewLabelSC = new JLabel("Sample Code :");
		lblNewLabelSC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelSC.setBounds(10, 1100, 100, 14);
		panel.add(lblNewLabelSC);
		
		JScrollPane scrollPaneSC = new JScrollPane();
		scrollPaneSC.setBounds(150, 1100, 600, 100);
		panel.add(scrollPaneSC);
		
		textAreaSampleCode = new JTextArea();
		textAreaSampleCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneSC.setViewportView(textAreaSampleCode);
		
		JLabel lblNewLabelKU = new JLabel("Known Uses :");
		lblNewLabelKU.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelKU.setBounds(10, 1210, 100, 14);
		panel.add(lblNewLabelKU);
		
		JScrollPane scrollPaneKU = new JScrollPane();
		scrollPaneKU.setBounds(150, 1210, 600, 100);
		panel.add(scrollPaneKU);
		
		textAreaKnownUses = new JTextArea();
		textAreaKnownUses.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneKU.setViewportView(textAreaKnownUses);

		JLabel lblNewLabelRP = new JLabel("Related Patterns :");
		lblNewLabelRP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelRP.setBounds(10, 1320, 100, 14);
		panel.add(lblNewLabelRP);
		
		JScrollPane scrollPaneRP = new JScrollPane();
		scrollPaneRP.setBounds(150, 1320, 600, 100);
		panel.add(scrollPaneRP);
		
		textAreaRelatedPatterns = new JTextArea();
		textAreaRelatedPatterns.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneRP.setViewportView(textAreaRelatedPatterns);
		
		
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
					String patternClassification = textFieldPatternClassification.getText();
					String intent = textAreaIntent.getText();
					String alsoKnownAs = textAreaAlsoKnownAs.getText();
					String motivation 	= textAreaMotivation.getText();
					String applicability = textAreaApplicability.getText();
					String structure = textAreaStructure.getText();
					String participants = textAreaParticipants.getText();
					String collaborations = textAreaCollaborations.getText();
					String consequences = textAreaConsequences.getText();
					String implementation = textAreaImplementation.getText();
					String sampleCode = textAreaSampleCode.getText();
					String knownUses = textAreaKnownUses.getText();
					String relatedPatterns = textAreaRelatedPatterns.getText();
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(patternClassification,1);
					pattern.setContents(intent,2);
					pattern.setContents(alsoKnownAs,3);
					pattern.setContents(motivation,4);
					pattern.setContents(applicability,5);
					pattern.setContents(structure,6);
					pattern.setContents(participants,7);
					pattern.setContents(collaborations,8);
					pattern.setContents(consequences,9);
					pattern.setContents(implementation,10);
					pattern.setContents(sampleCode,11);
					pattern.setContents(knownUses,12);
					pattern.setContents(relatedPatterns,13);
					
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
		textFieldPatternClassification.setText(pattern.getComponentsList().get(1).getContents());
		textAreaIntent.setText(pattern.getComponentsList().get(2).getContents());
		textAreaAlsoKnownAs.setText(pattern.getComponentsList().get(3).getContents());
		textAreaMotivation.setText(pattern.getComponentsList().get(4).getContents());
		textAreaApplicability.setText(pattern.getComponentsList().get(5).getContents());
		textAreaStructure.setText(pattern.getComponentsList().get(6).getContents());
		textAreaParticipants.setText(pattern.getComponentsList().get(7).getContents());
		textAreaCollaborations.setText(pattern.getComponentsList().get(8).getContents());
		textAreaConsequences.setText(pattern.getComponentsList().get(9).getContents());
		textAreaImplementation.setText(pattern.getComponentsList().get(10).getContents());
		textAreaSampleCode.setText(pattern.getComponentsList().get(11).getContents());
		textAreaKnownUses.setText(pattern.getComponentsList().get(12).getContents());
		textAreaRelatedPatterns.setText(pattern.getComponentsList().get(13).getContents());
	
		
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
					String patternClassification = textFieldPatternClassification.getText();
					String intent = textAreaIntent.getText();
					String alsoKnownAs = textAreaAlsoKnownAs.getText();
					String motivation 	= textAreaMotivation.getText();
					String applicability = textAreaApplicability.getText();
					String structure = textAreaStructure.getText();
					String participants = textAreaParticipants.getText();
					String collaborations = textAreaCollaborations.getText();
					String consequences = textAreaConsequences.getText();
					String implementation = textAreaImplementation.getText();
					String sampleCode = textAreaSampleCode.getText();
					String knownUses = textAreaKnownUses.getText();
					String relatedPatterns = textAreaRelatedPatterns.getText();
					
					pattern.setName(name);
						
					pattern.setContents(template,0);
					pattern.setContents(patternClassification,1);
					pattern.setContents(intent,2);
					pattern.setContents(alsoKnownAs,3);
					pattern.setContents(motivation,4);
					pattern.setContents(applicability,5);
					pattern.setContents(structure,6);
					pattern.setContents(participants,7);
					pattern.setContents(collaborations,8);
					pattern.setContents(consequences,9);
					pattern.setContents(implementation,10);
					pattern.setContents(sampleCode,11);
					pattern.setContents(knownUses,12);
					pattern.setContents(relatedPatterns,13);
										
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