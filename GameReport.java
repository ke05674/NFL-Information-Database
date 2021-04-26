package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameReport extends JFrame {

	private JPanel contentPane;
	private JTextField gameid;
	private JTable game;
	private JTable game2;
	private JTable player;
	private JTable media;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameReport frame = new GameReport();
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
	public GameReport() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("NFL Information Database");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(22, 11, 485, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter a Game ID to generate a report:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(78, 71, 221, 14);
		contentPane.add(lblNewLabel_1);
		
		gameid = new JTextField();
		gameid.setBounds(298, 69, 86, 20);
		contentPane.add(gameid);
		gameid.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("GO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfldatabase","root","");
					Statement stmt=conn.createStatement();
					String gameq="Select Home_Team_ID, Away_Team_ID, Game_ID, Date, Time from game where Game_ID='"+gameid.getText()+"'";
					ResultSet rsg=stmt.executeQuery(gameq);
					game.setModel(DbUtils.resultSetToTableModel(rsg));
					String game2q="Select Score, Winning_Team_ID, Stadium_ID from game_results, game where game_results.Home_Team_ID=game.Home_Team_ID";
					ResultSet rsgg=stmt.executeQuery(game2q);
					game2.setModel(DbUtils.resultSetToTableModel(rsgg));
					String game3q="Select Player.First_name, Player.Last_name, participates_in.Player_ID, playerteam.Team_ID, Team.Team_name from participates_in,game,playerteam,team,player where participates_in.Game_ID=game.Game_ID and participates_in.Player_ID=playerteam.Player_ID and team.Team_ID=playerteam.Team_ID and player.Player_ID=playerteam.Player_ID";
					ResultSet rsggg=stmt.executeQuery(game3q);
					player.setModel(DbUtils.resultSetToTableModel(rsggg));
					String mediaq="Select media_representation.News_outlet, media_representation.Projected_Winner_Team_ID from media_representation where media_representation.Game_ID='"+gameid.getText()+"'";
					ResultSet rsm=stmt.executeQuery(mediaq);
					media.setModel(DbUtils.resultSetToTableModel(rsm));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(394, 69, 56, 20);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 96, 485, 45);
		contentPane.add(scrollPane);
		
		game = new JTable();
		scrollPane.setViewportView(game);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 152, 485, 45);
		contentPane.add(scrollPane_1);
		
		game2 = new JTable();
		scrollPane_1.setViewportView(game2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(22, 208, 485, 80);
		contentPane.add(scrollPane_2);
		
		player = new JTable();
		scrollPane_2.setViewportView(player);
		
		JLabel lblNewLabel_2 = new JLabel("Game Report");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(22, 40, 485, 20);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TeamInfo back = new TeamInfo();
				back.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(214, 376, 104, 31);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(22, 298, 485, 67);
		contentPane.add(scrollPane_3);
		
		media = new JTable();
		scrollPane_3.setViewportView(media);
	}
}
