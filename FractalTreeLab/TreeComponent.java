import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.Line2D;

public class TreeComponent extends JPanel
{
   private final int PANEL_WIDTH = 400;
   private final int PANEL_HEIGHT = 400;

   private final double branchingAngle = Math.toRadians(20);
   private final double fractionLength = .75;
   private final double smallestBranch = 5;

   private int current; //current order
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public TreeComponent (int currentOrder)
   {
      current = currentOrder;
      setBackground (Color.black);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   }
   
   //-----------------------------------------------------------------
   //  Draws the fractal recursively. Base case is an order of 1 for
   //  which a simple straight line is drawn. Otherwise two lines are
   //  drawn extending from the parent line at an angle.
   //-----------------------------------------------------------------
   public void drawBranch (int order, double length, int startX, int startY, double angle, Graphics g)
   {
       int endX1, endY1, endX2, endY2;
       
       if (order == 1)
       {
           g.drawLine(startX, startY, startX, startY - (int)length);
       }
       else
       {
           double angle1 = angle + branchingAngle;
           double angle2 = angle - branchingAngle;
           length*=fractionLength;
          
           endX1 = (int)(startX - length*Math.sin(angle1));
           endY1 = (int)(startY - length*Math.cos(angle1));
          
           endX2 = (int)(startX - length*Math.sin(angle2));
           endY2 = (int)(startY - length*Math.cos(angle2)); 
          
           if (length > smallestBranch)
           {
               g.drawLine((int)startX, (int)startY, endX1, endY1);
               g.drawLine((int)startX, (int)startY, endX2, endY2);
               drawBranch(order - 1, length, endX1, endY1, angle1, g);
               drawBranch(order - 1, length, endX2, endY2, angle2, g);
           }
       }
   }
   
   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawBranch method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics g)
   {
      super.paintComponent(g);
      
      g.setColor(Color.blue);

      drawBranch(current, 100, 200, 375, 0, g);
   }
   
   //-----------------------------------------------------------------
   //  Sets the fractal order to the value specified.
   //-----------------------------------------------------------------
   public void setOrder (int order)
   {
      current = order;
   }

   //-----------------------------------------------------------------
   //  Returns the current order.
   //-----------------------------------------------------------------
   public int getOrder ()
   {
      return current;
   }
}