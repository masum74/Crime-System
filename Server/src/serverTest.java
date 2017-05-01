public class serverTest implements Runnable
{
    
  public void runServer()

  {
    Thread t;
    t = new Thread(this, "Demo Thread");
    t.start(); 
  }

  public void run() 
  {
     
    server ob=new server();
    ob.setSize(400,500);
    ob.setResizable(false);
    ob. setTitle("Crime System");
    ob. setVisible(false);
    ob.startRunning();
         
  }
}
