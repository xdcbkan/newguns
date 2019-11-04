package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.ArticleRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 文章记录 服务类
 *
 * @author zpy
 * @since 2019-09-24
 */
public interface IArticleRecordService extends IService<ArticleRecord> {

    /**
     * 根据条件查询任务列表
     */
    List<Map<String, Object>> selectArticleList(String articleNum,String name);

    int addArticleRecord(Long articleNum,String operationType);

}
