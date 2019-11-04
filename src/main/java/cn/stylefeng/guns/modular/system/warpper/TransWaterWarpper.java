package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ZhangLu
 * @time: 2019/4/17 14:10
 */
public class TransWaterWarpper extends BaseControllerWrapper {
    public TransWaterWarpper(Map<String, Object> single) {
        super(single);
    }

    public TransWaterWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public TransWaterWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public TransWaterWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("transStatus", ConstantFactory.me().getTransWaterStatus((Integer) map.get("trans_status")));
        map.put("transTypeName", ConstantFactory.me().getTransType((Integer) map.get("trans_type")));
        map.put("createName", ConstantFactory.me().getUserNameById((Integer) map.get("create_user")));
        map.put("userName", ConstantFactory.me().getUserNameById((Integer) map.get("user_id")));
    }
}
