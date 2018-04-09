package game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Main extends JFrame implements MouseListener{
      private int screenwide;
      private int screenhigh;
      private int colNum;
      private int rowNum;
      private int mineNum;
      private int restNum;
      private int min = 0;//分钟
      private int sec = 0;//秒钟
      private JLabel label1;//显示时间用
      private JLabel label2;//显示剩余雷数
      private JButton button;//笑脸按钮，用于重新开始
      private String gameState = "run";//游戏运行状态
      private boolean isfirst = true;//判断是否是第一次按下
      //难度选择菜单
      private JMenuBar menuBar;
      private JMenu menu;
      private JMenuItem low;
      private JMenuItem mid;
      private JMenuItem high;
      //核心模块
      private ArrayList<Bub>list = new ArrayList<Bub>(0);
      public boolean isFirst() {
          return isfirst;
      }
      public void initMenu() {
          menuBar = new JMenuBar();
          menu = new JMenu("难度设置");
          low = new JMenuItem("初级(10)");
          mid = new JMenuItem("中级(40)");
          high = new JMenuItem("高级(99)");
          low.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new Main(10);
              }
          });
          mid.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new Main(40);
              }
          });
          high.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new Main(99);
              }
          });
          menu.add(low);
          menu.add(mid);
          menu.add(high);
          menuBar.add(menu);
          setJMenuBar(menuBar);
      }
      class stopwatch extends Thread{
          public void run() {
              while(gameState.equals("run")) {
                  try {
                      Thread.sleep(1000);
                  }catch(InterruptedException e) {
                      e.printStackTrace();
                  }
                  sec++;
                  if(sec==60) {
                      sec=0;
                      min++;
                  }
                  if(min==0&&sec<10)
                	  label1.setText("00:0"+new Integer(sec).toString());
                  if(min==0&&sec>=10)
                	  label1.setText("00:"+new Integer(sec).toString());
                  if(min>0&&sec<10)
                	  label1.setText(new Integer(min).toString()+":0"+new Integer(sec).toString());
                  if(min>0&&sec>=10)
                	  label1.setText(new Integer(min).toString()+":"+new Integer(sec).toString());
              }
          }
      }
      public Main(int num) {
          super("扫雷");
          mineNum = num;
          restNum = mineNum;
          if(mineNum == 10) {
              screenwide = 350;
              screenhigh = 430;
              colNum = 9;
              rowNum = 9;
          }
          if(mineNum == 40) {
              screenwide = 594;
              screenhigh = 675;
              colNum = 16;
              rowNum = 16;
          }
          if(mineNum == 99) {
              screenwide = 1084;
              screenhigh = 675;
              colNum = 30;
              rowNum = 16;
          }
          initMenu();
          setSize(screenwide,screenhigh);
          setLayout(null);
          setLocationRelativeTo(null);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          JPanel p = new JPanel();
          p.setLayout(new GridLayout(rowNum,colNum,0,0));
          for(int i = 0;i<rowNum*colNum;++i) {
              Bub bub = new Bub();
              bub.showImg(13);
              bub.addMouseListener(this);
              list.add(bub);
              p.add(bub);
          }
          p.setBounds(8,40,colNum*35,rowNum*35);
          add(p);
          button = new JButton(new ImageIcon("smile1.png"));
          button.setBounds(screenwide/2-20, 5, 35, 35);
          button.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  new Main(mineNum);
              }
          });
          add(button);
          label1 = new JLabel("时间");
          label1.setBounds(10,5,60,20);
          add(label1);
          
          label2 = new JLabel("剩余: "+restNum);
          label2.setBounds(screenwide-70,5,60,20);
          add(label2);
          this.setVisible(true);
    }
    public static void main(String[]args) {
        new Main(10);
    }
    class Result extends JFrame {
        /**
         * 
         */
        private static final long serialVersionUID = 7899366376251467187L;

        public Result(String con) {
            super("游戏结果");
            setSize(250,150);
            setLocationRelativeTo(null);
            JButton button = new JButton("确定");
            setLayout(null);
            JLabel label3 = new JLabel();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            label3.setFont(new Font("微软雅黑",Font.BOLD,15));
            if(con.equals("win")) label3.setText("用时"+min+"分"+sec+"秒");
            if(con.equals("lose"))label3.setText("继续努力!");
            label3.setBounds(70, 5, 100, 40);
            add(label3);
            button.setBounds(80, 50, 70, 40);
            add(button);
            setVisible(true);
        }
    }
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO 自动生成的方法存根
        
    }
    public boolean judge(int index) { 
        if(index>=0&&index<list.size()) {
            if(list.get(index).getReal()==1)
            	return true;  //扫到雷了
        }
        return false;
    }
    public boolean judgeLimit(int index) {
        if(index>=0&&index<list.size()) 
        	return true;
        return false;
    }
    public void show(int index) {  //对当前点的判断
        Bub tmp1 = list.get(index);//当前点击位置的在链表中的索引
        int cnt = 0;                //四周雷数的记录值
        boolean edgeR = (index%colNum!= 0);     //判断是否在右边界
        boolean edgeL = ((index+1)%colNum!= 0);   //判断是否在左边界
        if(judge(index-colNum))
        	tmp1.setFace(++cnt);
        if(judge(index+colNum))
        	tmp1.setFace(++cnt);
        if(judge(index-1)&&edgeR)
        	tmp1.setFace(++cnt);
        if(judge(index+1)&&edgeL)
        	tmp1.setFace(++cnt);
        if(judge(index-colNum+1)&&edgeL)
        	tmp1.setFace(++cnt);
        if(judge(index+colNum+1)&&edgeL)
        	tmp1.setFace(++cnt);
        if(judge(index-colNum-1)&&edgeR)
        	tmp1.setFace(++cnt);
        if(judge(index+colNum-1)&&edgeR)
        	tmp1.setFace(++cnt);
        if(cnt==0) {               //判断空白是否向外展开
            tmp1.setFace(0);
            tmp1.showImg(0);
            if(!judge(index-colNum)&&judgeLimit(index-colNum)&&!list.get(index-colNum).getOpen())
            	show(index-colNum);//为了避免这里的递归陷入了死循环，要对相邻的按钮进行判断，如果已经处于翻开状态，就不用在进一步操作
            if(!judge(index+colNum)&&judgeLimit(index+colNum)&&!list.get(index+colNum).getOpen())
            	show(index+colNum);
            if(!judge(index-1)&&edgeR&&judgeLimit(index-1)&&!list.get(index-1).getOpen())
            	show(index-1);
            if(!judge(index+1)&&edgeL&&judgeLimit(index+1)&&!list.get(index+1).getOpen())
            	show(index+1);
            if(!judge(index-colNum+1)&&edgeL&&judgeLimit(index-colNum+1)&&!list.get(index-colNum+1).getOpen())
            	show(index-colNum+1);
            if(!judge(index+colNum+1)&&edgeL&&judgeLimit(index+colNum+1)&&!list.get(index+colNum+1).getOpen())
            	show(index+colNum+1);
            if(!judge(index-colNum-1)&&edgeR&&judgeLimit(index-colNum-1)&&!list.get(index-colNum-1).getOpen())
            	show(index-colNum-1);
            if(!judge(index+colNum-1)&&edgeR&&judgeLimit(index+colNum-1)&&!list.get(index+colNum-1).getOpen())
            	show(index+colNum-1);
        }
        else tmp1.showImg(cnt);    
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        Bub tmp = (Bub)e.getSource();
        int index = list.indexOf(tmp);
        if(this.gameState.equals("over"))
        	return;
        else {
            if(e.getButton()==MouseEvent.BUTTON1) {  //左键
                if(tmp.getFace()!=11&&tmp.getFace()!=12) {
                    if(isfirst) {      //第一次按下同时随机设置雷的分布
                        tmp.setReal(1);
                        Random r = new Random();
                        int len = list.size();
                        for(int i = 1;i<=mineNum;++i) {
                            while(true) {
                                int des = r.nextInt(len);
                                if(list.get(des).getReal()!=1) {
                                    list.get(des).setReal(1);
                                    list.get(des).showImg(13);
                                    break;
                                }
                            }
                        }
                        tmp.setReal(0);
                        isfirst = false;
                        show(index);
                        new stopwatch().start();
                    }
                    else if(tmp.getReal()!=1){    //幸运，不是雷
                        show(index);
                    }
                    else if(tmp.getReal()==1) {   //扫到雷咯
                        for(Bub bub:list) {       //展示全图
                                if(bub.getReal()==1)
                                	bub.showImg(9);         
                                if(bub.getReal()==0&&bub.getFace()==11) {
                                    bub.setFace(10);
                                    bub.showImg(10);
                                }
                            }
                        tmp.showImg(14);
                        gameState = "over";
                        button.setIcon(new ImageIcon("smile3.png"));
                        new Result("lose");
                        
                    }
                }
            }
            if(e.getButton()==MouseEvent.BUTTON3) {  //右键
                if(tmp.getFace()==13) {
                    tmp.setFace(11);
                    tmp.showImg(11);
                    restNum--;
                }
                else if(tmp.getFace()==11) {
                    tmp.setFace(12);
                    tmp.showImg(12);
                    restNum++;
                }
                else if(tmp.getFace()==12) {
                    tmp.setFace(13);
                    tmp.showImg(13);
                }
            }
            if(restNum==0) {
                int total = 0;
                for(Bub bub1:list) {
                    if(bub1.getFace()==11&&bub1.getReal()==1)
                    	total++;
                }
                if(total==mineNum) {
                    gameState = "over";
                    button.setIcon(new ImageIcon("smile2.png"));
                    new Result("win");
                }
            }
            label2.setText("剩余:"+restNum);
        }
    }
}