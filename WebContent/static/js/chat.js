$(document).ready(function(){

$("#btn-input").trigger("focus");


$('#btn-input').keypress(function (e) {
  var key = e.which;
  if(key == 13)  // the enter key code
  {
    $('#btn-chat').click();
    return false;  
  }
});


var data_table = $("#meet-table");
data_table.css("visibility", "visible");
$("#btn-chat").on("click", function() {
	var input = $("#btn-input");
	var utterance = $.trim(input.val());
	if (utterance !== "") {
		//location.href="<%=basePath%>/HelloServlet?p="+utterance;
		var user_node = $("ul.chat li.left.clearfix:first").clone();
		user_node.find('p').text(utterance);
		user_node.css('display', 'block');
		$("ul.chat").append(user_node);
		
		$.get("http://localhost:8080/WebContent/dialogue",
			{"utterance": utterance},
			
			function(data) {
				console.log("Hello World");
				var user_node = $("ul.chat li.right.clearfix:first").clone();
				user_node.find('p').text(data["sentence"]);
				user_node.css('display', 'block');
				$("ul.chat").append(user_node);

				
				for (key in data["slots"]) {
					data_table.find("#" + key + ":first").text(data["slots"][key]);
				}
				// 对话结束，请刷新
					if (data["end"]) {
					var over_table = $("#over-table");
					over_table.css("visibility", "visible");
				}

	// 滚动条到最下面
	$(".panel-body").scrollTop($(".panel-body")[0].scrollHeight);
			},
			"json"
			);
	}



	// 清空输入框
	input.val('');
	input.trigger("focus");
});

});
