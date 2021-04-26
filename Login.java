package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(20, 31, 199, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(20, 62, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBounds(26, 78, 193, 31);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(20, 120, 60, 14);
		contentPane.add(lblNewLabel_1_1);
		
		pass = new JPasswordField();
		pass.setBounds(26, 137, 193, 31);
		contentPane.add(pass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean test;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String sql="Select * from coachaccount,account where coachaccount.Username=account.Username and account.Username='"+user.getText()+"'and account.Password='"+pass.getText().toString()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next()) {
						dispose();
						PlayerEdit playEdit = new PlayerEdit();
						playEdit.setVisible(true);
						test=true;
					}
					String sql2="Select * from owneraccount, account where owneraccount.Username=account.Username and account.Username='"+user.getText()+"'and account.Password='"+pass.getText().toString()+"'";
					ResultSet rss=stmt.executeQuery(sql2);
				    if(rss.next()) {
						dispose();
						PlayerEdit playerEdit=new PlayerEdit();
						playerEdit.setVisible(true);
						test=true;
					}
					if(test=true) {
						JOptionPane.showMessageDialog(null,"Login Successfully");
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect username and Password");
					conn.close();
				} catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(130, 189, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("NFL Information Database");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(20, 11, 223, 23);
		contentPane.add(lblNewLabel_2);
	}
}
