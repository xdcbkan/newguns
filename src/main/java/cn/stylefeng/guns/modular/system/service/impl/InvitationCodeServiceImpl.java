package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.InvitationCode;
import cn.stylefeng.guns.modular.system.dao.InvitationCodeMapper;
import cn.stylefeng.guns.modular.system.service.IInvitationCodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户注册邀请码表 服务实现类
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-09
 */
@Service
public class InvitationCodeServiceImpl extends ServiceImpl<InvitationCodeMapper, InvitationCode> implements IInvitationCodeService {

    @Override
    public List<Map<String, Object>> selectInvitationCodes() {
        return this.baseMapper.selectInvitationCodes();
    }

    @Override
    public int reduceNumber(Integer id) {
        return this.baseMapper.reduceNumber(id);
    }

    @Override
    public InvitationCode selectByCode(String invitationCode) {
        return this.baseMapper.selectByCode(invitationCode);
    }
}
