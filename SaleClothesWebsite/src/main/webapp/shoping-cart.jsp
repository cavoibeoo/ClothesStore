<%@ page import="entity.CustomerProductEntity" %>
<%@ page import="java.util.List" %>
<%@ include file="includes/header.jsp" %>
	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home-02.html.html" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				Shoping Cart
			</span>
		</div>
	</div>
	<!-- Shoping Cart -->
	<form class="bg0 p-t-75 p-b-85" action = "order" method="post">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">

						<div class="table-responsive tab-all" style="overflow-x:auto;">
							<table class="table table-cart" id="my-orders-table">
								<thead class="thead-default">
								<tr class="table_head">
									<th class="column-0">Checks</th>
									<th class="column-1">Product</th>
									<th class="column-2">Type</th>
									<th class="column-3">Price</th>
									<th class="column-4">Quantity</th>
									<th class="column-5">Total</th>
								</tr>
								</thead>

								<% List<CustomerProductEntity> customerCart = (List<CustomerProductEntity>) session.getAttribute("cart"); %>
								<c:if test="${customerCart != null}">
									<% for (CustomerProductEntity product : customerCart) { %>
									<tr class="table_row">
										<td class="column-0" style="">
											<input class="ui-checkbox" type = checkbox name="checkedProduct" value="<%=product.getProductId()%>">
										</td>

										<td class="column-1">
											<a href="cart?action=add&id=<%=product.getProductId()%>&quantity=-<%=product.getQuantity()%>">
												<div class="how-itemcart1" >
													<% String itemCartVariable = "images/item_cart/item-cart-0" + product.getProductId() + ".jpg"; %>
													<img src="<%= itemCartVariable %>" alt="IMG">
												</div>
											</a>
										</td>

										<td class="column-2"><%= product.assignName() %>  (Size L / Color Red)</td>

										<td class="column-3">$ <%= product.assignUnitPrice() %></td>
										<% int currentQuantity = product.getQuantity();%>
										<td class="column-4" style="align-content: center">
											<div class="wrap-num-product flex-w m-l-auto m-r-0">
												<a class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m" href="cart?action=add&id=<%=product.getProductId()%>&quantity=-1">
													<i class="fs-16 zmdi zmdi-minus"></i>
												</a>

												<input class="mtext-104 cl3 txt-center num-product" type="number" name="quantity" value="<%= currentQuantity %>">

												<a class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m" href="cart?action=add&id=<%= product.getProductId() %>&quantity=1">
													<i class="fs-16 zmdi zmdi-plus"></i>
												</a>
											</div>
										</td>
										<td class="column-5">$ <%= product.assignUnitPrice()*currentQuantity %></td>
									</tr>
									<% } %>
								</c:if>
							</table>
						</div>

						<%--<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-0">Checks</th>
									<th class="column-1">Product</th>
									<th class="column-2"></th>
									<th class="column-3">Price</th>
									<th class="column-4">Quantity</th>
									<th class="column-5">Total</th>
								</tr>

								<% List<CustomerProductEntity> customerCart = (List<CustomerProductEntity>) session.getAttribute("cart"); %>
								<c:if test="${customerCart != null}">
									<% for (CustomerProductEntity product : customerCart) { %>
									<tr class="table_row">
										<td class="column-0">
											<input class="ui-checkbox" type = checkbox name="checkedProduct" value="<%=product.getProductId()%>">
										</td>
										<td class="column-1">
											<a href="cart?action=add&id=<%=product.getProductId()%>&quantity=-<%=product.getQuantity()%>">
											<div class="how-itemcart1" >
												<% String itemCartVariable = "images/item-cart-0" + product.getProductId() + ".jpg"; %>
												<img src="<%= itemCartVariable %>" alt="IMG">
											</div>
											</a>
										</td>
										<td class="column-2"><%= product.assignName() %></td>
										<td class="column-3">$ <%= product.assignUnitPrice() %></td>
										<% int currentQuantity = product.getQuantity();%>
										<td class="column-4">
											<div class="wrap-num-product flex-w m-l-auto m-r-0">
												<a class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m" href="cart?action=add&id=<%=product.getProductId()%>&quantity=-1">
													<i class="fs-16 zmdi zmdi-minus"></i>
												</a>

												<input class="mtext-104 cl3 txt-center num-product" type="number" name="quantity" value="<%= currentQuantity %>">

												<a class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m" href="cart?action=add&id=<%= product.getProductId() %>&quantity=1">
													<i class="fs-16 zmdi zmdi-plus"></i>
												</a>
											</div>
										</td>
										<td class="column-5">$ <%= product.assignUnitPrice()*currentQuantity %></td>
									</tr>
									<% } %>
								</c:if>

							</table>
						</div>--%>

						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder="Coupon Code">

								<%--<div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
									Apply coupon
								</div>--%>
							</div>

							<input type="hidden" name="action" value="create">
							<button type="submit" value="Submit">
								<div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
									Proceed Order
								</div>
							</button>
							<a href="orderdetails.html">Proceed Order</a>
						</div>
					</div>
				</div>

