<%@page contentType="text/html;charset=utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<!-- 
<script src={{ url_for('static', filename='js/bootstrap.js') }} type="text/javascript" charset="utf-8"></script>
<script src={{ url_for('static', filename='js/chat.js') }} type="text/javascript" charset="utf-8"></script>
 -->
 
<script src="/WebContent/static/js/jquery-2.1.1.js" type="text/javascript" charset="utf-8"></script>
<script src="/WebContent/static/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script src="/WebContent/static/js/chat.js" type="text/javascript" charset="utf-8"></script> 
<!--  
<link rel="stylesheet" href={{ url_for('static', filename='css/bootstrap.css') }} type="text/css" charset="utf-8"/>
<link rel="stylesheet" href={{ url_for('static', filename='css/chat.css') }} type="text/css" charset="utf-8"/>
-->
<link rel="stylesheet" href="/WebContent/static/css/bootstrap.css" type="text/css" charset="utf-8"/>
<link rel="stylesheet" href="/WebContent/static/css/chat.css" type="text/css" charset="utf-8"/>

<title>对话管理</title>
</head>
<body>
<div class="container">
	<div class="row">
		<h1 class="text-center">
			会议室预定对话系统展示
		</h1>
	</div>
	<div class="row">
		<div class="col-md-5 col-md-offset-1">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-comment"></span>
				</div>
				<div class="panel-body">
					<ul class="chat">
						<li class="left clearfix"><span class="chat-img pull-left">
							<img src="/WebContent/static/img/user.gif" alt="User" class="img-circle" />
						</span>
						<div class="chat-body clearfix">
							<p>
							这是一个苹果这是一个苹果这是一个苹果
							</p>
						</div>
						</li>
						<li class="right clearfix"><span class="chat-img pull-right">
							<img src="/WebContent/static/img/robot.gif" alt="Robort" class="img-circle" />
						</span>
						<div class="chat-body clearfix">
							<p class="pull-right">
							这是一个苹果这是一个苹果这是一个苹果
							</p>
						</div>
						</li>
					</ul>
				</div>
				<div class="panel-footer">
					<div class="input-group">
						<input id="btn-input" type="text" class="form-control input-sm" placeholder="输入需要发送的信息" />
						<span class="input-group-btn">
							<button class="btn btn-warning btn-sm" id="btn-chat">
								发送</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<table id="meet-table" class="table">
				<thead>
					<tr>
						<th>属性</th>
						<th>值</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td> <strong>地点</strong> </td>
					<td id="meet-location"></td>
				</tr>
				<tr>
					<td> <strong>人数</strong> </td>
					<td id="meet-number"></td>
				</tr>
				<tr>
					<td> <strong>日期</strong> </td>
					<td id="meet-date"></td>
				</tr>
				<tr>
					<td> <strong>时长</strong> </td>
					<td id="meet-duration"></td>
				</tr>
				<tr>
					<td> <strong>预算</strong> </td>
					<td id="meet-price"></td>
				</tr>
				<tr>
					
					
				</tr>
				</tbody>
			</table> 
			  <table id="over-table" class="table">
			<tbody>
			<tr><td> <strong>本轮对话结束，刷新继续测试</strong> </td></tr>			
			</tbody>
			
			</table>
		</div>
	</div>
</div>
</div>
</body>
</html>
