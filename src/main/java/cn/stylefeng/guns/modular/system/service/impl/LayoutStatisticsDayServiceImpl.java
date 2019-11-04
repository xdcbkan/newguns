package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.LayoutStatisticsDay;
import cn.stylefeng.guns.modular.system.dao.LayoutStatisticsDayMapper;
import cn.stylefeng.guns.modular.system.service.ILayoutStatisticsDayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配图每日统计表
 * 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
@Service
public class LayoutStatisticsDayServiceImpl extends ServiceImpl<LayoutStatisticsDayMapper, LayoutStatisticsDay> implements ILayoutStatisticsDayService {

    @Override
    public int insertLayoutStatisticsDay(List<LayoutStatisticsDay> layoutStatisticsDays) {
        return this.baseMapper.insertLayoutStatisticsDay(layoutStatisticsDays);
    }

    @Override
    public List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectDayList(userId,beginTime,endTime);
    }
}
