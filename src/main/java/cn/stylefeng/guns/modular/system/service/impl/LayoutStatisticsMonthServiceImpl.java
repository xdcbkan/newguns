package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.LayoutStatisticsMonth;
import cn.stylefeng.guns.modular.system.dao.LayoutStatisticsMonthMapper;
import cn.stylefeng.guns.modular.system.service.ILayoutStatisticsMonthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配图每月统计表
 * 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@Service
public class LayoutStatisticsMonthServiceImpl extends ServiceImpl<LayoutStatisticsMonthMapper, LayoutStatisticsMonth> implements ILayoutStatisticsMonthService {

    @Override
    public LayoutStatisticsMonth statisticLayoutLastMonth(int layoutId) {
        return this.baseMapper.statisticLayoutLastMonth(layoutId);
    }

    @Override
    public int insertLayoutStatisticsMonth(List<LayoutStatisticsMonth> layoutStatisticsMonths) {
        return this.baseMapper.insertLayoutStatisticsMonth(layoutStatisticsMonths);
    }

    @Override
    public List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectMonthList(userId,beginTime,endTime);
    }
}
