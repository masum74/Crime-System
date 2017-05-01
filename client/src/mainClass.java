import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class mainClass 
{

    public static void main(String[] args) 
    {
       myGUI ob=new myGUI();           
         
         ob.setSize(790,560);
         ob.setResizable(false);
         ob. setTitle("Crime System");
         ob.loginPage();
         ob. setVisible(true);
         ob.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
}
