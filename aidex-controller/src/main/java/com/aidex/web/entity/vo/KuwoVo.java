package com.aidex.web.entity.vo;



import com.aidex.web.entity.Kuwo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class KuwoVo implements Serializable {

    @ApiModelProperty(value = "数据列表")
    private List<Kuwo> list=new ArrayList<>();
    @ApiModelProperty(value = "总数")
    private Integer total;
}
