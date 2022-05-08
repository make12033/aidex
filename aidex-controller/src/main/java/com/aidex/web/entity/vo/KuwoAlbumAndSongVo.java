package com.aidex.web.entity.vo;

import com.aidex.web.entity.Kuwo;
import com.aidex.web.entity.KuwoAlbumAndSong;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class KuwoAlbumAndSongVo {

    @ApiModelProperty(value = "专辑列表")
    private List<KuwoAlbumAndSong> list=new ArrayList<>();
    @ApiModelProperty(value = "总数")
    private Integer total;
}
