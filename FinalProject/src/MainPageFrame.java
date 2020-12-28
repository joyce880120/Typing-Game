import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class MainPageFrame extends JFrame {

	private static final int FRAME_WIDTH = 830;
	private static final int FRAME_HEIGHT = 500;
	private static final int FIELD_WIDTH = 10;

	private JTextField userNameTextField;
	private JComboBox<String> methodCombo;
	private JLabel bgLabel;
	private ImageIcon background;
	private JButton ch1;
	private JButton ch2;
	private JButton ch3;
	private JButton ch4;

	public MainPageFrame() throws SQLException, MalformedURLException {
	
		background = new ImageIcon(getClass().getResource("MainPage3.png")); // 背景圖片
		bgLabel = new JLabel(background); // 把背景圖顯示在Label中
		this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面

		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		createName();
		createClass();
		cp.add(createPanel(), BorderLayout.CENTER);
		cp.add(createButton(), BorderLayout.SOUTH);
		((JPanel)cp).setOpaque(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Main Page");
		setVisible(true);

	}

	/*
	 * 我把userID和character放在一起，可以再調，character就是我們那事後說要放照片的，先不做他
	 */
	public void createName() throws MalformedURLException, SQLException {

		userNameTextField = new JTextField(FIELD_WIDTH);
		userNameTextField.setEditable(true);
		userNameTextField.setBounds(270, 120, 100, 20);
		Connection conn = SimpleDataSource.getConnection();
		ImageIcon medjed1 = new ImageIcon(getClass().getResource("medjed1.jpg"));
		ch1 = new JButton(medjed1);
		ch1.setBounds(300, 160, 50, 50);
		class Ch1Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{

				try
				{
					Statement stat = conn.createStatement();
					String query1 = "INSERT INTO Name (`Name`) VALUES ('" + getUserNameTextField().getText() + "')";
					stat.executeUpdate(query1);
					String query2 = "UPDATE Name SET `Character_Photo` = 'A' WHERE `Name` = '" 
					+ getUserNameTextField().getText() + "'";
					stat.executeUpdate(query2);
				} 
				catch (SQLException e) 
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		}
		ActionListener listener1 = new Ch1Listener();
		ch1.addActionListener(listener1);
		
		
		ImageIcon medjed2 = new ImageIcon(getClass().getResource("medjed2.jpg"));
		ch2 = new JButton(medjed2);
		ch2.setBounds(360, 160, 50, 50);
		class Ch2Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Statement stat = conn.createStatement();
					String query1 = "INSERT INTO Name (`Name`) VALUES ('" + getUserNameTextField().getText() + "')";
					stat.executeUpdate(query1);
					String query2 = "UPDATE Name SET `Character_Photo` = 'B' WHERE `Name` = '" 
					+ getUserNameTextField().getText() + "'";
					stat.executeUpdate(query2);
				} 
				catch (SQLException e) 
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		}
		ActionListener listener2 = new Ch2Listener();
		ch2.addActionListener(listener2);
		
		
		ImageIcon medjed3 = new ImageIcon(getClass().getResource("medjed3.jpg"));
		ch3 = new JButton(medjed3);
		ch3.setBounds(420, 160, 50, 50);
		class Ch3Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Statement stat = conn.createStatement();
					String query1 = "INSERT INTO Name (`Name`) VALUES ('" + getUserNameTextField().getText() + "')";
					stat.executeUpdate(query1);
					String query2 = "UPDATE Name SET `Character_Photo` = 'C' WHERE `Name` = '" 
					+ getUserNameTextField().getText() + "'";
					stat.executeUpdate(query2);
				} 
				catch (SQLException e) 
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		}
		ActionListener listener3 = new Ch3Listener();
		ch3.addActionListener(listener3);
		
		ImageIcon medjed4 = new ImageIcon(getClass().getResource("medjed4.jpg"));
		ch4 = new JButton(medjed4);
		ch4.setBounds(480, 160, 50, 50);
		class Ch4Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Statement stat = conn.createStatement();
					String query1 = "INSERT INTO Name (`Name`) VALUES ('" + getUserNameTextField().getText() + "')";
					stat.executeUpdate(query1);
					String query2 = "UPDATE Name SET `Character_Photo` = 'D' WHERE `Name` = '" 
					+ getUserNameTextField().getText() + "'";
					stat.executeUpdate(query2);
				} 
				catch (SQLException e) 
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		}
		ActionListener listener4 = new Ch4Listener();
		ch4.addActionListener(listener4);

	}

	/*
	 * 要使用者輸入想打的class那邊我單獨做，想說會比較難，用combobox，還沒加入任何東西
	 */
	public void createClass() {
		methodCombo = new JComboBox<String>();
		methodCombo.addItem("Math");
		methodCombo.addItem("Integer");
		methodCombo.addItem("Double");
		methodCombo.addItem("ArrayList");
		methodCombo.addItem("Array");
		methodCombo.addItem("String");
		methodCombo.addItem("JFrame");
		methodCombo.addItem("Scanner");
		methodCombo.addItem("System");
		methodCombo.addItem("JLabel");
		methodCombo.setBounds(230, 245, 80, 20);
	}
	
	public JPanel createButton() throws SQLException {
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(goToPlayFrame());
//		buttonPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.setOpaque(false);
		buttonPanel.repaint();
		buttonPanel.add(goToRankFrame());
		return buttonPanel;
	}
	
	private JButton goToPlayFrame(){
		JButton playBtn = new JButton("Play");
		
		class ClickListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				JFrame playFrame = new PlayFrame();
				playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				playFrame.setTitle("Play Frame");
				playFrame.setVisible(true);
				String str = getMethodCombo().getSelectedItem().toString();
				System.out.println(str);
			}
		}      
		ActionListener listener = new ClickListener();
		playBtn.addActionListener(listener);
		return playBtn;
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

	/*
	 * 這個是把所有的東西合在一起，還有點醜
	 */
	public JPanel createPanel() throws SQLException, MalformedURLException {
		//setSize(FRAME_WIDTH, FRAME_HEIGHT);
		JPanel mainPanel = new JPanel();
		mainPanel.add(userNameTextField);
		mainPanel.add(ch1);
		mainPanel.add(ch2);
		mainPanel.add(ch3);
		mainPanel.add(ch4);
		mainPanel.add(methodCombo);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(null);
		return mainPanel;
	}

	public JComboBox<String> getMethodCombo() {
		return methodCombo;
	}

	public JTextField getUserNameTextField() {
		return userNameTextField;
	}


}
