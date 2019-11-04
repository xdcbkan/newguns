package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.WithdrawApplication;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现申请表 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-13
 */
public interface IWithdrawApplicationService extends IService<WithdrawApplication> {

    /**
     * 根据条件查询提现申请列表
     */
    List<Map<String, Object>> selectWithdrawApplications(String name, String beginTime, String endTime, Integer withdrawStatus, Integer userId);

}
