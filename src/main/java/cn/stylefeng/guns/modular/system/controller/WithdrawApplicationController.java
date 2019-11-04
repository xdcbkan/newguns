package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.model.TransWater;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.ITransWaterService;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.guns.modular.system.warpper.WithdrawApplicationWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.WithdrawApplication;
import cn.stylefeng.guns.modular.system.service.IWithdrawApplicationService;

import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DateTime.now;


/**
 * 提现申请控制器
 *
 * @author fengshuonan
 * @Date 2019-05-13 14:27:44
 */
@Controller
@RequestMapping("/withdrawApplication")
public class WithdrawApplicationController extends BaseController {
    // 文编提现申请、提现申请列表
    // 后台财务 提现申请列表 修改提现申请、退款   1h

    private String PREFIX = "/system/withdrawApplication/";

    @Autowired
    private IWithdrawApplicationService withdrawApplicationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITransWaterService transWaterService;

    /**
     * 跳转到提现申请首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "withdrawApplication.html";
    }

    /**
     * 跳转到添加提现申请
     */
    @RequestMapping("/withdrawApplication_add")
    public String withdrawApplicationAdd() {
        return PREFIX + "withdrawApplication_add.html";
    }

    /**
     * 跳转到修改提现申请
     */
    @RequestMapping("/withdrawApplication_update/{withdrawApplicationId}")
    public String withdrawApplicationUpdate(@PathVariable Integer withdrawApplicationId, Model model) {
        WithdrawApplication withdrawApplication = withdrawApplicationService.selectById(withdrawApplicationId);
        model.addAttribute("item", withdrawApplication);
        LogObjectHolder.me().set(withdrawApplication);
        return PREFIX + "withdrawApplication_edit.html";
    }

    /**
     * 跳转到失败反馈
     */
    @RequestMapping("/withdrawApplication_paymentFailed/{withdrawApplicationId}")
    public String withdrawApplicationPaymentFailed(@PathVariable Integer withdrawApplicationId, Model model) {
        model.addAttribute("withdrawApplicationId", withdrawApplicationId);
        return PREFIX + "withdrawApplication_paymentFailed.html";
    }

