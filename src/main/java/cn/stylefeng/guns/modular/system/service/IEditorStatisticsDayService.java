package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.EditorStatisticsDay;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文编每日统计结算表 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-15
 */
public interface IEditorStatisticsDayService extends IService<EditorStatisticsDay> {

    /**
     * @param editorStatisticsDays
     * @Author: BaiYang
     * @Description: 插入文编每日统计记录
     * @Date: 2019/5/16
     * @return: int
     **/
    int insertEditorStatisticsDay(List<EditorStatisticsDay> editorStatisticsDays);

    /**
     * @Author: ZhangLu
     * @Description: 
     * @Date: 2019/5/20 
     * @param userId
 * @param beginTime
 * @param endTime
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> selectDayList(Integer userId, String beginTime, String endTime);

}
