package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.HistoricalArticle;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录文章历史版本 Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-16
 */
public interface HistoricalArticleMapper extends BaseMapper<HistoricalArticle> {

    /**
     * @param
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/17
     * @return: int
     **/
    int updateHistory(Integer newArticleId);

    /**
     * 管理员查看最新表文章
     */
    List<Map<String, Object>> selectArticleList(@Param("mainTitle") String mainTitle, @Param("articleTypeId") Integer articleTypeId, @Param("prescription") Integer prescription, @Param("articleStatus") Integer articleStatus);

    /**
     * 操作人员查看自己操作的版本
     */
    List<Map<String, Object>> getPersonalOperationRecord(@Param("condition") String condition, @Param("articleTypeId") Integer articleTypeId, @Param("prescription") Integer prescription, @Param("userId") Integer userId);


    /**
     * 管理员查看历史记录表
     */
    List<Map<String, Object>> getList(Integer newArticleId);


}
