package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.EditorStatisticsMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文编每月统计结算表 Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface EditorStatisticsMonthMapper extends BaseMapper<EditorStatisticsMonth> {

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 统计文编上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.EditorStatisticsDay
     **/
    EditorStatisticsMonth statisticEditorLastMonth(int editorId);

    /**
     * @param editorStatisticsMonths
     * @Author: BaiYang
     * @Description: 插入文编每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertEditorStatisticsMonth(List<EditorStatisticsMonth> editorStatisticsMonths);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @return: java.util.List<cn.stylefeng.guns.modular.system.model.EditorStatisticsDay>
     **/
    List<Map<String, Object>> selectMonthList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
