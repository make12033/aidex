package com.aidex.web.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class KuWoArtist implements Serializable {

    @ApiModelProperty(value = "专辑数")
    private Integer albumNum;  //专辑数
    @ApiModelProperty(value = "歌手粉丝数")
    private Integer artistFans;  //歌手粉丝数
    @ApiModelProperty(value = "生日")
    private String birthday;  //生日
    @ApiModelProperty(value = "出生地")
    private String birthplace; //出生地
    @ApiModelProperty(value = "星座")
    private String constellation;  //星座
    @ApiModelProperty(value = "性别")
    private String gener;  //性别
    @ApiModelProperty(value = "歌手id")
    private Integer id;   //歌手id
    @ApiModelProperty(value = "个人简介")
    private String info;  //个人简介
    @ApiModelProperty(value = "语言")
    private String language;  //语言
    @ApiModelProperty(value = "歌曲数量")
    private Integer musicNum;  //歌曲数量
    @ApiModelProperty(value = "姓名")
    private String name; //姓名
    @ApiModelProperty(value = "歌手封面")
    private String pic;  //歌手封面链接
    @ApiModelProperty(value = "歌手封面")
    private String pic70;  //歌手封面链接
    @ApiModelProperty(value = "歌手封面")
    private String pic120;  //歌手封面链接
    @ApiModelProperty(value = "歌手封面")
    private String pic300;  //歌手封面链接
    @ApiModelProperty(value = "身高")
    private  String tall;  //身高
    @ApiModelProperty(value = "体重")
    private String weight; //体重
    @ApiModelProperty(value = "国籍")
    private String country;  //国籍

}
