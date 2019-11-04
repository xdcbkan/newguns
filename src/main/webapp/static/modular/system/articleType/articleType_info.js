/**
 * 初始化文章分类管理详情对话框
 */
var ArticleTypeInfoDlg = {
    articleTypeInfoData : {}
};

/**
 * 清除数据
 */
ArticleTypeInfoDlg.clearData = function() {
    this.articleTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleTypeInfoDlg.set = function(key, val) {
    this.articleTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ArticleTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.ArticleType.layerIndex);
}

/**
 * 收集数据
 */
ArticleTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
ArticleTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleType/add", function(data){
        Feng.success("添加成功!");
        window.parent.ArticleType.table.refresh();
        ArticleTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ArticleTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleType/update", function(data){
        Feng.success("修改成功!");
        window.parent.ArticleType.table.refresh();
        ArticleTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleTypeInfoData);
    ajax.start();
}

$(function() {

});
