<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li><a href="${ctx}/ctc/sysUser/">用户列表</a></li>
		<li class="active"><a href="${ctx}/ctc/sysUser/form?id=${sysUser.id}">用户<shiro:hasPermission name="ctc:sysUser:edit">${not empty sysUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ctc:sysUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysUser" action="${ctx}/ctc/sysUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<input type="hidden" value="c492e0f067994659944b51e6fc3b52a5" name="companyId"><!-- 归属公司 --->

		<input type="hidden" value="b600ae729d5f4c93a06e1934502d6e53" name="office.id"><!-- 归属部门 --->

		<input type="hidden" value="1" name="loginFlag"><!-- 是否可用 --->

        <input type="hidden" value="3" name="userType"><!-- 用户类型 --->

        <input type="hidden" value="1e1f9b6103d441a99b56038b8dbe0103" name="roleId"><!-- 用户角色 --->


		<div class="control-group">
			<label class="control-label">登录名：</label>
			<div class="controls">
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码：</label>
			<div class="controls">
				<%--<form:input path="password" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>--%>
				<input id="newPassword" name="newPassword" type="password" class="input-xlarge"/>
					<span class="help-inline"><font color="red">添加时 如果输入为空 密码则为 123456</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


        <div class="control-group">
            <label class="control-label">渠道ID:</label>
            <div class="controls">
<%--                <form:input path="no" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font> </span>--%>

				<form:select path="no" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getCtcChannelList()}" itemLabel="channelName" itemValue="channelId" htmlEscape="false"/>
				</form:select>
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
			<shiro:hasPermission name="ctc:sysUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>