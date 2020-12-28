import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class GuessTimer{

	 public interface Listener
	 {
	 //通知時間到
	 public void timeOut() throws SQLException;
	 //秒數變動秒數
	 public void onChange(long sec);
	 }
	 
	 private Listener lis;
	 private Timer timer;
	 private JLabel timeLab;
	 private long delay;
	 private long sec;
	 
	 public GuessTimer()
	 {
		 delay = 1;
	 }
	 
	 public void setJLabel(JLabel lab)
	 {
		 timeLab = lab;
	 }
	 
	 public void addListener(Listener li)
	 {
		 lis = li;
	 }
	 
	 public void setJComponent(long d)
	 {
		 delay = d;
	 }
	 
	 public void startTimer(int s)
	 {
		 if(timer == null)
		 {
			 timer = new Timer();
			 sec = s;
			 
			 TimerTask task = new TimerTask()
			 {
				 public void run()
				 {
					 sec -= delay;
					 timeLab.setText(String.valueOf(sec));
					 if(lis != null)
					 {
						 lis.onChange(sec);
					 }
					 if(sec <= 0)
					 {
						 stopoTimer();
						 if(lis != null)
						 {
							 try {
								lis.timeOut();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
					 }

				 }
			 };
		long delaySec = delay * 1000;
		timer.schedule(task, delaySec, delaySec);
		}
	 }
	 public void stopoTimer()
	 {
		 if(timer != null)
		 {
			 timer.cancel();
			 timer = null;
		 }
	 }
	 
public static void main(String [] args){
	JFrame frame = new JFrame();
	JLabel lab = new JLabel();
	JLabel lab1 = new JLabel("0 : ");
	JPanel panel = new JPanel();
	
	panel.add(lab1);
	panel.add(lab);
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setSize(100, 100);
	GuessTimer timer = new GuessTimer();

	timer.setJLabel(lab);

	//傾聽計時器timeout事件(可選的事件，不實作也可以使用timer

	 timer.addListener(new GuessTimer.Listener() 
	 {
	 @Override
	 	public void timeOut() 
	 	{
		 	
	 	}
	 
	 	@Override
	 	public void onChange(long sec) 
	 	{
	 	}

	 });

	timer.startTimer(10);

	}
}

