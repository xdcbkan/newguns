package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.ProjectType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 项目类型表 Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-21
 */
public interface ProjectTypeMapper extends BaseMapper<ProjectType> {

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取项目分类下拉列表
     * @Date: 2019/5/13
     * @return: java.util.List<cn.stylefeng.guns.core.common.node.ZTreeNode>
     **/
    List<ZTreeNode> projectTypeTreeList();

    /**
     * @param projectTypeId
     * @Author: BaiYang
     * @Description: 获取项目分类列表树
     * @Date: 2019/5/22
     * @return: java.util.List<cn.stylefeng.guns.core.common.node.ZTreeNode>
     **/
    List<ZTreeNode> projectTypeTreeListByProjectTypeId(String[] projectTypeId);

}
