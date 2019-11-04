package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.ArticleType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文章分类表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-09
 */
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取文章分类下拉列表
     * @Date: 2019/5/13
     * @return: java.util.List<cn.stylefeng.guns.core.common.node.ZTreeNode>
     **/
    List<ZTreeNode> articleTypeTreeList();

    /**
     * @param articleTypeId
     * @Author: BaiYang
     * @Description: 获取文章分类列表树
     * @Date: 2019/5/13
     * @return: java.util.List<cn.stylefeng.guns.core.common.node.ZTreeNode>
     **/
    List<ZTreeNode> articleTypeTreeListByArticleTypeId(String[] articleTypeId);

    /**
     * 根据name查询ID
     *
     * @param name
     * @return
     */
    ArticleType findByName(String name);

}
