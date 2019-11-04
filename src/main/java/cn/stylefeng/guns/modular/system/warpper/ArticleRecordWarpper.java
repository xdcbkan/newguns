package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @author: zpy
 * @time: 2019/5/24
 */
public class ArticleRecordWarpper extends BaseControllerWrapper {

    public ArticleRecordWarpper(Map<String, Object> single) {
        super(single);
    }

    public ArticleRecordWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ArticleRecordWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ArticleRecordWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
    }
}
