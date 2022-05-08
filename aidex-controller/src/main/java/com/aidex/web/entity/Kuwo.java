package com.aidex.web.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Kuwo implements Serializable {

    @ApiModelProperty(value = "专辑名称")
    private String album;   //专辑名称
    @ApiModelProperty(value = "歌手名称")
    private String artist;   //歌手名称
    @ApiModelProperty(value = "持续时长(秒)")
    private Integer duration;   //持续时长(秒)
//    @ApiModelProperty(value = "数据列表")
    private boolean hasLossless;   //无损音质 false无，true有
//    @ApiModelProperty(value = "数据列表")
    private String lrcUrl;   //歌词链接
    @ApiModelProperty(value = "歌曲名称")
    private String name;   //歌曲名称
    @ApiModelProperty(value = "图片")
    private String pic;   //图片
    @ApiModelProperty(value = "小尺寸图片")
    private String pic120;   //小尺寸图片
    @ApiModelProperty(value = "发布时间")
    private String releaseDate;   //发布时间
    @ApiModelProperty(value = "歌曲的唯一标识")
    private Integer rid;   //歌曲的唯一标识
    @ApiModelProperty(value = "持续时长")
    private String songTimeMinutes;   //持续时长
    @ApiModelProperty(value = "歌曲链接")
    private String songUrl;   //歌曲链接
    @ApiModelProperty(value = "专辑id")
    private Integer albumid;//专辑id
    @ApiModelProperty(value = "歌手id")
    private Integer artistid; //歌手id


}
