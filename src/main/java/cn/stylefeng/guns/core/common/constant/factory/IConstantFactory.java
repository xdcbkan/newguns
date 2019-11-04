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
package cn.stylefeng.guns.core.common.constant.factory;

import cn.stylefeng.guns.modular.system.model.Dict;

import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 获取交易状态名称
     */
    String getTransType(Integer transType);

    /**
     * 获取交易状态名称
     */
    String getTransWaterStatus(Integer transWaterStatus);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * @param withdrawStatus
     * @Author: BaiYang
     * @Description: 获取提现状态名称
     * @Date: 2019/5/13
     * @return: java.lang.String
     **/
    public String getWithdrawStatus(Integer withdrawStatus);

    /**
     * @param timelinessCategory
     * @Author: BaiYang
     * @Description: 获取时效分类名称
     * @Date: 2019/5/21
     * @return: java.lang.String
     **/
    public String getTimelinessCategory(Integer timelinessCategory);

    /**
     * @param projectTypeId
     * @Author: BaiYang
     * @Description: 获取项目分类名称
     * @Date: 2019/5/22
     * @return: java.lang.String
     **/
    public String getProjectType(Integer projectTypeId);

    /**
     * @param taskStatus
     * @Author: BaiYang
     * @Description: 获取任务状态名称
     * @Date: 2019/5/21
     * @return: java.lang.String
     **/
    public String getTaskStatus(Integer taskStatus);

    /**
     * @param articleTypeIds
     * @Author: BaiYang
     * @Description: 根据文章分类ids获取文章分类名称
     * @Date: 2019/5/10
     * @return: java.lang.String
     **/
    String getArticleTypeName(String articleTypeIds);

    /**
     * @param projectTypeIds
     * @Author: BaiYang
     * @Description: 根据项目分类ids获取项目分类名称
     * @Date: 2019/5/23
     * @return: java.lang.String
     **/
    String getProjectTypeName(String projectTypeIds);

    /**
     * 通过文章分类id获取分类名称
     */
    String getArticleTypeNames(Integer articleTypeId);

    /**
     * 通过分类名称获取文章分类id
     */
    Integer getArticleTypeIdByName(String name);

    /**
     * 获取文章状态
     */
    String getArticleStatus(Integer articleStatus);

    /**
     * 获取文章前缀
     */
    String getPrefix(Integer prefix);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 根据字典名称的值获取对应的code
     */
    String getCodeByName(String name);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /**
     * 获取当前操作人的name
     * @return
     */
    String getOperatorName(Integer articleTaskId);

    public String getReleaseTypeName(Integer releaseType);

    public String getIsDownloadName(Integer isDownload);

}
