jQuery.noConflict();//让渡$的使用权，其他脚本库可以使用$
jQuery(document).ready(function($){
	var $span = $("#error").text();
	if($span!=""){
		alert($span);
		$span = "";
	}
});