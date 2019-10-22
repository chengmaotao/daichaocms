<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贷超产品管理</title>
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
		<li class="active"><a href="${ctx}/ctc/ctcProduct/">贷超产品列表</a></li>
		<shiro:hasPermission name="ctc:ctcProduct:edit"><li><a href="${ctx}/ctc/ctcProduct/form">贷超产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ctcProduct" action="${ctx}/ctc/ctcProduct/" method="post" class="breadcrumb form-search">
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
				<th>产品logo</th>
				<th>产品ID</th>
				<th>产品名称</th>
				<th>所属分类</th>

				<th>是否主推产品（0是其他不是）</th>


				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="ctc:ctcProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ctcProduct">
			<tr>

				<td>

                    <img src="${ctcProduct.voLogo}"  alt="产品Logo" />
				</td>
				<td>
						${ctcProduct.productId}
				</td>
				<td>
						${ctcProduct.productName}
				</td>
				<td>
						${ctcProduct.productCategoryNames}
				</td>

				<td>
						${ctcProduct.newProduct}
				</td>

				<td><a href="${ctx}/ctc/ctcProduct/form?id=${ctcProduct.id}">
					<fmt:formatDate value="${ctcProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ctcProduct.remarks}
				</td>
				<shiro:hasPermission name="ctc:ctcProduct:edit"><td>
    				<a href="${ctx}/ctc/ctcProduct/form?id=${ctcProduct.id}">修改</a>
					<a href="${ctx}/ctc/ctcProduct/delete?id=${ctcProduct.id}" onclick="return confirmx('确认要删除该贷超产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>