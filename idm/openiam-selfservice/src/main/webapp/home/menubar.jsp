<%@ page language="java" %>
<%@ page session="true" %>
<%@page import="java.util.*, org.openiam.idm.srvc.menu.dto.Menu, org.openiam.idm.srvc.user.dto.User,org.openiam.idm.srvc.user.dto.UserStatusEnum"%>
<%@ page import="org.openiam.idm.srvc.res.dto.Resource" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="java.util.ResourceBundle" %>


<%

    String selfserviceContext = (String)session.getAttribute("selfserviceContext");
    String selfserviceExtContext = (String)session.getAttribute("selfserviceExtContext");



	String rMenu = (String)request.getSession().getAttribute("hideRMenu");
	if (rMenu == null ||  !rMenu.equals("1")) {

%>	

<%
System.out.println("menubar.jsp");
  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");
  String login = (String)session.getAttribute("login");
  

  List<Menu> privRightMenuList1 = (List<Menu>)session.getAttribute("privateRightMenuGroup1");
  List<Menu> privRightMenuList2 = (List<Menu>)session.getAttribute("privateRightMenuGroup2");
    List<Menu> privEduList = (List<Menu>)session.getAttribute("privateEduMenu");


  String queryString = null;

  if (userId != null) {
    if (token != null) {
      queryString =  "tk=" + token ;
    }
  }


%>

		<div id="sidebar">
<% 
	if (userId != null && privRightMenuList1 != null ) { 
%>			
			<div class="head">
				Access Management Center
			</div>
			<ul class="menu">
<%	
			for (Menu m: privRightMenuList1) {
				if (m.getSelected()) {
					String url = m.getUrl();
				     if (url != null) {
				         if (url.indexOf("?") == -1) {
				            url = url + "?" + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         } else {    
				            url = url + "&"  + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         }
                         url = url.replace("{SELFSERVICE}", selfserviceContext);
                         url = url.replace("{SELFSERVICE_EXT}", selfserviceExtContext);

				      }



	%>				
				
				<li><a href="<%=url %>"><%=m.getMenuName() %></a></li>
                    <%
                        if ("Manage User".equalsIgnoreCase(m.getMenuName())) {
                    %>
                    <jsp:include page="sidemenu.jsp" />

	<%			    }
                }
			} 
	%>
				</ul>
	<%
		} 
	%>	
	
	<% 
		if (userId != null && privRightMenuList2 != null ) {
	%>
			<div class="head">
				Self - Service Center
			</div>
			<ul class="menu">
			
<%				 
			for (Menu m: privRightMenuList2) {
				if (m.getSelected()) {
					String url = m.getUrl();
				     if (url != null) {
				         if (url.indexOf("?") == -1) {
				            url = url + "?" + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         } else {    
				            url = url + "&"  + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
				         }
                         url = url.replace("{SELFSERVICE}", selfserviceContext);
                         url = url.replace("{SELFSERVICE_EXT}", selfserviceExtContext);
				      }
				    if (m.getUrl().toLowerCase().contains("http")) {				  	 
	%>
			            <li><a href="<%= request.getContextPath() %>/pub/launchProcess.selfserve?menuId=<%=m.getId().getMenuId()%> %>"><%=m.getMenuName() %></a></li>

	<% 				} else { %>
			<li><a href="<%=url %>"><%=m.getMenuName() %></a></li>

	<% 				}
				}
			}
    %>
            </ul>
    <%
		}

	%>

            <%
                if (userId != null && privEduList != null ) {
            %>
            <div class="head">
                Course Management Center
            </div>
            <ul class="menu">

                <%
                    for (Menu m: privEduList) {
                        if (m.getSelected()) {
                            String url = m.getUrl();
                            if (url != null) {
                                if (url.indexOf("?") == -1) {
                                    url = url + "?" + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
                                } else {
                                    url = url + "&"  + queryString + "&menuid=" + m.getId().getMenuId() + "&l=p";
                                }
                                url = url.replace("{SELFSERVICE}", selfserviceContext);
                                url = url.replace("{SELFSERVICE_EXT}", selfserviceExtContext);
                            }
                %>

                <li><a href="<%=url %>"><%=m.getMenuName() %></a></li>

                <%
                }
                }
                %>
            </ul>
            <%
                }

            %>



            <%
                }
            %>

            <div class="head">
		<a href="<%= request.getContextPath() %>/logout.do">Logout</a>
	</div>
	
	</div>

