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

        <link rel="stylesheet" href="../../css/bootstrap.min_1.css" type="text/css"/>
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
                                <h1 class="page-header">Chi tiết đơn hàng
                                </h1>
                            </div>
                        </div>
                        <!-- /.row -->

                        <!-- Portfolio Item Row -->

                        <div class="row">
                            <div class="col-lg-8">
                                <table border="1" cellspacing="0">
                                    <table class="table table-bordered">
                                        <thead>
                                            <!--forEach Order-->
                                            <tr>
                                                <th>Mã đơn hàng</th>
                                                <th>Minh Họa</th>
                                                <th>Tên sách</th>
                                                <th>Số lượng</th>
                                                <th>Đơn giá</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="orderDetail" items="${orderDetail}">
                                                <tr>
                                                    <td>${orderID}-${orderDetail.orderDetailID}</td>
                                                    <td><img src="/PRJ_Project/${orderDetail.book.illuLink}" alt="" style="display: block; height: 300px; width: 200px"></td>
                                                    <td>${orderDetail.book.bookTitle} - ${orderDetail.book.author}</td>
                                                    <td>${orderDetail.quantity}</td>
                                                    <td>${orderDetail.book.priceAfterSale}đ</td>
                                                </tr>
                                            </c:forEach>
                                                <tr>
                                                    <td colspan="4">Tổng</td>
                                                    <td>${total}đ</td>
                                                </tr>

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

