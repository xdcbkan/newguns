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
 * @time: 2019/5/20 14:18
 */
public class StatisticsWarpper extends BaseControllerWrapper {
    public StatisticsWarpper(Map<String, Object> single) {
        super(single);
    }

    public StatisticsWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public StatisticsWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public StatisticsWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("userName", ConstantFactory.me().getUserNameById((Integer) map.get("user_id")));
    }
}
