<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="JPAConfig.JPAConfig" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.logging.Level" %>
<%@ page import="entity.*" %>
<link rel="stylesheet" type="text/css" href="css/orderdetails.css">
<script src="js/vietnamlocalselector.js"></script>
<script src="js/citycombobox.js"></script>
<!--===============================================================================================-->
<html>
<head>
    <meta charset="UTF-8">
</head>
    <body>
    <div class="content">
        <div class="wrap">
            <div class="main">
                <div class="main-header">
                    <h1 class="logo-text">COZA SHOP</h1>
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="shoping-cart.jsp">Cart</a>
                        </li>
                        <li class="breadcrumb-item breadcrumb-item-current">
                            Order Details
                        </li>
                    </ul>
                </div>
                <div class="main-content">
                    <div class="step">
                        <div class="step-sections steps-onepage" step="1">
                            <div class="section">
                                <div class="section-header">
                                    <h2 class="section-title">Order Details</h2>
                                </div>
                                <div class="section-content section-customer-information no-mb">
                                    <div class="logged-in-customer-information">&nbsp;
                                        <div class="logged-in-customer-information-avatar-wrapper">
                                            <div class="logged-in-customer-information-avatar gravatar" style="background-image: url(//www.gravatar.com/avatar/d94b46a8456a3c92a530673be397eabf.jpg?s=100&amp;d=blank);"></div>
                                        </div>
                                        <%CustomeraccountEntity currAcc = (CustomeraccountEntity) session.getAttribute("user");%>
                                        <%CustomerEntity currCustomer = JPAConfig.getEntityManager().find(CustomerEntity.class, currAcc.getCustomerId());%>
                                        <p class="logged-in-customer-information-paragraph">
                                            <%=currCustomer.getFirstName() + currCustomer.getLastName()%> (<%=currAcc.getMail()%>) <span>- Level: <%=currAcc.getLevelId()%></span>
                                            <br>
                                            <a>Logout</a>
                                        </p>
                                    </div>

                                    <div class="fieldset">
                                        <div class="field field-show-floating-label">
                                            <div class="field-input-wrapper field-input-wrapper-select">
                                                <label class="field-label" for="stored_addresses">Add new address...</label>
                                                <select class="field-input" id="stored_addresses">
                                                    <option value="0" data-properties="{}">Default address</option>
                                                    <option value="1" data-properties="{}">Other address...</option>
                                                </select>
                                                <script>
                                                    document.getElementById('stored_addresses').addEventListener('change', function() {
                                                        var selectedValue = this.value;

                                                        if (selectedValue === '1') {
                                                            document.getElementById('billing_address_full_name').value = ''; // Clear the specific field value
                                                            document.getElementById('billing_address_phone').value = ''; // Clear the specific field value
                                                            document.getElementById('billing_address_address1').value = ''; // Clear the specific field value
                                                        }
                                                        else {
                                                            document.getElementById('billing_address_full_name').value = '<%=currCustomer.getFirstName()+currCustomer.getLastName()%>'; // Clear the specific field value
                                                            document.getElementById('billing_address_phone').value = '<%=currCustomer.getPhoneNumber()%>'; // Clear the specific field value
                                                            document.getElementById('billing_address_address1').value = '<%=currCustomer.getAddress()%>'; // Clear the specific field value
                                                        }
                                                    });
                                                </script>

                                            </div>
                                        </div>
                                        <div class="field field-required  field-show-floating-label">
                                            <div class="field-input-wrapper">
                                                <label class="field-label" for="billing_address_full_name">Full Name</label>
                                                <input placeholder="Full Name" autocapitalize="off" spellcheck="false" class="field-input" size="30" type="text"
                                                       id="billing_address_full_name" name="billing_address[full_name]"
                                                       value="<%=currCustomer.getFirstName()+currCustomer.getLastName()%>" autocomplete="false">
                                            </div>
                                        </div>
                                        <div class="field field-required field-show-floating-label">
                                            <div class="field-input-wrapper">
                                                <label class="field-label" for="billing_address_phone">Phone number</label>
                                                <input autocomplete="false" placeholder="Phone number" autocapitalize="off" spellcheck="false" class="field-input" size="30" maxlength="15" type="tel"
                                                       id="billing_address_phone" name="billing_address[phone]"
                                                       value="<%=currCustomer.getPhoneNumber()%>">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="section-content">
                                    <div class="fieldset">

                                        <form autocomplete="off" id="form_update_shipping_method" class="field default" accept-charset="UTF-8" method="post">
                                            <input name="utf8" type="hidden" value="✓">
                                            <div class="content-box mt0">

                                                <div id="form_update_location_customer_shipping" class="order-checkout__loading radio-wrapper content-box-row content-box-row-padding content-box-row-secondary " for="customer_pick_at_location_false">
                                                    <input name="utf8" type="hidden" value="✓">
                                                    <div class="order-checkout__loading--box">
                                                        <div class="order-checkout__loading--circle"></div>
                                                    </div>

                                                    <div class="field field-required  field-show-floating-label">
                                                        <div class="field-input-wrapper">
                                                            <label class="field-label" for="billing_address_address1">Address</label>
                                                            <input placeholder="Address" autocapitalize="off" spellcheck="false" class="field-input" size="30" type="text"
                                                                   id="billing_address_address1" name="billing_address[address1]"
                                                                   value="<%=currCustomer.getAddress()%>">
                                                        </div>
                                                    </div>
                                                    <input name="selected_customer_shipping_country" type="hidden" value="241">
                                                    <input name="selected_customer_shipping_province" type="hidden" value="40">
                                                    <input name="selected_customer_shipping_district" type="hidden" value="431">
                                                    <input name="selected_customer_shipping_ward" type="hidden" value="23500">
                                                    <div class="field field-show-floating-label field-required field-third ">
                                                        <div class="field-input-wrapper field-input-wrapper-select">
                                                            <label class="field-label" for="ls_province"> City  </label>
                                                            <select class="field-input" id="ls_province" name="customer_shipping_province">
                                                                <option data-code="null" value="null">
                                                                    Choose province </option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="field field-show-floating-label field-required field-third ">
                                                        <div class="field-input-wrapper field-input-wrapper-select">
                                                            <label class="field-label" for="ls_district">District</label>
                                                            <select class="field-input" id="ls_district" name="customer_shipping_district">
                                                                <option data-code="null" value="null">Choose districts</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="field field-show-floating-label field-required  field-third  ">
                                                        <div class="field-input-wrapper field-input-wrapper-select">
                                                            <label class="field-label" for="ls_ward">Ward</label>
                                                            <select class="field-input" id="ls_ward" name="customer_shipping_ward">
                                                                <option data-code="null" value="null">Choose ward</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="step-footer" id="step-footer-checkout">
                            <form action="order" id="checkout_complete" method="post">
                                <button type="submit" class="step-footer-continue-btn btn">
                                    <input type="hidden" name="action" value="create">
                                    <span class="btn-content">Complete</span>
                                    <i class="btn-spinner icon icon-button-spinner"></i>
                                </button>
                                <input name="__RequestVerificationToken" type="hidden">
                            </form>	<a class="step-footer-previous-link" href="cart?">Cart</a>
                        </div>
                    </div>
                </div>
                <div class="hrv-coupons-popup-site-overlay"></div>
