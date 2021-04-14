/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ticket;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.FontMetrics;

/**
 *
 * @author mic
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form bill_form
     */
    public Vista() {
        initComponents();
    }
  
    public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}






public class BillPrintable implements Printable {
    
   
    
    
  @Override
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
            ///////////////// Product names Get ///////////
                String  pn1a=pn1.getText();
                String pn2a=pn2.getText();
                String pn3a=pn3.getText();
                String pn4a=pn4.getText();
            ///////////////// Product names Get ///////////
                
            
            ///////////////// Product price Get ///////////
                double  pp1a=Integer.valueOf(pp1.getText());
                double  pp2a=Integer.valueOf(pp2.getText());
                double  pp3a=Integer.valueOf(pp3.getText());
                double  pp4a=Integer.valueOf(pp4.getText());
                double multiplica = pp1a*pp2a;
                double sum=pp3a+pp4a+multiplica;
                double iva = sum*21/100;;
                double total= sum+iva;
              
            ///////////////// Product price Get ///////////
                
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("         Copiadoras Costaluz         ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
            g2d.drawString("      EL CIF DE LA EMPRESA           ",10,y);y+=yShift;            
            g2d.drawString("      LA DIRECCION DE LA EMPRESA     ",10,y);y+=yShift;
            g2d.drawString("     NUMERO DE INFORME "+pn1a+"      ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cantidad  Concepto          Precio  ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            g2d.drawString(" "+pp1a+"......"+pn2a+".........."+pp2a+"",12,y);y+=yShift;
            g2d.drawString("                                     ",10,y);y+=yShift;
            g2d.drawString("                      "+pn3a+"         "+pp3a+"",10,y);y+=yShift;
            g2d.drawString("                      "+pn4a+"         "+pp4a+"",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" .................Importe : "+sum+"  ",10,y);y+=yShift;
            g2d.drawString("................. IVA     : "+iva+"  ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("                   Total : "+total+" ",10,y);y+=yShift;
            g2d.drawString("                                     ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("        GRACIAS POR SU VISITA        ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pn4 = new javax.swing.JTextField();
        pp4 = new javax.swing.JTextField();
        pn2 = new javax.swing.JTextField();
        pn3 = new javax.swing.JTextField();
        pn1 = new javax.swing.JTextField();
        pp1 = new javax.swing.JTextField();
        pp2 = new javax.swing.JTextField();
        pp3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Impresion de tickets");
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("IMPRESION DE TICKETS");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 240, 59));

        pn4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(pn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 283, 30));
        jPanel1.add(pp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 150, 30));
        jPanel1.add(pn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 283, 30));
        jPanel1.add(pn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 283, 30));

        pn1.setForeground(new java.awt.Color(51, 51, 51));
        pn1.setCaretColor(new java.awt.Color(51, 51, 51));
        jPanel1.add(pn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 160, -1));
        jPanel1.add(pp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 50, 30));
        jPanel1.add(pp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 150, 30));
        jPanel1.add(pp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 150, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("IMPRIMIR");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, 90, 40));

        jLabel8.setText("CANTIDAD");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLabel9.setText("ARTICULO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, -1));

        jLabel10.setText("PRECIO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, -1, -1));

        jLabel2.setText("NUMERO DE INFORME");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 146, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField pn1;
    private javax.swing.JTextField pn2;
    private javax.swing.JTextField pn3;
    private javax.swing.JTextField pn4;
    private javax.swing.JTextField pp1;
    private javax.swing.JTextField pp2;
    private javax.swing.JTextField pp3;
    private javax.swing.JTextField pp4;
    // End of variables declaration//GEN-END:variables
}
