<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="editAccount" method="post">
		<h3>User Information</h3>

		<table>
			<tr>
				<td>User ID:</td>
				<td>${sessionScope.account.username}</td>
			</tr>
			<tr>
				<td>New password:</td>
				<td><input type="text" name="account.password" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="text" name="account.repeatedPassword" /></td>
			</tr>
		</table>
		<%@ include file="IncludeAccountFields.jsp"%>

		<input type="submit" name="editAccount" value="Save Account Information" />

	</form>

<%--	<a href="listOrders">My Orders</a>--%>
<%--	<stripes:link--%>
<%--	beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean"--%>
<%--	event="listOrders">My Orders</stripes:link>--%>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
