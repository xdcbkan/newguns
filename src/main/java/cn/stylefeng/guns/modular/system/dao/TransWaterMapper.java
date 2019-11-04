package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.TransWater;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 交易流水表 Mapper 接口
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-14
 */
public interface TransWaterMapper extends BaseMapper<TransWater> {

    /**
     * 交易流水查询
     */
    List<Map<String, Object>> selectTransWaterList(@Param("userId") Integer userId, @Param("createUser") Integer createUser, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


}
