package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.Examine1StatisticsMonth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核1每月工作统计表
 * 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface IExamine1StatisticsMonthService extends IService<Examine1StatisticsMonth> {
    /**
     * @param examine1Id
     * @Author: BaiYang
     * @Description: 统计审核1上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.Examine1StatisticsMonth
     **/
    Examine1StatisticsMonth statisticExamine1LastMonth(int examine1Id);

    /**
     * @param examine1StatisticsMonths
     * @Author: BaiYang
     * @Description: 新增审核1每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertExamine1StatisticsMonth(List<Examine1StatisticsMonth> examine1StatisticsMonths);

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
