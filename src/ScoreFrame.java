import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class ScoreFrame extends JFrame {
    private Container cp;
    //容器
    private JLabel jlbName =new JLabel("請輸入名稱:");
    private JTextField jtxName =new JTextField();
    //名稱區
    private JLabel jlbScore =new JLabel("請輸入分數:");
    private JTextField jtxScore =new JTextField();

    private JButton jbtnSend =new JButton("送出");
    //上方輸入區
    private JPanel jplInput =new JPanel(new GridLayout(1,5));
    //放置輸入區
    private ArrayList<ScoreState> scoreStates =new ArrayList<ScoreState>();
    //資料儲存
    private Random rd=new Random();
    //預設分數
    private JPanel jplScore =new JPanel(new GridLayout(10,1));
    private JScrollPane jsc =new JScrollPane(jplScore);
    //放置分數
    int count=0;

    public ScoreFrame(){
        init();
    }

    private void init(){
        this.setBounds(30,30,500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        //基本設定
        cp.add(jplInput,BorderLayout.NORTH);
        cp.add(jplScore);
        //jpl放置
        jplInput.add(jlbName);
        jplInput.add(jtxName);
        jplInput.add(jlbScore);
        jplInput.add(jtxScore);
        jplInput.add(jbtnSend);
        //上方物件

        for (int i=0;i<10;i++){
            count++;
            String tmpName="default";
            ScoreState tmpScore =new ScoreState(tmpName,rd.nextInt(99999999),i+1);
            scoreStates.add(tmpScore);
            scoreStates= sort(scoreStates);
        }//新增資料並排序

        reflash(scoreStates);

        jbtnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtxName.getText().equals("")||jtxScore.getText().equals("")){
                    JOptionPane.showMessageDialog(ScoreFrame.this,"請輸入數值");
                }
                else {
                  try {
                      count++;
                      ScoreState tmpScore =new ScoreState(jtxName.getText(),Integer.parseInt(jtxScore.getText()),count);
                      scoreStates.add(tmpScore);
                      scoreStates= sort(scoreStates);
                      reflash(scoreStates);
                  }
                  catch (NumberFormatException e1){
                      JOptionPane.showMessageDialog(ScoreFrame.this,"請不要亂輸入好嗎?");
                  }
                }
            }
        });


    }
    private ArrayList<ScoreState> sort(ArrayList<ScoreState> sc){
        for (int i=0;i<sc.size()-1;i++){
            for (int j=i+1;j<sc.size();j++){
                if (sc.get(i).getScore()<sc.get(j).getScore()){
                    String tmpName =sc.get(i).getName();
                    int tmp =sc.get(i).getScore();
                    //取得站存資料
                    sc.get(i).setName(sc.get(j).getName());
                    sc.get(i).setScore(sc.get(j).getScore());
                    //前面更改
                    sc.get(j).setName(tmpName);
                    sc.get(j).setScore(tmp);
                }
            }
        }
        return sc;
    }//氣泡排序

    private void reflash(ArrayList<ScoreState>sc){
        jplScore.removeAll();
        jplScore.setLayout(new GridLayout(sc.size(),1));
        for (int i=0;i<sc.size();i++){
            jplScore.add(sc.get(i));
        }
        jplScore.revalidate();

    }

}
