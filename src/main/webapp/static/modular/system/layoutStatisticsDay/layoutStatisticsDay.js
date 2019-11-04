/**
 * 配图每日工作统计管理初始化
 */
var LayoutStatisticsDay = {
    id: "LayoutStatisticsDayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LayoutStatisticsDay.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '员工姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '领取数', field: 'receive', visible: true, align: 'center', valign: 'middle'},
            {title: '配图文章数', field: 'article_num', visible: true, align: 'center', valign: 'middle'},
            {title: '配图图片数', field: 'picture_num', visible: true, align: 'center', valign: 'middle'},
            {title: '统计日期', field: 'statistics_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
LayoutStatisticsDay.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LayoutStatisticsDay.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加配图每日工作统计
 */
LayoutStatisticsDay.openAddLayoutStatisticsDay = function () {
    var index = layer.open({
        type: 2,
        title: '添加配图每日工作统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/layoutStatisticsDay/layoutStatisticsDay_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看配图每日工作统计详情
 */
LayoutStatisticsDay.openLayoutStatisticsDayDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '配图每日工作统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/layoutStatisticsDay/layoutStatisticsDay_update/' + LayoutStatisticsDay.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除配图每日工作统计
 */
LayoutStatisticsDay.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/layoutStatisticsDay/delete", function (data) {
            Feng.success("删除成功!");
            LayoutStatisticsDay.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("layoutStatisticsDayId",this.seItem.id);
        ajax.start();
    }
};

LayoutStatisticsDay.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    LayoutStatisticsDay.search();
}

/**
 * 查询配图每日工作统计列表
 */
LayoutStatisticsDay.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    LayoutStatisticsDay.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LayoutStatisticsDay.initColumn();
    var table = new BSTable(LayoutStatisticsDay.id, "/layoutStatisticsDay/list", defaultColunms);
    table.setPaginationType("client");
    LayoutStatisticsDay.table = table.init();
});
