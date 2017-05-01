import javax.swing.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class myGUI extends JFrame
{
    private addCriminal criminals[] =new addCriminal[10000];
    private int CriminalIdx=0,i=0;
    
    private addPolice polices[]=new addPolice[10000];
    private int PoliceIdx=0,j=0;
    
    private addContact contacts[]=new addContact[1000];
    private int ContactIdx=0,k=0;
    
    
    public void MenuClient()
    {
        JMenuBar bar= new JMenuBar(); 
        setJMenuBar(bar);
       
        bar.setVisible(true);
        
        JMenu menu=new JMenu("File");
        JMenu report=new JMenu("Report");
        JMenu archive=new JMenu("Archive");
        JMenu home=new JMenu("Home");
        JMenu contact=new JMenu("Contact");
        JMenu help= new JMenu("Help");
      
        JMenuItem Exit=new JMenuItem("Exit");
        JMenuItem Criminal=new JMenuItem("Criminal");
        JMenuItem Police=new JMenuItem("Police");  
        JMenuItem Home=new JMenuItem("Home");
        JMenuItem contactUs=new JMenuItem("Contact Us");
        JMenuItem Help=new JMenuItem("Help");
        JMenuItem Report=new JMenuItem("Report Crime");       
        Exit.setToolTipText("Exit applicattion");
        
        bar.add(menu);  
        bar.add(report);
        bar.add(archive);
        bar.add(help);
        bar.add(contact);
        bar.add(home);
        bar.add(help);
                
        contact.add(contactUs);       
        home.add(Home);
        help.add(Help);
        menu.add(Exit);
        report.add(Report);
        
        archive.add(Criminal);
        archive.add(Police);
        
        setVisible(true);
          Exit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
          
        Criminal.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                criminalArchiveHomePage();
            }
        });
        
            Home.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                homePage();
            }
        });
            
        Police.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                policeArchiveHomePage();
            }
        });
            
         contactUs.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                contactInfo();
            }
        });
         
         Report.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                  RunReport ob=  new RunReport();
                  ob.runReport();
            }
        });
    }
        
    public void contactInfo()
    {
       addContact();
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back4.jpg")));
         
        ImageIcon nextIcon = new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\next.jpg");
        ImageIcon prevIcon = new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\prev.jpg");
 
        JButton next=new JButton(nextIcon);
        next.setBounds(650,210,40,40);
        add(next);
        
        JButton prev=new JButton(prevIcon);
        prev.setBounds(220,210,40,40);
        add(prev);
        
          
        final JTextField search=new JTextField(""); 
        search.setBounds(540,20,100,20);
        add(search);
      
        ImageIcon image=new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\search.jpg");
        JLabel label = new JLabel();
        label.setBounds(520, 20, 20, 20);
        label.setIcon(image);
        add(label);
        
         JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(new Color(0,0,0,125));
      panel.setBounds(270, 80, 370, 340);
      
       JTextArea text=new JTextArea(); 
      text.setBounds(50,50,320,350);
      text.setLayout( new BorderLayout());
      //text.setEditable(false);
      
      Font font = new Font("Verdana", Font.BOLD, 14);
      text.setFont(font);
      text.setForeground(Color.white);    
      text.setBackground(new Color(0,0,0,0));
      text.setLineWrap(true);
      

        text.setText("                   "+contacts[k].getName()
      +"\n\n\n Contact Number:  "+contacts[k].getNumber());  
        
       panel.add(text);
      add(panel);
      pack();
      
       next.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                k++;
                if(k>=ContactIdx) k=0;   
                     contactInfo();
            }
        });
       
       search.addActionListener(new ActionListener() 
       {
           @Override
           public void actionPerformed(ActionEvent e) {
               String whatToSearch = search.getText();
                k = SearchMethodContacts(whatToSearch);
                if(k==-1) System.out.println("Not Found");
                else contactInfo();
           }
       });
      
       
        prev.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                k--;
                if(k<0) k=ContactIdx-1;   
                contactInfo();
            }
        });
      
      setVisible(true);
    }
    
    public int SearchMethodContacts(String input)
    {	
        int m=0;
        while(m<ContactIdx)
        {
            if(input.equalsIgnoreCase(contacts[m].getName()))
            {
                return m;
            }
            m++;       
        }
        return -1;
    	
     }
    
     public int SearchMethodPolices(String input)
    {	
        int m=0;
        while(m<PoliceIdx)
        {
            if(input.equalsIgnoreCase(polices[m].getName()))
            {
                return m;
            }
            m++;
            
        }
        return -1;	
     }
    
       public int SearchMethodCriminals(String input)
    {	
    	int n=0;
    	while(n<CriminalIdx)
               {
                if(input.equalsIgnoreCase(criminals[n].getCriminalName()))
                {
                    return n;
                }
        	n++;
            }
               System.out.println(CriminalIdx);
    	return -1;
    	
     }
     
    public void  criminalArchiveHomePage()
    {
        addCriminal();
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back1.jpg")));
       
         JButton enter=new JButton("Enter");
        enter.setBounds(300,130,160,50);
        add(enter);
        
        final JTextField search=new JTextField("");
        search.setBounds(320,210,140,20);
        add(search);
        
         ImageIcon image=new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\search.jpg");
         JLabel label = new JLabel();
         label.setBounds(300, 210, 20, 20);
         label.setIcon(image);
         add(label);
        
        enter.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               criminalArchive();
            }
        });
        
         search.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
     
                 String whatToSearch = search.getText();
                i = SearchMethodCriminals(whatToSearch);
                if(i==-1) System.out.println("Not Found");
                else criminalArchive();
            }
        });
         
        setVisible(true);
    }
     
    public void policeArchiveHomePage()
    {
        addPolice();
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back3.jpg")));
       
         JButton enter=new JButton("Enter");
        enter.setBounds(300,130,160,50);
        add(enter);
        
        final JTextField search=new JTextField("");
        search.setBounds(320,210,140,20);
        add(search);
        
         ImageIcon image=new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\search.jpg");
          JLabel label = new JLabel();
         label.setBounds(300, 210, 20, 20);
         label.setIcon(image);
         add(label);
        
        enter.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               policeArchive();
            }
        });
        
         search.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String whatToSearch = search.getText(); 
                j = SearchMethodPolices(whatToSearch);
                if(j==-1) System.out.println("Not Found");
                else policeArchive();
            }
        });
         
        setVisible(true);
    }
    
   public void criminalArchive() 
   {   
       setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back1.jpg")));
       setLayout(new FlowLayout());
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(new Color(0,0,0,125));
      panel.setPreferredSize(new Dimension(650,480));
       
      
      JTextArea text=new JTextArea(); 
      text.setBounds(50,50,370,350);
      text.setLayout( new BorderLayout());
      text.setEditable(false);
      
      Font font = new Font("Verdana", Font.BOLD, 14);
      text.setFont(font);
      text.setForeground(Color.white);    
      text.setBackground(new Color(0,0,0,0));
      text.setLineWrap(true);
     // text.setWrapStyleWord(true);
      Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
      text.setBorder(border);
      
      text.setText("\n                    "+criminals[i].getCriminalName()
      +"\n\n\n Birth Day:\t\t"+criminals[i].getCriminalBirthday()+
              "\n Birth Place:\t\t"+criminals[i].getCriminalBornPlace()+
              "\n Physical Description:\t"+criminals[i].getCriminalPhysicalDescrip()+
              "\n Caught Date:\t\t"+criminals[i].getCaughtDate()+
              "\n Caught Place:\t"+criminals[i].getCaughtPlace()+
              "\n Caught Reason:\t"+criminals[i].getCaughtReason()+
              "\n Previous Criminal Records:\t"+criminals[i].getCriminalRecords());  

      panel.add(text);
          
      
      String str = "C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\criminal\\"+criminals[i].getCriminalName()+".jpg";
       JLabel label = new JLabel();   
      ImageIcon image=new ImageIcon(str);
         label.setBounds(470, 20, 200, 200);
         label.setIcon(image); 
         panel.add(label);
         
      ImageIcon nullImage=new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\criminal\\image.jpg");
        JLabel label2 = new JLabel();  
         label2.setBounds(470, 20, 200, 200);
         label2.setIcon(nullImage); 
         panel.add(label2); 
          
         ImageIcon nextIcon = new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\next.jpg");
         ImageIcon prevIcon = new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\prev.jpg");
 
         JButton next=new JButton(nextIcon);
       next.setBounds(530,410,40,40);
        panel.add(next);
        
        JButton prev=new JButton(prevIcon);
        prev.setBounds(30,410,40,40);
        panel.add(prev);

        
      add(panel);
      pack();
       
       next.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                i++;
                if(i>=CriminalIdx) i=0;   
                     criminalArchive();
            }
        });
       
        prev.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                i--;
                if(i<0) i=CriminalIdx-1;   
                criminalArchive();
            }
        });

   }
   
    public void policeArchive()
    {
      setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back3.jpg")));
      setLayout(new FlowLayout());
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(new Color(0,0,0,125));
      panel.setPreferredSize(new Dimension(650,480));
       
      
      JTextArea text=new JTextArea(); 
      text.setBounds(50,50,370,350);
      text.setLayout( new BorderLayout());
      text.setEditable(false);
      Font font = new Font("Verdana", Font.BOLD, 14);
      text.setFont(font);
      text.setForeground(Color.white);    
         text.setLineWrap(true);
      text.setBackground(new Color(0,0,0,0));
      Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
      text.setBorder(border);
      
      text.setText("\n                    "+polices[j].getName()
      +"\n\n\n Rank:\t\t"+polices[j].getRank()+
              "\n Birth Day:\t\t"+polices[j].getBirthday()+
              "\n Birth Place:\t\t"+polices[j].getBornPlace()+
              "\n Physical Description:\t"+polices[j].getPhysicalDescrip()+
              "\n Join Date:\t\t"+polices[j].getJoinDate()+
              "\n Current Thana:\t"+polices[j].getJoinDateInCurrentThana()+
              "\n Previous Postings:\t"+polices[j].getPreviousPostings()+
              "\n Handled Cases:\t"+polices[j].getHandledCases()+
              "\n Current Case:\t"+polices[j].getCurrentCase()+
              "\n Contact Number:\t"+polices[j].getContactNumber());
               
      panel.add(text);
            
      String str = "C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\police\\"+polices[j].getName()+".jpg";
         ImageIcon image=new ImageIcon(str);
         JLabel label = new JLabel();
         label.setBounds(470, 20, 220, 200);
         label.setIcon(image); 
         panel.add(label);
         
            ImageIcon nullImage=new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\criminal\\image.jpg");
        JLabel label2 = new JLabel();  
         label2.setBounds(470, 20, 200, 200);
         label2.setIcon(nullImage); 
         panel.add(label2); 
    
               ImageIcon nextIcon = new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\next.jpg");
         ImageIcon prevIcon = new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\button\\prev.jpg");
 
         JButton next=new JButton(nextIcon);
       next.setBounds(530,410,40,40);
        panel.add(next);
        
        JButton prev=new JButton(prevIcon);
        prev.setBounds(30,410,40,40);
        panel.add(prev);
        
      add(panel);
      pack();
       
       next.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                j++;
                if(j>=PoliceIdx) j=0;   
                     policeArchive();
            }
        });
       
        prev.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                j--;
                if(j<0) j=PoliceIdx-1;   
                policeArchive();
            }
        });
    }
    
    public void homePage()
    {
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back2.jpg")));   
        setVisible(true);
    }
    
   public void loginPage()
   {    
         setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back5.jpg")));   
          final JPanel panel = new JPanel(); 
          panel.setLayout(null);
          panel.setBackground(new Color(0,0,0,0));
          panel.setBounds(50,50,700,600);
         
          String s = null;
          
          JTextArea text= new JTextArea("");
          JPasswordField pass=new JPasswordField(s);
          JButton login=new JButton("Log in");
          JButton enter=new JButton("Enter Crime System");
         
          JLabel id = new JLabel("User ID:");
          id.setFont(new Font("Serif", Font.BOLD, 13));
          
          JLabel p=new JLabel("Password:");
          p.setFont(new Font("Serif", Font.BOLD, 13));
          
          id.setBounds(580,200,60,20);
          p.setBounds(580,230,60,20);
          add(id);
          add(p);
          
          text.setBounds(600, 150, 100, 20);
          pass.setBounds(600, 180, 100, 20);
          login.setBounds(600,210,70,20);
          enter.setBounds(250, 200, 150, 40);
          
          Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
          text.setBorder(border);
          pass.setBorder(border);
         
           panel.add(text);
           panel.add(pass);
           panel.add(login);
           panel.add(enter);
           add(panel);
           
           setVisible(true);
           
         enter.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                panel.setVisible(false);
                homePage();
                MenuClient();          
            }
        });
 
   }
   
    public void  addCriminal()
    {
            FileReader file=null;
       try
       {
           
           file=new FileReader("C:\\Users\\masum\\Desktop\\Crime System\\server\\Archive Info\\criminal.txt");
           BufferedReader fin=new BufferedReader(file);
             
         String test="";
        while ((test=fin.readLine()) != null)
        {
            addCriminal ob=new addCriminal();         
            ob.setCriminalName(test);
            ob.setCaughtDate(fin.readLine());
            ob.setCaughtReason(fin.readLine());
            ob.setCriminalBirthday(fin.readLine());
            ob.setCriminalBornPlace(fin.readLine());
            ob.setCriminalPhysicalDescrip(fin.readLine());
            ob.setCriminalRecords(fin.readLine());  
            ob.setCaughtPlace(fin.readLine());
            
            criminals[CriminalIdx]=ob;
            CriminalIdx++;
            
        }
        file.close();
       }
       
       catch(Exception e)
       {
           
       }
    }
    
    public void addPolice()
    {
         FileReader file=null;
       try
       {
           
           file=new FileReader("C:\\Users\\masum\\Desktop\\Crime System\\server\\Archive Info\\police.txt");
           BufferedReader fin=new BufferedReader(file);
             
         String test="";
        while ((test=fin.readLine()) != null)
        {
            addPolice ob=new addPolice();         
            ob.setName(test);
            ob.setRank(fin.readLine());
            ob.setBirthday(fin.readLine());
            ob.setBornPlace(fin.readLine());
            ob.setPhysicalDescrip(fin.readLine());
            ob.setJoinDate(fin.readLine());  
            ob.setJoinDateInCurrentThana(fin.readLine());
            ob.setPreviousPostings(fin.readLine());
            ob.setHandledCases(fin.readLine());
            ob.setCurrentCase(fin.readLine());
            ob.setContactNumber(fin.readLine());
            
            polices[PoliceIdx]=ob;
            PoliceIdx++;
        }
        file.close();
       }
       
       catch(Exception e)
       {
           
       }
    }
    
    public void addContact()
    {
        
       FileReader file=null;
       try
       {
           
           file=new FileReader("C:\\Users\\masum\\Desktop\\Crime System\\server\\Archive Info\\contact.txt");
           BufferedReader fin=new BufferedReader(file);
             
         String test="";
        while ((test=fin.readLine()) != null)
        {
            addContact ob=new addContact();         
            ob.setName(test);
            ob.setNumber(fin.readLine());
            
            contacts[ContactIdx]=ob;
            ContactIdx++;
        }
        file.close();
       }
       
       catch(Exception e)
       {
           
       }
    }
    
    public void thanaChoice()
    {
        
    }
        
}