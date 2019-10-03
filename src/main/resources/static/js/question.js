/**
 * question.html的js
 */
jQuery.noConflict();//让渡$的使用权，其他脚本库可以使用$
jQuery(document).ready(function($){
	$(".btn-success").click(function(){
		if(confirm("是否进行评论？")){
			var parentId = $("#parentId").val();
			var type = $("#type").val();
			var content = $("#content").val().replace(/\s+/g,"");
			if(content==""){
				alert("评论不能为空！！！");
				return;
			}
			$.ajax({
				"url":"/addComment",
				"data":JSON.stringify({"parentId":parentId,"type":type,"content":content}),
				"success":callBack,
				"contentType": 'application/json',
				"dataType":"json",
					"type":"post"
			});
			function callBack(data){
				if(data.code==200){
					alert("评论成功！！");
				}else{
					alert(data.message);
				}
				
			}
		}
	});
	
});