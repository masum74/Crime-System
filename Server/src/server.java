import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class server extends JFrame
{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket myserver;
    private Socket connection;
    
    //constructor
    
    public server()
    {
        userText=new JTextField();
        userText.setEditable(false);
        userText.addActionListener
        (
                new ActionListener()
                {
                     // press enter after typing a message. this'l wrk
                    public void actionPerformed(ActionEvent event)
                    {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                        
                    }
                            
                }
        );
        
        setGUI();
        
    }
    
    public void setGUI()
    {
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
    }
    
    //set up and run the server 
    public void startRunning()
    {
        try
        {
            myserver = new ServerSocket(1234,100); //port number(whre is this app) & backlog(how many people can excess it)
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
                    closeCrap();//closing everything
                }
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    //wait for connection, then display connection information
    private void waitForConnection() throws IOException
    {
        //showMessage("No Client! please wait...\n");
        connection = myserver.accept(); //connection created..for infinity loop it happens over n over
        JOptionPane.showMessageDialog(null, "A Client wants to Chat");
        setVisible(true);
        //showMessage("Now connected to "+connection.getInetAddress().getHostName());//returns othrs pc address and my ip address
    }
    
    //get stream to send and recieve data
    private void setupStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream()); //output messages
        output.flush(); //if some data left .. u shd flush and get it out
        
        input =new ObjectInputStream(connection.getInputStream());
        //showMessage("\n Streams are now setup \r");         
    }
    
    //during the chatting conversation
    private void whileChatting() throws IOException
    {
        String message = "You are now connected";
        sendMessage(message);
        ableToType(true); //let the user type now
        
        do
        {
            //have a conversation
            try
            {
                message = (String) input.readObject(); //input is the socket where the things are coming.. we save those texts in message variable
                showMessage("\n"+message); //show the message
            }
            catch(ClassNotFoundException classNotFoundException)
            {
                showMessage("\n i dun knw what user send!");//if the user doesn't send string objct this will shw
            }
        }while(!message.equals("Client-END"));// if the user types END the programs stop     
    }
    
    //close streams and sockets after you are done chatting
    private void closeCrap()
    {
        showMessage("\n Client Logged Out\n");
        ableToType(false);//stp the abality to type;
        try
        {
            output.close(); //streams close to them
            input.close(); //streams close from them
            connection.close();//socket close or the main connection betn the pc's
            
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    //send a message to pc that is connected to server
    private void sendMessage(String message) //this is diffrnt from show message
    {
        try
        {
            output.writeObject("Officer: "+message);//sends a message to output stream.. build in java writeObject
            output.flush();
            showMessage("\nOfficer: "+message);
        }
        catch(IOException ioException)//if for some reason it is unable to send message
        {
            chatWindow.append("\n Error: Dude I cant Send That");
        }
    }
    
    //update or work with chatWindow
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
                      chatWindow.append(text);//append that text at the chat window
                  }
              }
        );
    }
    
    //work with userText object.. let the user type into their box
    private void ableToType(final boolean tof)
    {
        SwingUtilities.invokeLater 
        (   
              //lets create a thred
              new Runnable()
              {
                  public void run()
                  {
                      userText.setEditable(tof);
                  }
              }
        );
    }
    
}