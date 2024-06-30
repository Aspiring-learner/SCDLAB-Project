package onlinevotingsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Cnic extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfFatherName;
	private JTextField tfDOB;
	private JTextField tfCNIC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cnic frame = new Cnic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cnic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setToolTipText("dd/MM/yyyy");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Online Voting System");
		lblNewLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 24));
		lblNewLabel.setBounds(207, 54, 285, 55);
		contentPane.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfName.setBounds(213, 142, 258, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(92, 144, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		tfFatherName = new JTextField();
		tfFatherName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfFatherName.setColumns(10);
		tfFatherName.setBounds(213, 187, 258, 20);
		contentPane.add(tfFatherName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Father Name :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(42, 189, 133, 14);
		contentPane.add(lblNewLabel_1_1);
		
		tfDOB = new JTextField();
		tfDOB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfDOB.setColumns(10);
		tfDOB.setBounds(213, 229, 258, 20);
		contentPane.add(tfDOB);
		
		JLabel lblNewLabel_1_2 = new JLabel("DOB :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(101, 231, 74, 14);
		contentPane.add(lblNewLabel_1_2);
		
		tfCNIC = new JTextField();
		tfCNIC.setToolTipText("");
		tfCNIC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCNIC.setColumns(10);
		tfCNIC.setBounds(213, 276, 258, 20);
		contentPane.add(tfCNIC);
		
		JLabel lblNewLabel_1_3 = new JLabel("CNIC :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(92, 278, 83, 14);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2 = new JLabel("dd/MM/yyyy");
		lblNewLabel_2.setBounds(481, 233, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("XXXXX-XXXXXXX-X");
		lblNewLabel_2_1.setBounds(481, 280, 115, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name=tfName.getText().toString().trim();
				String fatherName=tfFatherName.getText().toString().trim();
				String dob=tfDOB.getText().toString().trim();
				String cnic=tfCNIC.getText().toString().trim();
		
				checkValidity(name,fatherName,dob,cnic);

			}

			
			private boolean dobFormat(String dob) 
			{
				if(dob.length()!=10)
				{
					return true;
				}
				
				for(int i=0;i<dob.length();i++)
				{
					if(i==2 || i==5)
					{
						if (dob.charAt(i) != '/') 
						{
		                    return true;
		                }
					}
				}
				
				return false;
			}


			private boolean cnicFormat(String cnic)
			{
				if(cnic.length()!=15)
				{
					return true;
				}
				
				for(int i=0;i<cnic.length();i++)
				{
					if(i==5 || i==13)
					{
						if (cnic.charAt(i) != '-') 
						{
		                    return true;
		                }
					}
				}
				
				return false;
			}


			private void checkValidity(String name, String fatherName, String dob, String cnic) 
			{
				
				boolean emptyIPCheck=checkEmptyInputFields(name,fatherName,dob,cnic);
				
				if(emptyIPCheck)
				{
					if(cnicFormat(cnic))
					{
						JOptionPane.showMessageDialog(btnProceed, "CNIC format is not correct. Please enter CNIC in the format XXXXX-XXXXXXX-X");
						return;
					}
					
					if(dobFormat(dob))
					{
						JOptionPane.showMessageDialog(btnProceed, "DOB format is not correct. Please enter DOB in the format dd/MM/yyyy");
						return;
					}
					
					FileReader fr = null;
					BufferedReader br=null;
					try 
					{
						fr = new FileReader("cnic_records.txt");
						br=new BufferedReader(fr);
					} catch (FileNotFoundException e1) 
					{
						JOptionPane.showMessageDialog(btnProceed, e1.getMessage());
					}
					
					String line;
					boolean found=false;
					
					try 
					{
						while((line=br.readLine())!=null)
						{
							String details[]=line.split(",");
							
							String personName=details[0];
							String personFatherName=details[1];
							String personDOB=details[2];
							String personCNIC=details[3];
						
							if(personCNIC.equals(cnic) && personDOB.equals(dob) 
									&& personFatherName.equals(fatherName) &&personName.equals(name))
							{
								found=true;
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											Login frame = new Login();
											frame.setVisible(true);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								dispose();
								break;
							}	
						}
						
						 if (!found) 
						 {
					        JOptionPane.showMessageDialog(btnProceed, "CNIC validation failed: incorrect details");
					     }
						
					} catch (IOException e1) 
					{
					
						JOptionPane.showMessageDialog(btnProceed, e1.getMessage());
					}	
					
					try 
					{
						fr.close();
					} 
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(btnProceed, e.getMessage());	
					}
					try 
					{
						br.close();
					} 
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(btnProceed, e.getMessage());	
					}
				}
				else
				{
					JOptionPane.showMessageDialog(btnProceed, "Cannot proceed");	
				}
					
			}

			private boolean checkEmptyInputFields(String name, String fatherName, String dob, String cnic) {
				
				if(name.isEmpty())
				{
					JOptionPane.showMessageDialog(btnProceed, "Name cannot be empty");	
					return false;
				}
				
				if(fatherName.isEmpty())
				{
					JOptionPane.showMessageDialog(btnProceed, "Father Name cannot be empty");
					return false;
				}
				
				if(dob.isEmpty())
				{
					JOptionPane.showMessageDialog(btnProceed, "Date of Birth cannot be empty");	
					return false;
				}
				
				if(cnic.isEmpty())
				{
					JOptionPane.showMessageDialog(btnProceed, "CNIC cannot be empty");	
					return false;
				}
				
			
				if(!name.isEmpty() && !fatherName.isEmpty() && !dob.isEmpty() && !cnic.isEmpty())
				{
					return true;
				}
				return false;
			}
			
		});
		
		
		btnProceed.setBounds(213, 320, 89, 23);
		contentPane.add(btnProceed);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(42, 104, 552, 2);
		contentPane.add(separator);
	}
}
