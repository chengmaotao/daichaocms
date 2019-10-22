<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>醒目文案管理</title>
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
		<li class="active"><a href="${ctx}/ctc/ctcStriking/">醒目文案列表</a></li>
		<shiro:hasPermission name="ctc:ctcStriking:edit"><li><a href="${ctx}/ctc/ctcStriking/form">醒目文案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ctcStriking" action="${ctx}/ctc/ctcStriking/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>醒目文案：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>醒目文案</th>
				<th>更新时间</th>
				<th>备注信息</th>

				<shiro:hasPermission name="ctc:ctcStriking:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ctcStriking">
			<tr>

				<td>
						${ctcStriking.title}
				</td>

				<td><a href="${ctx}/ctc/ctcStriking/form?id=${ctcStriking.id}">
					<fmt:formatDate value="${ctcStriking.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ctcStriking.remarks}
				</td>
				<shiro:hasPermission name="ctc:ctcStriking:edit"><td>
    				<a href="${ctx}/ctc/ctcStriking/form?id=${ctcStriking.id}">修改</a>
					<a href="${ctx}/ctc/ctcStriking/delete?id=${ctcStriking.id}" onclick="return confirmx('确认要删除该醒目文案吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>