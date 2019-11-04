package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Program: article_manage
 * @ClassName: WithdrawApplicationWarpper
 * @Description: 提现申请的包装类
 * @Author: BaiYang
 * @Create: 2019-05-13 17:17
 * @Version: 1.0
 **/
public class WithdrawApplicationWarpper extends BaseControllerWrapper {
    public WithdrawApplicationWarpper(Map<String, Object> single) {
        super(single);
    }

    public WithdrawApplicationWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public WithdrawApplicationWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public WithdrawApplicationWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("realName", ConstantFactory.me().getUserNameById((Integer) map.get("user_id")));
        map.put("withdrawStatusName", ConstantFactory.me().getWithdrawStatus((Integer) map.get("withdraw_status")));
    }
}
