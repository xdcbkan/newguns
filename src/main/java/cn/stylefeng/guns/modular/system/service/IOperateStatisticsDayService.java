package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.OperateStatisticsDay;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营每天工作统计表
 * 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
public interface IOperateStatisticsDayService extends IService<OperateStatisticsDay> {
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
    List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime);
}
