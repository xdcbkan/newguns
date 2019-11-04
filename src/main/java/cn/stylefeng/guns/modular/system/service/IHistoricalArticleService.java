package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.HistoricalArticle;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录文章历史版本 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-16
 */
public interface IHistoricalArticleService extends IService<HistoricalArticle> {

    /**
     * @param newArticleId
     * @Author: ZhangLu
     * @Description: 变更旧版
     * @Date: 2019/5/17
     * @return: int
     **/
    int updateHistory(Integer newArticleId);

    /**
     * 管理员查看最新表文章
     */
    List<Map<String, Object>> selectArticleList(String mainTitle, Integer articleTypeId, Integer prescription, Integer articleStatus);

    /**
     * 操作人员查看自己操作的版本
     */
    List<Map<String, Object>> getPersonalOperationRecord(String condition, Integer articleTypeId, Integer prescription, Integer userId);

    /**
     * 管理员查看历史记录表
     */
    List<Map<String, Object>> getList(Integer newArticleId);

}
