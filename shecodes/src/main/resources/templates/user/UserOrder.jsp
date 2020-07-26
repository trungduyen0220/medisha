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
                                <h1 class="page-header">Lịch sử đơn hàng
                                </h1>
                            </div>
                        </div>
                        <!-- /.row -->

                        <!-- Portfolio Item Row -->

                        <div class="row">
                            <div class="col-lg-10">
                                    <table class="table table-bordered" cellspacing="0">
                                        <thead>
                                            <!--forEach Order-->
                                            <tr>
                                                <th>Mã đơn hàng</th>
                                                <th>Ngày mua</th>
                                                <th>Địa chỉ giao hàng</th>
                                                <th>Trạng thái</th>
                                                <th>Chi tiết</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${orderList}">
                                                <tr>
                                                    <td>${order.orderID}</td>
                                                    <td>${order.dateCreated}</td>
                                                    <td>${order.shippingAddress}</td>
                                                    <c:if test="${order.status == 'Đã nhận'}">
                                                        <td><div style="color:orange">${order.status}</div></td>
                                                        </c:if>
                                                        <c:if test="${order.status == 'Đang vận chuyển'}">
                                                        <td><div style="color:blue">${order.status}</div></td>
                                                        </c:if>
                                                        <c:if test="${order.status == 'Hoàn thành'}">
                                                        <td><div style="color:green">${order.status}</div></td>
                                                        </c:if>
                                                    <td><a href="OrderDetail?orderID=${order.orderID}">Chi tiết</a></td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
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

