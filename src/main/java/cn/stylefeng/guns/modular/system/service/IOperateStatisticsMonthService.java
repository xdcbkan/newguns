package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.OperateStatisticsMonth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营每月工作统计表
 * 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface IOperateStatisticsMonthService extends IService<OperateStatisticsMonth> {
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
     * @param beginTime
     * @param endTime
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime);
}
