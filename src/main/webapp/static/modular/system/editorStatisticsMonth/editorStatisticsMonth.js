/**
 * 文编每月统计管理初始化
 */
var EditorStatisticsMonth = {
    id: "EditorStatisticsMonthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
EditorStatisticsMonth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '员工姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '提交数', field: 'submit', visible: true, align: 'center', valign: 'middle'},
            {title: '审①退回数', field: 'examine1_return', visible: true, align: 'center', valign: 'middle'},
            {title: '审②退回数', field: 'examine2_return', visible: true, align: 'center', valign: 'middle'},
            {title: '审①修1数', field: 'examine1_return_edit1', visible: true, align: 'center', valign: 'middle'},
            {title: '审①修2数', field: 'examine1_return_edit2', visible: true, align: 'center', valign: 'middle'},
            {title: '审②修1数', field: 'examine2_return_edit1', visible: true, align: 'center', valign: 'middle'},
            {title: '审②修2数', field: 'examine2_return_edit2', visible: true, align: 'center', valign: 'middle'},
            {title: '作废数', field: 'abolish', visible: true, align: 'center', valign: 'middle'},
            {title: '审②通过数', field: 'pass', visible: true, align: 'center', valign: 'middle'},
            {title: '结算金额', field: 'settlement_amount', visible: true, align: 'center', valign: 'middle'},
            {title: '统计日期', field: 'statistics_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
EditorStatisticsMonth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        EditorStatisticsMonth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文编每月统计
 */
EditorStatisticsMonth.openAddEditorStatisticsMonth = function () {
    var index = layer.open({
        type: 2,
        title: '添加文编每月统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/editorStatisticsMonth/editorStatisticsMonth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文编每月统计详情
 */
EditorStatisticsMonth.openEditorStatisticsMonthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文编每月统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/editorStatisticsMonth/editorStatisticsMonth_update/' + EditorStatisticsMonth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文编每月统计
 */
EditorStatisticsMonth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/editorStatisticsMonth/delete", function (data) {
            Feng.success("删除成功!");
            EditorStatisticsMonth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("editorStatisticsMonthId",this.seItem.id);
        ajax.start();
    }
};

EditorStatisticsMonth.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    EditorStatisticsMonth.search();
}

/**
 * 查询文编每月统计列表
 */
EditorStatisticsMonth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    EditorStatisticsMonth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = EditorStatisticsMonth.initColumn();
    var table = new BSTable(EditorStatisticsMonth.id, "/editorStatisticsMonth/list", defaultColunms);
    table.setPaginationType("client");
    EditorStatisticsMonth.table = table.init();
});
