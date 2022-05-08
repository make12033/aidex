package com.aidex.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lx
 * @since 2022-03-31
 */
@ApiModel(value = "Song对象", description = "")
@Data
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String fileAddr;

    private String discAddr;

    private String songName;

    private String songSinger;

    private Integer songStatus;

    private String discName;

    private String songDisc;

//    private Integer creatby;

    @TableField("createTime")
    private String createTime;

//    private String remark;
//
//    private Integer songStyle;
//
//    private Integer songSubStyle;
//
//    private String songStyle1;
//
//    private String songSubStyle1;
//
//    private String songFeel;
//
//    private String songAuthorization;
//
//    private String songSolo;
//
//    private String songLanguage;
//
//    private String songLfsI;
//
//    private String songVoidc;
//
//    private String songNation;
//
//    private String songMelody;
//
//    private Integer songSpeed;
//
//    private String songComplex;
//
//    private Integer songEnergy;
//
//    private String songTonality;
//
//    private String songRms;
//
//    private String songCrest;
//
//    private BigDecimal songLra;

    private String fileName;

//    private String songWriter;

//    private String maker;
//
//    private String texture;
//
//    private String simpleSense;
//
//    private String special;
//
//    private String songYear;
//
//    private String songSplit;
//
//    private Integer interestedId;
//
//    private String copyRightId;

    @ApiModelProperty("曲作者")
    private String composeName;

    @ApiModelProperty("词作者")
    private String lyricistName;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")    // 返回格式
    private LocalDate publishTime;

    @ApiModelProperty("时长")
    private LocalTime fileDuration;

    @ApiModelProperty("歌词地址")
    private String lyricUrl;

    @ApiModelProperty("权重")
    private Float weight;



//    @ApiModelProperty("小场景1")
//    private Integer subScene1;

    @ApiModelProperty("歌手id")
    private Integer scene1;

    @ApiModelProperty("专辑id")
    private Integer scene2;

//    @ApiModelProperty("发行时间")
//    private String ca;

//    @ApiModelProperty("小场景2")
//    private Integer subScene2;
//
//    @ApiModelProperty("rid")
//    private Integer scene3;
//
//    @ApiModelProperty("小场景3")
//    private Integer subScene3;
//
//    @ApiModelProperty("推荐度")
//    private Integer recommendations;


    private Integer atSongId;

    @ApiModelProperty("1.mbm2.爱听")
    private Integer sourceType;

    private Integer copyType;

    private String exclusivity;

    private String allRate;

    private Integer hot;

    private Integer artistId;

    private String contentProvider;

    private String area;

    private String price;

    private String genre;

    private LocalDateTime modifydate;

    private Double dbfs;

//    @ApiModelProperty("爱听v2唯一id")
//    private String atV2SongId;

//    @ApiModelProperty("专辑名称")
//    private String albumName;


}
