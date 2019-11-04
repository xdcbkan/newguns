package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.OperateStatisticsMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营每月工作统计表
 * Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface OperateStatisticsMonthMapper extends BaseMapper<OperateStatisticsMonth> {
    /**
     * @param operatorId
     * @Author: BaiYang
     * @Description: 统计运营上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.OperateStatisticsMonth
     **/
    OperateStatisticsMonth statisticOperatorLastMonth(int operatorId);

    /**
     * @param operateStatisticsMonths
     * @Author: BaiYang
     * @Description: 插入运营每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertOperateStatisticsMonth(List<OperateStatisticsMonth> operateStatisticsMonths);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @return: java.util.List<cn.stylefeng.guns.modular.system.model.EditorStatisticsDay>
     **/
    List<Map<String, Object>> selectMonthList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
