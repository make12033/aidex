package com.aidex.web.util;

import com.aidex.common.core.domain.R;
import com.aidex.web.entity.KuWoArtist;
import com.aidex.web.entity.Kuwo;
import com.aidex.web.entity.KuwoAlbumAndSong;
import com.aidex.web.entity.vo.KuwoAlbumAndSongVo;
import com.aidex.web.entity.vo.KuwoVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class KuWoSongUtil {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${kuwo.reqId}")
    public static String reqId;

    public R searchWebSongByKey(Integer pageNum, Integer pageSize, String song_name, String brSound){


        try {

            //中文搜索转码
            String encode = URLEncoder.encode(song_name, "UTF-8");
            //酷我最新搜索接口
            String geturl = "http://www.kuwo.cn/api/www/search/searchMusicBykeyWord?key="+song_name+"&pn="+pageNum+"&rn="+pageSize+"&httpsStatus=1&reqId="+reqId;

            //伪装请求头，跳过nginx检测，访问酷我最新搜索接口
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            headers.add("Referer", "http://www.kuwo.cn/search/list?key= ");
            headers.add("csrf", "RUJ53PGJ4ZD");
            headers.add("Cookie", "Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1577029678,1577034191,1577034210,1577076651; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1577080777; kw_token=RUJ53PGJ4ZD");


            //访问
            HttpEntity<JSONObject> res = restTemplate
                    .exchange(geturl, HttpMethod.GET, new HttpEntity<>(null, headers),
                            JSONObject.class);

            //数据体
            JSONObject body = res.getBody();

            //判断搜索的数据或者链接失效
            if(!body.containsKey("code")&&body.containsKey("success")){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }else if( !body.getInteger("code").equals(200)){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }

            //获取json中的data下的list集合
            JSONArray list = body.getJSONObject("data").getJSONArray("list");



            KuwoVo kuwoDTO = new KuwoVo();
            kuwoDTO.setTotal(body.getJSONObject("data").getInteger("total"));

            for (int i = 0; i < list.size(); i++) {


                JSONObject listall =list.getJSONObject(i);
                Kuwo kuwo = JSON.toJavaObject(listall, Kuwo.class);

                String songUrl="http://antiserver.kuwo.cn/anti.s?type=convert_url&rid="+listall.getString("musicrid")+"&format=mp3&response=res&br="+brSound+"kmp3";
                kuwo.setSongUrl(songUrl);
                kuwo.setName(listall.getString("name"));

                if(kuwo.getRid()==null){
                    String rid = listall.getString("musicrid").substring(6, listall.getString("musicrid").length());
                    kuwo.setRid(Integer.valueOf(rid));
                }

                kuwoDTO.getList().add(kuwo);
            }

            //防止失效 reqId替换为新的
            reqId=body.getString("reqId");


            return R.data(kuwoDTO);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }


    public R searchWebSongByAlbum(Integer pageNum, Integer pageSize, String brSound,Integer albumId){


        try {

//            //中文搜索转码
//            String encode = URLEncoder.encode(song_name, "UTF-8");
            //酷我最新搜索接口
            String geturl = "http://www.kuwo.cn/api/www/album/albumInfo?albumId="+albumId+"&pn="+pageNum+"&rn="+pageSize+"&httpsStatus=1&reqId="+reqId;

            //伪装请求头，跳过nginx检测，访问酷我最新搜索接口
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            headers.add("Referer", "http://www.kuwo.cn/search/list?key= ");
            headers.add("csrf", "RUJ53PGJ4ZD");
            headers.add("Cookie", "Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1577029678,1577034191,1577034210,1577076651; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1577080777; kw_token=RUJ53PGJ4ZD");


            //访问
            HttpEntity<JSONObject> res = restTemplate
                    .exchange(geturl, HttpMethod.GET, new HttpEntity<>(null, headers),
                            JSONObject.class);

            //数据体
            JSONObject body = res.getBody();

            //判断搜索的数据或者链接失效
            if(!body.containsKey("code")&&body.containsKey("success")){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }else if( !body.getInteger("code").equals(200)){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }

            //获取json中的data下的list集合
            JSONArray list = body.getJSONObject("data").getJSONArray("musicList");



            KuwoAlbumAndSong kuwoDTO = new KuwoAlbumAndSong();
            kuwoDTO.setTotal(body.getJSONObject("data").getInteger("total"));
            kuwoDTO.setAlbum(body.getJSONObject("data").getString("album"));
            kuwoDTO.setAlbumid(body.getJSONObject("data").getInteger("albumid"));
            kuwoDTO.setAlbuminfo(body.getJSONObject("data").getString("albuminfo"));
            kuwoDTO.setArtist(body.getJSONObject("data").getString("artist"));
            kuwoDTO.setPic(body.getJSONObject("data").getString("pic"));
            kuwoDTO.setArtistid(body.getJSONObject("data").getInteger("artistid"));
            kuwoDTO.setLang(body.getJSONObject("data").getString("lang"));
            kuwoDTO.setReleaseDate(body.getJSONObject("data").getString("releaseDate"));

            for (int i = 0; i < list.size(); i++) {


                JSONObject listall =list.getJSONObject(i);
                Kuwo kuwo = JSON.toJavaObject(listall, Kuwo.class);

                String songUrl="http://antiserver.kuwo.cn/anti.s?type=convert_url&rid="+listall.getString("musicrid")+"&format=mp3&response=res&br="+brSound+"kmp3";
                kuwo.setSongUrl(songUrl);
                kuwo.setName(listall.getString("name"));

                if(kuwo.getRid()==null){
                    String rid = listall.getString("musicrid").substring(6, listall.getString("musicrid").length());
                    kuwo.setRid(Integer.valueOf(rid));
                }

                kuwoDTO.getList().add(kuwo);
            }

            //防止失效 reqId替换为新的
            reqId=body.getString("reqId");


            return R.data(kuwoDTO);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }


    public R searchArtistInfo(Integer artistid){


        try {

//            //中文搜索转码
//            String encode = URLEncoder.encode(song_name, "UTF-8");
            //酷我最新搜索接口
            String geturl = "http://www.kuwo.cn/api/www/artist/artist?artistid="+artistid+"&httpsStatus=1&reqId="+reqId;

            //伪装请求头，跳过nginx检测，访问酷我最新搜索接口
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            headers.add("Referer", "http://www.kuwo.cn/search/list?key= ");
            headers.add("csrf", "RUJ53PGJ4ZD");
            headers.add("Cookie", "Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1577029678,1577034191,1577034210,1577076651; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1577080777; kw_token=RUJ53PGJ4ZD");


            //访问
            HttpEntity<JSONObject> res = restTemplate
                    .exchange(geturl, HttpMethod.GET, new HttpEntity<>(null, headers),
                            JSONObject.class);

            //数据体
            JSONObject body = res.getBody();

            //判断搜索的数据或者链接失效
            if(!body.containsKey("code")&&body.containsKey("success")){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }else if( !body.getInteger("code").equals(200)){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }


            //获取json中的data下的list集合
            KuWoArtist data = body.getJSONObject("data").toJavaObject(KuWoArtist.class);


//            System.out.println("aaa");

            //防止失效 reqId替换为新的
            reqId=body.getString("reqId");


            return R.data(data);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }


    public R searchWebSongByArtist(Integer pageNum, Integer pageSize, String brSound,Integer artistid){


        try {

//            //中文搜索转码
//            String encode = URLEncoder.encode(song_name, "UTF-8");
            //酷我最新搜索接口
            String geturl = "http://www.kuwo.cn/api/www/artist/artistMusic?artistid="+artistid+"&pn="+pageNum+"&rn="+pageSize+"&httpsStatus=1&reqId="+reqId;

            //伪装请求头，跳过nginx检测，访问酷我最新搜索接口
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            headers.add("Referer", "http://www.kuwo.cn/search/list?key= ");
            headers.add("csrf", "RUJ53PGJ4ZD");
            headers.add("Cookie", "Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1577029678,1577034191,1577034210,1577076651; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1577080777; kw_token=RUJ53PGJ4ZD");


            //访问
            HttpEntity<JSONObject> res = restTemplate
                    .exchange(geturl, HttpMethod.GET, new HttpEntity<>(null, headers),
                            JSONObject.class);

            //数据体
            JSONObject body = res.getBody();

            //判断搜索的数据或者链接失效
            if(!body.containsKey("code")&&body.containsKey("success")){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }else if( !body.getInteger("code").equals(200)){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }

            //获取json中的data下的list集合
            JSONArray list = body.getJSONObject("data").getJSONArray("list");



            KuwoVo kuwoDTO = new KuwoVo();
            kuwoDTO.setTotal(body.getJSONObject("data").getInteger("total"));

            for (int i = 0; i < list.size(); i++) {


                JSONObject listall =list.getJSONObject(i);
                Kuwo kuwo = JSON.toJavaObject(listall, Kuwo.class);

                String songUrl="http://antiserver.kuwo.cn/anti.s?type=convert_url&rid="+listall.getString("musicrid")+"&format=mp3&response=res&br="+brSound+"kmp3";
                kuwo.setSongUrl(songUrl);
                kuwo.setName(listall.getString("name"));

                if(kuwo.getRid()==null){
                    String rid = listall.getString("musicrid").substring(6, listall.getString("musicrid").length());
                    kuwo.setRid(Integer.valueOf(rid));
                }

                kuwoDTO.getList().add(kuwo);
            }

            //防止失效 reqId替换为新的
            reqId=body.getString("reqId");


            return R.data(kuwoDTO);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }


    public R searchLrc(Integer rid){


        try {

//            //中文搜索转码
//            String encode = URLEncoder.encode(song_name, "UTF-8");
            //酷我最新搜索接口
            String geturl = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId="+rid+"&httpsStatus=1&reqId="+reqId;

            //伪装请求头，跳过nginx检测，访问酷我最新搜索接口
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            headers.add("Referer", "http://www.kuwo.cn/search/list?key= ");
            headers.add("csrf", "RUJ53PGJ4ZD");
            headers.add("Cookie", "Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1577029678,1577034191,1577034210,1577076651; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1577080777; kw_token=RUJ53PGJ4ZD");


            //访问
            HttpEntity<JSONObject> res = restTemplate
                    .exchange(geturl, HttpMethod.GET, new HttpEntity<>(null, headers),
                            JSONObject.class);

            //数据体
            JSONObject body = res.getBody();

            //判断搜索的数据或者链接失效
            if(!body.containsKey("status")){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }else if( !body.getInteger("status").equals(200)){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }


            //获取json中的data下的list集合
            JSONArray list = body.getJSONObject("data").getJSONArray("lrclist");



//            System.out.println("aaa");

            //防止失效 reqId替换为新的
            reqId=body.getString("reqId");


            return R.data(list);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }




    public R searchAlbumByArtist(Integer pageNum, Integer pageSize, Integer artistid){


        try {

//            //中文搜索转码
//            String encode = URLEncoder.encode(song_name, "UTF-8");
            //酷我最新搜索接口
            String geturl = "http://www.kuwo.cn/api/www/artist/artistAlbum?artistid="+artistid+"&pn="+pageNum+"&rn="+pageSize+"&httpsStatus=1&reqId="+reqId;

            //伪装请求头，跳过nginx检测，访问酷我最新搜索接口
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            headers.add("Referer", "http://www.kuwo.cn/search/list?key= ");
            headers.add("csrf", "RUJ53PGJ4ZD");
            headers.add("Cookie", "Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1577029678,1577034191,1577034210,1577076651; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1577080777; kw_token=RUJ53PGJ4ZD");


            //访问
            HttpEntity<JSONObject> res = restTemplate
                    .exchange(geturl, HttpMethod.GET, new HttpEntity<>(null, headers),
                            JSONObject.class);

            //数据体
            JSONObject body = res.getBody();

            //判断搜索的数据或者链接失效
            if(!body.containsKey("code")&&body.containsKey("success")){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }else if( !body.getInteger("code").equals(200)){
                //异常
                return R.fail(body.containsKey("msg") ? body.getJSONObject("msg") : "");
            }

            //获取json中的data下的list集合
            JSONArray list = body.getJSONObject("data").getJSONArray("albumList");


            List<KuwoAlbumAndSong> KuwoAlbumVos = new ArrayList<>();

            KuwoAlbumAndSongVo kuwoAlbumAndSongVo = new KuwoAlbumAndSongVo();
            kuwoAlbumAndSongVo.setTotal(body.getJSONObject("data").getInteger("total"));



            for (int i = 0; i < list.size(); i++) {


                JSONObject listall =list.getJSONObject(i);
                KuwoAlbumAndSong kuwoAlbumVo = JSON.toJavaObject(listall, KuwoAlbumAndSong.class);

                KuwoAlbumVos.add(kuwoAlbumVo);
            }
            kuwoAlbumAndSongVo.setList(KuwoAlbumVos);

            //防止失效 reqId替换为新的
            reqId=body.getString("reqId");


            return R.data(kuwoAlbumAndSongVo);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

}
