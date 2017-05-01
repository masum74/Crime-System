import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame
{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;//from pc to server
    private ObjectInputStream input;
    private String message= "";
    private String serverIP; //ip address if the server.whom um connecting woth
    private Socket connection;

    //constructor and gui unlike server class only i'm able to access it
    public Client(String host)
    {
        super("Client:");
        serverIP=host;
        userText=new JTextField();
        userText.setEditable(false);
        userText.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        
        setGui();
       

    }
    
    public void setGui()
    {
         add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow),BorderLayout.CENTER);
        setSize(500,500);
        setVisible(true);
    }

    //connect to server
    public void startRunning()
    {
        try
        {
            connectToServer();//connect to one specific server
            setupStreams();
            whileChatting();

        }
        catch(EOFException eofException)
        {
            showMessage("\n Client terminated connection");
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            closeCrap();
        }
    }


    //connect to a server
    private void connectToServer() throws IOException
    {
        //showMessage("Attempting Connection...\n");
        //now we need the ip address to connect to a specific server and a port to connect to a specific app
        connection= new Socket(InetAddress.getByName(serverIP),1234);
        //showMessage("Connected to: " + connection.getInetAddress().getHostName());
    }

    //setting up the streams to send and recieve messages
    private void setupStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        //showMessage("\n your streams are good to go!\n");

    }

    //while chatting with server little bit diffrnt from server class
    private void whileChatting() throws IOException
    {
        ableToType(true);
        do
        {
            try
            {
                message=(String) input.readObject();
                showMessage("\n"+message);
            }
            catch(ClassNotFoundException classNotfoundException)
            {
                showMessage("\n i dont know that object type");
            }


        }while(!message.equals("SERVER-END"));
    }

    //close the streams and sockets
    private void closeCrap()
    {
        showMessage("\n closing crap down..");
        ableToType(false);
        try
        {
            //close everything
            output.close();
            input.close();
            connection.close();
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    //send messages to server
    private void sendMessage(String message)
    {
        try
        {
            output.writeObject("Client: "+message);
            output.flush();
            showMessage("\nClient: "+ message);
        }

        catch(IOException ioException)
        {
            chatWindow.append("\n something messed up sending messages");
        }
    }

    //show the message.. update chat window(gui)
    private void showMessage(final String m)
    {
        SwingUtilities.invokeLater
        (
              new Runnable()
              {
                  public void run()
                  {
                      chatWindow.append(m);//append that text at the chat window(at end)
                  }
              }
        );
    }

    //gives user permission to type crap into the text box
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
