package com.aidex.quartz.task;

import org.springframework.stereotype.Component;

@Component("mbmSongTask")
public class MbmSongTask {

    public void getBandSong()
    {
        System.out.println("执行无参方法");
    }
}
