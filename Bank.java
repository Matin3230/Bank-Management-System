import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
import java.sql.*;  
import java.util.Date; 
import java.util.*;
import java.text.*;
import java.util.regex.*;


class Bank  
{
	//Initialization of variables
	
	static JLabel l1,l2,l4,ltitle,lname,choose,reg,rtitle,rl,e1,e2,re1,re2,re3,re4,re5,red6,rem6,rey6,re7,re8,re9,re10,re11;
	JTextField t1,rt1,rt2,rt3,rt4,rt5,rtd6,rtm6,rty6,rt8,rt9,rt10,rt11;
	JTextArea rt7;
	JPasswordField t2;          
	JButton login,show,create,back,trans,depo,with,check,lback;
	JRadioButton rd1,rd2,rd3;
	ButtonGroup bg=new ButtonGroup();
	static int a,b,c,db,mb,yb,age,bal,bal2,traa,trab; 
	Connection cn;
	Statement st;
	ResultSet rs;
	Icon bug1,bug2,bug3;
	static JFrame j3;
	Date dNow ;
	String Month;
    SimpleDateFormat dd,mm,yy;
	String bdate,gen,name,nm,with1,depo1,traa1,trab1;
	final Color lightblue=new Color(0, 0, 153); 
	JFrame hp,regf,logf;
	
	 
	 
