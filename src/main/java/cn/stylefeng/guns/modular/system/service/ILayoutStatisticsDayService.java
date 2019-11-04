package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.LayoutStatisticsDay;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配图每日统计表
 * 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
public interface ILayoutStatisticsDayService extends IService<LayoutStatisticsDay> {

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
    List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime);

}
