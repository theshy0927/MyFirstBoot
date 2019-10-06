/**
 * 发布问题
 */


function selectTags(dom){
	var value = jQuery(dom).data("tag");
	var content = jQuery("#tag").val();
	if(content){
		if(content.indexOf(value)!=-1){
			return;
		}
		jQuery("#tag").val(content+","+value);
	}else{
		jQuery("#tag").val(value);
	}
}

jQuery.noConflict();//让渡jQuery的使用权，其他脚本库可以使用jQuery
jQuery(document).ready(function(jQuery){
	var jQueryspan = jQuery("#error").text();
	if(jQueryspan!=""){
		alert(jQueryspan);
		jQueryspan = "";
	}
});