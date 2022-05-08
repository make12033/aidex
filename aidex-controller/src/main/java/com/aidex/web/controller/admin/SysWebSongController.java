package com.aidex.web.controller.admin;

import com.aidex.common.core.domain.R;
import com.aidex.common.utils.StringUtils;
import com.aidex.web.entity.KuWoArtist;
import com.aidex.web.entity.KuwoAlbumAndSong;
import com.aidex.web.entity.vo.KuwoVo;
import com.aidex.web.util.KuWoSongUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Api(tags = "kuwo相关接口")
@Controller
@RequestMapping("/sysWebSong")
public class SysWebSongController {

    @Autowired
    KuWoSongUtil kuWoSongUtil;

    @GetMapping("/page")
    @ResponseBody
    public R<KuwoVo> getSongByName(Integer pageNum, Integer pageSize, String song_name, String brSound){
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=5;
        }
        if(StringUtils.isEmpty(brSound)){
            brSound="128";
        }
        if(StringUtils.isEmpty(song_name)){
            song_name="周杰伦";
        }


        R r = kuWoSongUtil.searchWebSongByKey(pageNum, pageSize, song_name, brSound);
        return r;
    }



    //根据专辑获取列表
    @GetMapping("/pageAlbum")
    @ResponseBody
    public R<KuwoAlbumAndSong> pageAlbum(Integer pageNum, Integer pageSize, String brSound, Integer albumId){
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=5;
        }

        if(StringUtils.isEmpty(brSound)){
            brSound="128";
        }



        R r = kuWoSongUtil.searchWebSongByAlbum(pageNum, pageSize,brSound, albumId);
        return r;
    }


    //获取歌手详细资料
    @GetMapping("/getArtistInfo")
    @ResponseBody
    public R<KuWoArtist> getArtistInfo(Integer artistid){
        if(artistid==null){
         return   R.fail("歌手id为空！");
        }




        R r = kuWoSongUtil.searchArtistInfo(artistid);
        return r;
    }

    //根据歌手获取歌曲列表
    @GetMapping("/pageSongByArtist")
    @ResponseBody
    public R<KuwoVo> pageSongByArtist(Integer pageNum, Integer pageSize, String brSound,Integer artistid){
        if(artistid==null){
           return R.fail("歌手id为空！");
        }
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=5;
        }
        if(StringUtils.isEmpty(brSound)){
            brSound="128";
        }



        R r = kuWoSongUtil.searchWebSongByArtist(pageNum, pageSize,brSound, artistid);
        return r;
    }


    //根据歌曲获取歌词信息
    @GetMapping("/getLrcByRid")
    @ResponseBody
    public R getLrcByRid(Integer rid){
        if(rid==null){
           return R.fail("歌曲id为空！");
        }


        R r = kuWoSongUtil.searchLrc(rid);
        return r;
    }


    //根据歌手获取专辑列表
    @GetMapping("/pageAlbumByArtist")
    @ResponseBody
    public R pageAlbumByArtist(Integer pageNum, Integer pageSize, Integer artistid){
        if(artistid==null){
            R.fail("歌手id为空！");
        }
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=5;
        }




        R r = kuWoSongUtil.searchAlbumByArtist(pageNum, pageSize, artistid);
        return r;
    }
}
