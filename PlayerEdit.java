package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PlayerEdit extends JFrame {

	private JPanel contentPane;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerEdit frame = new PlayerEdit();
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
	public PlayerEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Seagulls");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String query="Select team.Team_name, team.Team_ID, player.First_name, player.Last_name, player.Player_ID, player.Player_grade, player.Position, player.Status from team,player,playerteam where Team_name='Seagulls' and playerteam.Team_ID=team.Team_ID and playerteam.Player_ID=player.Player_ID";
					ResultSet rs=stmt.executeQuery(query);
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(284, 51, 121, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pirates");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String query="Select team.Team_name, team.Team_ID, player.First_name, player.Last_name, player.Player_ID, player.Player_grade, player.Position, player.Status from team,player,playerteam where Team_name='Pirates' and playerteam.Team_ID=team.Team_ID and playerteam.Player_ID=player.Player_ID";
					ResultSet rs=stmt.executeQuery(query);
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(428, 51, 121, 47);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 781, 92);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_2 = new JButton("ADD");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddRemove add = new AddRemove();
				add.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(Color.GREEN);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(215, 222, 121, 56);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("REMOVE");
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddRemove remove = new AddRemove();
				remove.setVisible(true);
			}
		});
		btnNewButton_3.setBackground(Color.RED);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(477, 222, 121, 56);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Select a team name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(34, 58, 262, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NFL Information Database");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 11, 781, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("UPDATE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddRemove update = new AddRemove();
				update.setVisible(true);
			}
		});
		btnNewButton_4.setBackground(Color.PINK);
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setBounds(346, 222, 121, 56);
		contentPane.add(btnNewButton_4);
		
	}
}
