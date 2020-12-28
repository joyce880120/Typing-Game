import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayFrame extends JFrame {
	
	private static final int FRAME_WIDTH = 1025;
	private static final int FRAME_HEIGHT = 620;
	private static final int FIELD_WIDTH = 10;
	
	private ImageIcon background;
	private JLabel bgLabel;
	private JPanel timer;
	private JLabel score;
	private JLabel topic;
	private JTextField input;
	private JLabel lab;
	
	GuessTimer timer1 = new GuessTimer();
	private JPanel centerPanel;
	private int points = 0;
	private int i = 0;

	public PlayFrame() 
	{
		background = new ImageIcon(getClass().getResource("PlayPage2.jpg")); // 背景圖片
		bgLabel = new JLabel(background); // 把背景圖顯示在Label中
		this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
		score = new JLabel("0");  
		topic = new JLabel("Try");
		topic.setFont(new Font("Monospaced", Font.BOLD, 45));
		topic.repaint();
		input = new JTextField(FIELD_WIDTH);
		showKeyAdapterDemo();	
		time();
		topic.setBounds(350, 120, 600, 50);
		score.setBounds(700, 33, 100, 40);
		score.setFont(new java.awt.Font("Dialog", 1, 30));
		input.setBounds(350, 350, 345, 30);
		cp.add(createPanel(), BorderLayout.CENTER);
		
		((JPanel)cp).setOpaque(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Play Frame");
		setVisible(true);
	
 }

	public void time() 
	{
		lab = new JLabel();
		JLabel lab1 = new JLabel("0:");
		timer = new JPanel();

		timer.add(lab1);
		timer.add(lab);
		timer.setBounds(890, 40, 42, 20);
		timer1.setJLabel(lab);
		timer1.addListener(new GuessTimer.Listener() 
		{
			@Override
			public void timeOut() throws SQLException 
			{
				EndingFrame frame1 = new EndingFrame();
			    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame1.setTitle("Game End");
			    frame1.setVisible(true);
			    dispose();
			}

		   @Override
		   public void onChange(long sec) 
		   {
			   //System.out.println("jjj");
		   }

		});
		  timer1.startTimer(60);
	}
	
	private void showKeyAdapterDemo() 
	{
		input.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					System.out.println("return or enter");
					try 
					{
						sendString();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
	}

 	public void sendString() throws SQLException 
 	{
 		String entry = input.getText();
 		input.setText("");
 		if (entry.contentEquals(topic.getText())) 
 		{
 			points = getPoints() + entry.length() * 5;
 			score.setText("" + getPoints());
 			changeQuestion();
 			i++;
 		}
 		else 
 		{
 			topic.setForeground(Color.RED);
 		}
 	}

 	public void changeQuestion() throws SQLException 
 	{
		String methodChosen = getComboBoxFromMainPageFrame();
	 
 		Connection conn = SimpleDataSource.getConnection();
 		try 
 		{
 			Statement stat = conn.createStatement();
			String query = "SELECT `" + methodChosen + "` FROM Method_Table LIMIT " + i + ", 1";
			ResultSet result = stat.executeQuery(query); 
			System.out.println(methodChosen);
 			if(result.next())
 			{
 				topic.setText(result.getString(1));
 			}
 		}
 		finally
 		{
 			conn.close();
 		}
	 
		topic.setForeground(Color.BLACK);
 	}

 class Try extends KeyAdapter {
  int points = 0;

  public void keyPressed(KeyEvent e) {
   int key1 = e.getKeyCode();

   if (key1 == KeyEvent.VK_ENTER) {
    
   }
  }

 }

 	public JPanel createPanel() 
 	{
 		centerPanel = new JPanel();
 		centerPanel.add(topic);
 		centerPanel.add(score);
 		centerPanel.add(timer);
 		centerPanel.add(input);
 		centerPanel.setOpaque(false);
 		centerPanel.setLayout(null);
 		return centerPanel;
 	}
 
 	private String getComboBoxFromMainPageFrame() 
 	{
 		for(Frame frame: JFrame.getFrames()) 
 		{
 			if(frame.getTitle().equals("Main Page")) 
 			{
 				MainPageFrame MainPageFrame = (MainPageFrame) frame;
 				String str = MainPageFrame.getMethodCombo().getSelectedItem().toString();
 				return str;
 			}
 		}
 		return null;
	}

	public int getPoints() 
	{
		return points;
	}

}