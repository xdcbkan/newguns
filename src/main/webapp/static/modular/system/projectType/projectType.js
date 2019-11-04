/**
 * 项目类型管理初始化
 */
var ProjectType = {
    id: "ProjectTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProjectType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '项目类型名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ProjectType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ProjectType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加项目类型
 */
ProjectType.openAddProjectType = function () {
    var index = layer.open({
        type: 2,
        title: '添加项目类型',
        area: ['800px', '240px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/projectType/projectType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看项目类型详情
 */
ProjectType.openProjectTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '项目类型详情',
            area: ['800px', '240px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/projectType/projectType_update/' + ProjectType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除项目类型
 */
ProjectType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/projectType/delete", function (data) {
            Feng.success("删除成功!");
            ProjectType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("projectTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询项目类型列表
 */
ProjectType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ProjectType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ProjectType.initColumn();
    var table = new BSTable(ProjectType.id, "/projectType/list", defaultColunms);
    table.setPaginationType("client");
    ProjectType.table = table.init();
});
