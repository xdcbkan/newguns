package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.ProjectType;
import cn.stylefeng.guns.modular.system.dao.ProjectTypeMapper;
import cn.stylefeng.guns.modular.system.service.IProjectTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目类型表 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-21
 */
@Service
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeMapper, ProjectType> implements IProjectTypeService {

    @Override
    public List<ZTreeNode> projectTypeTreeList() {
        return this.baseMapper.projectTypeTreeList();
    }

    @Override
    public List<ZTreeNode> projectTypeTreeListByProjectTypeId(String[] projectTypeId) {
        return this.baseMapper.projectTypeTreeListByProjectTypeId(projectTypeId);
    }
}
