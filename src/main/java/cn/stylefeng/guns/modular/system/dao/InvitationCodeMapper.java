package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.InvitationCode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户注册邀请码表 Mapper 接口
 * </p>
 *
 * @author BaiYang
 * @since 2019-05-09
 */
public interface InvitationCodeMapper extends BaseMapper<InvitationCode> {

    /**
     * @Author: BaiYang
     * @Description: 查询邀请码列表
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

    InvitationCode selectByCode(@Param("invitationCode") String invitationCode);

}
