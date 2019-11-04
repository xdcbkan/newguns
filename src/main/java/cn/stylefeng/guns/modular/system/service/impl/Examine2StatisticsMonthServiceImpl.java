package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Examine2StatisticsMonth;
import cn.stylefeng.guns.modular.system.dao.Examine2StatisticsMonthMapper;
import cn.stylefeng.guns.modular.system.service.IExamine2StatisticsMonthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审2每月工作统计表
 * 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@Service
public class Examine2StatisticsMonthServiceImpl extends ServiceImpl<Examine2StatisticsMonthMapper, Examine2StatisticsMonth> implements IExamine2StatisticsMonthService {

    @Override
    public Examine2StatisticsMonth statisticExamine2LastMonth(int examine2Id) {
        return this.baseMapper.statisticExamine2LastMonth(examine2Id);
    }

    @Override
    public int insertExamine2StatisticsMonth(List<Examine2StatisticsMonth> examine2StatisticsMonths) {
        return this.baseMapper.insertExamine2StatisticsMonth(examine2StatisticsMonths);
    }

    @Override
    public List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectMonthList(userId,beginTime,endTime);
    }
}
