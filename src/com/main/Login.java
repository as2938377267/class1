package com.main;

import javax.swing.*;

public class Login extends JFrame
{
    public Login(){
        this.setSize(500,300);
        this.setTitle("登录");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initPanel();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);


        JLabel userName = (JLabel)Tools.getFiledOrLable(80,80,80,20,"JLabel","用户名:");
        jPanel.add(userName);

        JTextField usertxt = (JTextField)Tools.getFiledOrLable(170,80,80,20,"JTextField","") ;
        jPanel.add(usertxt);

        JLabel password = (JLabel)Tools.getFiledOrLable(80,120,80,20,"JLabel","密码:");
        jPanel.add(password);

        JPasswordField passwordtxt = (JPasswordField)Tools.getFiledOrLable(170,120,80,20,"JPasswordField","") ;
        jPanel.add(passwordtxt);

        JButton dl =(JButton)Tools.getFiledOrLable(80,190,80,20,"JButton","登录");
        jPanel.add(dl);

        JButton zc =(JButton) Tools.getFiledOrLable(200,190,80,20,"JButton","注册");
        jPanel.add(zc);

       this.add(jPanel);


    }
}
