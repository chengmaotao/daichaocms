<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贷超产品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ctc/ctcProduct/">贷超产品列表</a></li>
		<li class="active"><a href="${ctx}/ctc/ctcProduct/form?id=${ctcProduct.id}">贷超产品<shiro:hasPermission name="ctc:ctcProduct:edit">${not empty ctcProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ctc:ctcProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ctcProduct" action="${ctx}/ctc/ctcProduct/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		

		<div class="control-group">
			<label class="control-label">产品ID：</label>
			<div class="controls">
				<form:input path="productId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="productName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所属分类:</label>
			<div class="controls">
				<form:checkboxes path="productCategoryIdList" items="${fns:getProductCategoryList()}" itemLabel="productCategoryName" itemValue="productCategoryId"
								 htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">产品logo：</label>
			<div class="controls">
				<%--<form:input path="logo" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>

				<input type="file" name="image" class="input-xlarge">

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">可贷额度（列表展示）：</label>
			<div class="controls">
				<form:input path="quota" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最低日利率（列表展示）：</label>
			<div class="controls">
				<form:input path="rate" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">XX万人申请（列表展示）：</label>
			<div class="controls">
				<form:input path="ctcDescribe" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详情描述（门槛低 下款快 额度高）：</label>
			<div class="controls">
				<form:input path="detailsDescribe" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人数XXXX（详情页展示）：</label>
			<div class="controls">
				<form:input path="applyNums" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最快放款时间（详情页展示）：</label>
			<div class="controls">
				<form:input path="fastestLoanTime" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成功率XX（详情页展示）：</label>
			<div class="controls">
				<form:input path="successRate" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">额度范围（详情页展示）：</label>
			<div class="controls">
				<form:input path="quotaRange" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期限范围（详情页展示）：</label>
			<div class="controls">
				<form:input path="termRange" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日利率（详情页展示）：</label>
			<div class="controls">
				<form:input path="dateRate" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请流程（多条件以英文加号+隔开）：</label>
			<div class="controls">
				<form:input path="applyProcess" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请条件（多条件以英文加号+隔开）：</label>
			<div class="controls">
				<form:input path="applyCondition" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所需材料（多条件以英文加号+隔开）：</label>
			<div class="controls">
				<form:input path="applyMaterial" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否主推产品（0是其他不是）：</label>
			<div class="controls">
				<form:input path="newProduct" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="ctc:ctcProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>