<%--				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">--%>
<%--					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">--%>
<%--						<h4 class="mtext-109 cl2 p-b-30">--%>
<%--							Cart Totals--%>
<%--						</h4>--%>

<%--						<div class="flex-w flex-t bor12 p-b-13">--%>
<%--							<div class="size-208">--%>
<%--								<span class="stext-110 cl2">--%>
<%--									Subtotal:--%>
<%--								</span>--%>
<%--							</div>--%>

<%--							<div class="size-209">--%>
<%--								<span class="mtext-110 cl2">--%>
<%--									$79.65--%>
<%--								</span>--%>
<%--							</div>--%>
<%--						</div>--%>

<%--						<div class="flex-w flex-t bor12 p-t-15 p-b-30">--%>
<%--							<div class="size-208 w-full-ssm">--%>
<%--								<span class="stext-110 cl2">--%>
<%--									Shipping:--%>
<%--								</span>--%>
<%--							</div>--%>

<%--							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">--%>
<%--								<p class="stext-111 cl6 p-t-2">--%>
<%--									There are no shipping methods available. Please double check your address, or contact us if you need any help.--%>
<%--								</p>--%>
<%--								--%>
<%--								<div class="p-t-15">--%>
<%--									<span class="stext-112 cl8">--%>
<%--										Calculate Shipping--%>
<%--									</span>--%>

<%--									<div class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9">--%>
<%--										<select class="js-select2" name="time">--%>
<%--											<option>Select a country...</option>--%>
<%--											<option>USA</option>--%>
<%--											<option>UK</option>--%>
<%--										</select>--%>
<%--										<div class="dropDownSelect2"></div>--%>
<%--									</div>--%>

<%--									<div class="bor8 bg0 m-b-12">--%>
<%--										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="state" placeholder="State /  country">--%>
<%--									</div>--%>

<%--									<div class="bor8 bg0 m-b-22">--%>
<%--										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="postcode" placeholder="Postcode / Zip">--%>
<%--									</div>--%>
<%--									--%>
<%--									<div class="flex-w">--%>
<%--										<div class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer">--%>
<%--											Update Totals--%>
<%--										</div>--%>
<%--									</div>--%>
<%--										--%>
<%--								</div>--%>
<%--							</div>--%>
<%--						</div>--%>

<%--						<div class="flex-w flex-t p-t-27 p-b-33">--%>
<%--							<div class="size-208">--%>
<%--								<span class="mtext-101 cl2">--%>
<%--									Total:--%>
<%--								</span>--%>
<%--							</div>--%>

<%--							<div class="size-209 p-t-1">--%>
<%--								<span class="mtext-110 cl2">--%>
<%--									$79.65--%>
<%--								</span>--%>
<%--							</div>--%>
<%--						</div>--%>

<%--						<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">--%>
<%--							Proceed to Checkout--%>
<%--						</button>--%>
<%--					</div>--%>
<%--				</div>--%>
			</div>
		</div>
	</form>

	<!-- Footer -->
	<%@ include file="includes/footer.jsp" %>
</body>
</html>