package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.LayoutStatisticsMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配图每月统计表
 * Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface LayoutStatisticsMonthMapper extends BaseMapper<LayoutStatisticsMonth> {

    /**
     * @param layoutId
     * @Author: BaiYang
     * @Description: 统计配图上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.LayoutStatisticsMonth
     **/
    LayoutStatisticsMonth statisticLayoutLastMonth(int layoutId);

    /**
     * @param layoutStatisticsMonths
     * @Author: BaiYang
     * @Description: 插入配图每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertLayoutStatisticsMonth(List<LayoutStatisticsMonth> layoutStatisticsMonths);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @return: java.util.List<cn.stylefeng.guns.modular.system.model.EditorStatisticsDay>
     **/
    List<Map<String, Object>> selectMonthList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
