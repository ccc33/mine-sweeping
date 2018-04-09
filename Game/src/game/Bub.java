package game;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Bub extends JButton{
 
     private int face=13;       //表面上的图形显示
     private int real=0;    //有无雷，有雷为1，无雷为0
     private boolean isOpen = false;//表明当前按钮的状态，true表示已经翻开，false表示还未翻开
     private ImageIcon[]img = new ImageIcon[15];
     public Bub() {
         super();
         img[0] = new ImageIcon("0.png");
         img[1] = new ImageIcon("1.png");
         img[2] = new ImageIcon("2.png");
         img[3] = new ImageIcon("3.png");
         img[4] = new ImageIcon("4.png");
         img[5] = new ImageIcon("5.png");
         img[6] = new ImageIcon("6.png");
         img[7] = new ImageIcon("7.png");
         img[8] = new ImageIcon("8.png");
         img[9] = new ImageIcon("9.png");
         img[10] = new ImageIcon("10.png");
         img[11] = new ImageIcon("11.png");
         img[12] = new ImageIcon("12.png");
         img[13] = new ImageIcon("13.png");
         img[14] = new ImageIcon("boom.png");
     }
     public int getFace() {
         return face;
     }
     public int getReal() {
         return real;
     }
     public void setFace(int c) {
         face = c;
     }
     public void setReal(int c) {
         real = c;
     }
     public void setOpen(boolean swit) {
         isOpen = swit;
     }
     public boolean getOpen() {
         return isOpen;
     }
     public void showImg(int c) {
         this.setIcon(img[c]);
         if(c!=13)
        	 isOpen = true;
     }
}
