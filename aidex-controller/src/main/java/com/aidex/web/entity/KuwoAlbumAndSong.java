package com.aidex.web.entity;


import com.aidex.web.entity.Kuwo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class KuwoAlbumAndSong implements Serializable {

    @ApiModelProperty(value = "专辑名称")
    private String album;
    @ApiModelProperty(value = "专辑id")
    private Integer albumid;
    @ApiModelProperty(value = "专辑简介")
    private String albuminfo;
    @ApiModelProperty(value = "歌手")
    private String artist;
    @ApiModelProperty(value = "歌手id")
    private Integer artistid;
    @ApiModelProperty(value = "语种")
    private String lang;
    @ApiModelProperty(value = "专辑图片")
    private String pic;
    @ApiModelProperty(value = "发行时间")
    private String releaseDate;
    @ApiModelProperty(value = "数据列表")
    private List<Kuwo> list=new ArrayList<>();
    @ApiModelProperty(value = "总条数")
    private Integer total;
}
