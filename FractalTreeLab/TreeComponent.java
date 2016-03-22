import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.util.Random;

public class TreeComponent extends JPanel
{
   private final int PANEL_WIDTH = 400;
   private final int PANEL_HEIGHT = 400;

   private final double branchingAngle1 = Math.toRadians(25);
   private final double branchingAngle2 = Math.toRadians(10);
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
           int endY = startY - (int)length;
           g.drawLine(startX, startY, startX, endY);
           drawBranch(order + 1, length, startX, endY, angle, g);
       }
       else if (order <= current)
       {
           double angle1 = angle + branchingAngle1;
           double angle2 = angle - branchingAngle2;
           length*=fractionLength;
          
           endX1 = (int)(startX - length*Math.sin(angle1));
           endY1 = (int)(startY - length*Math.cos(angle1));
          
           endX2 = (int)(startX - length*Math.sin(angle2));
           endY2 = (int)(startY - length*Math.cos(angle2)); 

           g.drawLine((int)startX, (int)startY, endX1, endY1);
           g.drawLine((int)startX, (int)startY, endX2, endY2);
           drawBranch(order + 1, length, endX1, endY1, angle1, g);
           drawBranch(order + 1, length, endX2, endY2, angle2, g);
       }
   }
   
   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawBranch method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics g)
   {
      super.paintComponent(g);
      
      Random random = new Random();
      final float hue = random.nextFloat();
      // Saturation between 0.1 and 0.3
      final float saturation = (random.nextInt(2000) + 1000) / 10000f;
      final float luminance = 0.9f;
      final Color color = Color.getHSBColor(hue, saturation, luminance);
      g.setColor(color);

      drawBranch(1, 85, 250, 375, 0, g);
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