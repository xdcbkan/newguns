package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Examine1StatisticsMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核1每月工作统计表
 * Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-20
 */
public interface Examine1StatisticsMonthMapper extends BaseMapper<Examine1StatisticsMonth> {

    /**
     * @param examine1Id
     * @Author: BaiYang
     * @Description: 统计审核1上个月记录
     * @Date: 2019/5/20
     * @return: cn.stylefeng.guns.modular.system.model.Examine1StatisticsMonth
     **/
    Examine1StatisticsMonth statisticExamine1LastMonth(int examine1Id);

    /**
     * @param examine1StatisticsMonths
     * @Author: BaiYang
     * @Description: 插入审核1每月统计记录
     * @Date: 2019/5/20
     * @return: int
     **/
    int insertExamine1StatisticsMonth(List<Examine1StatisticsMonth> examine1StatisticsMonths);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/20
     * @param userId
     * @return: java.util.List<cn.stylefeng.guns.modular.system.model.EditorStatisticsDay>
     **/
    List<Map<String, Object>> selectMonthList(@Param("userId")Integer userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
