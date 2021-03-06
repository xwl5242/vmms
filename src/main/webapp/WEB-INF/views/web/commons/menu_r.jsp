<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath().toString();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="level" value="${level+1 }" scope="request" />
<c:forEach var="right" items="${treeList}">
	<c:set var="isUrl" value="${right.rightUrl=='#'?false:true }"></c:set>  
	<c:if test="${level==1 }">				
		<li class="site-menu-item ${fn:length(right.childNode)>0?'has-sub':'' } ">
        	<a href="<%=path %>/${isUrl?right.rightUrl:''}" ${isUrl?' data-pjax target="_blank"':'' }>
                <i class="site-menu-icon fa-laptop" aria-hidden="true"></i>
                <span class="site-menu-title">${right.rightName }</span>
                <span class="site-menu-arrow"></span>
            </a>
        <c:if test="${fn:length(right.childNode) > 0}">
        	<ul class="site-menu-sub">
			<li class="site-menu-item ${fn:length(right.childNode)>0?'has-sub':'' } ">
        </c:if>
	</c:if>					
	<c:if test="${level==2 }">						
		<a href="<%=path %>/${isUrl?right.rightUrl:''}" ${isUrl?' data-pjax target="_blank"':'' }>
			<span class="site-menu-title">${right.rightName }</span>
			<c:if test="${fn:length(right.childNode) > 0 }">
				<span class="site-menu-arrow"></span>
			</c:if>
		</a>
		<c:if test="${fn:length(right.childNode) > 0 }">
			<ul class="site-menu-sub">
		</c:if>
	</c:if>							
	<c:if test="${level==3 }">						
		<li class="site-menu-item ">
			<a href="<%=path %>/${isUrl?right.rightUrl:''}" ${isUrl?' data-pjax target="_blank"':'' }>
				<span class="site-menu-title">${right.rightName }</span>
			</a>
		</li>
	</c:if>		
	<c:if test="${level==lastLevel and flag == 1}">	
		<c:set var="lastLevel" value="${lastLevel-1 }"></c:set>		
		</ul>
	</c:if>								
	<c:if test="${level==lastLevel and flag == 1}">	
		<c:set var="lastLevel" value="${lastLevel-1 }"></c:set>							
		</li></ul></li>
	</c:if>						
	<c:choose>
		<c:when test="${fn:length(right.childNode) > 0}">
			<c:set var="treeList" value="${right.childNode}" scope="request" />
	  		<c:import url="menu_r.jsp"></c:import>
		</c:when>
		<c:otherwise>
			<c:set var="flag" value="1"></c:set>
			<c:set var="lastLevel" value="${level+1 }"></c:set>
		</c:otherwise>
	</c:choose>			
</c:forEach>
<c:set var="level" value="${level-1}" scope="request" />