/**
 * 已领取文章任务表管理初始化
 */
var ReceivedArticleTask = {
    id: "ReceivedArticleTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ReceivedArticleTask.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '主标题', field: 'main_title', visible: true, align: 'center', valign: 'middle'},
        {title: '副标题', field: 'subheading', visible: true, align: 'center', valign: 'middle'},
        {title: '时效性类别', field: 'timelinessCategoryName', visible: true, align: 'center', valign: 'middle'},
        {title: '任务状态', field: 'taskStatusName', visible: true, align: 'center', valign: 'middle'},
        {title: '文章分类', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'url地址', field: 'url', visible: true, align: 'center', valign: 'middle'},
        {title: '领取人', field: 'recipients', visible: true, align: 'center', valign: 'middle'},
        {title: '领取时间', field: 'pick_up_time', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
ReceivedArticleTask.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ReceivedArticleTask.seItem = selected[0];
        return true;
    }
};

/**
 * 查询文章任务表列表
 */
ReceivedArticleTask.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['timelinessCategory'] = $("#timelinessCategory").val();
    queryData['type'] = $("#type").val();
    ReceivedArticleTask.table.refresh({query: queryData});
};

ReceivedArticleTask.resetSearch = function () {
    $("#title").val("");
    $("#timelinessCategory").val("");
    $("#type").val("");
    ReceivedArticleTask.search();
}

$(function () {
    var defaultColunms = ReceivedArticleTask.initColumn();
    var table = new BSTable(ReceivedArticleTask.id, "/articleTask/receivedArticleList", defaultColunms);
    table.setPaginationType("client");
    ReceivedArticleTask.table = table.init();
});
