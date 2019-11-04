package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.ArticleRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章记录 Mapper 接口
 *
 * @author zpy
 * @since 2019-09-24
 */
public interface ArticleRecordMapper extends BaseMapper<ArticleRecord> {

    /**
     * 根据条件查询文章列表
     * @param articleNum
     * @param name
     * @return
     */
    List<Map<String, Object>> selectArticleList(@Param("articleNum") String articleNum,@Param("name") String name);

}
