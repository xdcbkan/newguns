package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Examine2StatisticsDay;
import cn.stylefeng.guns.modular.system.dao.Examine2StatisticsDayMapper;
import cn.stylefeng.guns.modular.system.service.IExamine2StatisticsDayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审2每日工作统计表
 * 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
@Service
public class Examine2StatisticsDayServiceImpl extends ServiceImpl<Examine2StatisticsDayMapper, Examine2StatisticsDay> implements IExamine2StatisticsDayService {

    @Override
    public int insertExamine2StatisticsDay(List<Examine2StatisticsDay> examine2StatisticsDays) {
        return this.baseMapper.insertExamine2StatisticsDay(examine2StatisticsDays);
    }

    @Override
    public List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectDayList(userId,beginTime,endTime);
    }
}
