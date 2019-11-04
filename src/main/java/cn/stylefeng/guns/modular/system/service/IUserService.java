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
package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface IUserService extends IService<User> {

    /**
     * 修改用户状态
     */
    int setStatus(Integer userId, int status);

    /**
     * 修改密码
     */
    int changePwd(Integer userId, String pwd);

    /**
     * 根据条件查询用户列表
     */
    List<Map<String, Object>> selectUsers(DataScope dataScope, String name, String beginTime, String endTime, Integer deptid);

    /**
     * 设置用户的角色
     */
    int setRoles(Integer userId, String roleIds);

    /**
     * @param userId
     * @param articleTypeIds
     * @Author: BaiYang
     * @Description: 设置用户可查看的文章分类
     * @Date: 2019/5/13
     * @return: int
     **/
    int setArticleTypes(Integer userId, String articleTypeIds);

    /**
     * @param userId
     * @param projectTypeIds
     * @Author: BaiYang
     * @Description: 设置用户可运营的项目分类
     * @Date: 2019/5/23
     * @return: int
     **/
    int setProjectTypes(Integer userId, String projectTypeIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(String account);

    /**
     * 根据userNumber获取用户
     * @param usernumber
     * @return
     */
    User selectByUserNumber(String usernumber);

    int selectEdit();

    /**
     * 减少一次剩余文章领取数
     */
    int setUserReceiveNum(Integer userId);

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取所有文编的id
     * @Date: 2019/5/16
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> selectAllEditorId();

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取所有审核1的id
     * @Date: 2019/5/17
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> selectAllExaminer1Id();

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取所有审核2的id
     * @Date: 2019/5/17
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> selectAllExaminer2Id();

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取所有配图的id
     * @Date: 2019/5/17
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> selectAllLayoutId();

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取所有运营的id
     * @Date: 2019/5/17
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> selectAllOperatorId();

    /**
     * @param
     * @Author: BaiYang
     * @Description: 获取所有单独设置文章领取上限人员的id
     * @Date: 2019/5/17
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> selectAllSpecialPopulationsIds();

    /**
     * @param ids
     * @Author: BaiYang
     * @Description: 重置单独设置文章领取上限人员的领取上限
     * @Date: 2019/5/17
     * @return: int
     **/
    int resetResidueReceiveNumSpecialPopulations(List<Integer> ids);

    /**
     * @param roleId
     * @Author: BaiYang
     * @Description: 根据角色重置没有单独设置文章领取上限人员的领取上限
     * @Date: 2019/5/17
     * @return: int
     **/
    int resetResidueReceiveNumByRoleId(int roleId);

}
