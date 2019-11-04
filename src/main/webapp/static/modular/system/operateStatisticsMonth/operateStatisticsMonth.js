/**
 * 运营每月统计管理初始化
 */
var OperateStatisticsMonth = {
    id: "OperateStatisticsMonthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OperateStatisticsMonth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '员工姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '领取数', field: 'receive', visible: true, align: 'center', valign: 'middle'},
            {title: '作废数', field: 'abolish', visible: true, align: 'center', valign: 'middle'},
            {title: '统计日期', field: 'statistics_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
OperateStatisticsMonth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OperateStatisticsMonth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加运营每月统计
 */
OperateStatisticsMonth.openAddOperateStatisticsMonth = function () {
    var index = layer.open({
        type: 2,
        title: '添加运营每月统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/operateStatisticsMonth/operateStatisticsMonth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看运营每月统计详情
 */
OperateStatisticsMonth.openOperateStatisticsMonthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '运营每月统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/operateStatisticsMonth/operateStatisticsMonth_update/' + OperateStatisticsMonth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除运营每月统计
 */
OperateStatisticsMonth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/operateStatisticsMonth/delete", function (data) {
            Feng.success("删除成功!");
            OperateStatisticsMonth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("operateStatisticsMonthId",this.seItem.id);
        ajax.start();
    }
};

OperateStatisticsMonth.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    OperateStatisticsMonth.search();
}

/**
 * 查询运营每月统计列表
 */
OperateStatisticsMonth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    OperateStatisticsMonth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OperateStatisticsMonth.initColumn();
    var table = new BSTable(OperateStatisticsMonth.id, "/operateStatisticsMonth/list", defaultColunms);
    table.setPaginationType("client");
    OperateStatisticsMonth.table = table.init();
});
