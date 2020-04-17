<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="dao.HibernateDAO" %>
<%@ page import="databean.ProductDataBean" %>
<%@ page import="databean.SellerDataBean" %>

<jsp:useBean id="sellerDataBean" class="databean.SellerDataBean"/>
<jsp:useBean id="productDataBean" class="databean.ProductDataBean"/>

<c:set var="table" value="${param.table}"></c:set>

<c:out value="${param}"></c:out>

<c:if test="${param.operation eq 'delete'}">
	<c:set var="objdelete" value="${productDataBean.delete(param.id_to_delete)}"></c:set>
</c:if>

<c:if test="${param.operation eq 'insertProduct'}">
	<c:set var="insertproduct" value="${productDataBean.insert(param.productname, param.productprice, param.id_seller)}"></c:set>
</c:if>

<c:if test="${param.operation eq 'insertSeller'}">
	<c:set var="insertseller" value="${sellerDataBean.insert(param.name, param.description)}"></c:set>
</c:if>


<c:if test="${param.operation eq 'update'}">
	<c:if test="${table eq 'seller'}">
		<c:out value="Update Seller"/>
	</c:if>
	
	<c:if test="${table eq 'product'}">
		<c:out value="Update Product"/>
		<c:set var="updateproduct" value="${productDataBean.update(param.id_to_update,param.productname, param.productprice, param.id_seller)}"></c:set> 
	</c:if>
	
</c:if>


<c:if test="${table eq 'seller'}">
	<table border="1", border-collapse="collapse">
		<tr>
			<td>ID</td>
			<td>Seller Name</td>
			<td>Specialization</td>
		</tr>
		<c:forEach items="${sellerDataBean.sellerlist}" var="seller">
			<tr>
				<td>
					<c:out value="${seller.getId()}"/>
				</td>
				<td>
					<input type="text" value="${seller.getName()}">
				</td>
				<td>
					<input type="text" value="${seller.getDescription()}">
				</td>
				<td>
					<input id="${seller.getId()}" class="upbutton" type="button" value="Update">
				</td>
				<td>
					<input id="${seller.getId()}" class="dlbutton" type="button" value="Delete">
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td><input id="sellername" type="text"></td>
			<td><input id="sellerspec" type="text"></td>
			<td colspan="2"><input id="insertSeller" class="inbutton" type="button" value="Insert"></td>
		</tr>
	</table>
</c:if>

<c:if test="${table eq 'product'}">
	<table border="1", border-collapse="collapse">
		<tr>
			<td>ID</td>
			<td>Product Name</td>
			<td>Price</td>
			<td>Seller Id</td>
		</tr>
		<c:forEach items="${productDataBean.productlist}" var="product">
			<tr class = "trs">
				<td>
					<c:out value="${product.getId()}"/>
				</td>
				<td>
					<input class="${product.getId()}" type="text" value="${product.getName()}">
				</td>
				<td>
					<input class="${product.getId()}" type="text" value="${product.getPrice()}">
				</td>
				<td>
					<input class="${product.getId()}" type="text" value="${product.getSeller().getId()}">
				</td>
				<td>
					<input id="${product.getId()}" class="upbutton" type="button" value="Update">
				</td>
				<td>
					<input id="${product.getId()}" class="dlbutton" type="button" value="Delete">
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td><input id="productname" type="text"></td>
			<td><input id="productprice" type="text"></td>
			<td><input id="id_seller" type="text"></td>
			<td colspan="2"><input id="insertProduct" class="inbutton" type="button" value="Insert"></td>
		</tr>
	</table>
</c:if>