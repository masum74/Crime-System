import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.*;
import java.net.*;
import javax.swing.SwingUtilities;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class ReportCrime extends JFrame
{
    int idx=0,flag=0;
    
    JTextArea nameText=new JTextArea("");
    JTextArea DOBText=new JTextArea("");
    JTextArea voterIdText=new JTextArea("");
    JTextArea AddressText=new JTextArea("");
    JTextArea phone1Text=new JTextArea("");
    JTextArea phone2Text=new JTextArea("");
    JTextArea ReasonText=new JTextArea("");
    JTextArea timeText=new JTextArea("");
    JTextArea placeText=new JTextArea("");
    JTextArea othersText=new JTextArea("");
    
    private ObjectInputStream input;
    private ServerSocket myserver;
    private Socket connection;
 
    public void showGUI()
    {
     
     nameText.setEditable(false);
     DOBText.setEditable(false);
     voterIdText.setEditable(false);
     AddressText.setEditable(false);
     phone1Text.setEditable(false);
     phone2Text.setEditable(false);
     ReasonText.setEditable(false);
     timeText.setEditable(false);
     placeText.setEditable(false);
     othersText.setEditable(false);
     
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
     
     JButton ContactWith=new JButton("Chat with a Client");
     JButton accept=new JButton("Accept");
     
     ContactWith.setBounds(620,100,150,60);
     accept.setBounds(620,450,80,40);
     

     
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
     
   
     panel.add(accept);
             
     add(panel);
     
           
     
     accept.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               acceptReport();
            }
        });
 
    }
    
    public void startRunning()
    {
        try
        {
            myserver = new ServerSocket(1111,100); //port number(whre is this app) & backlog(how many people can excess it)
            while(true)
            {
                try
                {
                    //connect and have conversation
                    waitForConnection();//wait for someone to connect;
                    setupStreams();//setup a coonection bet'n my pc and someones pc
                    whileChatting(); //send message back n forth
                    
                }
                catch(EOFException eofException)
                {
                    showMessage("\n Server ended the conncetion");//end of connection.. done running this program
                }
                finally
                {
                    
                }
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    private void waitForConnection() throws IOException
    {
        connection = myserver.accept();
        
    }
    
    private void setupStreams() throws IOException
    {
       input =new ObjectInputStream(connection.getInputStream()); 
    }
    
       private void showMessage(final String text)
    {
        //update part of the GUI (chat window)
        SwingUtilities.invokeLater 
        (   
              //lets create a thread
              new Runnable()
              {
                  public void run()
                  {
                      if(idx==0)
                      {
                          nameText.append(text);
                          idx++;
                      }
                      else if(idx==1)
                      {
                          DOBText.append(text);
                          idx++;
                      }
                      
                     else if(idx==2)
                      {
                          voterIdText.append(text);
                          idx++;
                      }
                      else if(idx==3)
                      {
                          AddressText.append(text);
                          idx++;
                      }
                      else if(idx==4)
                      {
                          phone1Text.append(text);
                          idx++;
                      }
                      else if(idx==5)
                      {
                          phone2Text.append(text);
                          idx++;
                      }
                      else if(idx==6)
                      {
                          timeText.append(text);
                          idx++;
                      }
                      else if(idx==7)
                      {
                          placeText.append(text);
                          idx++;
                      }
                      else if(idx==8)
                      {
                          ReasonText.append(text);
                          idx++;
                      }
                      
                      else if(idx==9)
                      {
                          othersText.append(text);
                          idx=0;
                      }
                  }
              }
        );
    }
       
    private void whileChatting() throws IOException
    {
        String message;
        do
        {
            //have a conversation
            try
            {
                message = (String) input.readObject(); //input is the socket where the things are coming.. we save those texts in message variable
                showMessage(message); //show the message
                if(!message.equals("")&&flag==0)
                {
                   JOptionPane.showMessageDialog(null, "A Client wants to file a Report");
                   setVisible(true);
                   flag=1;
                }
            }
            catch(ClassNotFoundException classNotFoundException)
            {
                showMessage("NULL");//if the user doesn't send string objct this will shw
            }
        }while(true);// if the user types END the programs stop     
    }
    
    
    public void acceptReport()
    {
        FileWriter file=null;
       try
       {
           
         file=new FileWriter("C:\\Users\\masum\\Desktop\\Crime System\\server\\diary.txt");
         BufferedWriter fout=new BufferedWriter(file);
             
         String s="check";
         fout.write( s);
        
         /*fout.write(nameText.getText());
         fout.write(DOBText.getText());
         fout.write(voterIdText.getText());
         fout.write(AddressText.getText());
         fout.write(phone1Text.getText());
         fout.write(phone2Text.getText());
         fout.write(timeText.getText());
         fout.write(placeText.getText());
         fout.write(ReasonText.getText());
         fout.write(othersText.getText());*/
        
        //file.close();
       }
       
       catch(Exception e)
       {
           
       }
       
       JOptionPane.showMessageDialog(null, "Report Accepted");
       setVisible(false);
       
     nameText.setText("");
     DOBText.setText("");
     voterIdText.setText("");
     AddressText.setText("");
     phone1Text.setText("");
     phone2Text.setText("");
     ReasonText.setText("");
     timeText.setText("");
     placeText.setText("");
     othersText.setText("");
       
    }
}
