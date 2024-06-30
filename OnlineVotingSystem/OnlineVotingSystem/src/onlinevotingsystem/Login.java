package onlinevotingsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMobile;
	private JTextField tfPassword;
	private ArrayList<String> list=new ArrayList<String>();
	private FileReader fr=null;
	private BufferedReader br=null;
	

	private void openFile()
	{
		try 
		{
			fr = new FileReader("Signup_records.txt");
			br=new BufferedReader(fr);
		} catch (FileNotFoundException e1) 
		{
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
		
	}
	
	private void closeFile()
	{
		try 
		{
			fr.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(contentPane, e.getMessage());	
		}
		try 
		{
			br.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(contentPane, e.getMessage());	
		}
	}
	
	private void loadDataIntoList()
	{
		String line;
		try 
		{
			while((line=br.readLine())!=null)
			{
				list.add(line);
			}
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(contentPane, e.getMessage());	
		}
		
	}
	
	private void saveDataIntoFile()
	{
		FileWriter fw=null;
		BufferedWriter bw=null;
		
		try 
		{
			fw = new FileWriter("Signup_records.txt");
			bw=new BufferedWriter(fw);
			
			for(String line:list)
			{
				bw.write(line);
				bw.newLine();	
			}
			
			JOptionPane.showMessageDialog(contentPane, "Password updated successfully");
			
		} catch (IOException e1) 
		{
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
	
		
		try {
			bw.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
		
		try {
			fw.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
		
		
	}
	
	public Login() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfMobile = new JTextField();
		tfMobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfMobile.setColumns(10);
		tfMobile.setBounds(233, 171, 258, 20);
		contentPane.add(tfMobile);
		
		JLabel lblNewLabel_1 = new JLabel("Mobile No :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(100, 173, 95, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(100, 218, 95, 14);
		contentPane.add(lblNewLabel_1_1);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPassword.setColumns(10);
		tfPassword.setBounds(233, 216, 258, 20);
		contentPane.add(tfPassword);
		
		JLabel lblNewLabel_1_3 = new JLabel("Do not have account ? Register Here");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(142, 403, 241, 29);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblWellonlineVotingSystem = new JLabel("Welcome to Online Voting System");
		lblWellonlineVotingSystem.setFont(new Font("Sakkal Majalla", Font.BOLD, 24));
		lblWellonlineVotingSystem.setBounds(142, 55, 410, 55);
		contentPane.add(lblWellonlineVotingSystem);
		
		JLabel lblLoginToContinue = new JLabel("Login to Continue");
		lblLoginToContinue.setFont(new Font("Dialog", Font.BOLD, 20));
		lblLoginToContinue.setBounds(254, 87, 198, 55);
		contentPane.add(lblLoginToContinue);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mobile=tfMobile.getText().toString().trim();
				String password=tfPassword.getText().toString();
				
				checkValidity(mobile,password);
				
			}

			private void checkValidity(String mobile, String password) {
				
				boolean emptyIPCheck=checkEmptyInputFields(mobile,password);
				
				if(emptyIPCheck)
				{
					openFile();
					list.clear();
					loadDataIntoList();
					
					boolean found=false;
					
					for(String line:list)
					{
						String details[]=line.split(",");
						
						String pass=details[3];
						String mob=details[1];
					
						if(mobile.equals(mob) && password.equals(pass))
						{
							found=true;
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										Home frame = new Home();
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
					
					if (!found) {
					    JOptionPane.showMessageDialog(btnLogin, "Mobile number or password is incorrect");
					}
					
					closeFile();
					
				}
				else
				{
					JOptionPane.showMessageDialog(btnLogin, "Cannot Login");	
				}
				
			}

			private boolean checkEmptyInputFields(String mobile, String password) {
				
				if(mobile.isEmpty())
				{
					JOptionPane.showMessageDialog(btnLogin, "Mobile cannot be empty");	
					return false;
				}
				
				if(password.isEmpty())
				{
					JOptionPane.showMessageDialog(btnLogin, "Password cannot be empty");	
					return false;
				}
				
				if(!mobile.isEmpty() && !password.isEmpty())
				{
					return true;
				}
				
				return false;
			}
		});
		btnLogin.setBounds(402, 251, 89, 23);
		contentPane.add(btnLogin);
	
		JButton btnSignUp = new JButton("Singup");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Signup frame = new Signup();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				dispose();
			}
		});
		btnSignUp.setBounds(393, 408, 89, 23);
		contentPane.add(btnSignUp);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-68, 390, 716, 2);
		contentPane.add(separator);
		
		JLabel ForgetPassword = new JLabel("Forget Password");
		ForgetPassword.setForeground(Color.BLUE);
		ForgetPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ForgetPassword.setBounds(233, 247, 114, 29);
	
		ForgetPassword.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				String email=JOptionPane.showInputDialog(ForgetPassword, "Enter your Email");

				openFile();
				list.clear();
				loadDataIntoList();
				
				boolean emailFound=false;
				int count=0;
				
				for(String line:list)
				{
					String details[]=line.split(",");
					
					String em=details[2];
				
					if(email.equals(em))
					{
						emailFound=true;
						String newPassword=JOptionPane.showInputDialog("Enter new password");
						
						list.remove(count);
						list.add(count, details[0]+","+details[1]+","+details[2]+","+newPassword+","+details[4]);
						saveDataIntoFile();
						count=0;
						break;
					}

					count++;
				}
				
				if(!emailFound)
				{
					JOptionPane.showMessageDialog(ForgetPassword, "Email is not correct");
				}
				
				closeFile();
			}

		});

		
		contentPane.add(ForgetPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(42, 133, 552, 2);
		contentPane.add(separator_1);
		
		
	}
}
