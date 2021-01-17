package WinBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RemindME extends JFrame {

	private JFrame frame;
	private JLabel timeTextLabel;
	private JTextField hourTextField;
	private JLabel timeseparatorLabel1;
	private JTextField minuteTextField;
	private JLabel timeseparatorLabel2;
	private JTextField secondTextField;
	private JTextField messageTextField;
	private JLabel mymarkLabel;
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemindME window = new RemindME();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RemindME() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("RemindME v0.2.0");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		timeTextLabel = new JLabel("TIME : ");
		timeTextLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		timeTextLabel.setForeground(Color.WHITE);
		timeTextLabel.setBounds(63, 235, 60, 20);
		frame.getContentPane().add(timeTextLabel);
		
		hourTextField = new JTextField();
		hourTextField.setBounds(134, 225, 35, 25);
		frame.getContentPane().add(hourTextField);
		hourTextField.setColumns(10);
		
		timeseparatorLabel1 = new JLabel(":");
		timeseparatorLabel1.setFont(new Font("Calibri", Font.BOLD, 20));
		timeseparatorLabel1.setForeground(Color.WHITE);
		timeseparatorLabel1.setBounds(174, 225, 6, 20);
		frame.getContentPane().add(timeseparatorLabel1);
		
		minuteTextField = new JTextField();
		minuteTextField.setBounds(185, 225, 35, 25);
		frame.getContentPane().add(minuteTextField);
		minuteTextField.setColumns(10);
		
		timeseparatorLabel2 = new JLabel(":");
		timeseparatorLabel2.setForeground(Color.WHITE);
		timeseparatorLabel2.setFont(new Font("Calibri", Font.BOLD, 20));
		timeseparatorLabel2.setBounds(225, 225, 6, 20);
		frame.getContentPane().add(timeseparatorLabel2);
		
		secondTextField = new JTextField();
		secondTextField.setBounds(236, 225, 35, 25);
		frame.getContentPane().add(secondTextField);
		secondTextField.setColumns(10);
		
		JLabel hourTextLabel = new JLabel("Hour");
		hourTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		hourTextLabel.setForeground(Color.WHITE);
		hourTextLabel.setBounds(134, 250, 30, 20);
		frame.getContentPane().add(hourTextLabel);
		
		JLabel minuteTextLabel = new JLabel("Minute");
		minuteTextLabel.setForeground(Color.WHITE);
		minuteTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		minuteTextLabel.setBounds(185, 250, 30, 20);
		frame.getContentPane().add(minuteTextLabel);
		
		JLabel secondTextLabel = new JLabel("Second");
		secondTextLabel.setForeground(Color.WHITE);
		secondTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		secondTextLabel.setBounds(236, 250, 30, 20);
		frame.getContentPane().add(secondTextLabel);
		
		messageTextField = new JTextField();
		messageTextField.setBounds(63, 275, 265, 25);
		frame.getContentPane().add(messageTextField);
		messageTextField.setColumns(10);
		
		JLabel messageTextLabel = new JLabel("Enter Message");
		messageTextLabel.setForeground(Color.WHITE);
		messageTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		messageTextLabel.setBounds(169, 300, 60, 20);
		frame.getContentPane().add(messageTextLabel);
		
		JButton setreminderButton = new JButton("Set Reminder");
		setreminderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AudioInputStream clickinput = AudioSystem.getAudioInputStream(getClass().getResource("/clickRemindME.wav"));
			        Clip clickclip = AudioSystem.getClip();
			        clickclip.open(clickinput);
			        clickclip.start();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
				String yearInput = yearTextField.getText();
				String monthInput = monthTextField.getText();
				String dayInput = dayTextField.getText();
				String hourInput = hourTextField.getText();
				String minuteInput = minuteTextField.getText();
				String secondInput = secondTextField.getText();
				String message = messageTextField.getText();
				String timeMarker = (String) comboBox.getSelectedItem();
				String dateAndTimeInput = dayInput + "-" + monthInput + "-" + yearInput + " " + hourInput + ":" + minuteInput + ":" + secondInput + " " + timeMarker;
				int oneDayMS = 86400000;
				int oneHourMS = 3600000;
				int oneMinuteMS = 60000;
				Thread thread = new Thread(() -> {
					try {
				        Date date = sdf.parse(dateAndTimeInput);
				        int currentMSplusdateAndTimeMS = (int) (date.getTime() - System.currentTimeMillis());
				        do {
					        String myTime = String.format("%02d hours, %02d minutes, and %02d seconds.",
					        //Hours
					        TimeUnit.MILLISECONDS.toHours(currentMSplusdateAndTimeMS) -
					                TimeUnit.HOURS.toHours(TimeUnit.MILLISECONDS.toDays(currentMSplusdateAndTimeMS)),
					        //Minutes
					        TimeUnit.MILLISECONDS.toMinutes(currentMSplusdateAndTimeMS) -
					                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(currentMSplusdateAndTimeMS)),
					        //Seconds
					        TimeUnit.MILLISECONDS.toSeconds(currentMSplusdateAndTimeMS) -
					                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentMSplusdateAndTimeMS)));
				        	Component Jframe = null;
							JOptionPane.showMessageDialog(Jframe, "Reminder Set! Next alarm in: " + myTime, "RemindME", JOptionPane.PLAIN_MESSAGE);
							Thread.sleep(currentMSplusdateAndTimeMS);
							AudioInputStream soundinput = AudioSystem.getAudioInputStream(getClass().getResource("/soundRemindME.wav"));
					        Clip soundclip = AudioSystem.getClip();
					        soundclip.open(soundinput);
					        soundclip.start();  
					        JOptionPane.showMessageDialog(Jframe, message, "RemindME", JOptionPane.PLAIN_MESSAGE);
					        currentMSplusdateAndTimeMS += oneDayMS+oneHourMS-oneMinuteMS-(currentMSplusdateAndTimeMS-oneMinuteMS);
					        soundclip.close();
				        }while(true);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				thread.start();
			}
		});
		setreminderButton.setForeground(new Color(0, 0, 0));
		setreminderButton.setBackground(new Color(47, 79, 79));
        setreminderButton.setBounds(140, 325, 115, 25);
		frame.getContentPane().add(setreminderButton);
		
		mymarkLabel = new JLabel("-dotSIS");
		mymarkLabel.setForeground(Color.WHITE);
		mymarkLabel.setBounds(330, 335, 50, 15);
		frame.getContentPane().add(mymarkLabel);
		
		JLabel imageLogoLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/imageRemindME.png")).getImage();
		imageLogoLabel.setIcon(new ImageIcon(img));
		imageLogoLabel.setBounds(86, 0, 200, 150);
		frame.getContentPane().add(imageLogoLabel);

		JLabel backgroundLabel = new JLabel("");
		Image bg = new ImageIcon(this.getClass().getResource("/backgroundRemindME.png")).getImage();
		
		JLabel dateTextLabel = new JLabel("DATE : ");
		dateTextLabel.setForeground(Color.WHITE);
		dateTextLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		dateTextLabel.setBounds(63, 185, 61, 20);
		frame.getContentPane().add(dateTextLabel);
		
		JLabel yearTextLabel = new JLabel("Year");
		yearTextLabel.setForeground(Color.WHITE);
		yearTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		yearTextLabel.setBounds(134, 200, 30, 20);
		frame.getContentPane().add(yearTextLabel);
		
		JLabel monthTextLabel = new JLabel("Month");
		monthTextLabel.setForeground(Color.WHITE);
		monthTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		monthTextLabel.setBounds(185, 200, 30, 20);
		frame.getContentPane().add(monthTextLabel);
		
		JLabel dayTextLabel = new JLabel("Day");
		dayTextLabel.setForeground(Color.WHITE);
		dayTextLabel.setFont(new Font("Calibri", Font.PLAIN, 10));
		dayTextLabel.setBounds(236, 200, 30, 20);
		frame.getContentPane().add(dayTextLabel);
		
		yearTextField = new JTextField();
		yearTextField.setColumns(10);
		yearTextField.setBounds(134, 175, 35, 25);
		frame.getContentPane().add(yearTextField);
		
		monthTextField = new JTextField();
		monthTextField.setColumns(10);
		monthTextField.setBounds(185, 175, 35, 25);
		frame.getContentPane().add(monthTextField);
		
		dayTextField = new JTextField();
		dayTextField.setColumns(10);
		dayTextField.setBounds(236, 175, 35, 25);
		frame.getContentPane().add(dayTextField);
		
		JLabel dateseparatorLabel1 = new JLabel("-");
		dateseparatorLabel1.setForeground(Color.WHITE);
		dateseparatorLabel1.setFont(new Font("Calibri", Font.BOLD, 20));
		dateseparatorLabel1.setBounds(174, 175, 6, 20);
		frame.getContentPane().add(dateseparatorLabel1);
		
		JLabel dateseparatorLabel2 = new JLabel("-");
		dateseparatorLabel2.setForeground(Color.WHITE);
		dateseparatorLabel2.setFont(new Font("Calibri", Font.BOLD, 20));
		dateseparatorLabel2.setBounds(225, 175, 6, 20);
		frame.getContentPane().add(dateseparatorLabel2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(283, 225, 45, 25);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("AM");
		comboBox.addItem("PM");
		backgroundLabel.setIcon(new ImageIcon(bg));
		backgroundLabel.setBounds(0, 0, 385, 360);
		frame.getContentPane().add(backgroundLabel);
	}
}
