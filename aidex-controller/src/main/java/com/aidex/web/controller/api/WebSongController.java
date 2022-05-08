package com.aidex.web.controller.api;

import com.aidex.common.core.domain.R;
import com.aidex.common.utils.StringUtils;
import com.aidex.web.entity.KuWoArtist;
import com.aidex.web.entity.KuwoAlbumAndSong;
import com.aidex.web.entity.vo.KuwoAlbumAndSongVo;
import com.aidex.web.entity.vo.KuwoVo;
import com.aidex.web.util.KuWoSongUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "曲库搜索相关接口")
@Controller
@RequestMapping("/claireApi/webSong")
public class WebSongController {

    @Autowired
    KuWoSongUtil kuWoSongUtil;

    @GetMapping("/getWebSong")
//    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @ApiOperation(value="搜索歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songName", value = "输入词",dataType = "String",defaultValue = "告白气球"),
            @ApiImplicitParam(name = "pageNum", value = "页码，不传默认1",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "获取条数，不传默认5",dataType = "Integer",defaultValue = "5"),
            @ApiImplicitParam(name = "brSound", value = "音质：128，192，320    不传默认128",defaultValue = "128")
    }
    )
    @ApiOperationSupport(order = 1)
    @ResponseBody
    public R<KuwoVo> getSongByName(Integer pageNum, Integer pageSize, String songName, String brSound){
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=5;
        }
        if(StringUtils.isEmpty(brSound)){
            brSound="128";
        }
        if(StringUtils.isEmpty(songName)){
            songName="周杰伦";
        }


        R r = kuWoSongUtil.searchWebSongByKey(pageNum, pageSize, songName, brSound);
        return r;
    }


    //根据专辑获取列表
    @GetMapping("/getWebSongByAlbum")
    @ResponseBody
    @ApiOperation(value="根据专辑id获取歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "专辑id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码，不传默认1",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "获取条数，不传默认5",dataType = "Integer",defaultValue = "5"),
            @ApiImplicitParam(name = "brSound", value = "音质：128，192，320    不传默认128",defaultValue = "128")
    }
    )
    @ApiOperationSupport(order = 2)
    public R<KuwoAlbumAndSong> getWebAlbum(Integer pageNum, Integer pageSize, String brSound, Integer albumId){
        if(albumId==null){
            return   R.fail("专辑id为空！");
        }
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
    @GetMapping("/getWebArtistInfo")
    @ResponseBody
    @ApiOperation(value="根据歌手id获取歌手信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "artistid", value = "歌手id",dataType = "Integer",required = true)
    }
    )
    @ApiOperationSupport(order = 3)
    public R<KuWoArtist> getArtistInfo(Integer artistid){
        if(artistid==null){
            return   R.fail("歌手id为空！");
        }


        R r = kuWoSongUtil.searchArtistInfo(artistid);
        return r;
    }

    //根据歌手获取歌曲列表
    @GetMapping("/getWebSongByArtist")
    @ResponseBody
    @ApiOperation(value="根据歌手id获取歌曲列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "artistid", value = "歌手id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码，不传默认1",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "获取条数，不传默认5",dataType = "Integer",defaultValue = "5"),
            @ApiImplicitParam(name = "brSound", value = "音质：128，192，320    不传默认128",defaultValue = "128")
    }
    )
    @ApiOperationSupport(order = 4)
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
    @GetMapping("/getWebLrcByRid")
    @ResponseBody
    @ApiOperation(value="根据歌曲id获取歌词信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rid", value = "歌曲id",dataType = "Integer",required = true)
    }
    )
    @ApiOperationSupport(order = 5)
    public R getLrcByRid(Integer rid){
        if(rid==null){
            return R.fail("歌曲id为空！");
        }


        R r = kuWoSongUtil.searchLrc(rid);
        return r;
    }


    //根据歌手获取专辑列表
    @GetMapping("/getWebAlbumByArtist")
    @ResponseBody
    @ApiOperation(value="根据歌手id获取专辑列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "artistid", value = "歌手id",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码，不传默认1",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "获取条数，不传默认5",dataType = "Integer",defaultValue = "5")
    }
    )
    @ApiOperationSupport(order = 6)
    public R<KuwoAlbumAndSongVo> pageAlbumByArtist(Integer pageNum, Integer pageSize, Integer artistid){
        if(artistid==null){
            return R.fail("歌手id为空！");
        }
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=5;
        }


        R r = kuWoSongUtil.searchAlbumByArtist(pageNum, pageSize, artistid);
        return r;
    }
}
