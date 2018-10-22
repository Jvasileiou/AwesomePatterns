package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import compositePattern.Pattern;
import compositePattern.PatternComponent;
import compositePattern.Play;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;

public class SelectTemplate extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> TemplateChoice;
	private JButton cancelButton,nextButton;
	private JLabel lblSelectTemplate;
	private DefaultListModel<String> modelPL;
	
	private DefaultListModel<String> modelP;
	//private JList<String> list;
	private MicroPattern newWindow;
	private InductivePattern newWindow2;
	private DeductivePattern newWindow3;
	private GangOfFourPattern newWindow4;
	private SystemOfPatterns newWindow5;
	private PatternComponent patternLanguage;
	private Play objectMain;
	private Pattern pattern;

	
	public SelectTemplate(Play newObjectMain, PatternComponent newPatternLanguage,
			DefaultListModel<String> newModelPL ,DefaultListModel<String> newModelP) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		patternLanguage = newPatternLanguage;
		objectMain = newObjectMain;
		modelPL = newModelPL;
		modelP = newModelP;	
		
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
			lblSelectTemplate = new JLabel("Select Template:");
			lblSelectTemplate.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectTemplate.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblSelectTemplate.setBounds(10, 52, 414, 40);
			contentPanel.add(lblSelectTemplate);
		}
		{
			TemplateChoice = new JComboBox<String>();
			TemplateChoice.setMaximumRowCount(4);
			TemplateChoice.setToolTipText("");
			TemplateChoice.setForeground(Color.BLACK);
			TemplateChoice.setBackground(Color.LIGHT_GRAY);
			TemplateChoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
			TemplateChoice.setModel(new DefaultComboBoxModel<String>(new String[] {"",
					"Micro Pattern",
					"Inductive Mini Pattern",
					"Deductive Mini Pattern",
					"Gang of Four Pattern",
					"System of Patterns"}));
			TemplateChoice.setBounds(20, 98, 394, 40);
			contentPanel.add(TemplateChoice);
		}
		{
			nextButton = new JButton("Next");
			nextButton.setBounds(334, 205, 90, 45);
			contentPanel.add(nextButton);
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					if(TemplateChoice.getSelectedIndex() == 0 )
					{	
						String st = "You did not choose template.\nDefault template is Micro";
						JOptionPane.showMessageDialog(null, st);
						pattern = objectMain.createPattern("MicroPattern");
													
						newWindow = new MicroPattern(objectMain, patternLanguage, pattern, modelPL, modelP);
						newWindow.setVisible(true);
					}
					else if(TemplateChoice.getSelectedIndex() == 1){
						
						pattern = objectMain.createPattern("MicroPattern");
													
						newWindow = new MicroPattern(objectMain, patternLanguage, pattern, modelPL, modelP);
						newWindow.setVisible(true);
					}
					else if(TemplateChoice.getSelectedIndex() == 2) {
						pattern = objectMain.createPattern("InductiveMiniPattern");
						
						newWindow2 = new InductivePattern(objectMain, patternLanguage, pattern, modelPL, modelP);
						newWindow2.setVisible(true);
					}
					else if(TemplateChoice.getSelectedIndex() == 3) {
						pattern = objectMain.createPattern("DeductiveMiniPattern");
						
						newWindow3 = new DeductivePattern(objectMain, patternLanguage, pattern, modelPL, modelP);
						newWindow3.setVisible(true);
					}
					else if(TemplateChoice.getSelectedIndex() == 4) {
						pattern = objectMain.createPattern("GangOfFourPattern");
						
						newWindow4 = new GangOfFourPattern(objectMain, patternLanguage, pattern, modelPL, modelP);
						newWindow4.setVisible(true);
					}
					else if(TemplateChoice.getSelectedIndex() == 5) {
						pattern = objectMain.createPattern("SystemOfPatterns");
						
						newWindow5 = new SystemOfPatterns(objectMain, patternLanguage, pattern, modelPL, modelP);
						newWindow5.setVisible(true);
					}
					
					dispose();
				}
			
			});
			nextButton.setActionCommand("next");
			getRootPane().setDefaultButton(nextButton);
		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.setBounds(10, 205, 90, 45);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditPattern patternMenu = new EditPattern(objectMain, patternLanguage, modelPL, modelP, false);
					patternMenu.setVisible(true);
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}

}