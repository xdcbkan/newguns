package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.LayoutStatisticsMonth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配图每月统计表
 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface ILayoutStatisticsMonthService extends IService<LayoutStatisticsMonth> {

    /**
     * @param layoutId
     * @Author: BaiYang
     * @Description: 统计配图上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.LayoutStatisticsMonth
     **/
    LayoutStatisticsMonth statisticLayoutLastMonth(int layoutId);

    /**
     * @param layoutStatisticsMonths
     * @Author: BaiYang
     * @Description: 插入配图每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertLayoutStatisticsMonth(List<LayoutStatisticsMonth> layoutStatisticsMonths);

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
