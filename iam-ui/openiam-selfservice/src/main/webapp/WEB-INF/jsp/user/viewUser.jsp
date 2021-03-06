<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>
<%--@elvariable id="user" type="org.openiam.idm.srvc.user.dto.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.openiam.com/tags/openiam" prefix="openiam" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${titleOrganizatioName} - <fmt:message key="openiam.ui.user.view.user"/></title>
    <link href="/openiam-ui-static/css/common/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/openiam-ui-static/css/common/style.client.css" rel="stylesheet" type="text/css"/>
    <link href="/openiam-ui-static/plugins/tablesorter/themes/yui/style.css" rel="stylesheet" type="text/css"/>
    <link href="/openiam-ui-static/css/selfservice/selfservice.my.info.css" rel="stylesheet" type="text/css"/>
    <link href="/openiam-ui-static/js/common/jquery/css/smoothness/jquery-ui-1.9.1.custom.css" rel="stylesheet"
          type="text/css"/>
    <link href="/openiam-ui-static/plugins/tiptip/tipTip.css" rel="stylesheet" type="text/css"/>
    <openiam:overrideCSS/>
    <script type="text/javascript" src="/openiam-ui-static/_dynamic/openiamResourceBundle.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/js/common/jquery/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/js/common/jquery/jquery-ui-1.9.1.custom.min.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/plugins/tablesorter/js/jquery.tablesorter-2.0.3.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/plugins/tablesorter/js/jquery.quicksearch.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/plugins/tablesorter/js/jquery.tablesorter.filer.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/plugins/tablesorter/js/jquery.tablesorter.pager.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/js/common/json/json.js"></script>

    <script type="text/javascript" src="/openiam-ui-static/js/common/openiam.common.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/plugins/tiptip/jquery.tipTip.js"></script>
    <script type="text/javascript"
            src="/openiam-ui-static/js/webconsole/plugins/usersearch/user.search.results.js"></script>
    <script type="text/javascript" src="/openiam-ui-static/js/selfservice/user/view.user.js"></script>
    <script type="text/javascript">
        OPENIAM = window.OPENIAM || {};
        OPENIAM.ENV = window.OPENIAM.ENV || {};
        OPENIAM.ENV.MenuTree = <c:choose><c:when test="${! empty requestScope.menuTree}">${requestScope.menuTree}</c:when><c:otherwise>null</c:otherwise></c:choose>;
        OPENIAM.ENV.MenuTreeAppendURL = null;
        OPENIAM.ENV.ContextPath = "${pageContext.request.contextPath}";
        OPENIAM.ENV.UserId = <c:choose><c:when test="${not empty requestScope.user.id}">"${requestScope.user.id}";
        OPENIAM.ENV.СolumnList = ${requestScope.columnList};
        </c:when><c:otherwise>null</c:otherwise></c:choose>;
    </script>
</head>
<body>
<div class="title">
    <fmt:message key="openiam.ui.user"/>: ${user.displayName}
