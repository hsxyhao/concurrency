package com.yiren.example.publis;

import com.yiren.annoations.NotThreadSafe;

import java.util.Arrays;

/**
 * @author wanghao
 * create 2018-04-10 14:32
 **/
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        System.out.println(Arrays.toString(unsafePublish.getStates()));
    }

    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }
}