<%--                <div class="main-footer footer-powered-by">Powered by Haravan</div>--%>
            </div>
            <div class="sidebar">
                <div class="sidebar-content">
                    <div class="order-summary order-summary-is-collapsed">
                        <h2 class="visually-hidden">Thông tin đơn hàng</h2>
                        <div class="order-summary-sections">
                            <div class="order-summary-section order-summary-section-product-list" data-order-summary-section="line-items">
                                <table class="product-table">
                                    <tbody>
                                    <%CustomerProductEntity[] products = (CustomerProductEntity[]) session.getAttribute("tempOrder");%>
                                    <%for (CustomerProductEntity product : products){%>
                                    <tr class="product" data-product-id="1050712263" data-variant-id="1113943469">
                                        <td class="product-image">
                                            <div class="product-thumbnail">
                                                <div class="product-thumbnail-wrapper">
                                                    <%String img_src = "images/item_cart/item-cart-0" + product.getProductId() + ".jpg";%>
                                                    <img class="product-thumbnail-image" alt="T-shirt" src="<%=img_src%>">
                                                </div>
                                                <span class="product-thumbnail-quantity" aria-hidden="true"><%=product.getQuantity()%></span>
                                            </div>
                                        </td>
                                        <td class="product-description">
                                            <span class="product-description-name order-summary-emphasis"><%=product.assignName()%></span>
                                            <span class="product-description-variant order-summary-small-text">
                                                <%=ColorEntity.findByID(product.getColorId()).getColorName() + "/" + SizeEntity.findByID(product.getSizeId()).getSizeName()%>
                                            </span>
                                        </td>
                                        <td class="product-quantity visually-hidden">2</td>
                                        <td class="product-price">
                                            <span class="order-summary-emphasis">$ <%=product.assignUnitPrice()*product.getQuantity()%></span>
                                        </td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                            </div>

                            <div class="order-summary-section order-summary-section-total-lines payment-lines" data-order-summary-section="payment-lines">
                                <table class="total-line-table">
                                    <thead>
                                    </thead>
                                    </tbody>

                                    <tr class="total-line">
                                        <td class="total-line-name payment-due-label">
                                            <span class="payment-due-label-total">Total product cost</span>
                                        </td>
                                        <td class="total-line-name payment-due">
                                            <span class="payment-due-currency">USD</span>
                                            <span class="payment-due-price" data-checkout-payment-due-target="144400000">
                                                <%float tempPrice = (float) session.getAttribute("tempOrderPrice");%>
                                                $<%=tempPrice%>
                                            </span>
                                        </td>
                                    </tr>

                                    <tr class="total-line">
                                        <td class="total-line-name payment-due-label">
                                            <span class="payment-due-label-total">Discount</span>
                                        </td>
                                        <td class="total-line-name payment-due">
                                            <span class="payment-due-currency">USD</span>
                                            <span class="payment-due-price" data-checkout-payment-due-target="144400000">
                                                <%BigDecimal discount = LevelsEntity.findByID(currAcc.getLevelId()).getDiscount().multiply(BigDecimal.valueOf(tempPrice));%>
                                                <%=discount%>
                                            </span>
                                        </td>
                                    </tr>


                                    <tfoot class="total-line-table-footer">
                                    <tr class="total-line">
                                        <td class="total-line-name payment-due-label">
                                            <span class="payment-due-label-total">Total Price</span>
                                        </td>
                                        <td class="total-line-name payment-due">
                                            <span class="payment-due-currency">USD</span>
                                            <span class="payment-due-price" data-checkout-payment-due-target="144400000">
                                                <%=BigDecimal.valueOf(tempPrice).subtract(discount)%>
                                        </span>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>