package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.NewArticle;
import cn.stylefeng.guns.modular.system.dao.NewArticleMapper;
import cn.stylefeng.guns.modular.system.service.INewArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录最新版本的文章 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-13
 */
@Service
public class NewArticleServiceImpl extends ServiceImpl<NewArticleMapper, NewArticle> implements INewArticleService {

    @Override
    public List<Map<String, Object>> selectArticleList(String newMainTitle, Integer articleTypeId, Integer createUserId, Integer prescription, Integer[] articleStatus) {
        return this.baseMapper.selectArticleList(newMainTitle, articleTypeId, createUserId, prescription, articleStatus);
    }

    @Override
    public int selectArticleCount(Integer createUserId) {
        return this.baseMapper.selectArticleCount(createUserId);
    }

    @Override
    public int receiveArticle(Integer newArticleId, Integer examineId, Integer examine2Id, Date updateTime, Integer articleStatus) {
        return this.baseMapper.receiveArticle(newArticleId, examineId, examine2Id, updateTime, articleStatus);
    }

    @Override
    public List<Map<String, Object>> getArticleList(String newMainTitle, Integer prescription, Integer articleStatus, String[] articleTypeId) {
        return this.baseMapper.getArticleList(newMainTitle, prescription, articleStatus, articleTypeId);
    }

    @Override
    public List<Map<String, Object>> getOperatorArticleList(String title, Integer prescription, String[] projectTypeId, Integer articleTypeId) {
        return this.baseMapper.getOperatorArticleList(title, prescription, projectTypeId, articleTypeId);
    }

    @Override
    public List<Map<String, Object>> getOperatorAvailableArticleList(String title, Integer prescription, String[] projectTypeId, String[] articleTypeId) {
        return this.baseMapper.getOperatorAvailableArticleList(title, prescription, projectTypeId, articleTypeId);
    }

    @Override
    public List<Map<String, Object>> getlayoutCompletedArticleList(String title, Integer prescription,  String[] articleTypeId) {
        return this.baseMapper.getlayoutCompletedArticleList(title, prescription,articleTypeId);
    }

    @Override
    public List<Map<String, Object>> selectMyArticle(String mainTitle, Integer prescription, Integer articleTypeId, Integer articleStatus, Integer examine1Id, Integer examine2Id, Integer layoutId, Integer operateId) {
        return this.baseMapper.selectMyArticle(mainTitle, prescription, articleTypeId, articleStatus, examine1Id, examine2Id, layoutId, operateId);
    }

    @Override
    public int examineReturn(Integer newArticleId, String content, String returnMessage, Integer articleStatus, Date updateTime) {
        return this.baseMapper.examineReturn(newArticleId, content, returnMessage, articleStatus, updateTime);
    }

    @Override
    public int examinePass(Integer newArticleId, Integer articleStatus, Date updateTime) {
        return this.baseMapper.examinePass(newArticleId, articleStatus, updateTime);
    }

    @Override
    public int editorStatisticsSubmit(int editorId) {
        return this.baseMapper.editorStatisticsSubmit(editorId);
    }

    @Override
    public int editorStatisticsExamine1Return(int editorId) {
        return this.baseMapper.editorStatisticsExamine1Return(editorId);
    }

    @Override
    public int editorStatisticsExamine2Return(int editorId) {
        return this.baseMapper.editorStatisticsExamine2Return(editorId);
    }

    @Override
    public int editorStatisticsExamine1ReturnEdit1(int editorId) {
        return this.baseMapper.editorStatisticsExamine1ReturnEdit1(editorId);
    }

    @Override
    public int editorStatisticsExamine1ReturnEdit2(int editorId) {
        return this.baseMapper.editorStatisticsExamine1ReturnEdit2(editorId);
    }

    @Override
    public int editorStatisticsExamine2ReturnEdit1(int editorId) {
        return this.baseMapper.editorStatisticsExamine2ReturnEdit1(editorId);
    }

    @Override
    public int editorStatisticsExamine2ReturnEdit2(int editorId) {
        return this.baseMapper.editorStatisticsExamine2ReturnEdit2(editorId);
    }

    @Override
    public int editorStatisticsAbolish(int editorId) {
        return this.baseMapper.editorStatisticsAbolish(editorId);
    }

    @Override
    public int editorStatisticsPass(int editorId) {
        // 当天审核2通过数
        int examine2Pass = this.baseMapper.editorStatisticsExamine2Pass(editorId);
        // 当天运营作废数
        int operatorAbolish = this.baseMapper.editorStatisticsOperatorAbolish(editorId);
        return examine2Pass - operatorAbolish;
    }

    @Override
    public int setReceiveArticle(Integer newArticleId, Integer layoutId, Integer operateId, Date updateTime, Integer articleStatus) {
        return this.baseMapper.setReceiveArticle(newArticleId, layoutId, operateId, updateTime, articleStatus);
    }

    @Override
    public int cancelArticle(Integer id, String returnMessage) {
        return this.baseMapper.cancelArticle(id, returnMessage);
    }

    @Override
    public List<Map<String, Object>> getOperationRecord(String condition, Integer articleTypeId, Integer prescription, Integer userId) {
        return this.baseMapper.getOperationRecord(condition, articleTypeId, prescription, userId);
    }
}
