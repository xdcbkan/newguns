/**
 * 文编每日统计管理初始化
 */
var EditorStatisticsDay = {
    id: "EditorStatisticsDayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
EditorStatisticsDay.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '员工姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '文章提交数', field: 'submit', visible: true, align: 'center', valign: 'middle'},
            {title: '审①退回数', field: 'examine1_return', visible: true, align: 'center', valign: 'middle'},
            {title: '审②退回数', field: 'examine2_return', visible: true, align: 'center', valign: 'middle'},
            {title: '审①修1数', field: 'examine1_return_edit1', visible: true, align: 'center', valign: 'middle'},
            {title: '审①修2数', field: 'examine1_return_edit2', visible: true, align: 'center', valign: 'middle'},
            {title: '审②修1数', field: 'examine2_return_edit1', visible: true, align: 'center', valign: 'middle'},
            {title: '审②修2数', field: 'examine2_return_edit2', visible: true, align: 'center', valign: 'middle'},
            {title: '文章作废数', field: 'abolish', visible: true, align: 'center', valign: 'middle'},
            {title: '文章通过数', field: 'pass', visible: true, align: 'center', valign: 'middle'},
            {title: '结算金额', field: 'settlement_amount', visible: true, align: 'center', valign: 'middle'},
            {title: '统计日期', field: 'statistics_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
EditorStatisticsDay.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        EditorStatisticsDay.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文编每日统计
 */
EditorStatisticsDay.openAddEditorStatisticsDay = function () {
    var index = layer.open({
        type: 2,
        title: '添加文编每日统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/editorStatisticsDay/editorStatisticsDay_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文编每日统计详情
 */
EditorStatisticsDay.openEditorStatisticsDayDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文编每日统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/editorStatisticsDay/editorStatisticsDay_update/' + EditorStatisticsDay.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文编每日统计
 */
EditorStatisticsDay.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/editorStatisticsDay/delete", function (data) {
            Feng.success("删除成功!");
            EditorStatisticsDay.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("editorStatisticsDayId",this.seItem.id);
        ajax.start();
    }
};

EditorStatisticsDay.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    EditorStatisticsDay.search();
}

/**
 * 查询文编每日统计列表
 */
EditorStatisticsDay.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    EditorStatisticsDay.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = EditorStatisticsDay.initColumn();
    var table = new BSTable(EditorStatisticsDay.id, "/editorStatisticsDay/list", defaultColunms);
    table.setPaginationType("client");
    EditorStatisticsDay.table = table.init();
});
