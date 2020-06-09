package com.main;

import javax.swing.*;

public class Tools {
    public static Object getFiledOrLable(int x, int y, int w, int h, String fieldName, String textName) {
        if (null != fieldName){
            if (fieldName.equals("JLabel")){
                JLabel jLabel =new JLabel();
                jLabel.setText(textName);
                jLabel.setBounds(x,y,w,h);
                return jLabel;
            }else if (fieldName.equals("JTextField")){
                JTextField jTextField = new JTextField();
                jTextField.setBounds(x,y,w,h);
                return jTextField;
            }else if (fieldName.equals("JPasswordField")){
                JPasswordField jPasswordField = new JPasswordField();
                jPasswordField.setBounds(x,y,w,h);
                return jPasswordField;
            }else if (fieldName.equals("JButton")){
                JButton jButton=new JButton();
                jButton.setText(textName);
                jButton.setBounds(x,y,w,h);
                return jButton;

            }
        }
        return null;
    }
}
