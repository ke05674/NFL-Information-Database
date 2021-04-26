package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TeamInfo extends JFrame {

	int x;
	private JPanel contentPane;
	private JTable team;
	private JTable stadium;
	private JTable player;
	private JTable table;
	private JTable coach;
	private JTable owner;
	private JTable game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamInfo frame = new TeamInfo();
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
	public TeamInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 103, 376, 41);
		contentPane.add(scrollPane);
		
		team = new JTable();
		scrollPane.setViewportView(team);
		team.setBorder(UIManager.getBorder("ScrollPane.border"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(439, 103, 430, 41);
		contentPane.add(scrollPane_1);
		
		stadium = new JTable();
		scrollPane_1.setViewportView(stadium);
		
		JButton seagulls = new JButton("Seagulls");
		seagulls.setFont(new Font("Tahoma", Font.PLAIN, 16));
		seagulls.setBackground(Color.CYAN);
		seagulls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String query="Select team.Team_name, team.Team_ID, team.Team_grade, team.Position_needs, team.Mascot from team where Team_name='Seagulls'";
					ResultSet rs=stmt.executeQuery(query);
					team.setModel(DbUtils.resultSetToTableModel(rs));
					String stadiumq="Select stadium.Stadium_name, stadium.Stadium_ID, stadium.Location, stadium.Seat_capacity from stadium where Stadium_ID='21'";
					ResultSet rss=stmt.executeQuery(stadiumq);
					stadium.setModel(DbUtils.resultSetToTableModel(rss));
					String playq="Select player.First_name, player.Last_name, player.Player_grade, player.Position, player.Status from player, playerteam where Team_ID='547829' and playerteam.Player_ID=player.Player_ID";
					ResultSet prs=stmt.executeQuery(playq);
					player.setModel(DbUtils.resultSetToTableModel(prs));
					String coachq="Select coach.First_name, coach.Last_name, coach.Favorability, coach.Start_Date, coach.End_Date, coach.Coach_ID from coach, coachteam where coachteam.Team_ID='547829' and coach.Coach_ID=coachteam.Coach_ID";
					ResultSet rsc=stmt.executeQuery(coachq);
					coach.setModel(DbUtils.resultSetToTableModel(rsc));
					String ownerq="Select owner.Net_worth, owner.Favorability, owner.Stake_percentage from owner, ownerteam where ownerteam.Team_ID='547829' and owner.Owner_ID=ownerteam.Owner_ID";
					ResultSet rso=stmt.executeQuery(ownerq);
					owner.setModel(DbUtils.resultSetToTableModel(rso));
					String gameq="Select Game_ID from game";
					ResultSet rsg=stmt.executeQuery(gameq);
					game.setModel(DbUtils.resultSetToTableModel(rsg));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		seagulls.setBounds(431, 51, 96, 29);
		contentPane.add(seagulls);
		
		JButton pirates = new JButton("Pirates");
		pirates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String query="Select team.Team_name, team.Team_ID, team.Team_grade, team.Position_needs, team.Mascot from team where Team_name='Pirates'";
					ResultSet rs=stmt.executeQuery(query);
					team.setModel(DbUtils.resultSetToTableModel(rs));
					String stadiumq="Select stadium.Stadium_name, stadium.Stadium_ID, stadium.Location, stadium.Seat_capacity from stadium where Stadium_ID='45'";
					ResultSet rss=stmt.executeQuery(stadiumq);
					stadium.setModel(DbUtils.resultSetToTableModel(rss));
					String playq="Select player.First_name, player.Last_name, player.Player_grade, player.Position, player.Status from player, playerteam where Team_ID='547367' and playerteam.Player_ID=player.Player_ID";
					ResultSet prs=stmt.executeQuery(playq);
					player.setModel(DbUtils.resultSetToTableModel(prs));
					String coachq="Select coach.First_name, coach.Last_name, coach.Favorability, coach.Start_Date, coach.End_Date, coach.Coach_ID from coach, coachteam where coachteam.Team_ID='547367' and coach.Coach_ID=coachteam.Coach_ID";
					ResultSet rsc=stmt.executeQuery(coachq);
					coach.setModel(DbUtils.resultSetToTableModel(rsc));
					String ownerq="Select owner.Net_worth, owner.Favorability, owner.Stake_percentage from owner, ownerteam where ownerteam.Team_ID='547367' and owner.Owner_ID=ownerteam.Owner_ID";
					ResultSet rso=stmt.executeQuery(ownerq);
					owner.setModel(DbUtils.resultSetToTableModel(rso));
					String gameq="Select Game_ID from game";
					ResultSet rsg=stmt.executeQuery(gameq);
					game.setModel(DbUtils.resultSetToTableModel(rsg));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pirates.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pirates.setBackground(Color.ORANGE);
		pirates.setBounds(537, 51, 96, 29);
		contentPane.add(pirates);
		
		JLabel lblNewLabel = new JLabel("Please select a team:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(251, 50, 170, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NFL Information Database");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(251, 11, 382, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Team:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(24, 81, 376, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stadium:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(439, 81, 343, 23);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 175, 376, 54);
		contentPane.add(scrollPane_2);
		
		player = new JTable();
		scrollPane_2.setViewportView(player);
		player.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		player.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_4 = new JLabel("Player:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(24, 151, 376, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Coach:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(24, 234, 376, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Owner:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(24, 305, 376, 14);
		contentPane.add(lblNewLabel_6);
		
		table = new JTable();
		table.setBounds(24, 259, 67, -30);
		contentPane.add(table);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(24, 255, 376, 45);
		contentPane.add(scrollPane_4);
		
		coach = new JTable();
		scrollPane_4.setViewportView(coach);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(24, 327, 376, 41);
		contentPane.add(scrollPane_5);
		
		owner = new JTable();
		scrollPane_5.setViewportView(owner);
		
		JButton btnNewButton = new JButton("Generate Game Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameReport report = new GameReport();
				report.setVisible(true);
			}
		});
		btnNewButton.setBounds(611, 188, 213, 33);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(480, 175, 122, 96);
		contentPane.add(scrollPane_3);
		
		game = new JTable();
		scrollPane_3.setViewportView(game);
		
		JLabel lblNewLabel_7 = new JLabel("Possible Game IDs:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(480, 155, 122, 14);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("Search Draft Prospects");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CoachDrafts report = new CoachDrafts();
				report.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(537, 305, 213, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_8 = new JLabel("You will need a Coach ID.");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(537, 339, 213, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("You will need a Game ID.");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(611, 225, 212, 14);
		contentPane.add(lblNewLabel_9);
	}
}
