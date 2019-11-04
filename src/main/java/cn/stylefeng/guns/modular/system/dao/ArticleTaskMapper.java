package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.ArticleTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章任务表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-21
 */
public interface ArticleTaskMapper extends BaseMapper<ArticleTask> {

    /**
     * 根据条件查询文章任务请列表
     */
    List<Map<String, Object>> selectArticleTasks(@Param("title") String title, @Param("timelinessCategory") Integer timelinessCategory, @Param("taskStatus") Integer taskStatus, @Param("type") Integer type);

}