    /**
     * 获取提现申请列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer withdrawStatus) {
        // 文编，查看自己的
        if (ShiroKit.hasRole(Const.EDITOR_NAME)) {
            Integer id = ShiroKit.getUser().getId();
            List<Map<String, Object>> withdrawApplications = withdrawApplicationService.selectWithdrawApplications(name, beginTime, endTime, withdrawStatus, id);
            return new WithdrawApplicationWarpper(withdrawApplications).wrap();
        }
        // 后台财务、管理员，看全部
        if (ShiroKit.hasRole(Const.BACKSTAGE_FINANCE_NAME) || ShiroKit.hasRole(Const.ADMIN_NAME)) {
            List<Map<String, Object>> withdrawApplications = withdrawApplicationService.selectWithdrawApplications(name, beginTime, endTime, withdrawStatus, null);
            return new WithdrawApplicationWarpper(withdrawApplications).wrap();
        }
        return null;
    }

    /**
     * 新增提现申请
     */
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    @Transactional
    public Object add(WithdrawApplication withdrawApplication) {
        ResponseData responseData = new ResponseData();
        try {
            // 提现金额
            Integer amount = withdrawApplication.getAmount();
            System.out.println("amount: " + amount);
            // 是否填写提现金额
            if (ToolUtil.isEmpty(amount) || amount.equals(0)) {
                responseData.setSuccess(false);
                responseData.setCode(20000);
                responseData.setMessage("请填写提现金额");
                return responseData;
            }
            // 获取用户id
            ShiroUser user = ShiroKit.getUser();
            Integer id = user.getId();
            // 获取当前用户信息
            User currentUser = userService.selectById(id);
            // 判断提现金额是否在积分余额范围内
            Integer balance = currentUser.getBalance();
            if (amount > balance) {
                responseData.setSuccess(false);
                responseData.setCode(20000);
                responseData.setMessage("余额不足");
                return responseData;
            }
            // 查看该用户提现资料是否完善
            String name = currentUser.getName();
            String aliPay = currentUser.getAlipay();
            if (ToolUtil.isEmpty(name) || ToolUtil.isEmpty(aliPay)) {
                responseData.setSuccess(false);
                responseData.setCode(20000);
                responseData.setMessage("请先完善提现所需资料");
                return responseData;
            }

            // 补全信息,新增提现申请
            withdrawApplication.setUserId(id);
            withdrawApplication.setCreateTime(now());
            withdrawApplication.setAliPay(aliPay);
            boolean b1 = withdrawApplicationService.insert(withdrawApplication);
            // 扣积分余额
            EntityWrapper<User> ew = new EntityWrapper<>();
            ew.eq("id", id);
            String setSql = "balance = balance -" + amount;
            boolean b2 = userService.updateForSet(setSql, ew);
            // 添加交易流水
            TransWater transWater = new TransWater();
            transWater.setCreateUser(id);
            transWater.setUserId(id);
            transWater.setAmount(amount);
            // 1 现金 2积分
            transWater.setTransType(2);
            // 1消费、2充值、3文编结算、4文编提现、5提现失败退款
            transWater.setTransStatus(4);
            transWater.setPointsBalance(balance - amount);
            transWater.setCreateTime(now());
            boolean b3 = transWaterService.insert(transWater);
            if (b1 && b2 && b3) {
                return SUCCESS_TIP;
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("服务器运行异常");
            return responseData;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("服务器异常");
            return responseData;
        }
    }

    /**
     * 删除提现申请
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer withdrawApplicationId) {
        withdrawApplicationService.deleteById(withdrawApplicationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改提现申请
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WithdrawApplication withdrawApplication) {
        withdrawApplicationService.updateById(withdrawApplication);
        return SUCCESS_TIP;
    }

    /**
     * @param withdrawApplicationId
     * @Author: BaiYang
     * @Description: 支付成功
     * @Date: 2019/5/14
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/paymentSuccessful")
    @ResponseBody
    public Object paymentSuccessful(@RequestParam("withdrawApplicationId") Integer withdrawApplicationId) {
        ResponseData responseData = new ResponseData();
        // 获取提现申请信息
        WithdrawApplication currentWithdrawApplication = withdrawApplicationService.selectById(withdrawApplicationId);
        // 判断提现申请状态
        Integer withdrawStatus = currentWithdrawApplication.getWithdrawStatus();
        if (withdrawStatus.equals(1)) {
            // 更新提现申请
            EntityWrapper<WithdrawApplication> ew = new EntityWrapper<>();
            ew.eq("id", withdrawApplicationId);
            String setSql = "withdraw_status = " + 2;
            boolean temp = withdrawApplicationService.updateForSet(setSql, ew);
            if (temp) {
                return SUCCESS_TIP;
            }
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("更新失败");
            return responseData;
        }
        responseData.setMessage("请正确操作");
        return responseData;
    }


    /**
     * @param withdrawApplication
     * @Author: BaiYang
     * @Description: 支付失败反馈
     * @Date: 2019/5/14
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/paymentFailed")
    @ResponseBody
    @Transactional
    public Object paymentFailed(WithdrawApplication withdrawApplication) {
        ResponseData responseData = new ResponseData();
        try {
            // 操作人id
            Integer createUserId = ShiroKit.getUser().getId();
            Integer withdrawApplicationId = withdrawApplication.getId();
            // 获取提现申请信息
            WithdrawApplication currentWithdrawApplication = withdrawApplicationService.selectById(withdrawApplicationId);
            Integer amount = currentWithdrawApplication.getAmount();
            Integer withdrawStatus = currentWithdrawApplication.getWithdrawStatus();
            Integer userId = currentWithdrawApplication.getUserId();
            User user = userService.selectById(userId);
            Integer balance = user.getBalance();
            // 判断提现申请状态
            if (withdrawStatus.equals(1)) {
                // 更新提现申请
                String errorMessage = withdrawApplication.getErrorMessage();
                EntityWrapper<WithdrawApplication> ew = new EntityWrapper<>();
                ew.eq("id", withdrawApplicationId);
                String setSql = "withdraw_status = " + 3 + ",error_message = '" + errorMessage + "'";
                boolean b1 = withdrawApplicationService.updateForSet(setSql, ew);
                // 退款
                EntityWrapper<User> ew2 = new EntityWrapper<>();
                ew.eq("id", userId);
                String setSql2 = "balance = balance +" + amount;
                boolean b2 = userService.updateForSet(setSql2, ew2);
                // 添加退款交易流水
                TransWater transWater = new TransWater();
                transWater.setCreateUser(createUserId);
                transWater.setUserId(userId);
                transWater.setAmount(amount);
                // 1 现金 2积分
                transWater.setTransType(2);
                // 1消费、2充值、3文编结算、4文编提现、5提现失败退款
                transWater.setTransStatus(5);
                transWater.setPointsBalance(balance + amount);
                transWater.setCreateTime(now());
                boolean b3 = transWaterService.insert(transWater);

                if (b1 && b2 && b3) {
                    return SUCCESS_TIP;
                }
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                responseData.setSuccess(false);
                responseData.setCode(20000);
                responseData.setMessage("服务器运行异常");
                return responseData;
            }
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("请正确操作");
            return responseData;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("服务器运行异常");
            return responseData;
        }
    }


}
