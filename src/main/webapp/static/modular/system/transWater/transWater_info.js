/**
 * 初始化交易流水管理详情对话框
 */
var TransWaterInfoDlg = {
    transWaterInfoData : {}
};

/**
 * 清除数据
 */
TransWaterInfoDlg.clearData = function() {
    this.transWaterInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TransWaterInfoDlg.set = function(key, val) {
    this.transWaterInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TransWaterInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TransWaterInfoDlg.close = function() {
    parent.layer.close(window.parent.TransWater.layerIndex);
}

/**
 * 收集数据
 */
TransWaterInfoDlg.collectData = function() {
    this
    .set('id')
    .set('amount')
    .set('transStatus')
    .set('createUser')
    .set('transType')
    .set('pointsBalance')
    .set('articleId')
    .set('userId')
    .set('createTime')
    .set('remark');
}

/**
 * 提交添加
 */
TransWaterInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/transWater/add", function(data){
        Feng.success("添加成功!");
        window.parent.TransWater.table.refresh();
        TransWaterInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.transWaterInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TransWaterInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/transWater/update", function(data){
        Feng.success("修改成功!");
        window.parent.TransWater.table.refresh();
        TransWaterInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.transWaterInfoData);
    ajax.start();
}

$(function() {

});
