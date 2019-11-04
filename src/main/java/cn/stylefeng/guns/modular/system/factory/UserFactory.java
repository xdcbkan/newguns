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
package cn.stylefeng.guns.modular.system.factory;

import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.transfer.UserDto;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static User createUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            return user;
        }
    }

    public static User editUser(UserDto newUser, User oldUser) {
        if (newUser == null || oldUser == null) {
            return oldUser;
        } else {
            if (ToolUtil.isNotEmpty(newUser.getAvatar())) {
                oldUser.setAvatar(newUser.getAvatar());
            }
            if (ToolUtil.isNotEmpty(newUser.getName())) {
                oldUser.setName(newUser.getName());
            }
            if (ToolUtil.isNotEmpty(newUser.getBirthday())) {
                oldUser.setBirthday(newUser.getBirthday());
            }
            if (ToolUtil.isNotEmpty(newUser.getDeptid())) {
                oldUser.setDeptid(newUser.getDeptid());
            }
            if (ToolUtil.isNotEmpty(newUser.getSex())) {
                oldUser.setSex(newUser.getSex());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmail())) {
                oldUser.setEmail(newUser.getEmail());
            }
            if (ToolUtil.isNotEmpty(newUser.getPhone())) {
                oldUser.setPhone(newUser.getPhone());
            }
            if (ToolUtil.isNotEmpty(newUser.getIdcard())) {
                oldUser.setIdcard(newUser.getIdcard());
            }
            if (ToolUtil.isNotEmpty(newUser.getAlipay())) {
                oldUser.setAlipay(newUser.getAlipay());
            }
            if (ToolUtil.isNotEmpty(newUser.getReceivenum())) {
                oldUser.setReceivenum(newUser.getReceivenum());
            }
            if (ToolUtil.isNotEmpty(newUser.getPrice())) {
                oldUser.setPrice(newUser.getPrice());
            }
            if (ToolUtil.isNotEmpty(newUser.getOfficeAddress())) {
                oldUser.setOfficeAddress(newUser.getOfficeAddress());
            }
            if (ToolUtil.isNotEmpty(newUser.getEntryTime())) {
                oldUser.setEntryTime(newUser.getEntryTime());
            }
            if (ToolUtil.isNotEmpty(newUser.getWechatid())) {
                oldUser.setWechatid(newUser.getWechatid());
            }
            if (ToolUtil.isNotEmpty(newUser.getQqid())) {
                oldUser.setQqid(newUser.getQqid());
            }
            if (ToolUtil.isNotEmpty(newUser.getSinaid())) {
                oldUser.setSinaid(newUser.getSinaid());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmployeeType())) {
                oldUser.setEmployeeType(newUser.getEmployeeType());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmployeeStatus())) {
                oldUser.setEmployeeStatus(newUser.getEmployeeStatus());
            }
            if (ToolUtil.isNotEmpty(newUser.getNation())) {
                oldUser.setNation(newUser.getNation());
            }
            if (ToolUtil.isNotEmpty(newUser.getIdAddress())) {
                oldUser.setIdAddress(newUser.getIdAddress());
            }
            if (ToolUtil.isNotEmpty(newUser.getCertificateValidityPeriod())) {
                oldUser.setCertificateValidityPeriod(newUser.getCertificateValidityPeriod());
            }
            if (ToolUtil.isNotEmpty(newUser.getFirstTimeWorke())) {
                oldUser.setFirstTimeWorke(newUser.getFirstTimeWorke());
            }
            if (ToolUtil.isNotEmpty(newUser.getMaritalStatus())) {
                oldUser.setMaritalStatus(newUser.getMaritalStatus());
            }
            if (ToolUtil.isNotEmpty(newUser.getRegistrationType())) {
                oldUser.setRegistrationType(newUser.getRegistrationType());
            }
            if (ToolUtil.isNotEmpty(newUser.getCurrentAddress())) {
                oldUser.setCurrentAddress(newUser.getCurrentAddress());
            }
            if (ToolUtil.isNotEmpty(newUser.getPoliticalOutlook())) {
                oldUser.setPoliticalOutlook(newUser.getPoliticalOutlook());
            }
            if (ToolUtil.isNotEmpty(newUser.getSocialSecurityAccount())) {
                oldUser.setSocialSecurityAccount(newUser.getSocialSecurityAccount());
            }
            if (ToolUtil.isNotEmpty(newUser.getProvidentFundAccount())) {
                oldUser.setProvidentFundAccount(newUser.getProvidentFundAccount());
            }
            if (ToolUtil.isNotEmpty(newUser.getEducation())) {
                oldUser.setEducation(newUser.getEducation());
            }
            if (ToolUtil.isNotEmpty(newUser.getUniversity())) {
                oldUser.setUniversity(newUser.getUniversity());
            }
            if (ToolUtil.isNotEmpty(newUser.getGraduationTime())) {
                oldUser.setGraduationTime(newUser.getGraduationTime());
            }
            if (ToolUtil.isNotEmpty(newUser.getMajor())) {
                oldUser.setMajor(newUser.getMajor());
            }
            if (ToolUtil.isNotEmpty(newUser.getContractCompany())) {
                oldUser.setContractCompany(newUser.getContractCompany());
            }
            if (ToolUtil.isNotEmpty(newUser.getContractType())) {
                oldUser.setContractType(newUser.getContractType());
            }
            if (ToolUtil.isNotEmpty(newUser.getFirstContractOrigin())) {
                oldUser.setFirstContractOrigin(newUser.getFirstContractOrigin());
            }
            if (ToolUtil.isNotEmpty(newUser.getFirstContractExpire())) {
                oldUser.setFirstContractExpire(newUser.getFirstContractExpire());
            }
            if (ToolUtil.isNotEmpty(newUser.getContractPeriod())) {
                oldUser.setContractPeriod(newUser.getContractPeriod());
            }
            if (ToolUtil.isNotEmpty(newUser.getRenewalTimes())) {
                oldUser.setRenewalTimes(newUser.getRenewalTimes());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmergencyContactName())) {
                oldUser.setEmergencyContactName(newUser.getEmergencyContactName());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmergencyContactRelationship())) {
                oldUser.setEmergencyContactRelationship(newUser.getEmergencyContactRelationship());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmergencyContactSex())) {
                oldUser.setEmergencyContactSex(newUser.getEmergencyContactSex());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmergencyContactPhone())) {
                oldUser.setEmergencyContactPhone(newUser.getEmergencyContactPhone());
            }
            if (ToolUtil.isNotEmpty(newUser.getWithoutChildren())) {
                oldUser.setWithoutChildren(newUser.getWithoutChildren());
            }
            if (ToolUtil.isNotEmpty(newUser.getChildrenName())) {
                oldUser.setChildrenName(newUser.getChildrenName());
            }
            if (ToolUtil.isNotEmpty(newUser.getChildrenSex())) {
                oldUser.setChildrenSex(newUser.getChildrenSex());
            }
            if (ToolUtil.isNotEmpty(newUser.getChildrenBirthday())) {
                oldUser.setChildrenBirthday(newUser.getChildrenBirthday());
            }
            if (ToolUtil.isNotEmpty(newUser.getIdCardPositive())) {
                oldUser.setIdCardPositive(newUser.getIdCardPositive());
            }
            if (ToolUtil.isNotEmpty(newUser.getIdCardBack())) {
                oldUser.setIdCardBack(newUser.getIdCardBack());
            }
            if (ToolUtil.isNotEmpty(newUser.getAcademicCertificate())) {
                oldUser.setAcademicCertificate(newUser.getAcademicCertificate());
            }
            if (ToolUtil.isNotEmpty(newUser.getDiploma())) {
                oldUser.setDiploma(newUser.getDiploma());
            }
            if (ToolUtil.isNotEmpty(newUser.getLeavingCertificate())) {
                oldUser.setLeavingCertificate(newUser.getLeavingCertificate());
            }
            if (ToolUtil.isNotEmpty(newUser.getPhoto())) {
                oldUser.setPhoto(newUser.getPhoto());
            }
            return oldUser;
        }
    }
}
