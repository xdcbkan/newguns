package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.TransWater;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-14
 */
public interface ITransWaterService extends IService<TransWater> {

    /**
     * 交易流水查询
     */
    List<Map<String, Object>> selectTransWaterList(Integer userId,Integer createUser,String beginTime,String endTime);


}
