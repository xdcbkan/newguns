/**
 * 初始化文编每日统计详情对话框
 */
var EditorStatisticsDayInfoDlg = {
    editorStatisticsDayInfoData : {}
};

/**
 * 清除数据
 */
EditorStatisticsDayInfoDlg.clearData = function() {
    this.editorStatisticsDayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EditorStatisticsDayInfoDlg.set = function(key, val) {
    this.editorStatisticsDayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EditorStatisticsDayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EditorStatisticsDayInfoDlg.close = function() {
    parent.layer.close(window.parent.EditorStatisticsDay.layerIndex);
}

/**
 * 收集数据
 */
EditorStatisticsDayInfoDlg.collectData = function() {
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
EditorStatisticsDayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/editorStatisticsDay/add", function(data){
        Feng.success("添加成功!");
        window.parent.EditorStatisticsDay.table.refresh();
        EditorStatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.editorStatisticsDayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EditorStatisticsDayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/editorStatisticsDay/update", function(data){
        Feng.success("修改成功!");
        window.parent.EditorStatisticsDay.table.refresh();
        EditorStatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.editorStatisticsDayInfoData);
    ajax.start();
}

$(function() {

});
