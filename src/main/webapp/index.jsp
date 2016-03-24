<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>

    <!-- The styles -->
    <%--<link id="bs-css" href="<%=basePath%>/charisma/css/bootstrap-cerulean.min.css" rel="stylesheet">--%>
    <link href="<%=basePath%>/charisma/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="<%=basePath%>/charisma/css/charisma-app.css" rel="stylesheet">
    <link href='<%=basePath%>/charisma/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='<%=basePath%>/charisma/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/jquery.noty.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/noty_theme_default.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/elfinder.min.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/elfinder.theme.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/uploadify.css' rel='stylesheet'>
    <link href='<%=basePath%>/charisma/css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="<%=basePath%>/charisma/bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="<%=basePath%>/charisma/img/favicon.ico">

</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Main</li>
                        <li><a class="ajax-link" href="index.html"><i class="glyphicon glyphicon-home"></i><span> Dashboard</span></a>
                        </li>
                        <li><a class="ajax-link" href="ui.html"><i class="glyphicon glyphicon-eye-open"></i><span> UI Features</span></a>
                        </li>
                        <li><a class="ajax-link" href="form.html"><i
                                    class="glyphicon glyphicon-edit"></i><span> Forms</span></a></li>
                        <li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span> Charts</span></a>
                        </li>
                        <li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span> Typography</span></a>
                        </li>
                        <li><a class="ajax-link" href="gallery.html"><i class="glyphicon glyphicon-picture"></i><span> Gallery</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>
                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <div class="row">
            <div class="box col-md-12">
            <div class="box-inner">
            <%--<div class="box-header well" data-original-title="">--%>
                <%--&lt;%&ndash;<h2><i class="glyphicon glyphicon-user"></i> Datatable + Responsive</h2>&ndash;%&gt;--%>
                <%--<div class="box-icon">--%>
                    <%--<a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>--%>
                    <%--<a href="#" class="btn btn-minimize btn-round btn-default"><i--%>
                            <%--class="glyphicon glyphicon-chevron-up"></i></a>--%>
                    <%--<a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>--%>
                <%--</div>--%>
            <%--</div>--%>
            </div>
            </div>
            </div><!--/row-->
        </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->
    <hr>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <%--<div class="modal-dialog">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal">×</button>--%>
                    <%--<h3>Settings</h3>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>
                    <%--<p>Here settings can be configured...</p>--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>--%>
                    <%--<a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://www.isee.zju.edu.cn" target="_blank">浙江大学信电系</a> 2015 - 2016</p>
        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Developed by: <a href="http://www.isee.zju.edu.cn">智慧医疗实验室</a></p>
    </footer>

</div><!--/.fluid-container-->

<!-- external javascript -->

<script src="<%=basePath%>/charisma/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="<%=basePath%>/charisma/js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='<%=basePath%>/charisma/bower_components/moment/min/moment.min.js'></script>
<script src='<%=basePath%>/charisma/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='<%=basePath%>/charisma/js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="<%=basePath%>/charisma/bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="<%=basePath%>/charisma/bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="<%=basePath%>/charisma/js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="<%=basePath%>/charisma/bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="<%=basePath%>/charisma/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="<%=basePath%>/charisma/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="<%=basePath%>/charisma/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="<%=basePath%>/charisma/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="<%=basePath%>/charisma/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="<%=basePath%>/charisma/js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="<%=basePath%>/charisma/js/charisma.js"></script>


</body>
</html>
