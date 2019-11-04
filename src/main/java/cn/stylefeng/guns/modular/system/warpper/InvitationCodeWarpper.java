package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Program: article_manage
 * @ClassName: InvitationCodeWarpper
 * @Description: TODO
 * @Author: BaiYang
 * @Create: 2019-05-09 15:06
 * @Version: 1.0
 **/
public class InvitationCodeWarpper extends BaseControllerWrapper {
    public InvitationCodeWarpper(Map<String, Object> single) {
        super(single);
    }

    public InvitationCodeWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public InvitationCodeWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public InvitationCodeWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("createUserName", ConstantFactory.me().getUserNameById((Integer) map.get("create_user_id")));
    }
}
