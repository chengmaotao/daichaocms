<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告轮播图管理</title>
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
		<li class="active"><a href="${ctx}/ctc/ctcAdvet/">广告轮播图列表</a></li>
		<shiro:hasPermission name="ctc:ctcAdvet:edit"><li><a href="${ctx}/ctc/ctcAdvet/form">广告轮播图添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ctcAdvet" action="${ctx}/ctc/ctcAdvet/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>轮播图</th>
				<th>跳转地址</th>
				<th>产品ID</th>

				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="ctc:ctcAdvet:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ctcAdvet">
			<tr>

				<td>
					<img src="${ctcAdvet.voAdvetUrl}"  alt="轮播图" />
				</td>

				<td>
						${ctcAdvet.advetJumpUrl}
				</td>

				<td>
						${ctcAdvet.productId}
				</td>

				<td><a href="${ctx}/ctc/ctcAdvet/form?id=${ctcAdvet.id}">
					<fmt:formatDate value="${ctcAdvet.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ctcAdvet.remarks}
				</td>
				<shiro:hasPermission name="ctc:ctcAdvet:edit"><td>
    				<a href="${ctx}/ctc/ctcAdvet/form?id=${ctcAdvet.id}">修改</a>
					<a href="${ctx}/ctc/ctcAdvet/delete?id=${ctcAdvet.id}" onclick="return confirmx('确认要删除该轮播图吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>