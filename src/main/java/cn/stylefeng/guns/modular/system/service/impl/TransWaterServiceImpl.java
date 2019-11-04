package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.TransWater;
import cn.stylefeng.guns.modular.system.dao.TransWaterMapper;
import cn.stylefeng.guns.modular.system.service.ITransWaterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 交易流水表 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-14
 */
@Service
public class TransWaterServiceImpl extends ServiceImpl<TransWaterMapper, TransWater> implements ITransWaterService {

    @Override
    public List<Map<String, Object>> selectTransWaterList(Integer userId, Integer createUser, String beginTime, String endTime) {
        return this.baseMapper.selectTransWaterList(userId,createUser,beginTime,endTime);
    }
}
