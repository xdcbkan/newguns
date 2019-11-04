package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.OperateStatisticsDay;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营每天工作统计表
 * Mapper 接口
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
public interface OperateStatisticsDayMapper extends BaseMapper<OperateStatisticsDay> {

    /**
     * @param operateStatisticsDays
     * @Author: BaiYang
     * @Description: 插入运营每日统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertOperateStatisticsDay(List<OperateStatisticsDay> operateStatisticsDays);

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
