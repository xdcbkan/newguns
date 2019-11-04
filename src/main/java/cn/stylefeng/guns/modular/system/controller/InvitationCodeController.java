package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.warpper.InvitationCodeWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.InvitationCode;
import cn.stylefeng.guns.modular.system.service.IInvitationCodeService;

import java.util.List;
import java.util.Map;

/**
 * 注册邀请码管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-09 13:59:41
 */
@Controller
@RequestMapping("/invitationCode")
public class InvitationCodeController extends BaseController {

    private String PREFIX = "/system/invitationCode/";

    @Autowired
    private IInvitationCodeService invitationCodeService;

    /**
     * 跳转到注册邀请码管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "invitationCode.html";
    }

    /**
     * 跳转到添加注册邀请码管理
     */
    @RequestMapping("/invitationCode_add")
    public String invitationCodeAdd() {
        return PREFIX + "invitationCode_add.html";
    }

    /**
     * 跳转到修改注册邀请码管理
     */
    @RequestMapping("/invitationCode_update/{invitationCodeId}")
    public String invitationCodeUpdate(@PathVariable Integer invitationCodeId, Model model) {
        InvitationCode invitationCode = invitationCodeService.selectById(invitationCodeId);
        model.addAttribute("item", invitationCode);
        LogObjectHolder.me().set(invitationCode);
        return PREFIX + "invitationCode_edit.html";
    }

    /**
     * 获取注册邀请码管理列表
     * return invitationCodeService.selectList(null);
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> codes = invitationCodeService.selectInvitationCodes();
        return new InvitationCodeWarpper(codes).wrap();
    }

    /**
     * 新增注册邀请码管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InvitationCode invitationCode) {
        // 获取介绍人id
        ShiroUser user = ShiroKit.getUser();
        Integer userId = user.getId();
        // 生成邀请码
        String code = RandomStringUtils.randomAlphanumeric(6);

        // 完善信息
        invitationCode.setCreateUserId(userId);
        invitationCode.setInvitationCode(code);
        invitationCodeService.insert(invitationCode);
        return SUCCESS_TIP;
    }

    /**
     * 删除注册邀请码管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer invitationCodeId) {
        invitationCodeService.deleteById(invitationCodeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改注册邀请码管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InvitationCode invitationCode) {
        invitationCodeService.updateById(invitationCode);
        return SUCCESS_TIP;
    }

    /**
     * 注册邀请码管理详情
     */
    @RequestMapping(value = "/detail/{invitationCodeId}")
    @ResponseBody
    public Object detail(@PathVariable("invitationCodeId") Integer invitationCodeId) {
        return invitationCodeService.selectById(invitationCodeId);
    }
}
