<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.criterion.Order" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Or" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="entity.*" %>
<%@ page import="java.util.ServiceLoader" %>
<%@ include file="includes/header.jsp" %>

<!-- AccountInformation -->
<section class="sec-product bg0 p-t-100 p-b-50">
    <div class="container">
        <div id="parent" class="row">
            <div id="a" class="col-xs-12 col-sm-12 col-lg-9 col-left-account">
                <div class="title_head margin-bottom-20 m992">
                    <h1 class="title_center_page left">
                        <div id="padding__">Customer Account</div>
                    </h1>
                </div>
                <div class="form-signup name-account m992">
                    <p><strong>Anhonn, <a href="#" style="color:#ad8610;">Quang Tran</a>&nbsp;!</strong></p>
                </div>
                <div class="col-xs-12 col-sm-12 col-lg-12 no-padding">
                    <div class="my-account">
                        <div class="dashboard">
                            <div class="recent-orders">
                                <div class="table-responsive tab-all" style="overflow-x:auto;">
                                    <table class="table table-cart" id="my-orders-table">
                                        <thead class="thead-default">
                                        <tr class="table_head">
                                            <th class="column-0">Order</th>
                                            <th class="column-1">Order Date</th>
                                            <th class="column-1.5">Complete Date</th>
                                            <th class="column-2">Order Price</th>
                                            <th class="column-3">Status</th>
                                            <th class="column-4"></th>
                                        </tr>
                                        </thead>

                                        <% List<OrdersEntity> customerOrders = (List<OrdersEntity>) session.getAttribute("customerOrders");
                                        if (session.getAttribute("customerOrders")!= null){%>

                                            <% for (OrdersEntity order : customerOrders) { %>
                                            <tr class="table_row">
                                                <td class="column-0">
                                                        <input type="hidden" name="requestedOrder" value="<%=order.getOrderId()%>">
                                                        <button type="button" data-bs-toggle="modal" data-bs-target="#largeModal<%=order.getOrderId()%>" style="color: #9a6e3a" name="getOrderBtn" value="<%=order.getOrderId()%>">
                                                            <%=order.getOrderId()%>
                                                        </button>

<%--                                                    Edit Order in POPUP--%>
                                                    <div class="modal fade" id="largeModal<%=order.getOrderId()%>" tabindex="-1" aria-hidden="true" style="display: none; position: fixed; top: 90px; left: 0; width: 100%; height: 80vh;">
                                                        <div class="modal-dialog modal-lg " style="min-height: 100vh; width: 50%;">
                                                            <div class="modal-content" style="background-color: #f0f0f0fa">
                                                                <div class="letterhead"></div>
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title">Order Information</h5>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form>
                                                                        <div class="mb-3">
                                                                            <label style="font-size: larger"><box-icon type='solid' name='location-plus'></box-icon>Address:</label>
                                                                            <%CustomeraccountEntity account = (CustomeraccountEntity) session.getAttribute("user");%>
                                                                            <%CustomerEntity customer = CustomerEntity.findByID(account.getCustomerId());%>
                                                                            <label style="font-weight: 700;color: #222;"> <%=customer.getFirstName() + customer.getLastName() + " - " + customer.getPhoneNumber()%>
                                                                            </label>
                                                                            <label><%=customer.getAddress()%></label>
                                                                        </div>
                                                                        <div class="mb-3">
                                                                            <label style="font-weight: 700;color: #222;">Date: <%=order.getDate()%> </label>
                                                                            <%String completeDate = "Not Completed";
                                                                            if (order.getDateComplete() != null) completeDate = order.getDateComplete().toString();
                                                                            %>
                                                                            <label style="font-weight: 700;color: #222;">Date: <%=completeDate%> </label>
                                                                                <div class="col-md-12 col-sm-12 col-xs-12 content-page customer-table-wrap detail-table-order">
                                                                                <div class="customer-table-bg">
                                                                                    <p classs="title-detail">Order details	</p>
                                                                                    <div class="table-responsive">
                                                                                        <table id="order_details" class="table tableOrder">
                                                                                            <tbody><tr height="40px">
                                                                                                <th class="">Product</th>
                                                                                                <%--<th class="text-center">Product id</th>--%>
                                                                                                <th class="text-center">Price</th>
                                                                                                <th class="text-center">Quantity</th>
                                                                                                <th class="total text-right">Total</th>
                                                                                            </tr>

                                                                                            <%List<OrderProductsEntity> products = OrderProductsEntity.getOrderProductById(order.getOrderId());
                                                                                            for (OrderProductsEntity productOrder:products){

                                                                                            ProductsEntity product = ProductsEntity.findByID(productOrder.getProductId());
                                                                                            %>

                                                                                            <tr height="40px" id="1196577302" class="odd">
                                                                                                <td class="" style="max-width:300px">
                                                                                                    <a href="productDetail?id=<%=product.getProductId()%>" title="">
                                                                                                        <img src="images/item_cart/item-cart-0<%=product.getProductId()%>.jpg" alt="IMG">
                                                                                                    </a>
                                                                                                    <br>
                                                                                                    <%=product.getProductName()%>
                                                                                                    <br>
                                                                                                    <span class="variant_acc"><%="Size: " + productOrder.getSizeId() + "/Color: " + productOrder.getColorId() %></span>

                                                                                                </td>
                                                                                                <%--<td class="sku text-center">STDT003</td>--%>
                                                                                                <td class="money text-center"><%=product.getUnitPrice()%></td>
                                                                                                <td class="quantity center text-center"><%=productOrder.getQuantity()%></td>
                                                                                                <td class="total money text-right"><%=productOrder.getTotal()%></td>
                                                                                            </tr>
                                                                                            <%}%>

                                                                                            <tr height="40px" class="order_summary">
                                                                                                <td class="text-right" colspan="4"><b>Discount</b></td>
                                                                                                <td class="total money text-right"><b><%=order.getDiscount()%></b></td>
                                                                                            </tr>

                                                                                            <tr height="40px" class="order_summary order_total">
                                                                                                <td class="text-right" colspan="4"><b>Total Price</b></td>
                                                                                                <td class="total money text-right"><b><%=order.getTotalAmount()%></b></td>
                                                                                            </tr>
                                                                                            </tbody></table>
                                                                                    </div>
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
<%--                                                                    <button type="button" class="btn btn-primary">Save Changes</button>--%>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="column-1">
                                                    <%=order.getDate()%>
                                                </td>
                                                <td class="column-1.5">
                                                    <%=completeDate%>
                                                </td>
                                                <td class="column-2"><%= order.getTotalAmount() %></td>
                                                <%
                                                    String tmpStatus = "Not confirmed";
                                                    if (order.getIsCancel() == 1) tmpStatus = "Canceled";
                                                    else if (order.getStatus() == 1) tmpStatus = "Shipping";
                                                    else if (order.getStatus() == 2) tmpStatus = "Complete";
                                                %>
                                                <td class="column-3"> <%= tmpStatus %></td>
                                                <%if (order.getIsCancel() == 0){%>
                                                <td class="column-4"><button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#verticalycentered<%=order.getOrderId()%>">Cancel</button>
                                                <div class="modal fade" id="verticalycentered<%=order.getOrderId()%>" tabindex="-1" aria-hidden="true" style="display: none; position: fixed; top: 150px; left: 0; width: 100%; height: 80vh;">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">CANCEL ORDER</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to cancel this order? please check carefully brfore taking this action.
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <a href="order?action=cancel&orderId=<%=order.getOrderId()%>">
                                                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Yes, Cancel this order</button>
                                                                </a>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%}%>
                                                </td>

                                            </tr>
                                            <% } %>
                                            <% } %>
                                        <tbody>
                                        <c:if test="${customerOrders == null}">
                                        <tr>
                                            <td colspan="6"><p style="color: #666666">There are no order.</p></td>
                                        </tr>
                                        </c:if>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="text-xs-right">
                                </div>
                            </div>
                            <div class="paginate-pages pull-right page-account">
                                <nav>
                                    <ul class="pagination clearfix">
                                        <li class="page-item disabled"><a class="page-link" href="#"><</a></li>
                                        <li class="page-item disabled"><a class="page-link" href="#">></a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="b" class="col-xs-12 col-sm-12 col-lg-3 col-right-account">
                <div class="block-account">
                    <div class="block-title-account"><h5 style="color: #323c3f; font-size: 20px">My Account</h5></div>
                    <div class="block-content form-signup">
                        <%CustomeraccountEntity currAcc = (CustomeraccountEntity) session.getAttribute("user");%>
                        <%CustomerEntity currCustomer = CustomerEntity.findByID(currAcc.getCustomerId());%>
                        <p>Account Name: <strong style="color:#ad8610; line-height: 20px;"> <%=currCustomer.getFirstName() + " " + currCustomer.getLastName()%></strong></p>
                        <p><i class="fa fa-home font-some" aria-hidden="true"></i>  <span>Address: <%=currCustomer.getAddress()%></span></p>
                        <p><i class="fa fa-mobile font-some" aria-hidden="true"></i> <span>Phone numbers: <%=currCustomer.getPhoneNumber()%></span> </p>

                        <p><i class="fa fa-map-marker font-some" aria-hidden="true"></i> <span> Level: <%=currAcc.getLevelId()%></span></p>
                        <p><i class="fa fa-yelp font-some" aria-hidden="true"></i> <span> Total Payment: <%=currAcc.getTotalPayment()%></span></p>
                        <p style="margin-top:20px;"><a href="editcustomerin4.jsp" class="btn btn-bg full-width btn-lg btn-style article-readmore">Edit</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<%@ include file="includes/footer.jsp" %>
</body>
</html>
