package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.ArticleType;
import cn.stylefeng.guns.modular.system.dao.ArticleTypeMapper;
import cn.stylefeng.guns.modular.system.service.IArticleTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章分类表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-09
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements IArticleTypeService {

    @Override
    public List<ZTreeNode> articleTypeTreeList() {
        return this.baseMapper.articleTypeTreeList();
    }

    @Override
    public List<ZTreeNode> articleTypeTreeListByArticleTypeId(String[] articleTypeId) {
        return this.baseMapper.articleTypeTreeListByArticleTypeId(articleTypeId);
    }
}
