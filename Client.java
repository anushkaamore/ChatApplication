package chattingapplication;

import java.awt.*;


import javax.swing.*;//to design a frame

import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener {  //JFrame is present in Swing class

	JTextField text;
	static JPanel a1;
	static Box vertical=Box.createVerticalBox();
	static DataOutputStream dout;
	
	static JFrame f=new JFrame();
	
	 Client(){
		f.setLayout(null);
		
		//panel is used to divide/to add things on frame(ex.header)
		//pannel for header
		JPanel pl=new JPanel();
		pl.setBackground(Color.red);
		pl.setBounds(0,0,450,70);
		pl.setLayout(null);
		f.add(pl);          //whom to set
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/3.png"));
		Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel back=new JLabel(i3);
		back.setBounds(5,20,25,25);
		pl.add(back);
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icon/2.png"));
		Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon i6=new ImageIcon(i5);
		JLabel profile=new JLabel(i6);
		profile.setBounds(40,10,50,50);
		pl.add(profile);
		
		ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/video.png"));
		Image i8=i7.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon i9=new ImageIcon(i8);
		JLabel video=new JLabel(i9);
		video.setBounds(300,20,30,30);
		pl.add(video);
		
		ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icon/phone.png"));
		Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		ImageIcon i12=new ImageIcon(i11);
		JLabel phone=new JLabel(i12);
		phone.setBounds(360,20,35,30);
		pl.add(phone);
		
		ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icon/3icon.png"));
		Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
		ImageIcon i15=new ImageIcon(i14);
		JLabel morevert=new JLabel(i15);
		morevert.setBounds(420,20,10,25);
		pl.add(morevert);
		
		//to write something on frame use : JLabel
		JLabel name=new JLabel("Sakshi");
		name.setBounds(110,15,100,18);
		name.setForeground(Color.black);
		name.setFont(new Font("SAN SERIF",Font.BOLD,18));
		pl.add(name);
		
		JLabel status=new JLabel("online");
		status.setBounds(110,40,100,18);
		status.setForeground(Color.green);
		status.setFont(new Font("SAN SERIF",Font.BOLD,15));
		pl.add(status);
		
		//pannel for textarea
		a1=new JPanel();
		a1.setBounds(5,75,440,570);
		a1.setBackground(Color.yellow);
		f.add(a1);
		
	    text=new JTextField();
		text.setBounds(5,655,310,40);
		text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f.add(text);
		
		JButton send=new JButton("send");
		send.setBounds(320,655,123,40);
		send.setBackground(new Color(7,94,84));
		send.setForeground(Color.white);
		send.addActionListener(this);
		send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f.add(send);
		
		f.setSize(450,700); 
		f.setLocation(800,50);
		f.getContentPane().setBackground(Color.yellow);//Color class is present awt pacakage
		f.setVisible(true);
	 }
	 
	 public void actionPerformed(ActionEvent ae) {
		 
		 try {
		String out=text.getText();
		//System.out.println();
		
		//JLabel output=new /*JLabel(out);*/formatLabel(out);
		
		JPanel p2=formatLabel(out);
		//p2.add(output);
		
		a1.setLayout(new BorderLayout());
		
		JPanel right=new JPanel(new BorderLayout());
		right.add(p2,BorderLayout.LINE_END);
		vertical.add(right); //to align the messages
		vertical.add(Box.createVerticalStrut(15));
		
		a1.add(vertical,BorderLayout.PAGE_START);
		
		dout.writeUTF(out);
		
		text.setText("");
		
		f.repaint();
		f.invalidate();
		f.validate();
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 }
	 public static JPanel formatLabel(String out) {
		 JPanel panel = new JPanel();
		 panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		 
		 JLabel output = new JLabel(out);
		 output.setFont(new Font("Tahoma",Font.PLAIN,16));
		 
		 
		 panel.add(output);
		 
		 Calendar cal=Calendar.getInstance();
		 SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		 
		 JLabel time=new JLabel();
		 time.setText(sdf.format(cal.getTime()));
		 
		 panel.add(time);
		 
		 return panel;
	 }
	 
	public static void main(String[] args) {
		new Client();
		
		try {
			
			Socket s=new Socket("127.0.0.1",6001);
			 DataInputStream din = new DataInputStream(s.getInputStream());
			  dout = new DataOutputStream(s.getOutputStream());
			  
			  while(true) {
				  a1.setLayout(new BorderLayout() );
			        String msg = din.readUTF();//protocol of socket programming
			        JPanel panel=formatLabel(msg);
			        
			        JPanel left=new JPanel(new BorderLayout());
			        left.add(panel,BorderLayout.LINE_START);
			        
			        vertical.add(left);
			        vertical.add(Box.createVerticalStrut(15));
			        a1.add(vertical,BorderLayout.PAGE_START);
			        
			        f.validate();
			                
			 }
			  
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
