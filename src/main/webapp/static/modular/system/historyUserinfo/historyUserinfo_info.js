/**
 * 初始化用户信息历史版本详情对话框
 */
var HistoryUserinfoInfoDlg = {
    historyUserinfoInfoData : {}
};

/**
 * 清除数据
 */
HistoryUserinfoInfoDlg.clearData = function() {
    this.historyUserinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HistoryUserinfoInfoDlg.set = function(key, val) {
    this.historyUserinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HistoryUserinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HistoryUserinfoInfoDlg.close = function() {
    parent.layer.close(window.parent.HistoryUserinfo.layerIndex);
}

/**
 * 收集数据
 */
HistoryUserinfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('avatar')
    .set('account')
    .set('password')
    .set('salt')
    .set('balance')
    .set('name')
    .set('birthday')
    .set('sex')
    .set('email')
    .set('phone')
    .set('idcard')
    .set('alipay')
    .set('residuereceivenum')
    .set('receivenum')
    .set('price')
    .set('userid')
    .set('usernumber')
    .set('roleid')
    .set('articletypeid')
    .set('passnum')
    .set('deptid')
    .set('status')
    .set('createtime')
    .set('version')
    .set('officeAddress')
    .set('entryTime')
    .set('wechatid')
    .set('qqid')
    .set('sinaid')
    .set('employeeType')
    .set('employeeStatus')
    .set('postRank')
    .set('nation')
    .set('idAddress')
    .set('certificateValidityPeriod')
    .set('firstTimeWorke')
    .set('maritalStatus')
    .set('registrationType')
    .set('currentAddress')
    .set('politicalOutlook')
    .set('socialSecurityAccount')
    .set('providentFundAccount')
    .set('education')
    .set('university')
    .set('graduationTime')
    .set('major')
    .set('contractCompany')
    .set('contractType')
    .set('firstContractOrigin')
    .set('firstContractExpire')
    .set('currentContractOrigin')
    .set('currentContractExpire')
    .set('contractPeriod')
    .set('renewalTimes')
    .set('emergencyContactName')
    .set('emergencyContactRelationship')
    .set('emergencyContactSex')
    .set('emergencyContactPhone')
    .set('withoutChildren')
    .set('childrenName')
    .set('childrenSex')
    .set('childrenBirthday')
    .set('idCardPositive')
    .set('idCardBack')
    .set('academicCertificate')
    .set('diploma')
    .set('leavingCertificate')
    .set('photo')
    .set('projectTypeId')
    .set('sysUserId');
}

/**
 * 提交添加
 */
HistoryUserinfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/historyUserinfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.HistoryUserinfo.table.refresh();
        HistoryUserinfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.historyUserinfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HistoryUserinfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/historyUserinfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.HistoryUserinfo.table.refresh();
        HistoryUserinfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.historyUserinfoInfoData);
    ajax.start();
}

$(function() {

});
