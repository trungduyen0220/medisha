<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>BakaBookShop Homepage</title>

        <link rel="stylesheet" href="../css/bootstrap.min_1.css" type="text/css"/>
        <link rel="stylesheet" href="../css/bootstrap.min_1.css" type="text/css">
        <link rel="stylesheet" href="../css/shop-homepage.css" type="text/css"/>
        <link rel="stylesheet" href="../css/shop-homepage.css" type="text/css">
        <link rel="stylesheet" href="../css/bootstrap.css" type="text/css"/>
        <link rel="stylesheet" href="../css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="../css/bootstrap-theme.min.css" type="text/css"/>








        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <%@include file="/header.jsp" %>
    <center>
        <!-- Page Content -->
        <div class="container">

            <div class="row">
                <%@include file="navProfile.jsp" %>

                <div style="text-align: left"class="col-md-9">
                    <div class="container">
                        <!-- Portfolio Item Heading -->
                        <div class="row">
                            <div class="col-lg-12">
                                <h1 class="page-header">Đổi mật khẩu
                                </h1>
                            </div>
                        </div>
                        <!-- /.row -->

                        <!-- Portfolio Item Row -->
                        <div class="row">

                            <div class="col-md-2">
                                <div style="height: 50px">Mật khẩu cũ</div>  
                                <div style="height: 50px">Mật khẩu mới</div>  
                                <div style="height: 50px">Xác nhận mật khẩu</div>  
                            </div>

                            <div class="col-md-4">
                                <form method="post" action="ChgPwd">
                                    <!-- full name = first name + last name -->
                                    <c:if test="${error == 'oldpass'}">
                                        <span style="color:red;">Mật khẩu cũ không chính xác.</span>
                                    </c:if>
                                    <c:if test="${error == 'password'}">
                                        <span style="color:red;">Mật khẩu không trùng khớp</span>
                                    </c:if>
                                    <div class="form-group"><input class="form-control" type="password" name="password" required></div>
                                    <div class="form-group"><input class="form-control" type="password" name="newPassword" required></div>
                                    <div class="form-group"><input class="form-control" type="password" name="confirmPassword"  required></div>
                                    <div class="form-group"><input type="submit" class="btn btn-primary"></div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </center>
    <%@include file="/footer.jsp" %>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>

