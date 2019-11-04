package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.HistoricalArticle;
import cn.stylefeng.guns.modular.system.dao.HistoricalArticleMapper;
import cn.stylefeng.guns.modular.system.service.IHistoricalArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录文章历史版本 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-16
 */
@Service
public class HistoricalArticleServiceImpl extends ServiceImpl<HistoricalArticleMapper, HistoricalArticle> implements IHistoricalArticleService {

    @Override
    public int updateHistory(Integer newArticleId) {
        return this.baseMapper.updateHistory(newArticleId);
    }

    @Override
    public List<Map<String, Object>> selectArticleList(String mainTitle, Integer articleTypeId, Integer prescription, Integer articleStatus) {
        return this.baseMapper.selectArticleList(mainTitle,articleTypeId,prescription,articleStatus);
    }

    @Override
    public List<Map<String, Object>> getPersonalOperationRecord(String condition, Integer articleTypeId, Integer prescription, Integer userId) {
        return this.baseMapper.getPersonalOperationRecord(condition, articleTypeId, prescription, userId);
    }

    @Override
    public List<Map<String, Object>> getList(Integer newArticleId) {
        return this.baseMapper.getList(newArticleId);
    }
}
