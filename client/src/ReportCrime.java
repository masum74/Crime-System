import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class ReportCrime extends JFrame
{
    
    final JTextField nameText=new JTextField("");
    final JTextField DOBText=new JTextField("");
    final JTextField voterIdText=new JTextField("");
    final JTextField AddressText=new JTextField("");
    final JTextField phone1Text=new JTextField("");
    final JTextField phone2Text=new JTextField("");
    final JTextArea ReasonText=new JTextArea("");
    final JTextField timeText=new JTextField("");
    final JTextField placeText=new JTextField("");
    final JTextArea othersText=new JTextArea("N/A");
    
    
     private ObjectOutputStream output;
     private Socket connection;
     private String serverIP;
     
     public ReportCrime(String host)
     {
         serverIP=host;
     }
 
    public void showGUI()
    {
      //setContentPane(new JLabel(new ImageIcon("C:\\Users\\masum\\Desktop\\Crime System\\server\\image\\background\\back3.jpg")));
       
      //setLayout(new FlowLayout());
      final JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(new Color(0,10,0,125));
      panel.setPreferredSize(new Dimension(720,520));
      
      
      
    JLabel name = new JLabel("Name:");  
    JLabel DOB=new JLabel("Date of Birth:");
    JLabel VoterID=new JLabel("Voter ID:");
    JLabel Address=new JLabel("Address:");
    JLabel Phone1=new JLabel("Phone 1:");
    JLabel Phone2=new JLabel("Phone 2:");
    JLabel Reason=new JLabel("Reason:");
    JLabel time=new JLabel("Time of occurrence:");
    JLabel place=new JLabel("Address of occurrence:");
    JLabel others=new JLabel("Others:");
   
          
     name.setBounds(20,20,110,20);
     DOB.setBounds(20,50,110,20);
     VoterID.setBounds(20,80,110,20);
     Address.setBounds(20,110,110,20);
     Phone1.setBounds(20,140,110,20);
     Phone2.setBounds(20,170,110,20);
     time.setBounds(20,200,150,20);
     place.setBounds(20,230,150,20);
     Reason.setBounds(20,260,110,20);
     others.setBounds(20,390,110,20);
     
     
     nameText.setBounds(160,20,400,20);
     DOBText.setBounds(160,50,400,20);
     voterIdText.setBounds(160,80,400,20);
     AddressText.setBounds(160,110,400,20);
     phone1Text.setBounds(160,140,400,20);
     phone2Text.setBounds(160,170,400,20);
     timeText.setBounds(160,200,400,20);
     placeText.setBounds(160,230,400,20);
     ReasonText.setBounds(160,260,400,120);
     othersText.setBounds(160,390,400,120);
     
     JButton ContactWith=new JButton("Chat with an Officer");
     JButton submit=new JButton("Submit");
     
     ContactWith.setBounds(620,100,150,60);
     submit.setBounds(620,450,80,40);
     

     
     panel.add(name);
     panel.add(DOB);
     panel.add(VoterID);
     panel.add(Address);
     panel.add(Phone1);
     panel.add(Phone2);
     panel.add(Reason);
     panel.add(time);
     panel.add(place);
     panel.add(others);
     
     
     panel.add(nameText);
     panel.add(DOBText);
     panel.add(voterIdText);
     panel.add(AddressText);
     panel.add(phone1Text);
     panel.add(phone2Text);
     panel.add(ReasonText);
     panel.add(timeText);
     panel.add(placeText);
     panel.add(othersText);
     
     panel.add(ContactWith);
     panel.add(submit);
             
     add(panel);
     
     setVisible(true);
     
     ContactWith.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               setVisible(false);
               clientTest ob=new clientTest();
               ob.runClient();
            }
        });
     
     submit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if(checkIfNull()==1)
                {
                    String sendIt;
                    sendIt=nameText.getText();
                    sendMessage(sendIt);
                    sendIt=DOBText.getText();
                    sendMessage(sendIt);
                    sendIt=voterIdText.getText();
                    sendMessage(sendIt);
                    sendIt=AddressText.getText();
                    sendMessage(sendIt);
                    sendIt=phone1Text.getText();
                    sendMessage(sendIt);
                    sendIt=phone2Text.getText();
                    sendMessage(sendIt);
                    sendIt=timeText.getText();
                    sendMessage(sendIt);
                    sendIt=placeText.getText();
                    sendMessage(sendIt);
                    sendIt=ReasonText.getText();
                    sendMessage(sendIt);
                    sendIt=othersText.getText();
                    sendMessage(sendIt);
                    
                    JOptionPane.showMessageDialog(null, "Report Submitted");
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All Boxes");
                }
            }
        });
 
    }
    
    private int checkIfNull()
    {
        if(nameText.getText().equalsIgnoreCase("")) return 0;
        else if(DOBText.getText().equalsIgnoreCase("")) return 0;
        else if(voterIdText.getText().equalsIgnoreCase("")) return 0;
        else if(AddressText.getText().equalsIgnoreCase("")) return 0;
        else if(phone1Text.getText().equalsIgnoreCase("")) return 0;
        else if(phone2Text.getText().equalsIgnoreCase("")) return 0;
        else if(timeText.getText().equalsIgnoreCase("")) return 0;
        else if(placeText.getText().equalsIgnoreCase("")) return 0;
        else if(ReasonText.getText().equalsIgnoreCase("")) return 0;
        else if(othersText.getText().equalsIgnoreCase("")) return 0;
        return 1;
    }
    
    private void sendMessage(String message) //this is diffrnt from show message
    {
        try
        {
            output.writeObject(message);//sends a message to output stream.. build in java writeObject
            output.flush();
        }
        
        catch(IOException ioException)//if for some reason it is unable to send message
        {
           
        }
    }
    
    public void startRunning()
    {
        try
        {
            connectToServer();//connect to one specific server
            setupStreams();
            //whileChatting();

        }
        catch(EOFException eofException)
        {
            
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            
        }
    }
        
    private void connectToServer() throws IOException
    {
       
        //now we need the ip address to connect to a specific server and a port to connect to a specific app
        connection= new Socket(InetAddress.getByName(serverIP),1234);
        
    }
    
      private void setupStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

    }
        
        
}
