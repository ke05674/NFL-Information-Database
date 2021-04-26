package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;

public class CoachDrafts extends JFrame {

	private JPanel contentPane;
	private JTable draft;
	private JTextField coachid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoachDrafts frame = new CoachDrafts();
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
	public CoachDrafts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 581, 136);
		contentPane.add(scrollPane);
		
		draft = new JTable();
		scrollPane.setViewportView(draft);
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String draftq="Select looks_for.Coach_ID, draft_prospects.Draft_ID, draft_prospects.Draft_Number, draft_prospects.Draft_Grade, draft_prospects.Combine_invite, draft_prospects.Predicted_round from looks_for, draft_prospects where looks_for.Draft_ID=draft_prospects.Draft_ID and Coach_ID='"+coachid.getText()+"'";
					ResultSet rsd=stmt.executeQuery(draftq);
					draft.setModel(DbUtils.resultSetToTableModel(rsd));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(377, 53, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Please enter a Coach ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(139, 54, 147, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Draft Report");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 32, 559, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NFL Information Database");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 581, 20);
		contentPane.add(lblNewLabel_2);
		
		coachid = new JTextField();
		coachid.setBounds(281, 54, 86, 20);
		contentPane.add(coachid);
		coachid.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Go Back");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TeamInfo back = new TeamInfo();
				back.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(471, 234, 100, 31);
		contentPane.add(btnNewButton_1);
	}
}
