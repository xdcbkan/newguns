package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Examine2StatisticsMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审2每月工作统计表
 * Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface Examine2StatisticsMonthMapper extends BaseMapper<Examine2StatisticsMonth> {
    /**
     * @param examine2Id
     * @Author: BaiYang
     * @Description: 统计审核2上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.Examine2StatisticsMonth
     **/
    Examine2StatisticsMonth statisticExamine2LastMonth(int examine2Id);

    /**
     * @param examine2StatisticsMonths
     * @Author: BaiYang
     * @Description: 插入审核2每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertExamine2StatisticsMonth(List<Examine2StatisticsMonth> examine2StatisticsMonths);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @return: java.util.List<cn.stylefeng.guns.modular.system.model.EditorStatisticsDay>
     **/
    List<Map<String, Object>> selectMonthList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
