package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.Examine2StatisticsMonth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审2每月工作统计表
 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface IExamine2StatisticsMonthService extends IService<Examine2StatisticsMonth> {

    /**
     * @param examine2Id
     * @Author: BaiYang
     * @Description: 统计审核2上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.Examine2StatisticsMonth
     **/
    Examine2StatisticsMonth statisticExamine2LastMonth(int examine2Id);

    /**
     * @param examine2StatisticsMonths
     * @Author: BaiYang
     * @Description: 插入审核2每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertExamine2StatisticsMonth(List<Examine2StatisticsMonth> examine2StatisticsMonths);

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
