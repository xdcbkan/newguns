package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.OperateStatisticsDay;
import cn.stylefeng.guns.modular.system.dao.OperateStatisticsDayMapper;
import cn.stylefeng.guns.modular.system.service.IOperateStatisticsDayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运营每天工作统计表
 * 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-17
 */
@Service
public class OperateStatisticsDayServiceImpl extends ServiceImpl<OperateStatisticsDayMapper, OperateStatisticsDay> implements IOperateStatisticsDayService {

    @Override
    public int insertOperateStatisticsDay(List<OperateStatisticsDay> operateStatisticsDays) {
        return this.baseMapper.insertOperateStatisticsDay(operateStatisticsDays);
    }

    @Override
    public List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectDayList(userId,beginTime,endTime);
    }
}
