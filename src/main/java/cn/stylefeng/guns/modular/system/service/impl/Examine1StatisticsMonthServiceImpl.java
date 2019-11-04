package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Examine1StatisticsMonth;
import cn.stylefeng.guns.modular.system.dao.Examine1StatisticsMonthMapper;
import cn.stylefeng.guns.modular.system.service.IExamine1StatisticsMonthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核1每月工作统计表
 * 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@Service
public class Examine1StatisticsMonthServiceImpl extends ServiceImpl<Examine1StatisticsMonthMapper, Examine1StatisticsMonth> implements IExamine1StatisticsMonthService {

    @Override
    public Examine1StatisticsMonth statisticExamine1LastMonth(int examine1Id) {
        return this.baseMapper.statisticExamine1LastMonth(examine1Id);
    }

    @Override
    public int insertExamine1StatisticsMonth(List<Examine1StatisticsMonth> examine1StatisticsMonths) {
        return this.baseMapper.insertExamine1StatisticsMonth(examine1StatisticsMonths);
    }

    @Override
    public List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectMonthList(userId,beginTime,endTime);
    }

}
