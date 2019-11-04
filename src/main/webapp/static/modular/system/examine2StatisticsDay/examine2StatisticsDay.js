/**
 * 审核2每日工作统计管理初始化
 */
var Examine2StatisticsDay = {
    id: "Examine2StatisticsDayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Examine2StatisticsDay.initColumn = function () {
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
Examine2StatisticsDay.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Examine2StatisticsDay.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加审核2每日工作统计
 */
Examine2StatisticsDay.openAddExamine2StatisticsDay = function () {
    var index = layer.open({
        type: 2,
        title: '添加审核2每日工作统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/examine2StatisticsDay/examine2StatisticsDay_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看审核2每日工作统计详情
 */
Examine2StatisticsDay.openExamine2StatisticsDayDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审核2每日工作统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/examine2StatisticsDay/examine2StatisticsDay_update/' + Examine2StatisticsDay.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除审核2每日工作统计
 */
Examine2StatisticsDay.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/examine2StatisticsDay/delete", function (data) {
            Feng.success("删除成功!");
            Examine2StatisticsDay.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("examine2StatisticsDayId",this.seItem.id);
        ajax.start();
    }
};

Examine2StatisticsDay.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    Examine2StatisticsDay.search();
}

/**
 * 查询审核2每日工作统计列表
 */
Examine2StatisticsDay.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    Examine2StatisticsDay.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Examine2StatisticsDay.initColumn();
    var table = new BSTable(Examine2StatisticsDay.id, "/examine2StatisticsDay/list", defaultColunms);
    table.setPaginationType("client");
    Examine2StatisticsDay.table = table.init();
});
