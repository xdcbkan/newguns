/**
 * 初始化文章历史版本记录详情对话框
 */
var HistoricalArticleInfoDlg = {
    historicalArticleInfoData : {}
};

/**
 * 清除数据
 */
HistoricalArticleInfoDlg.clearData = function() {
    this.historicalArticleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HistoricalArticleInfoDlg.set = function(key, val) {
    this.historicalArticleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HistoricalArticleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HistoricalArticleInfoDlg.close = function() {
    parent.layer.close(window.parent.HistoricalArticle.layerIndex);
}

/**
 * 收集数据
 */
HistoricalArticleInfoDlg.collectData = function() {
    this
    .set('id')
    .set('articleNum')
    .set('mainTitle')
    .set('subheading')
    .set('content')
    .set('prefix')
    .set('articleStatus')
    .set('pictureNum')
    .set('articleTypeId')
    .set('examine1Id')
    .set('examine2Id')
    .set('layoutId')
    .set('operateId')
    .set('purchaseId')
    .set('editorNewFlag')
    .set('returnMessage')
    .set('createTime')
    .set('updateTime')
    .set('createUserId')
    .set('updateUserId')
    .set('newArticleId');
}

/**
 * 提交添加
 */
HistoricalArticleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/historicalArticle/add", function(data){
        Feng.success("添加成功!");
        window.parent.HistoricalArticle.table.refresh();
        HistoricalArticleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.historicalArticleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HistoricalArticleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/historicalArticle/update", function(data){
        Feng.success("修改成功!");
        window.parent.HistoricalArticle.table.refresh();
        HistoricalArticleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.historicalArticleInfoData);
    ajax.start();
}

$(function() {

});
