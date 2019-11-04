package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.model.InvitationCode;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IInvitationCodeService;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static cn.hutool.core.date.DateTime.now;

/**
 * @description:
 * @author: ZhangLu
 * @time: 2019/5/10 16:32
 */
@Controller
public class RegisterController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IInvitationCodeService invitationCodeService;

//    private String PREFIX = "/system/registers/";

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/register")
    public String login() {
        return "/register.html";
    }

    /**
     * 点击注册执行的动作
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object loginVali(Model model) {

        String username = super.getPara("username").trim();
        String name = super.getPara("name").trim();
        String password = super.getPara("password").trim();
        String truePassword = super.getPara("truePassword").trim();
        String invitationCode = super.getPara("invitationCode").trim();

//        验证用户名是否已存在
        User byAccount = userService.getByAccount(username);

        Integer editCount = userService.selectEdit()+10001;
        InvitationCode invitationCode1 = invitationCodeService.selectByCode(invitationCode);

        if (byAccount != null){
            model.addAttribute("tips", "用户名已被占用!");
            return "/register.html";
        }
        if (!password.equals(truePassword)||!truePassword.equals(password)){
            model.addAttribute("tips", "两次密码前后不一致!");
            return "/register.html";
        }
        if (invitationCode1 == null){
            model.addAttribute("tips", "邀请码不存在!");
            return "/register.html";
        }
        if ( invitationCode1.getAmount() == 0){
            model.addAttribute("tips", "邀请码次数已用完,请联系管理员更换邀请码!");
            return "/register.html";
        }
        User user = new User();
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setAccount(username);
        user.setPassword(ShiroKit.md5(truePassword, user.getSalt()));
        user.setUserid(invitationCode1.getCreateUserId());
        user.setCreatetime(now());
        user.setName(name);
        user.setUsernumber(editCount);
        user.setRoleid("6");
        user.setStatus(ManagerStatus.OK.getCode());
        userService.insert(user);
//        验证邀请码是否正确并扣除邀请码次数
        invitationCodeService.reduceNumber(invitationCode1.getId());
        return REDIRECT + "/login";
    }
}
