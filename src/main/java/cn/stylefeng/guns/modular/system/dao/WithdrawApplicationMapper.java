package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WithdrawApplication;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现申请表 Mapper 接口
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-13
 */
public interface WithdrawApplicationMapper extends BaseMapper<WithdrawApplication> {

    /**
     * 根据条件查询提现申请列表
     */
    List<Map<String, Object>> selectWithdrawApplications(@Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("withdrawStatus") Integer withdrawStatus, @Param("userId") Integer userId);


}
