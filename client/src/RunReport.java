public class RunReport implements Runnable
{
    
  public void runReport()

  {
    Thread t;
    t = new Thread(this, "Demo Thread");
    t.start(); 
  }

  public void run() 
  {
     
    ReportCrime ob=new ReportCrime("127.0.0.1");
    ob.setSize(790,560);
    ob.setResizable(false);
    ob. setTitle("Crime System");
    ob. setVisible(true);
    ob.showGUI();
    ob.startRunning();
    //ob.setDefaultCloseOperation(EXIT_ON_CLOSE);

         
  }
}
