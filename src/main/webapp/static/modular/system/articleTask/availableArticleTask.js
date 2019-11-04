/**
 * 文章任务表管理初始化
 */
var ArticleTask = {
    id: "ArticleTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticleTask.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '供参考标题', field: 'main_title', visible: true, align: 'center', valign: 'middle'},
        // {title: '副标题', field: 'subheading', visible: true, align: 'center', valign: 'middle'},
        // {title: '文章内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '时效性类别', field: 'timelinessCategoryName', visible: true, align: 'center', valign: 'middle'}
        // {title: '任务状态', field: 'taskStatusName', visible: true, align: 'center', valign: 'middle'},
        // {title: 'url地址', field: 'url', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ArticleTask.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ArticleTask.seItem = selected[0];
        return true;
    }
};

/**
 * 删除文章任务表
 */
ArticleTask.receiveArticleTask = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/articleTask/receive", function (data) {
            if (data.code === 20000) {
                Feng.error(data.message + "!");
            } else {
                Feng.success("领取成功!");
            }
            ArticleTask.table.refresh();
        }, function (data) {
            Feng.error("领取失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articleTaskId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章任务表列表
 */
ArticleTask.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['timelinessCategory'] = $("#timelinessCategory").val();
    ArticleTask.table.refresh({query: queryData});
};

ArticleTask.resetSearch = function () {
    $("#title").val("");
    $("#timelinessCategory").val("");
    WithdrawApplication.search();
}

$(function () {
    var defaultColunms = ArticleTask.initColumn();
    var table = new BSTable(ArticleTask.id, "/articleTask/availableTasks", defaultColunms);
    table.setPaginationType("client");
    ArticleTask.table = table.init();
});
