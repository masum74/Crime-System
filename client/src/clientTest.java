import javax.swing.JFrame;

public class clientTest implements Runnable
{
    
   
  public void runClient()
  {
      Thread t; 
      t = new Thread(this, "Demo Thread");
    t.start(); 
  }
  public void run() 
  {
     
     Client max;
     max = new Client("127.0.0.1");//means ip address of loacal host.. in the computer i am right now..testing purposes
     max.startRunning();
      
  }
}
