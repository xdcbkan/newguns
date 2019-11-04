/**
 * 审核2每月统计管理初始化
 */
var Examine2StatisticsMonth = {
    id: "Examine2StatisticsMonthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Examine2StatisticsMonth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '员工姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
        {title: '领取数', field: 'receive', visible: true, align: 'center', valign: 'middle'},
        {title: '通过数', field: 'pass', visible: true, align: 'center', valign: 'middle'},
        {title: '未通过数', field: 'unpass', visible: true, align: 'center', valign: 'middle'},
        {title: '审核中的数量', field: 'examining_num', visible: true, align: 'center', valign: 'middle'},
        {title: '作废数', field: 'abolish', visible: true, align: 'center', valign: 'middle'},
        {title: '统计日期', field: 'statistics_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Examine2StatisticsMonth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Examine2StatisticsMonth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加审核2每月统计
 */
Examine2StatisticsMonth.openAddExamine2StatisticsMonth = function () {
    var index = layer.open({
        type: 2,
        title: '添加审核2每月统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/examine2StatisticsMonth/examine2StatisticsMonth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看审核2每月统计详情
 */
Examine2StatisticsMonth.openExamine2StatisticsMonthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审核2每月统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/examine2StatisticsMonth/examine2StatisticsMonth_update/' + Examine2StatisticsMonth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除审核2每月统计
 */
Examine2StatisticsMonth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/examine2StatisticsMonth/delete", function (data) {
            Feng.success("删除成功!");
            Examine2StatisticsMonth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("examine2StatisticsMonthId",this.seItem.id);
        ajax.start();
    }
};

Examine2StatisticsMonth.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    Examine2StatisticsMonth.search();
}

/**
 * 查询审核2每月统计列表
 */
Examine2StatisticsMonth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    Examine2StatisticsMonth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Examine2StatisticsMonth.initColumn();
    var table = new BSTable(Examine2StatisticsMonth.id, "/examine2StatisticsMonth/list", defaultColunms);
    table.setPaginationType("client");
    Examine2StatisticsMonth.table = table.init();
});
