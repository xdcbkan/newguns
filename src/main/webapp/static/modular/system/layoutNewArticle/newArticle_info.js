/**
 * 初始化全部文章详情对话框
 */
var NewArticleInfoDlg = {
    newArticleInfoData : {}
};

/**
 * 清除数据
 */
NewArticleInfoDlg.clearData = function() {
    this.newArticleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewArticleInfoDlg.set = function(key, val) {
    this.newArticleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewArticleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewArticleInfoDlg.close = function() {
    parent.layer.close(window.parent.NewArticle.layerIndex);
}

/**
 * 收集数据
 */
NewArticleInfoDlg.collectData = function() {
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
    .set('articlePrice')
    .set('prescription')
    .set('label')
    .set('projectTypeId')
    .set('illustrationType');
}

/**
 * 提交添加
 */
NewArticleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/add", function(data){
        Feng.success("添加成功!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NewArticleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/update", function(data){
        Feng.success("修改成功!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}

$(function() {

});
