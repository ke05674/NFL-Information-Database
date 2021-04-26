package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddRemove extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField playerID;
	private JTextField playergrade;
	private JTextField position;
	private JTextField status;
	private JTextField teamid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRemove frame = new AddRemove();
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
	public AddRemove() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					PreparedStatement ps=conn.prepareStatement("insert into player(First_name,Last_name,Player_ID,Player_grade,Position,Status) values(?,?,?,?,?,?);");
					ps.setString(1,firstname.getText());
					ps.setString(2,lastname.getText());
					ps.setString(3,playerID.getText());
					ps.setString(4,playergrade.getText());
					ps.setString(5,position.getText());
					ps.setString(6,status.getText());
					int x = ps.executeUpdate();
					if(x>0) {
						JOptionPane.showMessageDialog(null, "Player successfully created.");
					}
					else {
						JOptionPane.showMessageDialog(null , "The player already exists.");
					}
					PreparedStatement pss=conn.prepareStatement("insert into playerteam(Player_ID,Team_ID) values(?,?);");
					pss.setString(1,playerID.getText());
					pss.setString(2, teamid.getText());
					pss.executeUpdate();
					conn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(20, 199, 122, 45);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REMOVE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					PreparedStatement ps=conn.prepareStatement("delete from player where player.Player_ID='"+playerID.getText()+"'");
					int x = ps.executeUpdate();
					if(x>0) {
						JOptionPane.showMessageDialog(null, "Player successfully deleted.");
					}
					else {
						JOptionPane.showMessageDialog(null , "The player still exists.");
					}
					conn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(20, 255, 122, 45);
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("First name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 23, 73, 21);
		contentPane.add(lblNewLabel);
		
		firstname = new JTextField();
		firstname.setBounds(33, 45, 104, 21);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(147, 25, 73, 17);
		contentPane.add(lblNewLabel_1);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(158, 45, 104, 21);
		contentPane.add(lastname);
		
		JLabel lblNewLabel_2 = new JLabel("Player ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(20, 68, 73, 17);
		contentPane.add(lblNewLabel_2);
		
		playerID = new JTextField();
		playerID.setColumns(10);
		playerID.setBounds(33, 88, 104, 21);
		contentPane.add(playerID);
		
		JLabel lblNewLabel_3 = new JLabel("Player grade:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(148, 66, 96, 21);
		contentPane.add(lblNewLabel_3);
		
		playergrade = new JTextField();
		playergrade.setColumns(10);
		playergrade.setBounds(158, 88, 104, 21);
		contentPane.add(playergrade);
		
		JLabel lblNewLabel_4 = new JLabel("Position:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(20, 115, 61, 17);
		contentPane.add(lblNewLabel_4);
		
		position = new JTextField();
		position.setColumns(10);
		position.setBounds(33, 132, 104, 21);
		contentPane.add(position);
		
		JLabel lblNewLabel_5 = new JLabel("Status:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(148, 115, 61, 17);
		contentPane.add(lblNewLabel_5);
		
		status = new JTextField();
		status.setColumns(10);
		status.setBounds(158, 132, 104, 21);
		contentPane.add(status);
		
		JLabel lblNewLabel_6 = new JLabel("NFL Information Database");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(10, 3, 267, 21);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Team ID:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(59, 164, 61, 24);
		contentPane.add(lblNewLabel_7);
		
		teamid = new JTextField();
		teamid.setBounds(116, 165, 104, 23);
		contentPane.add(teamid);
		teamid.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("GO BACK");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PlayerEdit back = new PlayerEdit();
				back.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(Color.BLACK);
		btnNewButton_2.setBounds(152, 255, 118, 45);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("UPDATE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					PreparedStatement ps=conn.prepareStatement("Update player set First_name='"+firstname.getText()+"' ,Last_name='"+lastname.getText()+"' ,Player_ID='"+playerID.getText()+"' ,Player_grade='"+playergrade.getText()+"' ,Position='"+position.getText()+"' ,Status='"+status.getText()+"' where Player_ID='"+playerID.getText()+"' ");
					int x = ps.executeUpdate();
					if(x>0) {
						JOptionPane.showMessageDialog(null, "Player successfully updated.");
					}
					else {
						JOptionPane.showMessageDialog(null , "The player was not updated.");
					}
					conn.close();
					PreparedStatement pss=conn.prepareStatement("Update playerteam set Player_ID='"+playerID.getText()+"', Team_ID='"+teamid.getText()+"'");
					pss.executeUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBackground(Color.PINK);
		btnNewButton_3.setBounds(152, 199, 118, 45);
		contentPane.add(btnNewButton_3);
	}
}