</div>
<div class="frameContentDivider">

    <c:if test="${! empty requestScope.profilePicture}">
        <p>
            <span><img
                    src='<c:url context="${pageContext.request.contextPath}" value="/rest/api/images/${requestScope.profilePicture}" />'/></span>
        </p>
    </c:if>

    <c:if test="${! empty user.firstName}">
        <p>
            <label><fmt:message key="openiam.ui.user.firstname"/>: </label>
            <span>${user.firstName}</span>
        </p>
    </c:if>
    <c:if test="${! empty user.lastName}">
        <p>
            <label><fmt:message key="openiam.ui.user.lastname"/>: </label>
            <span>${user.lastName}</span>
        </p>
    </c:if>
    <c:if test="${! empty user.title}">
        <p>
            <label><fmt:message key="openiam.ui.title"/>: </label>
            <span>${user.title}</span>
        </p>
    </c:if>
    <%--<c:if test="${! empty user.employeeId}">--%>
        <%--<p>--%>
            <%--<label><fmt:message key="openiam.ui.user.employee.id"/>: </label>--%>
            <%--<span>${user.employeeId}</span>--%>
        <%--</p>--%>
    <%--</c:if>--%>
    <c:if test="${! empty requestScope.organizationList and fn:length(requestScope.organizationList) > 0}">
        <p>
            <label><fmt:message key="openiam.ui.common.organizations"/>: </label>
        <ul>
            <c:forEach var="company" items="${requestScope.organizationList}">
                <li>${company.name}</li>
            </c:forEach>
        </ul>
        </p>
    </c:if>
    <c:if test="${! empty user.employeeTypeId}">
        <p>
            <label><fmt:message key="openiam.ui.common.employee.type"/>: </label>
            <span>${user.employeeTypeId}</span>
        </p>
    </c:if>
    <c:if test="${! empty user.jobCodeId}">
        <p>
            <label><fmt:message key="openiam.ui.user.job.code"/>: </label>
            <span>${user.jobCodeId}</span>
        </p>
    </c:if>
    <c:if test="${! empty user.emailAddresses}">
        <p>
            <label><fmt:message key="openiam.ui.common.email.address"/>: </label>

        <ul>
            <c:forEach var="ea" items="${ user.emailAddresses}">
                <li>
                <span>
                    <c:if test="${! empty ea}">
                        <span>  ${ea.typeDescription}: ${ea.emailAddress}</span>
                    </c:if>
                </span></li>
            </c:forEach>
        </ul>

        </p>
    </c:if>
    <c:if test="${! empty user.phones}">
        <p>
            <label><fmt:message key="openiam.ui.common.phone.number"/>: </label>
        <ul>
            <c:forEach var="ph" items="${ user.phones}">
                <li>
            <span>
                    <c:if test="${! empty ph}">
                        ${ph.typeDescription}: <c:if
                            test="${! empty ph.areaCd}">(${ph.areaCd}) </c:if> ${ph.phoneNbr}
                    </c:if>
                </span></li>
            </c:forEach>
        </ul>
        </p>
    </c:if>
    <c:if test="${! empty user.addresses}">
        <p>
            <label><fmt:message key="openiam.ui.common.address"/>: </label>
        <ul>
            <c:forEach var="addr" items="${ user.addresses}">
                <li>
                    <span>
                        <c:if test="${! empty addr}">
                            <c:if test="${! empty addr.typeDescription}">${addr.typeDescription}</c:if>:
                            <c:if test="${! empty addr.postalCd}">${addr.postalCd}</c:if>
                            <c:if test="${! empty addr.country}">${addr.country}</c:if>
                            <c:if test="${! empty addr.state}">${addr.state}</c:if>
                            <c:if test="${! empty addr.city}">${addr.city}</c:if>
                            <c:if test="${! empty addr.address1}">${addr.address1}</c:if>
                            <c:if test="${! empty addr.address2}">${addr.address2}</c:if>
                            <c:if test="${! empty addr.bldgNumber}">${addr.bldgNumber}</c:if>
                        </c:if>
                    </span>
                </li>
            </c:forEach>
        </ul>
        </p>
    </c:if>
    <c:choose>
        <c:when test="${! empty requestScope.supervisorList and fn:length(requestScope.supervisorList) > 0}">
            <p>
                <label><fmt:message key="openiam.ui.user.supervisors"/>: </label>
            <ul>
                <c:forEach var="supervisor" items="${requestScope.supervisorList}">
                    <li>${supervisor.displayName}</li>
                </c:forEach>
            </ul>
            </p>
        </c:when>
        <c:otherwise>
            <p>
                <label><fmt:message key="openiam.ui.user.supervisors"/>: </label>
                <span><fmt:message key="openiam.ui.common.na"/></span>
            </p>
        </c:otherwise>
    </c:choose>
</div>
<div class="title">
    <fmt:message key="openiam.ui.user.direct.reports"/>
</div>
<div id="userResultsArea"></div>
</body>
</html>