package com.aidex.web.service;


import com.aidex.common.annotation.DataSource;
import com.aidex.common.enums.DataSourceType;
import com.aidex.web.entity.Song;
import com.aidex.web.mapper.SongMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @since 2022-03-31
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> getSong(Integer source_type, Integer song_status) {
        List<Song> songs = songMapper.selectList(new QueryWrapper<Song>().eq("source_type", source_type).eq("song_status", song_status));
        return songs;
    }
}
