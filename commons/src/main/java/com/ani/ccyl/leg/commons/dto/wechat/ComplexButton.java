package com.ani.ccyl.leg.commons.dto.wechat;

/**
 * Created by lihui on 17-12-3.
 */
public class ComplexButton extends Button {  
    private Button[] sub_button;  
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}  
