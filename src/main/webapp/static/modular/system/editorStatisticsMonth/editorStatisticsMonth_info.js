/**
 * 初始化文编每月统计详情对话框
 */
var EditorStatisticsMonthInfoDlg = {
    editorStatisticsMonthInfoData : {}
};

/**
 * 清除数据
 */
EditorStatisticsMonthInfoDlg.clearData = function() {
    this.editorStatisticsMonthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EditorStatisticsMonthInfoDlg.set = function(key, val) {
    this.editorStatisticsMonthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EditorStatisticsMonthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EditorStatisticsMonthInfoDlg.close = function() {
    parent.layer.close(window.parent.EditorStatisticsMonth.layerIndex);
}

/**
 * 收集数据
 */
EditorStatisticsMonthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('submit')
    .set('examine1Return')
    .set('examine2Return')
    .set('examine1ReturnEdit1')
    .set('examine1ReturnEdit2')
    .set('examine2ReturnEdit1')
    .set('examine2ReturnEdit2')
    .set('abolish')
    .set('pass')
    .set('settlementAmount')
    .set('statisticsTime');
}

/**
 * 提交添加
 */
EditorStatisticsMonthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/editorStatisticsMonth/add", function(data){
        Feng.success("添加成功!");
        window.parent.EditorStatisticsMonth.table.refresh();
        EditorStatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.editorStatisticsMonthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EditorStatisticsMonthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/editorStatisticsMonth/update", function(data){
        Feng.success("修改成功!");
        window.parent.EditorStatisticsMonth.table.refresh();
        EditorStatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.editorStatisticsMonthInfoData);
    ajax.start();
}

$(function() {

});
