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
    <script>
        function changeType() {
            document.getElementById("address").removeAttribute("disabled");
            return false;
        }
    </script>
    <body>
        <%@include file="/header.jsp" %>
    <center>
        <!-- Page Content -->
        <div class="container">

            <div class="row">


                <div style="text-align: left"class="col-md-12">

                    <!-- Portfolio Item Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Thông tin mua hàng
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->

                    <!-- Portfolio Item Row -->
                    <h1>Đơn hàng</h1>
                    <table class="table table-bordered">
                        <thead>
                            <!--forEach Order-->
                            <tr>
                                <th>Tên sách</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cartItem" items="${cart}">
                                <tr>
                                    <td>${cartItem.book.bookTitle} - ${cartItem.book.author}</td>
                                    <td>${cartItem.quantity}</td>
                                    <td>${cartItem.book.priceAfterSale}đ</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="2">
                                    <b>Tổng</b>
                                </td>
                                <td>
                                    <b>${totalPrice}đ</b>
                                </td>
                            </tr>
                        </tbody>
                    </table>                
                    <form method="post" action="Checkout">
                        Địa chỉ giao hàng <input type="text" name="address" id="address" value="${user.address}" disabled required><button onclick="return changeType()">Sửa địa chỉ giao hàng</button>
                        <input type="submit" value="Xác nhận">
                    </form>

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

