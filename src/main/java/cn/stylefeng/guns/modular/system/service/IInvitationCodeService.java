package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.InvitationCode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户注册邀请码表 服务类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-09
 */
public interface IInvitationCodeService extends IService<InvitationCode> {
    /**
     * @Author: BaiYang
     * @Description: 获取激活码列表
     * @Date: 2019/5/9
     * @param
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> selectInvitationCodes();

    /**
     * @Author: BaiYang
     * @Description: 邀请码可用次数-1
     * @Date: 2019/5/9
     * @param id
     * @return: int
     **/
    int reduceNumber(Integer id);

    InvitationCode selectByCode(String invitationCode);
}
