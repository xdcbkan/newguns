package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.ArticleReceive;
import cn.stylefeng.guns.modular.system.dao.ArticleReceiveMapper;
import cn.stylefeng.guns.modular.system.service.IArticleReceiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 领取文章记录表
 * 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-14
 */
@Service
public class ArticleReceiveServiceImpl extends ServiceImpl<ArticleReceiveMapper, ArticleReceive> implements IArticleReceiveService {

    @Override
    public int updateReceive(Integer resultsEnforcement, Date operateTime, Integer articleId, Integer userId) {
        return this.baseMapper.updateReceive(resultsEnforcement, operateTime, articleId, userId);
    }

    @Override
    public int examine1StatisticsReceive(int examiner1Id) {
        return this.baseMapper.examine1StatisticsReceive(examiner1Id);
    }

    @Override
    public int examine1StatisticsPass(int examiner1Id) {
        return this.baseMapper.examine1StatisticsPass(examiner1Id);
    }

    @Override
    public int examine1StatisticsUnPass(int examiner1Id) {
        return this.baseMapper.examine1StatisticsUnPass(examiner1Id);
    }

    @Override
    public int examine1StatisticsExamining(int examiner1Id) {
        return this.baseMapper.examine1StatisticsExamining(examiner1Id);
    }

    @Override
    public int examine1StatisticsAbolish(int examiner1Id) {
        return this.baseMapper.examine1StatisticsAbolish(examiner1Id);
    }

    @Override
    public int examine2StatisticsReceive(int examiner2Id) {
        return this.baseMapper.examine2StatisticsReceive(examiner2Id);
    }

    @Override
    public int examine2StatisticsPass(int examiner2Id) {
        return this.baseMapper.examine2StatisticsPass(examiner2Id);
    }

    @Override
    public int examine2StatisticsUnPass(int examiner2Id) {
        return this.baseMapper.examine2StatisticsUnPass(examiner2Id);
    }

    @Override
    public int examine2StatisticsExamining(int examiner2Id) {
        return this.baseMapper.examine2StatisticsExamining(examiner2Id);
    }

    @Override
    public int examine2StatisticsAbolish(int examiner2Id) {
        return this.baseMapper.examine2StatisticsAbolish(examiner2Id);
    }

    @Override
    public int layoutStatisticReceive(int layoutId) {
        return this.baseMapper.layoutStatisticReceive(layoutId);
    }

    @Override
    public int layoutStatisticArticleNum(int layoutId) {
        return this.baseMapper.layoutStatisticArticleNum(layoutId);
    }

    @Override
    public int layoutStatisticPictureNum(int layoutId) {
        return this.baseMapper.layoutStatisticPictureNum(layoutId);
    }

    @Override
    public int operatorStatisticReceive(int operatorId) {
        return this.baseMapper.operatorStatisticReceive(operatorId);
    }

    @Override
    public int operatorStatisticAbolish(int operatorId) {
        return this.baseMapper.operatorStatisticAbolish(operatorId);
    }
}
