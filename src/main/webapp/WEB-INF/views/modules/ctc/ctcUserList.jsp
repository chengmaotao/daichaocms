<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导流用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ctc/ctcUser/">导流用户列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ctcUser" action="${ctx}/ctc/ctcUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ctcUser.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>

			<li><label>截止时间：</label>
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ctcUser.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>

			<li><label>来源渠道：</label>
				<%--<form:input path="channelId" htmlEscape="false" maxlength="32" class="input-medium"/>--%>
				<form:select path="channelId" class="input-xlarge">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getCtcChannelList()}" itemLabel="channelName" itemValue="channelId" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>用户ID</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>手机号</th>
				<th>来源渠道</th>
				<th>用户状态</th>
				<th>实名成功时间</th>



				<th>创建时间</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ctcUser">
			<tr>

				<td>
						${ctcUser.userId}
				</td>
				<td>
						${ctcUser.userName}
				</td>
				<td>
						${ctcUser.userSfz}
				</td>
				<td>
						${ctcUser.userPhone}
				</td>
				<td>
						${fns:getCtcChannelById(ctcUser.channelId).channelName}
				</td>
				<td>
						${ctcUser.voUserState}
				</td>
				<td>
					<fmt:formatDate value="${ctcUser.userRealNameTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
					<fmt:formatDate value="${ctcUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ctcUser.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>