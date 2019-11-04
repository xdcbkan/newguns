package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.dao.ArticleRecordMapper;
import cn.stylefeng.guns.modular.system.model.ArticleRecord;
import cn.stylefeng.guns.modular.system.model.NewArticle;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IArticleRecordService;
import cn.stylefeng.guns.modular.system.service.INewArticleService;
import cn.stylefeng.guns.modular.system.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DateTime.now;

/**
 * 文章记录 服务实现类
 *
 * @author zpy
 * @since 2019-09-24
 */
@Service
public class ArticleRecordServiceImpl extends ServiceImpl<ArticleRecordMapper, ArticleRecord> implements IArticleRecordService {

    @Autowired
    private IUserService userService;
    @Autowired
    private INewArticleService newArticleService;

    @Override
    public List<Map<String, Object>> selectArticleList(String articleNum,String name) {
        return this.baseMapper.selectArticleList(articleNum,name);
    }

    @Override
    public int addArticleRecord(Long articleNum, String operationType) {
        Integer loginUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(loginUserId);
        NewArticle article = new NewArticle();
        article.setArticleNum(articleNum);
        NewArticle newArticle = newArticleService.selectOne(new EntityWrapper<>(article));
        ArticleRecord articleRecord = new ArticleRecord();
        articleRecord.setArticleNum(articleNum);
        if (newArticle.getNewMainTitle() != null && !newArticle.getNewMainTitle().isEmpty()) {
            articleRecord.setMainTitle(newArticle.getNewMainTitle());
        } else {
            articleRecord.setMainTitle(newArticle.getMainTitle());
        }
        if (newArticle.getNewSubheading() != null && !newArticle.getNewSubheading().isEmpty()) {
            articleRecord.setSubheading(newArticle.getNewSubheading());
        } else {
            articleRecord.setSubheading(newArticle.getSubheading());
        }
        articleRecord.setOperator(user.getName());
        articleRecord.setOperationType(operationType);
        articleRecord.setCreateTime(now());
        articleRecord.setOperationContent(user.getName() + operationType + "了文章");
        return this.baseMapper.insert(articleRecord);
    }

}
