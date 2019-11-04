/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.dao.UserMapper;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int setStatus(Integer userId, int status) {
        return this.baseMapper.setStatus(userId, status);
    }

    @Override
    public int changePwd(Integer userId, String pwd) {
        return this.baseMapper.changePwd(userId, pwd);
    }

    @Override
    public List<Map<String, Object>> selectUsers(DataScope dataScope, String name, String beginTime, String endTime, Integer deptid) {
        return this.baseMapper.selectUsers(dataScope, name, beginTime, endTime, deptid);
    }

    @Override
    public int setRoles(Integer userId, String roleIds) {
        return this.baseMapper.setRoles(userId, roleIds);
    }

    @Override
    public int setArticleTypes(Integer userId, String articleTypeIds) {
        return this.baseMapper.setArticleTypes(userId, articleTypeIds);
    }

    @Override
    public int setProjectTypes(Integer userId, String projectTypeIds) {
        return this.baseMapper.setProjectTypes(userId, projectTypeIds);
    }

    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }

    @Override
    public User selectByUserNumber(String usernumber) {
        return this.baseMapper.selectByUserNumber(usernumber);
    }

    @Override
    public int selectEdit() {
        return this.baseMapper.selectEdit();
    }

    @Override
    public int setUserReceiveNum(Integer userId) {
        return this.baseMapper.setUserReceiveNum(userId);
    }

    @Override
    public List<Integer> selectAllEditorId() {
        return this.baseMapper.selectAllEditorId();
    }

    @Override
    public List<Integer> selectAllExaminer1Id() {
        return this.baseMapper.selectAllExaminer1Id();
    }

    @Override
    public List<Integer> selectAllExaminer2Id() {
        return this.baseMapper.selectAllExaminer2Id();
    }

    @Override
    public List<Integer> selectAllLayoutId() {
        return this.baseMapper.selectAllLayoutId();
    }

    @Override
    public List<Integer> selectAllOperatorId() {
        return this.baseMapper.selectAllOperatorId();
    }

    @Override
    public List<Integer> selectAllSpecialPopulationsIds() {
        return this.baseMapper.selectAllSpecialPopulationsIds();
    }

    @Override
    public int resetResidueReceiveNumSpecialPopulations(List<Integer> ids) {
        return this.baseMapper.resetResidueReceiveNumSpecialPopulations(ids);
    }

    @Override
    public int resetResidueReceiveNumByRoleId(int roleId) {
        return this.baseMapper.resetResidueReceiveNumByRoleId(roleId);
    }
}
