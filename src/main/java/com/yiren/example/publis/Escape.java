package com.yiren.example.publis;

import com.yiren.annoations.NotRecommend;
import com.yiren.annoations.NotThreadSafe;

/**
 * @author wanghao
 * create 2018-04-10 14:37
 **/
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape;

    public Escape() {

    }

    private class InnerClass {
        public InnerClass(){
            System.out.println(Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
        new Escape();
    }

}
