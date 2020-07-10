package view;

import service.UserService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends BaseFrame {
    public LoginFrame(){
        this.init();
    }
    public LoginFrame(String title){
        super(title);
        this.init();
    }

    //面板
    private JPanel panel = new JPanel();
    //标题
    private JLabel titleLabel = new JLabel("考 试 管 理 系 统");
    private JLabel userLabel = new JLabel("账 号");
    private JLabel passLabel = new JLabel("密 码");
    //输入框
    private JPasswordField passText = new JPasswordField(20);
    private JTextField userText = new JTextField(20);
    //按钮
    private JButton buttonLogin = new JButton("登 录");
    private JButton buttonExit = new JButton("退 出");

    //设置每一个组件的位置 大小 字体 布局等等
    protected void setFontAndSoOn(){
        //设置布局为自定义布局
        panel.setLayout(null);
        //设置每一个组件的位置
        titleLabel.setBounds(150,25,500,40);
        titleLabel.setFont(new Font("黑体",Font.BOLD,26));
        userLabel.setBounds(50,100,100,30);
        userLabel.setFont(new Font("黑体",Font.BOLD,20));
        passLabel.setBounds(50,150,100,30);
        passLabel.setFont(new Font("黑体",Font.BOLD,20));
        userText.setBounds(150,100,300,30);
        userText.setFont(new Font("黑体",Font.BOLD,20));
        passText.setBounds(150,150,300,30);
        passText.setFont(new Font("黑体",Font.BOLD,20));
        buttonLogin.setBounds(120,200,100,30);
        buttonLogin.setFont(new Font("黑体",Font.BOLD,20));
        buttonExit.setBounds(320,200,100,30);
        buttonExit.setFont(new Font("黑体",Font.BOLD,20));
    }
    //将所有组件添加至窗体上
    protected void addElement(){
        //添加组件
        panel.add(titleLabel);
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(userText);
        panel.add(passText);
        panel.add(buttonExit);
        panel.add(buttonLogin);
        this.add(panel);
    }

    //设置窗体的显示、大小、关闭
    protected void setFrameSelf(){
        this.setBounds(500,250,550,300);//大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//显示
        this.setResizable(false);//是否可以改变大小

    }
    //设置监听器
    protected void addListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = userText.getText();
                String password = new String(passText.getPassword());
                UserService login = MySpring.getBean("service.UserService");
                String result = login.LoginText(account, password);
                if(result.equals("登录成功")){
                    //弹出新窗口
                    LoginFrame.this.setVisible(false);//将登录窗口隐藏
                    //弹出新的考试界面
                    new ExamFrame(account+"的考试页面");
                }else{
                    //弹出一个警告框 告诉登录失败啦
                    JOptionPane.showMessageDialog(LoginFrame.this,result);
                    //设置文本框和密码框的值为空
                    passText.setText("");
                    userText.setText("");
                }
            }
        };
        buttonLogin.addActionListener(actionListener);
    }
}
