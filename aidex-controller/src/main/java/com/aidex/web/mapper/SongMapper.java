package com.aidex.web.mapper;

import com.aidex.common.annotation.DataSource;
import com.aidex.common.enums.DataSourceType;
import com.aidex.web.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lx
 * @since 2022-03-31
 */
@DataSource(value = DataSourceType.SLAVE)
public interface SongMapper extends BaseMapper<Song> {

}
