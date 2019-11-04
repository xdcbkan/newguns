package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.EditorStatisticsDay;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文编每日统计结算表 Mapper 接口
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-15
 */
public interface EditorStatisticsDayMapper extends BaseMapper<EditorStatisticsDay> {

    /**
     * @param editorStatisticsDays
     * @Author: BaiYang
     * @Description: 插入文编每日统计记录
     * @Date: 2019/5/16
     * @return: int
     **/
    int insertEditorStatisticsDay(List<EditorStatisticsDay> editorStatisticsDays);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @return: java.util.List<cn.stylefeng.guns.modular.system.model.EditorStatisticsDay>
     **/
    List<Map<String, Object>> selectDayList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
