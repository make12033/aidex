package com.aidex.web.service;

import com.aidex.common.annotation.DataSource;
import com.aidex.common.enums.DataSourceType;
import com.aidex.web.entity.Song;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @since 2022-03-31
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ISongService extends IService<Song> {

    List<Song> getSong(Integer source_type,Integer song_status);


}
