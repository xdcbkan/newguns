package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.Examine2StatisticsDay;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审2每日工作统计表
 * 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
public interface IExamine2StatisticsDayService extends IService<Examine2StatisticsDay> {

    /**
     * @param examine2StatisticsDays
     * @Author: BaiYang
     * @Description: 插入审核2每日统计记录
     * @Date: 2019/5/17
     * @return: int
     **/
    int insertExamine2StatisticsDay(List<Examine2StatisticsDay> examine2StatisticsDays);

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
