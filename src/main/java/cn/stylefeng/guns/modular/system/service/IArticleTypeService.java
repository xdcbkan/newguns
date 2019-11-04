package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.ArticleType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 文章分类表 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-09
 */
public interface IArticleTypeService extends IService<ArticleType> {

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取文章分类树列表
     * @Date: 2019/5/13
     * @return: java.util.List<cn.stylefeng.guns.core.common.node.ZTreeNode>
     **/
    List<ZTreeNode> articleTypeTreeList();

    /**
     * @param articleTypeId
     * @Author: BaiYang
     * @Description: 获取文章分类树列表
     * @Date: 2019/5/13
     * @return: java.util.List<cn.stylefeng.guns.core.common.node.ZTreeNode>
     **/
    List<ZTreeNode> articleTypeTreeListByArticleTypeId(String[] articleTypeId);

}
