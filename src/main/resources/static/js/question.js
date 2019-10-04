/**
 * question.html的js
 */


jQuery.noConflict();//让渡$的使用权，其他脚本库可以使用$
jQuery(document).ready(function($){
	
	$(".glyphicon").hover(function(){
		$(this).toggleClass("iconhover");
	});
	
	/**
	 * 封装异步请求 作为问题回复和回复的回复的统一调用接口
	 */
	function post(content,type,parentId){
		
		if(confirm("是否进行评论？")){
			
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
					location.reload();
				}else{
					alert(data.message);
				}
				
			}
		}
	}
	
	$(".btn-success").click(function(){
		var parentId = $("#parentId").val();
		var type =2;
		var content = $("#content").val().replace(/\s+/g,"");
		post(content,type,parentId);
	});
	
	function get(id){
		$.getJSON("/comment/"+id,function(data){
			moment.locale('CN');
			var $comment = $("#comment-"+id);
			$.each(JSON.parse(data.model),function(i,v){
				var day = moment(v.gmtCreated).format('YYYYMMDD');
				$comment.append("<div class='media questionList'>"+
						"<div class='media-left'>"+
							"<a href='#'> <img class='media-object'"+
								"src='"+v.creator.avatarUrl+"' alt='...'>"+
							"</a>"+
						"</div>"+
						"<div class='media-body'>"+
							"<h5 class='media-heading'>"+
								"<a href='/' >"+v.creator.name+"</a><span style='margin-left:10px'>"+moment(day,"YYYYMMDD").fromNow()+"</span>"+
							"</h5>"+
							"<p>"+v.content+"</p>"+
						"</div>"+
					"</div>");
			});
			$comment.append("<div class='sub-form'>" +
					"<input id='sub-content-"+id+"' class='form-control' placeholder='评论一下(✪ω✪)'>" +
					"<p><button type='button' class='btn btn-success' data-id='"+id+"'>评论</button><p>" +
					"</div>");
			$(".sub-comment").on("click",".btn-success",function(){
				var id = $(this).data("id");
				post($("#sub-content-"+id).val(),1,id);
			});
			
		});
	}
	
	
	
	$(".glyphicon-comment").click(function(){
		
		 var id = $(this).data("id");
		 $(this).toggleClass("active");
		 if($(this).hasClass("active")){
			 get(id);
		 }else{
			 $("#comment-"+id).html("");
		 }
		 $("#comment-"+id).toggleClass("in");
		 
	});
	
});