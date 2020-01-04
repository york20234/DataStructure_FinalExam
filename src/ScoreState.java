import javax.swing.*;
import java.awt.*;

public class ScoreState extends JPanel {
    private JLabel level =new JLabel();
    //名次
    private JLabel name=new JLabel();
//    private JTextField inputName =new JTextField();
    //名字
    private JLabel score =new JLabel();
    //分數

    String data_name;//名字
    int data_score; //分數
    int count; //記錄第幾筆資料

    public ScoreState(String tmp_name,int sc,int counter) {
        data_name=tmp_name;
        data_score=sc;
        count=counter;
        init();
    }   //放名字跟分數
    private void init(){
        this.setLayout(new GridLayout(1,3));

        level.setText(Integer.toString(count));
        score.setText(Integer.toString(data_score));
        name.setText(data_name);

        this.add(level);
        this.add(name);
        this.add(score);


    }
    public void setName(String newName){
        name.setText(newName);
    }

    public void setScore(int newScore){
        score.setText(Integer.toString(newScore));
    }

    public String getName(){
        return name.getText();
    }

    public int getScore(){
        return Integer.parseInt(score.getText());
    }


}
