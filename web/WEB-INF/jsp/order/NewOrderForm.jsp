<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<%--	<stripes:form--%>
<%--	beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean">--%>
		<form action="#" method="post">

	<table>
		<tr>
			<th colspan=2>Payment Details</th>
		</tr>
		<tr>
			<td>Card Type:</td>
			<td>
				<stripes:select name="order.cardType">
				<stripes:options-collection
					collection="${sessionScope.creditCardTypes}" />
			</stripes:select>
			</td>
		</tr>
		<tr>
			<td>Card Number:</td>
			<td>
<%--				<stripes:text name="order.creditCard" /> * Use a fake--%>
<%--			number!--%>
				<input type="text" name="${order.creditCard}"/>
			</td>
		</tr>
		<tr>
			<td>Expiry Date (MM/YYYY):</td>
			<td>
<%--				<stripes:text name="order.expiryDate" />--%>
				<input type="text" name="${order.expiryDate}"/>
			</td>
		</tr>
		<tr>
			<th colspan=2>Billing Address</th>
		</tr>

		<tr>
			<td>First name:</td>
			<td>
<%--				<stripes:text name="order.billToFirstName" />--%>
				<input type="text" name="${order.billToFirstName}"/>
			</td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td>
<%--				<stripes:text name="order.billToLastName" />--%>
				<input type="text" name="${order.billToLastName}"/>
			</td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td>
<%--				<stripes:text size="40" name="order.billAddress1" />--%>
				<input type="text" size="40" name="${order.billAddress1}"/>
			</td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td>
<%--				<stripes:text size="40" name="order.billAddress2" />--%>
				<input type="text" size="40" name="${order.billAddress2}"/>
			</td>
		</tr>
		<tr>
			<td>City:</td>
			<td>
<%--				<stripes:text name="order.billCity" />--%>
				<input type="text" name="${order.billCity}"/>
			</td>
		</tr>
		<tr>
			<td>State:</td>
			<td>
<%--				<stripes:text size="4" name="order.billState" />--%>
				<input type="text" size="4" name="${order.billState}"/>
			</td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td>
<%--				<stripes:text size="10" name="order.billZip" />--%>
				<input type="text" size="10" name="${order.billZip}"/>
			</td>
		</tr>
		<tr>
			<td>Country:</td>
			<td>
<%--				<stripes:text size="15" name="order.billCountry" />--%>
				<input type="text" size="15" name="${order.billCountry}"/>
			</td>
		</tr>

		<tr>
			<td colspan=2>
<%--				<stripes:checkbox name="shippingAddressRequired" />--%>
<%--			Ship to different address...--%>
				<input type="checkbox" name="shippingAddressRequired"/>Ship to different address...
			</td>
		</tr>

	</table>

<%--	<stripes:submit name="newOrder" value="Continue" />--%>
			<input type="submit" name="newOrder" value="Continue">

		</form>></div><%--</stripes:form>--%>


<%@ include file="../common/IncludeBottom.jsp"%>