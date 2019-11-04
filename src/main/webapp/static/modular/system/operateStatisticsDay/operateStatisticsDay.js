/**
 * 运营每日工作统计管理初始化
 */
var OperateStatisticsDay = {
    id: "OperateStatisticsDayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OperateStatisticsDay.initColumn = function () {
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
OperateStatisticsDay.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OperateStatisticsDay.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加运营每日工作统计
 */
OperateStatisticsDay.openAddOperateStatisticsDay = function () {
    var index = layer.open({
        type: 2,
        title: '添加运营每日工作统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/operateStatisticsDay/operateStatisticsDay_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看运营每日工作统计详情
 */
OperateStatisticsDay.openOperateStatisticsDayDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '运营每日工作统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/operateStatisticsDay/operateStatisticsDay_update/' + OperateStatisticsDay.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除运营每日工作统计
 */
OperateStatisticsDay.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/operateStatisticsDay/delete", function (data) {
            Feng.success("删除成功!");
            OperateStatisticsDay.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("operateStatisticsDayId",this.seItem.id);
        ajax.start();
    }
};

OperateStatisticsDay.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    OperateStatisticsDay.search();
}

/**
 * 查询运营每日工作统计列表
 */
OperateStatisticsDay.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    OperateStatisticsDay.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OperateStatisticsDay.initColumn();
    var table = new BSTable(OperateStatisticsDay.id, "/operateStatisticsDay/list", defaultColunms);
    table.setPaginationType("client");
    OperateStatisticsDay.table = table.init();
});
