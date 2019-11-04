package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.OperateStatisticsMonth;
import cn.stylefeng.guns.modular.system.dao.OperateStatisticsMonthMapper;
import cn.stylefeng.guns.modular.system.service.IOperateStatisticsMonthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营每月工作统计表
 * 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@Service
public class OperateStatisticsMonthServiceImpl extends ServiceImpl<OperateStatisticsMonthMapper, OperateStatisticsMonth> implements IOperateStatisticsMonthService {

    @Override
    public OperateStatisticsMonth statisticOperatorLastMonth(int operatorId) {
        return this.baseMapper.statisticOperatorLastMonth(operatorId);
    }

    @Override
    public int insertOperateStatisticsMonth(List<OperateStatisticsMonth> operateStatisticsMonths) {
        return this.baseMapper.insertOperateStatisticsMonth(operateStatisticsMonths);
    }

    @Override
    public List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectMonthList(userId,beginTime,endTime);
    }
}