	Bank()
	{
		// Declaration of variables
		
		 
		l1=new JLabel("Account No :");
		l2=new JLabel("Password :");  
		l4=new JLabel("- By Fantastic 3"); 
		t1=new JTextField(10); 
		t2=new JPasswordField(10);
		t2.setEchoChar('*');
		login=new JButton("Login");
		reg=new JLabel("<HTML><U>Create New Account</U></HTML>");
		
		show=new JButton("Fancy Button",bug1);
		bug3=new ImageIcon("back.JPG");
		JLabel background=new JLabel(bug3); 
		e1=new JLabel(" ");
		e2=new JLabel(" ");
		rd1= new JRadioButton("Male",true);
		rd2= new JRadioButton("Female");
		rd3= new JRadioButton("Other");
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Home page Frame
		hp=new JFrame("My Bank - Home Page");
		hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hp.setExtendedState(JFrame.MAXIMIZED_BOTH);				//1920,1080
		hp.setLayout(null);
		hp.getContentPane().setBackground(Color.gray);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Main Heading
		JLabel mh=new JLabel("---  MY BANK  ---",JLabel.CENTER);
		mh.setBounds(350,90,720,110);		 
		mh.setFont(new Font("Times new roman",Font.BOLD,66));
		mh.setBorder(BorderFactory.createMatteBorder(2, 4, 0, 4, Color.black));
		mh.setOpaque(true);
		mh.setBackground(Color.darkGray);
		mh.setForeground(Color.white);
		hp.add(mh);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Slogan
		JLabel sg=new JLabel("Put Money and Don't Worry",JLabel.CENTER);
		sg.setBounds(350,200,720,50);
		sg.setFont(new Font("Times new roman",Font.PLAIN,20));
		sg.setBorder(BorderFactory.createMatteBorder(0, 4, 2, 4, Color.black));
		sg.setOpaque(true);
		sg.setBackground(Color.darkGray);
		sg.setForeground(Color.white);
		hp.add(sg);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Developed By... - LOGO
		JLabel lg=new JLabel("~ By Group G2",JLabel.RIGHT);
		lg.setBounds(350,245,720,50);
		lg.setFont(new Font("Comic Sans MS",Font.ITALIC+Font.BOLD,14));
		lg.setToolTipText("Prathamesh , Prajval , Matin , Gaurav , Chaitannya");
		lg.setForeground(Color.black);
		hp.add(lg);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Panel Heading - SIGN IN
		JLabel phead=new JLabel("Sign In",JLabel.CENTER);
		phead.setBounds(360,300,700,50);
		phead.setFont(new Font("Times new roman",Font.BOLD,40));
		phead.setForeground(Color.white);
		phead.setOpaque(true);
		phead.setBackground(Color.darkGray);
		hp.add(phead);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////New User Label
		JLabel nuser=new JLabel("<html><u><b>  Create New Account  </b></u></html>");
		nuser.setFont(new Font("Times new roman",Font.PLAIN,18));
		nuser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuser.setBounds(510,560,250,40);
		nuser.setForeground(Color.cyan);
		hp.add(nuser);
		nuser.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(me.getButton()==MouseEvent.BUTTON1)
				{
					registrationform();
				}
			}
		});
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Login Button
		JButton Login=new JButton("<html><b>Login</b></html>");
		Login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Login.setFont(new Font("Times new roman",Font.PLAIN,20));
		Login.setBounds(795,560,150,40);
		Login.setBackground(Color.blue);
		Login.setForeground(Color.white);
		hp.add(Login);
		
		Login.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					validataccount();
					validatepass();
					if(e1.getText()==""&&e2.getText()=="")
					{
						try
						{
							if(!((t1.getText().equals(" *Account No")||(!(Pattern.matches("\\d{8}",t1.getText()))))))
							{
								if(!(t2.getText().equals(" *Password")))
			
								{ 
							
									rs=st.executeQuery("select Pass from Accounts where AcNo="+Integer.parseInt(t1.getText()));
									if(rs.next())
									{
										if(rs.getString(1).equals(t2.getText()))
										{ 
											Login();
											e1.setText("");
											e2.setText("");
										}
										else
										{	
											e1.setText("");
											e2.setText("* Invalid Password");
										}
									}
									else
									{
										e2.setText("");
										e1.setText("* Invalid Account No");
									}
								}
								else
								{	
									e1.setText("");
									e2.setText("* Required");
								}
							}
							else
							{
								e2.setText("");
								e1.setText("* Required");
							}
						}	
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(hp,"There is Exception "+e,"Warning",JOptionPane. WARNING_MESSAGE);
						}
					}
				}
			}
			);
		

		///////////////////////////////////////////////////////////////////////////////////////////////
		////Show Button
		bug1=new ImageIcon("hide.gif");
		bug2=new ImageIcon("sh.gif");
		final JButton show=new JButton(bug1);
		
		show.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		show.setBounds(920,455,32,27);
		hp.add(show);
		
		show.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(t2.getEchoChar()=='*')
				{
					t2.setEchoChar((char)0); 
					show.setRolloverIcon(bug2); 
				}
				
				else if(t2.getEchoChar()==(char)0)
				{
					t2.setEchoChar('*'); 
					show.setRolloverIcon(bug2);
				}
				
				if(t2.getText().equals(" *Password"))
				{
					t2.setEchoChar((char)0); 
				}
			}
			
		});
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Login Content
		JLabel la=new JLabel("Account No :  ");
		la.setFont(new Font("Times new roman",Font.PLAIN,20));
		la.setForeground(Color.white);
		
		JLabel lp=new JLabel("Password    :  ");
		lp.setFont(new Font("Times new roman",Font.PLAIN,20));
		lp.setForeground(Color.white);
		
		e1.setBounds(640,430,300,27);
		e1.setFont(new Font("Times New Roman",Font.BOLD,12));
		e1.setForeground(Color.white);
		hp.add(e1);
		
		e2.setBounds(640,480,300,27);
		e2.setFont(new Font("Times New Roman",Font.BOLD,12));
		e2.setForeground(Color.white);
		hp.add(e2);
		
		t1=new JTextField(20);
		t1.setFont(new Font("Times new roman",Font.PLAIN,20));
		t1.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
		t1.setBackground(Color.black);
		t1.setForeground(Color.white);
		
		t2=new JPasswordField(20);
		t2.setEchoChar((char)0); 
		t2.setText(" *Password");
		t2.setFont(new Font("Times new roman",Font.PLAIN,20));
		t2.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
		t2.setBackground(Color.black);
		t2.setForeground(Color.darkGray);
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(la);	p1.add(t1);
		p1.setBounds(370,400,700,40);
		hp.add(p1);
		p1.setBackground(Color.darkGray);
		
		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(lp);	p2.add(t2);
		p2.setBounds(370,450,700,40);
		hp.add(p2);
		p2.setBackground(Color.darkGray);

		JPanel lpan=new JPanel();
		lpan.setBounds(350,290,720,380);
		lpan.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.black));
		lpan.setBackground(Color.darkGray);
		hp.add(lpan);
		
		hp.setVisible(true);
		
		
		t1.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(t1.getText().equals(" *Account No"))
				{
					t1.setText("");  
					t1.setForeground(new Color(50, 50, 50));  
				}
				t1.setForeground(Color.white); 
				e1.setText("");	
				t1.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.white));
			}  
			public void focusLost(FocusEvent e) 
			{ 
				if (t1.getText().length() == 0) 
				{  
					t1.setText(" *Account No");  
					t1.setForeground(new Color(150, 150, 150));  
				}  
				validataccount();
				t1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
			}  
		});
		
		t2.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(t2.getText().equals(" *Password"))
				{
					t2.setText("");  
					t2.setEchoChar('*'); 
					t2.setForeground(new Color(50, 50, 50));  
				}
					e2.setText("");
					t2.setEchoChar('*'); 
					t2.setForeground(Color.white); 
					t2.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.white));
			} 
			public void focusLost(FocusEvent e) 
			{ 
				if (t2.getText().length() == 0) 
				{  
					t2.setEchoChar((char)0); 
					t2.setText(" *Password");  
					t2.setForeground(new Color(150, 150, 150));  
				}  
				t2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				validataccount();
				validatepass();
			}  
		});
	
	

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");			//Register the Driver
			cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Matin");		//Establish the Connection
			st=cn.createStatement();								//Create Statement Object
		}

		catch(Exception e)
		{
			 
			JOptionPane.showMessageDialog(null,"Exception");
		}
 	 
 
	}

	public void registrationform()
	{
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Registration form Frame
		regf=new JFrame("Registration Form");
		regf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		regf.setExtendedState(JFrame.MAXIMIZED_BOTH);				//1920,1080
		regf.setLayout(null);
		regf.getContentPane().setBackground(new Color(255,153,153));
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Main Heading
		JLabel mh=new JLabel("Registration Form",JLabel.CENTER);
		mh.setBounds(200,10,1000,100);
		mh.setFont(new Font("Times new roman",Font.BOLD,32));
		mh.setBorder(BorderFactory.createMatteBorder(2, 8, 2, 8, Color.black));
		mh.setOpaque(true);
		mh.setBackground( new Color(255,225,31));
		mh.setForeground(Color.black);
		mh.setToolTipText("Fill the Form to Create your bank account");
		regf.add(mh);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////Panel
		JPanel pm=new JPanel();
		pm.setBounds(200,120,1000,580);
		pm.setLayout(null);
		pm.setBackground(new Color(255,204,153));
		pm.setBorder(BorderFactory.createMatteBorder(2, 2, 6, 2, Color.red));
		regf.add(pm);
		JLabel l; 
	
	
		///////////////////////////////////////////////////////////////////////////////////////////////
		////	First Name
		
		rl=new JLabel("Name :");
		rl.setBounds(280,30,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
		
		rt1=new JTextField(13);
		rt1.setBounds(450,35,90,27); 
		rt1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		rt1.setText(" *First");  
		rt1.setForeground(new Color(150, 150, 150)); 
		pm.add(rt1);

		rt1.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt1.getText().equals(" *First"))
				{
					rt1.setText("");  
					rt1.setForeground(new Color(50, 50, 50)); 
				}	
				re1.setText("");
				rt1.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt1.getText().length() == 0) 
				{  
					rt1.setText(" *First");  
					rt1.setForeground(new Color(150, 150, 150));  
				}  
				else
				{
					if(rt1.getText().equals(" *First"))
					{
						re1.setText("* Required");
					}
					else if(!(Pattern.matches("[A-Za-z]{1,}",rt1.getText())))
					{
						re1.setText("* Invalid Name"); 
					}	
				}
				rt1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				validatefirst();

			}  
		});
		
		re1=new JLabel(" ");
		re1.setBounds(450,60,90,27);
		re1.setFont(new Font("Times New Roman",Font.BOLD,12));
		re1.setForeground(Color.red);
		pm.add(re1);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		////	Middle Name
		
		rt2=new JTextField(13);
		rt2.setBounds(550,35,90,27);
		rt2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt2);
		rt2.setText(" *Middle");  
		rt2.setForeground(new Color(150, 150, 150)); 

		rt2.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt2.getText().equals(" *Middle"))
				{
					rt2.setText("");  
					rt2.setForeground(new Color(50, 50, 50));  
				}
				validatefirst();	
				
				re2.setText("");	
				rt2.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
					
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt2.getText().length() == 0) 
				{  
					rt2.setText(" *Middle");  
					rt2.setForeground(new Color(150, 150, 150));  
				}  
				validatefirst();
				validatemiddle();
				rt2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
			}  
		});
		
		re2=new JLabel(" ");
		re2.setBounds(550,60,90,27);
		re2.setFont(new Font("Times New Roman",Font.BOLD,12));
		re2.setForeground(Color.red);
		pm.add(re2);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	Last Name
	
		rt3=new JTextField(13);
		rt3.setBounds(650,35,90,27);
		rt3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt3);
		rt3.setText(" *Last");  
		rt3.setForeground(new Color(150, 150, 150)); 

		rt3.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt3.getText().equals(" *Last"))
				{
					rt3.setText("");  
					rt3.setForeground(new Color(50, 50, 50));  
				}
				re3.setText("");
				rt3.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				validatefirst();
				validatemiddle();
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt3.getText().length() == 0) 
				{  
					rt3.setText(" *Last");  
					rt3.setForeground(new Color(150, 150, 150));  
				}  
				rt3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				
				validatelast();
			}  
		});
	
		re3=new JLabel(" ");
		re3.setBounds(650,60,90,27);
		re3.setFont(new Font("Times New Roman",Font.BOLD,12));
		re3.setForeground(Color.red);
		pm.add(re3);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	Phone Number
		
		rl=new JLabel("Phone Number :");
		rl.setBounds(280,80,120,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
		
		rt4 = new JTextField(15);
		rt4.setBounds(450,85,200,27);
		rt4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt4);
		rt4.setText(" *Number");  
		rt4.setForeground(new Color(150, 150, 150));	
		
		rt4.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt4.getText().equals(" *Number"))
				{
					rt4.setText("");  
					rt4.setForeground(new Color(50, 50, 50));  
				}
				rt4.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re4.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
			
			}  
	
			public void focusLost(FocusEvent e) 
			{ 
			
				if (rt4.getText().length() == 0) 
				{  
					rt4.setText(" *Number");  
					rt4.setForeground(new Color(150, 150, 150));  
				}  
				rt4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				
				validatenumber();
			}  
		});
		
		re4=new JLabel(" ");
		re4.setBounds(450,110,200,27);
		re4.setFont(new Font("Times New Roman",Font.BOLD,12));
		re4.setForeground(Color.red);
		pm.add(re4);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Email ID
		
		rl=new JLabel("Email ID :");
		rl.setBounds(280,130,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
		
		rt5=new JTextField(15);
		rt5.setBounds(450,135,200,27);
		rt5.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt5);
		rt5.setText(" *Email ID");  
		rt5.setForeground(new Color(150, 150, 150));
		
		rt5.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt5.getText().equals(" *Email ID"))
				{
					rt5.setText("");  
					rt5.setForeground(new Color(50, 50, 50));  
				}
				rt5.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re5.setText("");
				
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt5.getText().length() == 0) 
				{  
					rt5.setText(" *Email ID");  
					rt5.setForeground(new Color(150, 150, 150));  
				}  
				rt5.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				
				validateemail();
			}  
		});
		
		re5=new JLabel(" ");
		re5.setBounds(450,160,200,27);
		re5.setFont(new Font("Times New Roman",Font.BOLD,12));
		re5.setForeground(Color.red);
		pm.add(re5);
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Aadhar Number
		
		rl=new JLabel("Adhaar Number :");
		rl.setBounds(280,180,130,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
		
		rt11=new JTextField(15);
		rt11.setBounds(450,185,200,27);
		rt11.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt11);
		rt11.setText(" *Adhaar Number");  
		rt11.setForeground(new Color(150, 150, 150));

		rt11.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt11.getText().equals(" *Adhaar Number"))
				{
					rt11.setText("");  
					rt11.setForeground(new Color(50, 50, 50));  
				}
				rt11.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re11.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 

				if (rt11.getText().length() == 0) 
				{  
					rt11.setText(" *Adhaar Number");  
					rt11.setForeground(new Color(150, 150, 150));  
				}  
				rt11.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				
				validateaadhar();
			}  
		});
		
		re11=new JLabel(" ");
		re11.setBounds(450,210,200,27);
		re11.setFont(new Font("Times New Roman",Font.BOLD,12));
		re11.setForeground(Color.red);
		pm.add(re11);
	
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Date Of Birth (DOB)
		
	
		rl=new JLabel("DOB :");
		rl.setBounds(280,230,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
		
		//////////		Day		///////////////////////////////////////////////////////////////////////
		
		rtd6=new JTextField(2);
		rtd6.setBounds(450,235,50,27);
		rtd6.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		rtd6.setText("DD");  
		rtd6.setForeground(new Color(150, 150, 150)); 
		pm.add(rtd6);

		dNow = new Date( );
		dd = new SimpleDateFormat ("dd");
		mm = new SimpleDateFormat ("MM");
		yy = new SimpleDateFormat ("yyyy");
		
		a= Integer.parseInt(dd.format(dNow));
		b= Integer.parseInt(mm.format(dNow));
		c= Integer.parseInt(yy.format(dNow));

		rtd6.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rtd6.getText().equals("DD"))
				{
					rtd6.setText("");  
					rtd6.setForeground(new Color(50, 50, 50));  
				}
				rtd6.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				red6.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
				
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 

				if (rtd6.getText().length() == 0) 
				{  
					rtd6.setText("DD");  
					rtd6.setForeground(new Color(150, 150, 150));  
				}  
				rtd6.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));

			}  
		});
		
		red6=new JLabel(" ");
		red6.setBounds(450,260,300,27);
		red6.setFont(new Font("Times New Roman",Font.BOLD,12));
		red6.setForeground(Color.red);
		pm.add(red6);
	
		
		//////////		Month		///////////////////////////////////////////////////////////////////// 
		
		rtm6=new JTextField(2);
		rtm6.setBounds(505,235,50,27);
		rtm6.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		rtm6.setText("MM");  
		rtm6.setForeground(new Color(150, 150, 150)); 
		pm.add(rtm6);
		
		rtm6.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rtm6.getText().equals("MM"))
				{
					rtm6.setText("");  
					rtm6.setForeground(new Color(50, 50, 50));  
				} 
				rtm6.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				red6.setText("");
				rem6.setText("");
				rey6.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rtm6.getText().length() == 0) 
				{  
					rtm6.setText("MM");  
					rtm6.setForeground(new Color(150, 150, 150));  
				}  
				rtm6.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
			}  
		});
		
		rem6=new JLabel(" ");
		rem6.setBounds(450,260,200,27);
		rem6.setFont(new Font("Times New Roman",Font.BOLD,12));
		rem6.setForeground(Color.red);
		pm.add(rem6);
	
		
		//////////		Year		//////////////////////////////////////////////////////////////////////
		
		rty6=new JTextField(2);
		rty6.setBounds(560,235,50,27);
		rty6.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		rty6.setText("YYYY");  
		rty6.setForeground(new Color(150, 150, 150));
		pm.add(rty6);
		
		rty6.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rty6.getText().equals("YYYY"))
				{
					rty6.setText("");  
					rty6.setForeground(new Color(50, 50, 50));  
				}
				rty6.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				red6.setText("");
				rem6.setText("");
				rey6.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rty6.getText().length() == 0) 
				{  
					rty6.setText("YYYY");  
					rty6.setForeground(new Color(150, 150, 150));  
				}
				rty6.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));

			}  
		});
		
		rey6=new JLabel(" ");
		rey6.setBounds(450,260,200,27);
		rey6.setFont(new Font("Times New Roman",Font.BOLD,12));
		rey6.setForeground(Color.red);
		pm.add(rey6);
	
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Gender
		
		rl=new JLabel("Gender :");
		rl.setBounds(280,280,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
	
		pm.add(rd1);
		pm.add(rd2);
		pm.add(rd3);
		rd1.setBounds(450,290,60,13);
		rd1.setBackground(new Color(255,204,153));
		rd1.setForeground(Color.blue);
		rd2.setBounds(520,290,80,13);
		rd2.setBackground(new Color(255,204,153));
		rd2.setForeground(Color.blue);
		rd3.setBounds(600,290,90,13);
		rd3.setBackground(new Color(255,204,153));
		rd3.setForeground(Color.blue);
		bg.add(rd1);
		bg.add(rd2);
		bg.add(rd3);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Address

		rl=new JLabel("Address :");
		rl.setBounds(280,330,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);

		rt7=new JTextArea(15,2);
		rt7.setBounds(450,335,250,35);
		rt7.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt7);
		rt7.setText(" *Address");  
		rt7.setForeground(new Color(150, 150, 150));  

		rt7.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt7.getText().equals(" *Address"))
				{
					rt7.setText("");  
					rt7.setForeground(new Color(50, 50, 50));  
				}
				rt7.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re7.setText("");
				validatedob();
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
				validatedob();
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt7.getText().length() == 0) 
				{  
					rt7.setText(" *Address");  
					rt7.setForeground(new Color(150, 150, 150));  
				}  
				rt7.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				validateaddress();
			}  
		});
		
		re7=new JLabel(" ");
		re7.setBounds(450,363,200,27);
		re7.setFont(new Font("Times New Roman",Font.BOLD,12));
		re7.setForeground(Color.red);
		pm.add(re7);
	
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Amount
		
		rl=new JLabel("Amount :");
		rl.setBounds(280,380,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
	
		rt8=new JTextField(15);
		rt8.setBounds(450,385,200,27);
		rt8.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		rt8.setText(" *Amount");  
		rt8.setForeground(new Color(150, 150, 150));  
		pm.add(rt8);

		rt8.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt8.getText().equals(" *Amount"))
				{
					rt8.setText("");  
					rt8.setForeground(new Color(50, 50, 50));  
				}
				rt8.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re8.setText("");
				validateaddress();
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
				validatedob();
				validateaddress();
			
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt8.getText().length() == 0) 
				{  
					rt8.setText(" *Amount");  
					rt8.setForeground(new Color(150, 150, 150));  
				}  
				rt8.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				
				validateamount();
			}  
		});
		
		re8=new JLabel(" ");
		re8.setBounds(450,410,200,27);
		re8.setFont(new Font("Times New Roman",Font.BOLD,12));
		re8.setForeground(Color.red);
		pm.add(re8);
	
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Password
		
		rl=new JLabel("Password :");
		rl.setBounds(280,430,100,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
		
		rt9=new JTextField(15);
		rt9.setBounds(450,435,200,27);
		rt9.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		pm.add(rt9);
		rt9.setText(" *Password");  
		rt9.setForeground(new Color(150, 150, 150));  

		rt9.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt9.getText().equals(" *Password"))
				{
					rt9.setText("");  
					rt9.setForeground(new Color(50, 50, 50));  
				}
				rt9.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re9.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
				validatedob();
				validateaddress();
				validateamount();	
				
			}  
	   
			public void focusLost(FocusEvent e) 
			{ 
				if (rt9.getText().length() == 0) 
				{  
					rt9.setText(" *Password");  
					rt9.setForeground(new Color(150, 150, 150));  
				}  
				rt9.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
					
				validatepassword();
			}  
		});
		
		re9=new JLabel(" ");
		re9.setBounds(450,460,200,27);
		re9.setFont(new Font("Times New Roman",Font.BOLD,12));
		re9.setForeground(Color.red);
		pm.add(re9);
			
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		//// 	 Confirm Password
		
		rl=new JLabel("Confirm Password :");
		rl.setBounds(280,480,150,40);
		rl.setForeground(Color.blue);
		rl.setFont(new Font("Times New Roman",Font.BOLD,15));
		pm.add(rl);
	
		rt10=new JTextField(15);
		rt10.setBounds(450,485,200,27);
		rt10.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		rt10.setText(" *Confirm Password");  
		rt10.setForeground(new Color(150, 150, 150));  
		pm.add(rt10);
		rt10.setEditable(false);

		rt10.addFocusListener(new FocusListener() 
		{     
			public void focusGained(FocusEvent e) 
			{  
				if(rt10.getText().equals(" *Confirm Password"))
				{
					rt10.setText("");  
					rt10.setForeground(new Color(50, 50, 50));  
					
					if(rt9.getText().equals(" *Password"))
					{
					   re9.setText("* Required");
					}
				}
				rt10.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.red));
				re10.setText("");
				validatefirst();
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
				validatedob();
				validateaddress();
				validateamount();
				validatepassword() ;
			}  
	 
			public void focusLost(FocusEvent e) 
			{ 
				if (rt10.getText().length() == 0) 
				{  
					rt10.setText(" *Confirm Password");  
					rt10.setForeground(new Color(150, 150, 150));  
				}  
				else
				rt10.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
				re10.setText("");
				validateconpassword();
				
				
			}  
		});
		
		re10=new JLabel(" ");
		re10.setBounds(450,510,300,27);
		re10.setFont(new Font("Times New Roman",Font.BOLD,12));
		re10.setForeground(Color.red);
		pm.add(re10);

		
	

		///////////////////////////////////////////////////////////////////////////////////////////////
		////	Register Button
	
		create=new JButton("Create Account");
		create.setBounds(520,530,150,30);
		create.setForeground(Color.white);
		create.setBackground(Color.green);
		create.setFont(new Font("Times New Roman",Font.BOLD,17));
		pm.add(create);
		
	 
	 
		create.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				validatefirst(); 
				validatemiddle();
				validatelast();
				validatenumber();
				validateemail();
				validateaadhar();
				validatedob();
				validateaddress();
				validateamount();
				validatepassword();
				validateconpassword();
							
				if(re1.getText()==""&&re2.getText()==""&&re3.getText()==""&&re4.getText()==""&&re5.getText()==""&&re11.getText()==""&&red6.getText()==""&&rem6.getText()==""&&rey6.getText()==""&&re7.getText()==""&&re8.getText()==""&&re9.getText()==""&&re10.getText()=="")
				{
					
						try
						{
						rs=st.executeQuery("select Aadhar from Accounts where Aadhar="+rt11.getText());
						
						int cnt=0;	 
						if(rs.next())
						{
							cnt++;
						}
						if(cnt==0)
						{	
							rs=st.executeQuery("Select max(AcNo) from Accounts");
							if(rs.next())
							{
								int acc=rs.getInt(1)+1;
								if(rd1.isSelected())
								{	
									gen="Male";
								}
								else if(rd2.isSelected())
								{	
									gen="Female";
								}
								else
								{
									gen="Other";
								}
								name=rt1.getText()+" "+rt2.getText()+" "+rt3.getText();
								JOptionPane.showMessageDialog(null,"<html>Registration Successfull.<hr><div style=color:blue>Your Account No :<u>"+acc+"</div></u></html>","                        *** No Bank ***",JOptionPane. PLAIN_MESSAGE);
								
							 st.executeUpdate("insert into Accounts values("+acc+",'"+name+"','"+rt9.getText()+"',"+rt8.getText()+","+rt4.getText()+",'"+rt5.getText()+"',"+rt11.getText()+",'"+bdate+"','"+gen+"','"+rt7.getText()+"')");
								
								
								 
							}
						}
						
						else
						{
							JOptionPane.showMessageDialog(regf,"You have already an Account.","Warning",JOptionPane. WARNING_MESSAGE);
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(regf,"There is an Exception "+e,"Warning",JOptionPane. WARNING_MESSAGE);
					}
					
				}
			}
		});	

		///////////////////////////////////////////////////////////////////////////////////////////////
		////	Back Button
		JButton back=new JButton("Back");
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setFont(new Font("Times new roman",Font.PLAIN,18));
		back.setBounds(310,530,110,30);
		back.setBackground(Color.blue);
		back.setForeground(Color.white);
		pm.add(back);
		
		regf.setVisible(true);
		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				regf.dispose();
				hp.setVisible(true);
			}
		});
	
		 
		regf.setLayout(null);
		regf.setTitle("Banking System");
		regf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		regf.setLocationRelativeTo(null);
	
		
	}
	public void Login()
	{
		j3=new JFrame();
		ltitle=new JLabel("--- My Bank ---",JLabel.CENTER);
		choose=new JLabel("  --  Select The Service  -- ",JLabel.CENTER);
		trans=new JButton("Transfer");
		depo=new JButton("Deposit");
		with=new JButton("Withdarw");
		check=new JButton("Check Balance");
		lback=new JButton("Logout"); 
		j3.getContentPane().setBackground(new Color(255,153,153));
		
		
		
		
		ltitle.setBounds(320,100,500,60);
		ltitle.setForeground(Color.black);
		ltitle.setFont(new Font("Times New Roman",Font.BOLD,28));
		//ltitle.setBorder(BorderFactory.createMatteBorder(1,3,3,1, Color.black));
		ltitle.setBackground(new Color(255,253,123));
		
		try
		{	
			rs=st.executeQuery("select Name from Accounts where Acno="+Integer.parseInt(t1.getText()));
				
				if(rs.next())
					{
						nm=rs.getString(1);
						
					}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(j3,"There is Exception "+e,"Warning",JOptionPane. WARNING_MESSAGE);
		}
		lname=new JLabel("Welcome "+nm);
		lname.setBounds(515,170,400,30);
		lname.setForeground(Color.red);
		lname.setFont(new Font("Times New Roman",Font.BOLD,22));
		
		choose.setBounds(530,220,280,30);
		choose.setForeground(Color.blue);
		choose.setFont(new Font("Times New Roman",Font.BOLD,19)); 
		
		trans.setFont(new Font("Times New Roman",Font.BOLD,20));
		//trans.setBackground(Color.red);
		trans.setForeground(lightblue);
		trans.setBorder(BorderFactory.createMatteBorder(1,2,2,1, Color.red));
		
		depo.setFont(new Font("Times New Roman",Font.BOLD,20));
		//depo.setBackground(Color.white);
		depo.setForeground(lightblue);
		depo.setBorder(BorderFactory.createMatteBorder(1,2,2,1, Color.red));
		
		with.setFont(new Font("Times New Roman",Font.BOLD,20));
		//with.setBackground(Color.white);
		with.setForeground(lightblue);
		with.setBorder(BorderFactory.createMatteBorder(1,2,2,1, Color.red));
		
		check.setFont(new Font("Times New Roman",Font.BOLD,20));
		//check.setBackground(Color.white);
		check.setForeground(lightblue);
		check.setBorder(BorderFactory.createMatteBorder(1,2,2,1, Color.red));
		
		lback.setFont(new Font("Times New Roman",Font.BOLD,20));
		lback.setBackground(Color.green);
		lback.setForeground(Color.white);
		lback.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.red));
		
		trans.setBounds(520,280,300,40);
		depo.setBounds(520,330,300,40);
		with.setBounds(520,380,300,40);
		check.setBounds(520,430,300,40);
		lback.setBounds(600,530,120,30);
		
		JPanel p1=new JPanel();
		p1.setBorder(BorderFactory.createMatteBorder(1,4,5,2, Color.red));
		p1.setBounds(420,150,500,430);
		p1.setBackground(new Color(255,253,153));
		
		JPanel p2=new JPanel();
		p2.setBorder(BorderFactory.createMatteBorder(1,4,1,4, Color.black));
		p2.setBounds(420,90,500,50);
		p2.setBackground(new Color(255,253,153));
		p2.add(ltitle);
		
		j3.add(p2);
		
	 
		j3.add(lname);
		j3.add(choose);
		j3.add(trans);
		j3.add(depo);
		j3.add(with);
		j3.add(check);
		j3.add(lback);
		j3.add(p1);
		
		
		trans.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				
				traa1= JOptionPane.showInputDialog("Enter Account Number Where you want to Transfer Balance: ");
				try
				{	
					if(traa1.equals("")||(!(Pattern.matches("\\d{8}",traa1)))||traa1.equals(t1.getText()))
					{
						JOptionPane.showMessageDialog(j3,"Invalid Account Number","Warning",JOptionPane. WARNING_MESSAGE);
					}
					
					else
					{
						try
						{
							rs=st.executeQuery("select AcNo from Accounts where AcNo="+Integer.parseInt(traa1));
							int cnt=0;	 
							if(rs.next())
							{
								if(rs.getString(1).equals(traa1))
								{
									cnt++;
								}
							}
								if(cnt>0)
								{	
									rs=st.executeQuery("select Amt from Accounts where AcNo="+Integer.parseInt(traa1));
								 
									if(rs.next())
									{					
										bal2=rs.getInt(1);
									}
									
									trab1= JOptionPane.showInputDialog("Enter Balance you want to Transfer: ");
									if(!(Pattern.matches("\\d{0,}",trab1)))
									{
										JOptionPane.showMessageDialog(j3,"Invalid Amount","Warning",JOptionPane. WARNING_MESSAGE);
									}
									
									else
									{
										rs=st.executeQuery("select Amt from Accounts where AcNo="+Integer.parseInt(t1.getText()));
										if(rs.next())
										{
											bal=rs.getInt(1);					
											if((bal-Integer.parseInt(trab1))>=0)
											{
												st.executeUpdate("Update Accounts set Amt="+ (bal-Integer.parseInt(trab1))+" where AcNo="+t1.getText());						
												rs=st.executeQuery("select Amt from Accounts where AcNo="+Integer.parseInt(traa1));
												if(rs.next())
												{
													int bal3=rs.getInt(1)+Integer.parseInt(trab1);					
													st.executeUpdate("Update Accounts set Amt="+bal3+" where AcNo="+Integer.parseInt(traa1));			
													JOptionPane.showMessageDialog(j3,"<html><u>"+trab1+"</u> Rs Transfered Sucessfuly.","                        *** No Bank ***",JOptionPane. PLAIN_MESSAGE);	
												}		 
											}
											else
											{
												JOptionPane.showMessageDialog(j3,"<html>Sorry, You have not enough money Left.</html>","Warning",JOptionPane. WARNING_MESSAGE);
											}
										}
									}
								}

							else
							{
								JOptionPane.showMessageDialog(j3,"Account Number is not available.","Warning",JOptionPane. WARNING_MESSAGE);
							}
							
						}
						catch(Exception e)
						{
							 
						}
					}
				}
				catch(NullPointerException e) 
				{ 
					
				} 
			
			}
		});
		
	
		depo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
					{	
						try
						{
							depo1= JOptionPane.showInputDialog(j3,"Enter Amount you want to Deposit: ","                        *** My Bank ***",JOptionPane. PLAIN_MESSAGE);
							if(!(Pattern.matches("\\d{0,}",depo1)))
							{
								JOptionPane.showMessageDialog(j3,"Invalid Amount","Warning",JOptionPane. WARNING_MESSAGE);
							}
							else
							{		
								try
								{	 
									rs=st.executeQuery("select Amt from Accounts where AcNo="+Integer.parseInt(t1.getText()));				
									if(rs.next())
									{
										bal=rs.getInt(1)+Integer.parseInt(depo1);
										st.executeUpdate("Update Accounts set Amt="+bal+" where AcNo="+t1.getText());			
										JOptionPane.showMessageDialog(j3,"<html><u>"+Integer.parseInt(depo1)+"</u> Rs Deposited Sucessfuly.","                        *** No Bank ***",JOptionPane. PLAIN_MESSAGE);	
									}
										
								}
								catch(Exception e){}						
							}
						}
						catch(NullPointerException e){} 
					}
				});
				
			
		with.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				with1= JOptionPane.showInputDialog(j3,"Enter Amount you want to Withdarw: ","                        *** My Bank ***",JOptionPane. PLAIN_MESSAGE);
				try
				{
					if(!(Pattern.matches("\\d{0,}",with1)))
					{
						JOptionPane.showMessageDialog(j3,"Invalid Amount","Warning",JOptionPane. WARNING_MESSAGE);
					}
					else
					{		
						try
						{	 
							rs=st.executeQuery("select Amt from Accounts where AcNo="+Integer.parseInt(t1.getText()));
							if(rs.next())
							{			
								bal=rs.getInt(1)-Integer.parseInt(with1);	
								if(bal>=100)
								{
									st.executeUpdate("Update Accounts set Amt="+bal+" where AcNo="+t1.getText());			
									JOptionPane.showMessageDialog(j3,"<html><u>"+Integer.parseInt(with1)+"</u> Rs Withdrawn Sucessfuly.</html>","                        *** No Bank ***",JOptionPane. PLAIN_MESSAGE);	
								}
								else
								{
									JOptionPane.showMessageDialog(j3,"<html>Sorry, You have not enough money Left.</html>","Warning",JOptionPane. WARNING_MESSAGE);
								}
							}
						}
						catch(Exception e){}
					}
				}
				catch(NullPointerException e) 
				{ 
					
				} 
			}
		});


		check.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
				{	
					try
					{	 
						rs=st.executeQuery("select Amt from Accounts where AcNo="+Integer.parseInt(t1.getText()));
					
						if(rs.next())
						{
							int bal=rs.getInt(1);
							JOptionPane.showMessageDialog(j3,"<html>Your Amt : <u>"+bal+"</u> Rs.</html> ","                        *** My Bank ***",JOptionPane. PLAIN_MESSAGE);
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(j3,"There is Exception "+e,"Warning",JOptionPane. WARNING_MESSAGE);
					}
		
				}
		});
	 
		
		lback.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
				{	
					hp.setVisible(true);
					j3.dispose();
					e1.setText("");
					e2.setText("");
					t1.setText("");
					
					t1.setText(" *Account No");  
					t1.setForeground(new Color(150, 150, 150));	
					
					t2.setText("");
					t2.setEchoChar((char)0); 
					t2.setText(" *Password");  
					t2.setForeground(new Color(150, 150, 150));
					
				}
		});
		
		j3.setLayout(null);
		j3.setVisible(true);
		j3.setTitle("Banking System");
		j3.setExtendedState(JFrame.MAXIMIZED_BOTH);
		j3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j3.setLocationRelativeTo(null);
		hp.setVisible(false);
	}


	public void  validataccount()
	{
		//Validations for Account No
		
		if(t1.getText().equals(" *Account No"))
		{
			e1.setText("* Required");
		}
		else if(!(Pattern.matches("\\d{8}",t1.getText())))
		{
			e1.setText("* Invalid Account No"); 
		}	
	}
	
	public void  validatepass()
	{
		// Validatios for Password
	
		if(t2.getText().equals(" *Password"))
		{
			e2.setText("* Required");
		}
	}
	
	public void  validatefirst()
	{
		//Validations for First Name
		
		if(rt1.getText().equals(" *First"))
		{
			re1.setText("* Required");
		}
		else if(!(Pattern.matches("[A-Za-z]{1,}",rt1.getText())))
		{
			re1.setText("* Invalid Name"); 
		}
	}
	
	public void  validatemiddle()
	{
		//Validations for Middle Name
	
		if(rt2.getText().equals(" *Middle"))
		{
			re2.setText("* Required");
		}
		else if(!(Pattern.matches("[A-Za-z]{1,}",rt2.getText())))
		{
			re2.setText("* Invalid Name");			 
		}
	}
	
	public void  validatelast()
	{
		//Validations for Last Name
	
		if(rt3.getText().equals(" *Last"))
		{
			re3.setText("* Required");
		}
		else if(!(Pattern.matches("[A-Za-z]{1,}",rt3.getText())))
		{
			re3.setText("* Invalid Name");
		}
	}
	
	public void  validatenumber()
	{
		//Validations for Number
		
		if(rt4.getText().equals(" *Number"))
		{
			re4.setText("* Required");
		}
		else if(!(Pattern.matches("\\d{10}",rt4.getText())))
		{
			re4.setText("* Invalid Number");
		}
	 
	}
	
	public void  validateemail()
	{
		//Validations for Email ID
	
		if(rt5.getText().equals(" *Email ID"))
		{
			re5.setText("* Required");
		}
		else if(!(rt5.getText().trim().endsWith("@gmail.com")))
		{
			re5.setText("* Invalid Email ID");			 
		}
	}
	
	public void  validateaadhar()
	{
		//Validations for Number
		
		if(rt11.getText().equals(" *Adhaar Number"))
		{
			re11.setText("* Required");
		}
		else if(!(Pattern.matches("\\d{12}",rt11.getText())))
		{
			re11.setText("* Invalid Adhaar Number");
		}
	 
	}

	public void  validatedob()
	{
		//Validations for DOB

		red6.setText("");
		rem6.setText("");
		rey6.setText("");
	
		a= Integer.parseInt(dd.format(dNow));
		b= Integer.parseInt(mm.format(dNow));
		c= Integer.parseInt(yy.format(dNow));
		
		if(rtd6.getText().equals("DD")||rtd6.getText().length()==0)
		{
			red6.setText(" *Required ");
		}
		else if(rtm6.getText().equals("MM")||rtm6.getText().length()==0)
		{
			rem6.setText(" *Required ");
		}
		else if(rty6.getText().equals("YYYY")||rty6.getText().length()==0)
		{
			rey6.setText(" *Required ");
		}
		else if(rtd6.getText().length()>2||(rtd6.getText().matches("[a-zA-Z]+")))
		{
			red6.setText("* Invalid Date");					 
		}
		else if(rtm6.getText().length()>2||(rtm6.getText().matches("[a-zA-Z]+")))
		{
			red6.setText("* Invalid Date"); 
		}
		else if(rty6.getText().length()>4||(rty6.getText().matches("[a-zA-Z]+")))
		{
			red6.setText("* Invalid Date");
		}					
		else
		{		
			if(validateJavaDate(Integer.parseInt(rtd6.getText())+"/"+Integer.parseInt(rtm6.getText())+"/"+Integer.parseInt(rty6.getText())))
			{
				if((Integer.parseInt(rtd6.getText())>a))
				{
					b--;
				}
				if((Integer.parseInt(rtm6.getText())> b))
				{
					c--;
				}
				age= c-(Integer.parseInt(rty6.getText()));
				if(age>70)
				{
				red6.setText("* You are too Old for Creating Account. ");
				}
				if(age<10)
				{
				red6.setText("* You are Minor for Creating Account. ");
				}	
				switch(Integer.parseInt(rtm6.getText()))
				{
					case 1: Month="JAN";
							break;
					case 2: Month="FEB"; break;
					case 3: Month="MAR"; break;
					case 4: Month="APR"; break;
					case 5: Month="MAY"; break;
					case 6: Month="JUN"; break;
					case 7: Month="JUL"; break;
					case 8: Month="AUG"; break;
					case 9: Month="SEP"; break;
					case 10: Month="OCT"; break;
					case 11: Month="NOV"; break;
					case 12: Month="DEC"; break;
					
				}
				bdate=(Integer.parseInt(rtd6.getText())+"/"+Month+"/"+Integer.parseInt(rty6.getText()));
			}
		}
	}
	
	
	public void  validateaddress()
	{
		//Validations for Address
		
		if(rt7.getText().equals(" *Address"))
		{
			re7.setText("* Required");
		}
		if(rt7.getText().matches("[0-9]+"))
		{
			re7.setText("* Invalid Address");	 
		}
	}
	
	
	public void  validateamount()
	{
		// Validations for Amount
	
		if(rt8.getText().equals(" *Amount"))
		{
			re8.setText("* Required");
		}
		else if(!(Pattern.matches("\\d{0,}",rt8.getText())))
		{
			re8.setText("* Invalid Amount");
		}
		else if(Integer.parseInt(rt8.getText())<500)
		re8.setText("* Amount Sould be greater than 500");
	}
	
	
	public void  validatepassword()
	{
		// Validatios for Password
	
		if(rt9.getText().equals(" *Password"))
		{
			re9.setText("* Required");
		}
		else if(!(Pattern.matches("\\S{8,}",rt9.getText())))
		{					 
			re9.setText("* Password Should be Greater than 8");					
		}
		else
		rt10.setEditable(true);
	}


	public void  validateconpassword()
	{
		//Validations for Confirm Password
		
		if(rt10.getText().equals(" *Confirm Password"))
		{
			re10.setText("* Required");
		}
		if(!(rt9.getText().equals(" *Password")))
		{
			String s1=rt10.getText();
			String s2=rt9.getText();
			if(s1.equals(s2))
			{
				re10.setText("");
			}
			else if(!(s1.equals(s2)))
			{
				re10.setText("* Password and Confirm Password must be same");
			}
		}
	}

	
	public static boolean validateJavaDate(String strDate)
	{
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		sdfrmt.setLenient(false);
		Date javaDate,cu;
		try
		{
			javaDate = sdfrmt.parse(strDate); 
			String custr=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			cu=sdfrmt.parse(custr);	
		}
		catch (ParseException e)
		{
			red6.setText("* Invalid Date");
			return false;
		} 
		if(javaDate.compareTo(cu)<=0)
		{
			return true;
		}
		else
		{
			red6.setText("* Invalid Date");
			return false;
		}
	}
	
    
	public static void main(String args[]) 
	{
		new Bank();
	}	
}