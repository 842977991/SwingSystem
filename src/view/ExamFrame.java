package view;

import domain.Question;
import service.QuestionService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ExamFrame extends BaseFrame {
    //获取对象
    private QuestionService questionService = MySpring.getBean("service.QuestionService");
    //生成试卷
    private ArrayList<Question> paper = questionService.getPaper(10);
    //存储答案
    private String[] answers = new String[paper.size()];

    //创建几个变量 控制右侧
    private int nowNum = 0;//记录当前题号
    private int totalCount = paper.size();//记录题目总数
    private int answerCount = 0;//记录已答
    private int unanswerCount = totalCount;//记录未答
        //记录时间
    private int time = 95;
    private TimeControlThread timeControlThread =new TimeControlThread();
    //-------------------------------
    public ExamFrame(){
        this.init();
    }
    public ExamFrame(String title){
        super(title);
        this.init();
    }

    //添加区域分割的面板
    private JPanel mainPanel = new JPanel();//答题区域
    private JPanel messagePanel = new JPanel();//右边区域
    private JPanel buttonPanel = new JPanel();//按钮区域
    //添加按钮组件
    private JButton aButton = new JButton("A");
    private JButton bButton = new JButton("B");
    private JButton cButton = new JButton("C");
    private JButton dButton = new JButton("D");
    private JButton prevButton = new JButton("上一题");
    private JButton nextButton = new JButton("下一题");
    private JButton submitButton = new JButton("提交试卷");
    //添加答题区域组件
    private JTextArea examArea = new JTextArea();//考试文本域 展示题目
    private JScrollPane scrollPane = new JScrollPane(examArea);//滚动条
    //添加右侧组件
    private JLabel pictureLabel = new JLabel();//图片
    private JLabel nowNumLabel = new JLabel("当前题号:");//当前题号
    private JLabel totalCountLabel = new JLabel("题目总数:");//题目总数
    private JLabel answerCountLabel = new JLabel("已答题数:");//已答题数
    private JLabel unanswerCountLabel = new JLabel("剩余题数:");//剩余题数
    private JTextField nowNumField = new JTextField();////当前题号
    private JTextField totalCountField = new JTextField();//题目总数
    private JTextField answerCountField = new JTextField();//已答题数
    private JTextField unanswerCountField = new JTextField();//剩余题数
    private JLabel timeLabel = new JLabel("剩余时间");//剩余时间
    private JLabel realTimeLabel = new JLabel();//剩余时间

    //设置每一个组件的位置 大小 字体 布局等等
    @Override
    protected void setFontAndSoOn() {
        //答题布局
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        //右边布局
        messagePanel.setLayout(null);
        messagePanel.setBounds(700,20,280,550);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //按钮布局
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(20,400,650,150);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        scrollPane.setBounds(20,10,650,380);
        examArea.setFont(new Font("黑体",Font.BOLD,34));

        //设置每一个组件的位置、大小、布局
        pictureLabel.setBounds(10,10,250,200);
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //设置当前题号
        nowNumLabel.setBounds(10,270,100,30);
        nowNumLabel.setFont(new Font("黑体",Font.PLAIN,20));
        nowNumField.setBounds(150,270,100,30);
        nowNumField.setFont(new Font("黑体",Font.BOLD,20));
        nowNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nowNumField.setEnabled(false);
        nowNumField.setHorizontalAlignment(JTextField.CENTER);

        //设置题目总数
        totalCountLabel.setBounds(10,310,100,30);
        totalCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        totalCountField.setBounds(150,310,100,30);
        totalCountField.setFont(new Font("黑体",Font.BOLD,20));
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalCountField.setEnabled(false);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);

        //设置已答题数
        answerCountLabel.setBounds(10,350,100,30);
        answerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        answerCountField.setBounds(150,350,100,30);
        answerCountField.setFont(new Font("黑体",Font.BOLD,20));
        answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        answerCountField.setEnabled(false);
        answerCountField.setHorizontalAlignment(JTextField.CENTER);

        //设置剩余题目
        unanswerCountLabel.setBounds(10,390,100,30);
        unanswerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        unanswerCountField.setBounds(150,390,100,30);
        unanswerCountField.setFont(new Font("黑体",Font.BOLD,20));
        unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        unanswerCountField.setEnabled(false);
        unanswerCountField.setHorizontalAlignment(JTextField.CENTER);

        //剩余时间
        timeLabel.setBounds(10,430,100,30);
        timeLabel.setFont(new Font("黑体",Font.PLAIN,20));
        timeLabel.setForeground(Color.BLUE);
        realTimeLabel.setBounds(150,430,100,30);
        realTimeLabel.setFont(new Font("黑体",Font.BOLD,20));
        realTimeLabel.setForeground(Color.BLUE);

        aButton.setBounds(40,10,120,30);
        aButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bButton.setBounds(190,10,120,30);
        bButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cButton.setBounds(340,10,120,30);
        cButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dButton.setBounds(490,10,120,30);
        dButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        prevButton.setBounds(40,50,100,30);
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setBounds(510,50,100,30);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(276,50,100,30);
        submitButton.setForeground(Color.RED);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //展示第一道题目
        this.showQuestionAndPicture();
        //
        nowNumField.setText(nowNum+1+"");
        totalCountField.setText(totalCount+"");
        answerCountField.setText(answerCount+"");
        unanswerCountField.setText(unanswerCount+"");
        realTimeLabel.setText(time+"");
    }
    //添加组件
    @Override
    protected void addElement() {
        messagePanel.add(pictureLabel);
        messagePanel.add(nowNumLabel);
        messagePanel.add(nowNumField);
        messagePanel.add(totalCountLabel);
        messagePanel.add(totalCountField);
        messagePanel.add(answerCountLabel);
        messagePanel.add(answerCountField);
        messagePanel.add(unanswerCountField);
        messagePanel.add(unanswerCountLabel);
        messagePanel.add(timeLabel);
        messagePanel.add(realTimeLabel);

        buttonPanel.add(aButton);
        buttonPanel.add(bButton);
        buttonPanel.add(cButton);
        buttonPanel.add(dButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        mainPanel.add(scrollPane);
        mainPanel.add(messagePanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
    }


    //设置监听器
    @Override
    protected void addListener() {
        //用于提交
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //显示一个确认框
                int value = JOptionPane.showConfirmDialog(ExamFrame.this,"是否确认提交");
                if(value==0){
                    //1.时间停止
                    timeControlThread.setFlag(false);//切换线程状态
                    //2.所有按钮失效
                    ExamFrame.this.setOptionButtonEnable(false);
                    prevButton.setEnabled(false);
                    nextButton.setEnabled(false);
                    //3.计算成绩
                    float score = ExamFrame.this.checkPaper();
                    examArea.setText("考试结束,您的成绩为："+score);
                    realTimeLabel.setForeground(Color.red);
                }

            }
        });

        //创建一个监听器，用于上一个题
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExamFrame.this.clearOptionButtonColor();//清除颜色
                ExamFrame.this.setOptionButtonEnable(true);//设置所有按钮为可用
                //设置下一题按钮 变成可用
                nextButton.setEnabled(true);
                nowNum--;
                //如果当前题号为0,上一题按钮禁用
                if(nowNum==0){
                    prevButton.setEnabled(false);
                }
                ExamFrame.this.restoreButton();
                ExamFrame.this.showQuestionAndPicture();
                nowNumField.setText(nowNum+1+"");
                answerCountField.setText(--answerCount+"");
                unanswerCountField.setText(++unanswerCount+"");
            }
        });

        //创建一个监听器，用于下一个题
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExamFrame.this.clearOptionButtonColor();
                nowNum++;//题目序号加一
                if(nowNum==totalCount){
                    examArea.setText("题目已经回答完毕，请提交试卷");
                    //同时使下一题按钮失效
                    nextButton.setEnabled(false);
                    //使全部按钮失效
                    ExamFrame.this.setOptionButtonEnable(false);
                }else {
                    ExamFrame.this.showQuestionAndPicture();
                    nowNumField.setText(nowNum+1+"");
                    prevButton.setEnabled(true);
                }
                answerCountField.setText(++answerCount+"");
                unanswerCountField.setText(--unanswerCount+"");
            }
        });
        //创建一个监听器，用来实现按键功能
        ActionListener optionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //清除之前所有按钮的颜色
                 ExamFrame.this.clearOptionButtonColor();
            //获取是哪个按钮按下
                JButton button = (JButton) e.getSource();
            //按下后按钮变色
                 button.setBackground(Color.gray);
            //将当前按钮的属性存储下
                answers[nowNum] =  button.getText();
            }
        };
        //将监听器绑定至按钮上
        aButton.addActionListener(optionListener);
        bButton.addActionListener(optionListener);
        cButton.addActionListener(optionListener);
        dButton.addActionListener(optionListener);
    }
    //设置窗体的大小显示...
    @Override
    protected void setFrameSelf() {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(260,130,1000,600);//大小
        timeControlThread.start();
    }

    //设计一个内部类 处理时间
    private class TimeControlThread extends Thread{
        private boolean flag = true;
        public void setFlag(boolean flag){
                this.flag = flag;
        }
        public void run(){
            //time转换
            int hour = time/60;
            int minute = time%60;
            int second = 0;
            while (flag){
                //将时间进行拼串
                StringBuilder stringBuilder = new StringBuilder();
                //处理时
                if(hour>= 0 &  hour <10){
                    stringBuilder.append("0");
                }
                stringBuilder.append(hour);
                stringBuilder.append(":");
                //处理分
                if(minute>= 0 &  minute <10){
                    stringBuilder.append("0");
                }
                stringBuilder.append(minute);
                stringBuilder.append(":");
                //处理秒
                if(second>= 0 &  second<10){
                    stringBuilder.append("0");
                }
                stringBuilder.append(second);
                realTimeLabel.setText(stringBuilder.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //时间开始变换
                if(second>0){
                    second--;
                }else {
                    if(minute>0){
                        minute--;
                        second=59;
                    }else {
                        if(hour>0){
                            hour--;
                            minute=59;
                            second=59;
                        }else {
                            System.out.println("考试结束,请提交试卷");
                            realTimeLabel.setForeground(Color.red);//时间变为红色
                            ExamFrame.this.setOptionButtonEnable(false);
                            prevButton.setEnabled(false);
                            nextButton.setEnabled(false);
                            JOptionPane.showMessageDialog(ExamFrame.this,"考试结束，请提交试卷");
                            break;
                        }
                    }
                }
            }
        }
    }
    //计算成绩
    private float checkPaper(){
        float score = 0;
        for(int i = 0 ; i < paper.size();i++){//paper 是一个ArrayList集合
            Question questionPaper = paper.get(i);
            String realAnswer = questionPaper.getAnswer();
            if(realAnswer.equals(answers[i])){
                score+=(100/paper.size());
            }
        }
        return score;
    }

    //设计一个方法，负责还原上一题的选项
    private void restoreButton(){
        //获取当前题目的答案(学生选择的那个)
        String answer = answers[nowNum];
        if(answer==null){
            return ;
        }
        switch(answer){
            case "A":
                aButton.setBackground(Color.gray);
                break;
            case "B":
                bButton.setBackground(Color.gray);
                break;
            case "C":
                cButton.setBackground(Color.gray);
                break;
            case "D":
                dButton.setBackground(Color.gray);
                break;
            default:
                this.clearOptionButtonColor();
                break;
        }
    }
    //设计一个方法，使所有按钮失效
    private void setOptionButtonEnable(boolean key){
        aButton.setEnabled(key);
        bButton.setEnabled(key);
        cButton.setEnabled(key);
        dButton.setEnabled(key);
    }
    //设计一个方法，清除之前所有按钮
    private void clearOptionButtonColor(){
        aButton.setBackground(null);
        bButton.setBackground(null);
        cButton.setBackground(null);
        dButton.setBackground(null);
    }

    //设计一个方法，用来展示图片
    private ImageIcon drawImage(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        //设置Imageicon中的Image属性
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT));
        return imageIcon;
    }
    //----------------用来展示一道题目
    private void showQuestionAndPicture(){
        Question question = paper.get(nowNum);
        String picture = question.getPicture();//获取路径
        if(picture!=null){
            pictureLabel.setIcon(this.drawImage("src//img//"+picture));
        }else {
            pictureLabel.setIcon(null);
        }
        examArea.setText((nowNum+1)+"."+question.getTitle().replace("<br>","\n    "));
    }
}
