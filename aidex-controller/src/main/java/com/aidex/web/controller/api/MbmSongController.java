//package com.aidex.web.controller.api;
//
//
//import com.aidex.common.core.domain.R;
//import com.aidex.web.es.entity.MbmSong;
//import com.aidex.web.es.repository.MbmSongRepository;
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@RestController
//@RequestMapping("/mbm_api/mbm_song")
//@Api(tags = "mbm歌曲库相关接口")
//public class MbmSongController {
//
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @Autowired
//    private MbmSongRepository mbmSongRepository;
//
////    @RequestMapping(value = "/create-index", method = RequestMethod.POST)
////    @ApiOperation("创建索引")
////    public Object createEsIndex() {
////        boolean index = elasticsearchRestTemplate.createIndex(MbmSong.class);
////        elasticsearchRestTemplate.putMapping(MbmSong.class);
////
////        System.out.println("创建索引结果是" + index);
////        return index;
////    }
////
////    @RequestMapping(value = "/delete-index", method = RequestMethod.POST)
////    @ApiOperation("删除索引")
////    public Object deleteEsIndex() {
////        boolean deleteIndex = elasticsearchRestTemplate.deleteIndex(MbmSong.class);
////        System.out.println("删除索引结果是" + deleteIndex);
////        return deleteIndex;
////    }
////
////    @RequestMapping(value = "/exist-index", method = RequestMethod.POST)
////    @ApiOperation("是否存在索引")
////    public Object existEsIndex() {
////        boolean existsIndex = elasticsearchRestTemplate.indexExists(MbmSong.class);
////        System.out.println("是否存在的结果是" + existsIndex);
////        return existsIndex;
////    }
//
////    @RequestMapping(value = "/save-doc", method = RequestMethod.POST)
////    @ApiOperation("保存歌曲")
////    public MbmSong saveEsDoc(@RequestBody MbmSong mbmSong) {
//////        mbmSongRepository.index()
////        MbmSong result = mbmSongRepository.save(mbmSong);
////        return result;
////    }
//
//    @RequestMapping(value = "/queryByMbmSong", method = RequestMethod.GET)
//    @ApiOperation("根据字段查询歌曲")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "songName", value = "歌曲名称",dataType = "String",defaultValue = "告白气球"),
//            @ApiImplicitParam(name = "songSinger", value = "歌手名称",dataType = "String"),
//            @ApiImplicitParam(name = "discName", value = "专辑名称",dataType = "String"),
//            @ApiImplicitParam(name = "pageNum", value = "页码，不传默认1",dataType = "Integer",defaultValue = "1"),
//            @ApiImplicitParam(name = "pageSize", value = "获取条数，不传默认10",dataType = "Integer",defaultValue = "10")
//    })
//    public R<Page<MbmSong>> queryByName(String songName, String songSinger, String discName, Integer pageNum, Integer pageSize) {
//
//
//        pageNum=pageNum==null ? 0 : pageNum;
//        pageSize=pageSize==null ? 10 : pageSize;
//
//
//        // 先构建查询条件
//        BoolQueryBuilder defaultQueryBuilder = QueryBuilders.boolQuery();
//
//        // 分页条件
//        PageRequest pageRequest = PageRequest.of(pageNum-1,pageSize);
//
//
//        if(StringUtils.isBlank(songName)&&StringUtils.isBlank(songSinger)&&StringUtils.isBlank(discName)){
//
//            defaultQueryBuilder.must(QueryBuilders.matchQuery( "songSinger", "周杰伦"));
//
//
//        }
//
//        if(!StringUtils.isBlank(songName)){
//            defaultQueryBuilder.must(QueryBuilders.matchQuery( "songName", songName));
//        }
//        if(!StringUtils.isBlank(songSinger)){
//            defaultQueryBuilder.must(QueryBuilders.matchQuery( "songSinger", songSinger));
//        }
//        if(!StringUtils.isBlank(discName)){
//            defaultQueryBuilder.must(QueryBuilders.matchQuery( "discName", discName));
//        }
//
//
//        //组装条件
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(defaultQueryBuilder)
////                .withHighlightBuilder(highlightBuilder)
//                .withPageable(pageRequest)
////                .withSort(sortBuilder)
//                .build();
//
//
//        //开始查询
//        SearchHits<MbmSong> search = elasticsearchRestTemplate.search(searchQuery, MbmSong.class);
//
//
//        List<MbmSong> userVoList = new ArrayList<>();
//        for (org.springframework.data.elasticsearch.core.SearchHit<MbmSong> mbmSongSearchHit : search) {
//            MbmSong content = mbmSongSearchHit.getContent();
//            userVoList.add(content);
//        }
//
//        // 组装分页对象
//        Page<MbmSong> userPage = new PageImpl<MbmSong>(userVoList,pageRequest,search.getTotalHits());
//
//        return R.data(userPage);
//    }
//
//    @RequestMapping(value = "/getMbmSongById", method = RequestMethod.GET)
//    @ApiOperation("根据id精确查询歌曲(可批量)")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ids", value = "歌曲id，若多条请用英文逗号隔开",dataType = "String"),
//    })
//    public R<List<MbmSong>> existDoc(String ids) {
//
//        if(StringUtils.isBlank(ids)){
//            return R.fail("id不能为空");
//        }
//
//        String[] split = ids.split(",");
//        List<Long> idList = new ArrayList<>();
//
//        for (String s : split) {
//            idList.add(Long.parseLong(s));
//        }
//
//
//        Iterable<MbmSong> songs = mbmSongRepository.findAllById(idList);
//
//        List<MbmSong> dataList = new ArrayList<>();
//        for (MbmSong song : songs) {
//            dataList.add(song);
//        }
//        return R.data(dataList);
//    }
//
//
////    //---------------- 复杂查询 ------------------
////    @GetMapping("getMbmSong")
////    @ApiOperation("聚合查询歌曲")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "key", value = "关键字(根据关键字去歌曲名，歌手，专辑中聚合查询)",dataType = "String",defaultValue = "告白气球"),
////            @ApiImplicitParam(name = "pageNum", value = "页码，不传默认1",dataType = "Integer",defaultValue = "1"),
////            @ApiImplicitParam(name = "pageSize", value = "获取条数，不传默认10",dataType = "Integer",defaultValue = "10")
////    })
////    public R<Page<MbmSong>> getMbmSong(String key,Integer pageNum,Integer pageSize) {
////
////        pageNum=pageNum==null ? 1 : pageNum;
////        pageSize=pageSize==null ? 10 : pageSize;
////
////        // 先构建查询条件
////        HashMap<Object, Object> map = new HashMap<>();
////        map.put("songSinger", 1.1);
////        map.put("songName", 1.2);
////        map.put("discName", 1.0);
////
////
////        BoolQueryBuilder defaultQueryBuilder = QueryBuilders.boolQuery();
////            defaultQueryBuilder.should(QueryBuilders.multiMatchQuery(StringUtils.isBlank(key) ? "周杰伦" : key,
////                    "songSinger","songName","discName"));
////
////        // 分页条件
////        PageRequest pageRequest = PageRequest.of(pageNum-1,pageSize);
//////        // 高亮条件
//////        HighlightBuilder highlightBuilder = getHighlightBuilder("songName", "tags");
//////        // 排序条件
//////        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("age").order(SortOrder.DESC);
////        //组装条件
////        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
////                .withQuery(defaultQueryBuilder)
//////                .withHighlightBuilder(highlightBuilder)
////                .withPageable(pageRequest)
////
//////                .withSort(sortBuilder)
////                .build();
////
////        searchQuery.setTrackTotalHits(true);
////
//////                defaultQueryBuilder.trackTotalHits(true);
////
////
////        SearchHits<MbmSong> search = elasticsearchRestTemplate.search(searchQuery, MbmSong.class);
////
////        List<MbmSong> userVoList = new ArrayList<>();
////        for (SearchHit<MbmSong> mbmSongSearchHit : search) {
////            MbmSong content = mbmSongSearchHit.getContent();
////            userVoList.add(content);
////        }
////
////        // 组装分页对象
////        Page<MbmSong> userPage = new PageImpl<MbmSong>(userVoList,pageRequest,search.getTotalHits());
////
////        return R.data(userPage);
////
////
////
////    }
//
//
//
//
//    // 设置高亮字段
//    private HighlightBuilder getHighlightBuilder(String... fields) {
//        // 高亮条件
//        HighlightBuilder highlightBuilder = new HighlightBuilder(); //生成高亮查询器
//        for (String field : fields) {
//            highlightBuilder.field(field);//高亮查询字段
//        }
//        highlightBuilder.requireFieldMatch(false);     //如果要多个字段高亮,这项要为false
//        highlightBuilder.preTags("<span style=\"color:red\">");   //高亮设置
//        highlightBuilder.postTags("</span>");
//        //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
//        highlightBuilder.fragmentSize(800000); //最大高亮分片数
//        highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段
//
//        return highlightBuilder;
//    }
//}
