import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EndingFrame extends JFrame{
	
	private static final int FRAME_WIDTH = 1025;
	private static final int FRAME_HEIGHT = 620;
	private ImageIcon background;
	private JLabel bgLabel;
	private JButton returnBtn;
	private JPanel endingPanel;
	private JLabel timeup;
	private JLabel endScore;
	private JPanel buttonPanel;
	
	public EndingFrame() throws SQLException
	{
		background = new ImageIcon(getClass().getResource("EndPage2.jpg")); // 背景圖片
		bgLabel = new JLabel(background); // 把背景圖顯示在Label中
		this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
		createTimeScore();
		timeup.setBounds(290, 410, 400, 50);
		endScore.setBounds(580, 340, 400, 50);
		endScore.setFont(new java.awt.Font("Dialog", 1, 40));
		cp.add(createEnding(), BorderLayout.CENTER);
		cp.add(buttons(), BorderLayout.SOUTH);
		((JPanel)cp).setOpaque(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Ending Frame");
		setVisible(true);
		
	}
	public JPanel buttons()
	{
		buttonPanel = new JPanel();
		buttonPanel.add(goToMainPageFrame());
		buttonPanel.add(goToRankFrame());
		buttonPanel.setOpaque(false);
		buttonPanel.repaint();
		return buttonPanel;
	}
	//失敗
	private JButton goToMainPageFrame() {
		returnBtn = new JButton("Return");
		class ClickListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				dispose();
			}
		}      
		ActionListener listener = new ClickListener();
		returnBtn.addActionListener(listener);
		return returnBtn;
	}
	//失敗
	private JButton goToRankFrame() {
		JButton rankBtn = new JButton("Rank");
		class ClickListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				JFrame rankFrame;
				try {
					rankFrame = new RankFrame();
					rankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					rankFrame.setTitle("Rank Frame");
					rankFrame.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}      
		ActionListener listener = new ClickListener();
		rankBtn.addActionListener(listener);
		return rankBtn;
	}
	
	public void createTimeScore() throws SQLException
	{
		timeup = new JLabel("TIME'S UP :( ");
		timeup.setFont(new Font("Monospaced", Font.BOLD, 45));
		endScore = new JLabel(getPointsFromPlayFrame());
		Connection conn = SimpleDataSource.getConnection();
		try
		{
			Statement stat = conn.createStatement();
			String query = "UPDATE Name SET `Score` = " + getPointsFromPlayFrame() + 
					" WHERE `Name` = '" + getUserNameFromMainPage() + "'";
			stat.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
	
	public JPanel createEnding()
	{
		endingPanel = new JPanel();
		endingPanel.add(timeup);
		endingPanel.add(endScore);
		endingPanel.setOpaque(false);
		endingPanel.setLayout(null);
		endingPanel.repaint();
		return endingPanel;
	}
	private String getPointsFromPlayFrame() 
 	{
 		for(Frame frame: JFrame.getFrames()) 
 		{
 			if(frame.getTitle().equals("Play Frame")) 
 			{
 				PlayFrame PlayFrame = (PlayFrame) frame;
 				String str = Integer.toString(PlayFrame.getPoints());
 				return str;
 			}
 		}
 		return null;
	}
	
	private String getUserNameFromMainPage() 
 	{
 		for(Frame frame: JFrame.getFrames()) 
 		{
 			if(frame.getTitle().equals("Main Page")) 
 			{
 				MainPageFrame MainPageFrame = (MainPageFrame) frame;
 				String str = MainPageFrame.getUserNameTextField().getText();
 				return str;
 			}
 		}
 		return null;
	}
}
