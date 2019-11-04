package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.EditorStatisticsMonth;
import cn.stylefeng.guns.modular.system.dao.EditorStatisticsMonthMapper;
import cn.stylefeng.guns.modular.system.service.IEditorStatisticsMonthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文编每月统计结算表 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
@Service
public class EditorStatisticsMonthServiceImpl extends ServiceImpl<EditorStatisticsMonthMapper, EditorStatisticsMonth> implements IEditorStatisticsMonthService {

    @Override
    public EditorStatisticsMonth statisticEditorLastMonth(int editorId) {
        return this.baseMapper.statisticEditorLastMonth(editorId);
    }

    @Override
    public int insertEditorStatisticsMonth(List<EditorStatisticsMonth> editorStatisticsMonths) {
        return this.baseMapper.insertEditorStatisticsMonth(editorStatisticsMonths);
    }

    @Override
    public List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime) {
        return this.baseMapper.selectMonthList(userId,beginTime,endTime);
    }
}
