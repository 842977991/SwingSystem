package util;

import javax.swing.*;


public abstract class BaseFrame extends JFrame {
    public BaseFrame(){}
    public BaseFrame(String title){
        super(title);
    }

    protected void init(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    //1.设置 字体 颜色 背景 布局等等
    protected abstract void setFontAndSoOn();
    //2.将属性添加到窗体里
    protected abstract void addElement();
    //3.添加事件监听
    protected abstract void addListener();
    //4.设置窗体自身
    protected abstract void setFrameSelf();
}
