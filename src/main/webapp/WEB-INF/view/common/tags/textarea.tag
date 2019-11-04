@/*
表单中textarea框标签中各个参数的说明:

hidden : textarea hidden框的id
id : textarea框id
name : textarea框名称
readonly : readonly属性
clickFun : 点击事件的方法名
style : 附加的css属性
@*/
<div class="form-group">
    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-9">
        <textarea class="form-control" id="${id}" name="${id}" placeholder="${placeholder!}"
               @if(isNotEmpty(value)){
               value="${tool.dateType(value)}"
               @}
               @if(isNotEmpty(type)){
               type="${type}"
               @}else{
               type="text"
               @}
               @if(isNotEmpty(readonly)){
               readonly="${readonly}"
               @}
               @if(isNotEmpty(clickFun)){
               onclick="${clickFun}"
               @}
               @if(isNotEmpty(style)){
               style="${style}"
               @}
               @if(isNotEmpty(disabled)){
               disabled="${disabled}"
               @}
        >${tagBody!}</textarea>
        @if(isNotEmpty(hidden)){
        <textarea class="form-control" type="hidden" id="${hidden}" >${hiddenValue!}</textarea>
        @}

        @if(isNotEmpty(selectFlag)){
        <div id="${selectId}" style="display: none; position: absolute; z-index: 200;">
            <ul id="${selectTreeId}" class="ztree tree-box" style="${selectStyle!}"></ul>
        </div>
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}


