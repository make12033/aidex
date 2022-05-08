package com.aidex.web.es.repository;


import com.aidex.web.es.entity.MbmSong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MbmSongRepository  extends ElasticsearchRepository<MbmSong,Long> {

    long deleteMbmSongsBySongName(String name);

    Page<MbmSong> queryMbmSongsBySongNameAndSongSingerAndDiscName(String songName, String songSinger, String discName, Pageable pageable);

}
