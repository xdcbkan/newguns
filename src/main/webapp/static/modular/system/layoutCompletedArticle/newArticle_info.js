/**
 * 初始化全部文章详情对话框
 */
var NewArticleInfoDlg = {
    newArticleInfoData : {},
    validateFields: {
        pictureNum: {
            validators: {
                notEmpty: {
                    message: '退回原因不能为空'
                }
            }
        }
    }
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
    .set('newMainTitle')
    .set('subheading')
    .set('newSubheading')
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
 * 验证配图数是否为空
 */
NewArticleInfoDlg.validateRmg = function () {
    var pictureNum = this.get("pictureNum");
    if (pictureNum && pictureNum.trim("") != "") {
        return true;
    } else {
        return false;
    }
};

/**
 * 提交配图
 */
NewArticleInfoDlg.layoutSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validateRmg()) {
        Feng.error("完成配图并提交时，请填写相应配图的数量!");
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/layoutArticle", function(data){
        Feng.success("已成功提交!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    },function(data){
        Feng.error("文章提交失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}

$(function() {

});
