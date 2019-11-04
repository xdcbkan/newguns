/**
 * 初始化项目类型详情对话框
 */
var ProjectTypeInfoDlg = {
    projectTypeInfoData : {}
};

/**
 * 清除数据
 */
ProjectTypeInfoDlg.clearData = function() {
    this.projectTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProjectTypeInfoDlg.set = function(key, val) {
    this.projectTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProjectTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProjectTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.ProjectType.layerIndex);
}

/**
 * 收集数据
 */
ProjectTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
ProjectTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/projectType/add", function(data){
        Feng.success("添加成功!");
        window.parent.ProjectType.table.refresh();
        ProjectTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.projectTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProjectTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/projectType/update", function(data){
        Feng.success("修改成功!");
        window.parent.ProjectType.table.refresh();
        ProjectTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.projectTypeInfoData);
    ajax.start();
}

$(function() {

});
