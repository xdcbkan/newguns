package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.LayoutStatisticsDay;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配图每日统计表
 * Mapper 接口
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
public interface LayoutStatisticsDayMapper extends BaseMapper<LayoutStatisticsDay> {

    /**
     * @param layoutStatisticsDays
     * @Author: BaiYang
     * @Description: 插入配图每日统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertLayoutStatisticsDay(List<LayoutStatisticsDay> layoutStatisticsDays);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @param beginTime
     * @param endTime
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> selectDayList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
