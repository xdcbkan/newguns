package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.WithdrawApplication;
import cn.stylefeng.guns.modular.system.dao.WithdrawApplicationMapper;
import cn.stylefeng.guns.modular.system.service.IWithdrawApplicationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现申请表 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-13
 */
@Service
public class WithdrawApplicationServiceImpl extends ServiceImpl<WithdrawApplicationMapper, WithdrawApplication> implements IWithdrawApplicationService {

    @Override
    public List<Map<String, Object>> selectWithdrawApplications(String name, String beginTime, String endTime, Integer withdrawStatus, Integer userId) {
        return this.baseMapper.selectWithdrawApplications(name, beginTime, endTime, withdrawStatus, userId);
    }
}
