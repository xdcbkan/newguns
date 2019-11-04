/**
 * 初始化提现申请详情对话框
 */
var WithdrawApplicationInfoDlg = {
    withdrawApplicationInfoData : {}
};

/**
 * 清除数据
 */
WithdrawApplicationInfoDlg.clearData = function() {
    this.withdrawApplicationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WithdrawApplicationInfoDlg.set = function(key, val) {
    this.withdrawApplicationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WithdrawApplicationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WithdrawApplicationInfoDlg.close = function() {
    parent.layer.close(window.parent.WithdrawApplication.layerIndex);
}

/**
 * 收集数据
 */
WithdrawApplicationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('amount')
    .set('withdrawStatus')
    .set('errorMessage')
    .set('createTime');
}

/**
 * 提交添加
 */
WithdrawApplicationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/withdrawApplication/add", function(data){
        if (data.code === 20000) {
            Feng.error(data.message + "!");
        } else {
            Feng.success("申请成功!");
            window.parent.WithdrawApplication.table.refresh();
        }
        WithdrawApplicationInfoDlg.close();
    },function(data){
        Feng.error("申请失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.withdrawApplicationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WithdrawApplicationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/withdrawApplication/update", function(data){
        Feng.success("更新成功!");
        window.parent.WithdrawApplication.table.refresh();
        WithdrawApplicationInfoDlg.close();
    },function(data){
        Feng.error("更新失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.withdrawApplicationInfoData);
    ajax.start();
}

/**
 * 提交失败反馈
 */
WithdrawApplicationInfoDlg.FailureFeedbackSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/withdrawApplication/paymentFailed", function(data){
        if (data.code === 20000) {
            Feng.error(data.message + "!");
        } else {
            Feng.success("更新成功!");
            window.parent.WithdrawApplication.table.refresh();
        }
        WithdrawApplicationInfoDlg.close();
    },function(data){
        Feng.error("更新失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.withdrawApplicationInfoData);
    ajax.start();
}

$(function() {

});
