/**
 * 配图每月统计管理初始化
 */
var LayoutStatisticsMonth = {
    id: "LayoutStatisticsMonthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LayoutStatisticsMonth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '用户姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '领取数', field: 'receive', visible: true, align: 'center', valign: 'middle'},
            {title: '配图文章数', field: 'article_num', visible: true, align: 'center', valign: 'middle'},
            {title: '配图图片数', field: 'picture_num', visible: true, align: 'center', valign: 'middle'},
            {title: '统计日期', field: 'statistics_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
LayoutStatisticsMonth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LayoutStatisticsMonth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加配图每月统计
 */
LayoutStatisticsMonth.openAddLayoutStatisticsMonth = function () {
    var index = layer.open({
        type: 2,
        title: '添加配图每月统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/layoutStatisticsMonth/layoutStatisticsMonth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看配图每月统计详情
 */
LayoutStatisticsMonth.openLayoutStatisticsMonthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '配图每月统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/layoutStatisticsMonth/layoutStatisticsMonth_update/' + LayoutStatisticsMonth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除配图每月统计
 */
LayoutStatisticsMonth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/layoutStatisticsMonth/delete", function (data) {
            Feng.success("删除成功!");
            LayoutStatisticsMonth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("layoutStatisticsMonthId",this.seItem.id);
        ajax.start();
    }
};

LayoutStatisticsMonth.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    LayoutStatisticsMonth.search();
}

/**
 * 查询配图每月统计列表
 */
LayoutStatisticsMonth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    LayoutStatisticsMonth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LayoutStatisticsMonth.initColumn();
    var table = new BSTable(LayoutStatisticsMonth.id, "/layoutStatisticsMonth/list", defaultColunms);
    table.setPaginationType("client");
    LayoutStatisticsMonth.table = table.init();
});
