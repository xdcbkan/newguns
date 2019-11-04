/**
 * 初始化注册邀请码管理详情对话框
 */
var InvitationCodeInfoDlg = {
    invitationCodeInfoData : {}
};

/**
 * 清除数据
 */
InvitationCodeInfoDlg.clearData = function() {
    this.invitationCodeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InvitationCodeInfoDlg.set = function(key, val) {
    this.invitationCodeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InvitationCodeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InvitationCodeInfoDlg.close = function() {
    parent.layer.close(window.parent.InvitationCode.layerIndex);
}

/**
 * 收集数据
 */
InvitationCodeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('invitationCode')
    .set('amount')
    .set('createUserId');
}

/**
 * 提交添加
 */
InvitationCodeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/invitationCode/add", function(data){
        Feng.success("添加成功!");
        window.parent.InvitationCode.table.refresh();
        InvitationCodeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.invitationCodeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InvitationCodeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/invitationCode/update", function(data){
        Feng.success("修改成功!");
        window.parent.InvitationCode.table.refresh();
        InvitationCodeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.invitationCodeInfoData);
    ajax.start();
}

$(function() {

});
