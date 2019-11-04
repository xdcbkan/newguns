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
        {title: '主标题', field: 'main_title', visible: true, align: 'center', valign: 'middle'},
        {title: '副标题', field: 'subheading', visible: true, align: 'center', valign: 'middle'},
        {title: '时效性类别', field: 'timelinessCategoryName', visible: true, align: 'center', valign: 'middle'},
        {title: '任务状态', field: 'taskStatusName', visible: true, align: 'center', valign: 'middle'},
        {title: '文章分类', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'url地址', field: 'url', visible: true, align: 'center', valign: 'middle'}
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
 * 点击添加文章任务表
 */
ArticleTask.openAddArticleTask = function () {
    var index = layer.open({
        type: 2,
        title: '添加话题任务',
        area: ['1100px', '820px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/articleTask/articleTask_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章任务表详情
 */
ArticleTask.openArticleTaskDetail = function () {
    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        if (selected[0].task_status === 1) {
            var index = layer.open({
                type: 2,
                title: '话题任务详情',
                area: ['1100px', '820px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/articleTask/articleTask_update/' + ArticleTask.seItem.id
            });
            this.layerIndex = index;
        } else {
            Feng.error("该任务已被领取，不可进行编辑!");
        }
    }
};

/**
 * 删除文章任务表
 */
ArticleTask.delete = function () {
    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        if (selected[0].task_status === 1) {
            var ajax = new $ax(Feng.ctxPath + "/articleTask/delete", function (data) {
                if (data.code === 20000) {
                    Feng.error(data.message + "!");
                } else {
                    Feng.success("删除成功!");
                }
                ArticleTask.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("articleTaskId", this.seItem.id);
            ajax.start();
        } else {
            Feng.error("该任务已被领取，不可进行删除!");
        }
    }
};

/**
 * 查询文章任务表列表
 */
ArticleTask.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['timelinessCategory'] = $("#timelinessCategory").val();
    queryData['taskStatus'] = $("#taskStatus").val();
    queryData['type'] = $("#type").val();
    ArticleTask.table.refresh({query: queryData});
};

ArticleTask.resetSearch = function () {
    $("#title").val("");
    $("#timelinessCategory").val("");
    $("#taskStatus").val("");
    $("#type").val("");
    WithdrawApplication.search();
}

$(function () {
    var defaultColunms = ArticleTask.initColumn();
    var table = new BSTable(ArticleTask.id, "/articleTask/list", defaultColunms);
    table.setPaginationType("client");
    ArticleTask.table = table.init();
});
