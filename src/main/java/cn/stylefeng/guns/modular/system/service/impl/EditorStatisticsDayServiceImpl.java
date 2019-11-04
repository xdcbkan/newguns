package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.EditorStatisticsDay;
import cn.stylefeng.guns.modular.system.dao.EditorStatisticsDayMapper;
import cn.stylefeng.guns.modular.system.service.IEditorStatisticsDayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文编每日统计结算表 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-15
 */
@Service
public class EditorStatisticsDayServiceImpl extends ServiceImpl<EditorStatisticsDayMapper, EditorStatisticsDay> implements IEditorStatisticsDayService {

    @Override
    public int insertEditorStatisticsDay(List<EditorStatisticsDay> editorStatisticsDays) {
        return this.baseMapper.insertEditorStatisticsDay(editorStatisticsDays);
    }

    @Override
    public List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectDayList(userId,beginTime,endTime);
    }
}
