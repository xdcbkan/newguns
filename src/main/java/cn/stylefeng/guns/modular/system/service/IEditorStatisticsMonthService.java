package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.EditorStatisticsMonth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文编每月统计结算表 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface IEditorStatisticsMonthService extends IService<EditorStatisticsMonth> {

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 统计文编上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.EditorStatisticsDay
     **/
    EditorStatisticsMonth statisticEditorLastMonth(int editorId);

    /**
     * @param editorStatisticsMonths
     * @Author: BaiYang
     * @Description: 新增文编每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertEditorStatisticsMonth(List<EditorStatisticsMonth> editorStatisticsMonths);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @param beginTime
     * @param endTime
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> selectMonthList(Integer userId, String beginTime, String endTime);

}
