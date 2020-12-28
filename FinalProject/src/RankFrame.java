import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;


public class RankFrame extends JFrame {
	private static final int FRAME_WIDTH = 1025;
	private static final int FRAME_HEIGHT = 620;
	private JLabel No1IDLabel;
	private JLabel No1ScoreLabel;
	private JLabel No1CharacterLabel;
	private JLabel No2IDLabel;
	private JLabel No2ScoreLabel;
	private JLabel No2CharacterLabel;
	private JLabel No3IDLabel;
	private JLabel No3ScoreLabel;
	private JLabel No3CharacterLabel;
	private JLabel bgLabel;
	private ImageIcon background;
	private JPanel contentPanel;
	private JButton returnBtn;

	public RankFrame() throws SQLException {

		background = new ImageIcon(getClass().getResource("RankPage1.jpg")); // 背景圖片
		bgLabel = new JLabel(background); // 把背景圖顯示在Label中
		this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
		createRankname();
		createCharacterPanel();
		goToMainPageFrame();
		returnBtn.setBounds(445, 500, 100, 50);
		cp.add(createContentPanel(), BorderLayout.CENTER);
		
		((JPanel)cp).setOpaque(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Rank Frame");
		setVisible(true);
	}
	
	public void createRankname() throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();

		Statement stat1 = conn.createStatement();
		String query1 = "SELECT `Name`, `Score` FROM Name WHERE `Score` = (SELECT MAX(`Score`) FROM Name)";
		ResultSet result1 = stat1.executeQuery(query1);
		if(result1.next())
		{
			No1IDLabel = new JLabel(result1.getString(1));
			No1ScoreLabel = new JLabel(result1.getString(2));
		}
		
		Statement stat2 = conn.createStatement();
		String query2 = "SELECT `Name`, `Score` FROM Name WHERE `Score` = (SELECT MAX(`Score`) FROM Name WHERE `Score` < (SELECT MAX(`Score`) FROM Name))";
		ResultSet result2 = stat2.executeQuery(query2);
		if(result2.next())
		{
			No2IDLabel = new JLabel(result2.getString(1));
	        No2ScoreLabel = new JLabel(result2.getString(2));
		}
		
		
		Statement stat3 = conn.createStatement();
		String query3 = "SELECT `Name`, `Score` FROM Name WHERE `Score` = \r\n" + 
				"(SELECT MAX(`Score`) FROM Name WHERE `Score` < ((SELECT MAX(`Score`) FROM Name WHERE `Score` < (SELECT MAX(`Score`) FROM Name))))";
		ResultSet result3 = stat3.executeQuery(query3);
		if(result3.next())
		{
			No3IDLabel = new JLabel(result3.getString(1));
	        No3ScoreLabel = new JLabel(result3.getString(2));
		}
	
		No1IDLabel.setBounds(460, 280, 100, 20);
		No1IDLabel.setFont(new java.awt.Font("Dialog", 1, 20));
		No2IDLabel.setBounds(230, 418, 100, 20);
		No2IDLabel.setFont(new java.awt.Font("Dialog", 1, 20));
		No3IDLabel.setBounds(710, 418, 100, 20);
		No3IDLabel.setFont(new java.awt.Font("Dialog", 1, 20));
		No1ScoreLabel.setBounds(460, 320, 100, 20);
		No1ScoreLabel.setFont(new java.awt.Font("Dialog", 1, 20));
		No2ScoreLabel.setBounds(230, 458, 100, 20);
		No2ScoreLabel.setFont(new java.awt.Font("Dialog", 1, 20));
		No3ScoreLabel.setBounds(710, 458, 100, 20);
		No3ScoreLabel.setFont(new java.awt.Font("Dialog", 1, 20));
	}
	
	
	
	public void createCharacterPanel() throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		
		String no1 = "";
		String no2 = "";
		String no3 = "";

		
		Statement stat1 = conn.createStatement();
		String query1 = "SELECT `Character_Photo` FROM Name WHERE `Score` = (SELECT MAX(`Score`) FROM Name)";
		ResultSet result1 = stat1.executeQuery(query1);
		
		Statement stat2 = conn.createStatement();
		String query2 = "SELECT `Character_Photo` FROM Name WHERE `Score` = (SELECT MAX(`Score`) FROM Name WHERE `Score` < (SELECT MAX(`Score`) FROM Name))";
		ResultSet result2 = stat2.executeQuery(query2);
		
		Statement stat3 = conn.createStatement();
		String query3 = "SELECT `Character_Photo` FROM Name WHERE `Score` = \r\n" + 
				"(SELECT MAX(`Score`) FROM Name WHERE `Score` < ((SELECT MAX(`Score`) FROM Name WHERE `Score` < (SELECT MAX(`Score`) FROM Name))))";
		ResultSet result3 = stat3.executeQuery(query3);

		if(result1.next())
		{
			no1 = result1.getString(1);
		}
		
		if(result2.next())
		{
			no2 = result2.getString(1);
		}
		
		if(result3.next())
		{
			no3 = result3.getString(1);
		}
		No1CharacterLabel = chooseCharacterLabel(no1);
		No1CharacterLabel.setBounds(435, 122, 100, 100);
		No2CharacterLabel = chooseCharacterLabel(no2);
		No2CharacterLabel.setBounds(205, 250, 100, 100);
		No3CharacterLabel = chooseCharacterLabel(no3);
		No3CharacterLabel.setBounds(663, 250, 100, 100);
	}
	
	public JLabel chooseCharacterLabel(String ch)
	{ 
		if(ch.equals("A"))
		{
			ImageIcon medjed1 = new ImageIcon(getClass().getResource("medjed1.jpg"));
			JLabel medjed1Label = new JLabel(medjed1);
			return medjed1Label;
		}
		else if(ch.equals("B"))
		{
			ImageIcon medjed2 = new ImageIcon(getClass().getResource("medjed2.jpg"));
			JLabel medjed2Label = new JLabel(medjed2);
			return medjed2Label;
		}
		else if(ch.equals("C"))
		{

			ImageIcon medjed3 = new ImageIcon(getClass().getResource("medjed3.jpg"));
			JLabel medjed3Label = new JLabel(medjed3);
			return medjed3Label;
		}
		else
		{
			ImageIcon medjed4 = new ImageIcon(getClass().getResource("medjed4.jpg"));
			JLabel medjed4Label = new JLabel(medjed4);
			return medjed4Label;
		}
	}

	private void goToMainPageFrame() {
		returnBtn = new JButton("Return");
		class ClickListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				JFrame mainPageFrame = null;
				try {
					dispose();
					mainPageFrame = new MainPageFrame();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mainPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainPageFrame.setTitle("Main Page");
				mainPageFrame.setVisible(true);
			}
		}      
		ActionListener listener = new ClickListener();
		returnBtn.addActionListener(listener);
	}
	public JPanel createContentPanel() throws SQLException {

		contentPanel = new JPanel();
		contentPanel.add(No1IDLabel);
		contentPanel.add(No2IDLabel);
		contentPanel.add(No3IDLabel);
		contentPanel.add(No1ScoreLabel);
		contentPanel.add(No2ScoreLabel);
		contentPanel.add(No3ScoreLabel);
		contentPanel.add(No1CharacterLabel);
		contentPanel.add(No2CharacterLabel);
		contentPanel.add(No3CharacterLabel);
		contentPanel.add(returnBtn);
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);

		return contentPanel;
	}

}