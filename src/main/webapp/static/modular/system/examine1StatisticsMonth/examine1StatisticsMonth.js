/**
 * 审核1每月统计管理初始化
 */
var Examine1StatisticsMonth = {
    id: "Examine1StatisticsMonthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Examine1StatisticsMonth.initColumn = function () {
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
Examine1StatisticsMonth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Examine1StatisticsMonth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加审核1每月统计
 */
Examine1StatisticsMonth.openAddExamine1StatisticsMonth = function () {
    var index = layer.open({
        type: 2,
        title: '添加审核1每月统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/examine1StatisticsMonth/examine1StatisticsMonth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看审核1每月统计详情
 */
Examine1StatisticsMonth.openExamine1StatisticsMonthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审核1每月统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/examine1StatisticsMonth/examine1StatisticsMonth_update/' + Examine1StatisticsMonth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除审核1每月统计
 */
Examine1StatisticsMonth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/examine1StatisticsMonth/delete", function (data) {
            Feng.success("删除成功!");
            Examine1StatisticsMonth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("examine1StatisticsMonthId",this.seItem.id);
        ajax.start();
    }
};

Examine1StatisticsMonth.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    Examine1StatisticsMonth.search();
}

/**
 * 查询审核1每月统计列表
 */
Examine1StatisticsMonth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    Examine1StatisticsMonth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Examine1StatisticsMonth.initColumn();
    var table = new BSTable(Examine1StatisticsMonth.id, "/examine1StatisticsMonth/list", defaultColunms);
    table.setPaginationType("client");
    Examine1StatisticsMonth.table = table.init();
});
