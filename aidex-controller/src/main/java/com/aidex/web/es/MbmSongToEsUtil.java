//package com.aidex.web.es;
//
//
//import com.aidex.common.enums.DataSourceType;
//import com.aidex.framework.datasource.DynamicDataSourceContextHolder;
//import com.aidex.web.entity.Song;
//import com.aidex.web.es.entity.MbmSong;
//import com.aidex.web.es.repository.MbmSongRepository;
//import com.aidex.web.service.ISongService;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class MbmSongToEsUtil {
//
//    @Autowired
//    private ISongService iSongService;
//
//    @Autowired
//    private MbmSongRepository mbmSongRepository;
//
//
//    //项目启动时执行一次
//    @PostConstruct
//    public void execute() {
//
//        long songCount = mbmSongRepository.count();
//
////        理论上数据大于几十万条证明没问题，不需要同步
//        if(songCount>900000){
//            return;
//        }
//
////        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE.name());
//        List<Song> songList = iSongService.getSong(3,1);
//
//        List<MbmSong> mbmSongList = new ArrayList<>();
//        for (Song song : songList) {
//            MbmSong mbmSong = new MbmSong();
//            mbmSong.setId(song.getId().longValue());
//            mbmSong.setFileAddr(song.getFileAddr());
//            mbmSong.setDiscAddr(song.getDiscAddr());
//            mbmSong.setSongName(song.getSongName());
//            mbmSong.setSongSinger(song.getSongSinger());
//            if(StringUtils.isNotBlank(song.getDiscName())&& !song.getDiscName().equals("0")){
//                mbmSong.setDiscName(song.getDiscName());
//            }
//            mbmSong.setLyricUrl(song.getLyricUrl());
//            mbmSong.setSourceType(song.getSourceType());
//
//            mbmSong.setFileDuration("00:00");
//            mbmSong.setFileDurationDigital(0);
//
//            if(song.getFileDuration()!=null){
//                mbmSong.setFileDuration(song.getFileDuration().toString());
//                mbmSong.setFileDurationDigital(song.getFileDuration().getHour()*3600
//                        +song.getFileDuration().getMinute()*60+song.getFileDuration().getSecond());
//            }
//
//            mbmSong.setPublishTime(song.getPublishTime()==null ? null : song.getPublishTime().toString());
//
//            mbmSong.setAlbumid(song.getScene2());
//            mbmSong.setArtistid(song.getScene1());
//
//
//            mbmSongList.add(mbmSong);
//
//            //避免内存过载，一次只执行2w条
//            if(mbmSongList.size()>=20000){
//                mbmSongRepository.saveAll(mbmSongList);
//                mbmSongList=new ArrayList<>();
//            }
//
//
//        }
//
//        if(mbmSongList.size()>0){
//            mbmSongRepository.saveAll(mbmSongList);
//        }
//
//        System.out.println("aaaa");
//    }
//}
