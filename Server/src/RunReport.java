
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
     
    ReportCrime ob=new ReportCrime();
    ob.setSize(790,560);
    ob.setResizable(false);
    ob. setTitle("Crime System");
    ob. setVisible(false);
    ob.showGUI();
    ob.startRunning();
     
  }
}
