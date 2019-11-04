package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Examine1StatisticsDay;
import cn.stylefeng.guns.modular.system.dao.Examine1StatisticsDayMapper;
import cn.stylefeng.guns.modular.system.service.IExamine1StatisticsDayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核1每日工作统计表
 * 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
@Service
public class Examine1StatisticsDayServiceImpl extends ServiceImpl<Examine1StatisticsDayMapper, Examine1StatisticsDay> implements IExamine1StatisticsDayService {

    @Override
    public int insertExamine1StatisticsDay(List<Examine1StatisticsDay> examine1StatisticsDays) {
        return this.baseMapper.insertExamine1StatisticsDay(examine1StatisticsDays);
    }

    @Override
    public List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectDayList(userId,beginTime,endTime);
    }
}
