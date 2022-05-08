package com.aidex.web.es.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@ApiModel(value = "音乐列表", description = "")
@Document(indexName = "mbm_song2")
public class MbmSong {

    @Id
    @Field(type = FieldType.Keyword)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

//    @Field(type = FieldType.Keyword)
//    private Long rid;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    @ApiModelProperty("歌曲名称")
    private String songName;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    @ApiModelProperty("歌手")
    private String songSinger;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    @ApiModelProperty("专辑名称")
    private String discName;
//    @Field(type = FieldType.Integer)
//    private Integer songStatus;
//    @Field(type = FieldType.Text)
//    private String createTime;
    @Field(type = FieldType.Text)
    @ApiModelProperty("歌曲时长")
    private String fileDuration;
    @Field(type = FieldType.Integer)
    @ApiModelProperty("歌曲时长(秒)")
    private Integer fileDurationDigital;
    @Field(type = FieldType.Text)
    @ApiModelProperty("歌词链接")
    private String lyricUrl;

    @Field(type = FieldType.Integer)
    private Integer sourceType;

    @Field(type = FieldType.Text)
    @ApiModelProperty("歌曲链接")
    private String fileAddr;
    @Field(type = FieldType.Text)
    @ApiModelProperty("封面图片链接")
    private String discAddr;


    @Field(type = FieldType.Integer)
    private Integer albumid;
    @Field(type = FieldType.Integer)
    private Integer artistid;

    @Field(type = FieldType.Text)
    @ApiModelProperty("发行时间")
    private String publishTime;

//    @Field(type = FieldType.Keyword)
//    private List<String> tags;
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    private String desc;


}