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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfEmail;
	private JTextField tfCnic;
	private JTextField tfCPassword;
	private JTextField tfPassword;
	
	
	
	

	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWellonlineVotingSystem = new JLabel("Welcome to Online Voting System");
		lblWellonlineVotingSystem.setFont(new Font("Sakkal Majalla", Font.BOLD, 24));
		lblWellonlineVotingSystem.setBounds(126, 40, 409, 55);
		contentPane.add(lblWellonlineVotingSystem);
		
		JLabel lblSignupHere = new JLabel("Signup Here");
		lblSignupHere.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSignupHere.setBounds(274, 72, 128, 55);
		contentPane.add(lblSignupHere);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfName.setColumns(10);
		tfName.setBounds(211, 152, 258, 20);
		contentPane.add(tfName);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(100, 152, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mobile No :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(70, 189, 115, 14);
		contentPane.add(lblNewLabel_1_1);
		
		tfMobile = new JTextField();
		tfMobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfMobile.setColumns(10);
		tfMobile.setBounds(211, 189, 258, 20);
		contentPane.add(tfMobile);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfEmail.setColumns(10);
		tfEmail.setBounds(211, 227, 258, 20);
		contentPane.add(tfEmail);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(100, 227, 74, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("CNIC :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(100, 268, 83, 14);
		contentPane.add(lblNewLabel_1_3);
		
		tfCnic = new JTextField();
		tfCnic.setToolTipText("");
		tfCnic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCnic.setColumns(10);
		tfCnic.setBounds(211, 268, 258, 20);
		contentPane.add(tfCnic);
		
		JLabel lblNewLabel_2_1 = new JLabel("XXXXX-XXXXXXX-X");
		lblNewLabel_2_1.setBounds(479, 272, 115, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String name=tfName.getText().toString().trim();
			String mobile=tfMobile.getText().toString().trim();
			String email=tfEmail.getText().toString().trim();
			String cnic=tfCnic.getText().toString().trim();
			String password=tfPassword.getText().toString();
			String cPassword=tfCPassword.getText().toString();
			
			boolean emptyIPCheck=checkEmptyInputFields(name,mobile,email,cnic,password,cPassword);
			
			if(emptyIPCheck)
			{
				if(cnicFormat(cnic))
				{
					JOptionPane.showMessageDialog(btnSignup, "CNIC format is not correct. Please enter CNIC in the format XXXXX-XXXXXXX-X");
					return;
				}
				
				if(!cPassword.equals(password))
				{
					JOptionPane.showMessageDialog(btnSignup, "Password mis-macthed");
					return;
				}
				else
				{
					FileWriter fw=null;
					BufferedWriter bw=null;
					
					try 
					{
						fw = new FileWriter("Signup_records.txt",true);
						bw=new BufferedWriter(fw);
						bw.write(name+","+mobile+","+email+","+password+","+cnic);
						bw.newLine();
						JOptionPane.showMessageDialog(btnSignup, "Account created successfully");
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try 
								{
									Home frame = new Home();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						dispose();
						
						
					} catch (IOException e1) 
					{
						JOptionPane.showMessageDialog(btnSignup, e1.getMessage());
					}
				
					
					try {
						bw.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(btnSignup, e1.getMessage());
					}
					
					try {
						fw.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(btnSignup, e1.getMessage());
					}
					
				}
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(btnSignup, "Cannot SignUp");
			}
			
			}

			private boolean checkEmptyInputFields(String name, String mobile, 
					String email, String cnic, String password,String cPassword) 
			{
				
				if(name.isEmpty())
				{
					JOptionPane.showMessageDialog(btnSignup, "Name cannot be empty");	
					return false;
				}			

				if(mobile.isEmpty())
				{
					JOptionPane.showMessageDialog(btnSignup, "Mobile Number cannot be empty");	
					return false;
				}
					
				if(email.isEmpty())
				{
					JOptionPane.showMessageDialog(btnSignup, "Email cannot be empty");	
					return false;
				}
					
				if(cnic.isEmpty())
				{
					JOptionPane.showMessageDialog(btnSignup, "CNIC cannot be empty");	
					return false;
				}
			
				if(password.isEmpty())
				{
					JOptionPane.showMessageDialog(btnSignup, "Password cannot be empty");	
					return false;
				}
				
				if(cPassword.isEmpty())
				{
					JOptionPane.showMessageDialog(btnSignup, "Confirm Password cannot be empty");	
					return false;
				}
				
				if(!name.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !cnic.isEmpty()
						&& !password.isEmpty() && !cPassword.isEmpty())
				{
					return true;
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
			
			
		});
		btnSignup.setBounds(211, 386, 89, 23);
		contentPane.add(btnSignup);
		
		tfCPassword = new JTextField();
		tfCPassword.setToolTipText("");
		tfCPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCPassword.setColumns(10);
		tfCPassword.setBounds(211, 345, 258, 20);
		contentPane.add(tfCPassword);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPassword.setColumns(10);
		tfPassword.setBounds(212, 306, 258, 20);
		contentPane.add(tfPassword);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Password :");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(70, 306, 96, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Confirm Password :");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(10, 347, 152, 14);
		contentPane.add(lblNewLabel_1_3_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(42, 119, 552, 2);
		contentPane.add(separator);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
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
			
			}
		});
		btnGoBack.setBounds(380, 386, 89, 23);
		contentPane.add(btnGoBack);
	}
}
