<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li class="active"><a href="${ctx}/ctc/sysUser/">用户列表</a></li>
		<shiro:hasPermission name="ctc:sysUser:edit"><li><a href="${ctx}/ctc/sysUser/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysUser" action="${ctx}/ctc/sysUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录名</th>
				<th>姓名</th>
				<th>渠道ID</th>

				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="ctc:sysUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysUser">
			<tr>

				<td>
						${sysUser.loginName}
				</td>

				<td><a href="${ctx}/ctc/sysUser/form?id=${sysUser.id}">
					${sysUser.name}
				</a></td>

				<td>
						${sysUser.no}
				</td>

				<td>
					<fmt:formatDate value="${sysUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysUser.remarks}
				</td>
				<shiro:hasPermission name="ctc:sysUser:edit"><td>
    				<a href="${ctx}/ctc/sysUser/form?id=${sysUser.id}">修改</a>
					<a href="${ctx}/ctc/sysUser/delete?id=${sysUser.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>