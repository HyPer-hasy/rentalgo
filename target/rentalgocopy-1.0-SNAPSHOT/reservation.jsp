
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="comp/meta.jsp" %>
        <%@include file="comp/cssl.jsp" %>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Resevation </title>
    </head>
    <body >
        <%@include file="comp/navbar.jsp" %>
        <!--  1 upper banner with image and some text  --> 

        <div class="wheel-start3">
            <img src="images/bg10.jpg" alt="" class="wheel-img">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 padd-lr0">
                        <div class="wheel-start3-body clearfix marg-lg-t255 marg-lg-b75 marg-sm-t190 marg-xs-b30">
                            <h3>Car Selection</h3>    
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- code for step one car selection --> 
        <div class="step-wrap">
            <!-- ////////////////////////////////////////// -->
            <div class="container padd-lr0">
                <div class="row">
                    <div class="col-xs-12 padd-lr0">
                        <ul class="steps">
                            <li class="title-wrap active  ">
                                <div class="title">
                                    <span>1.</span>Login to your  account
                                </div>
                            </li>
                            <li class="title-wrap active">
                                <div class="title">
                                    <span>2.</span>Select your car
                                </div>
                            </li>
                            <li class="title-wrap  active">
                                <div class="title">
                                    <span>3.</span>Information & Reviews
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <!-- ////////////////////////////////////////// -->
        </div>
        
            <div class="prosuct-wrap">
                <div class="container padd-lr0 xs-padd">
                    <div class="product-list product-list2 wheel-bgt clearfix">
                        <div class="row">
                            <%
                                ResultSet rs2 = null;
                                String carName = null;
                                String carPrice = null;
                                // Establish JDBC connection
                                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrent", "hyper", "hyper");

                                // Retrieve car ID from URL parameter
                                String no = request.getParameter("id");

                                // Execute SQL query to fetch details of selected car
                                PreparedStatement stmt2 = con1.prepareStatement("SELECT * FROM car WHERE car_no = ?");
                                stmt2.setString(1, no);

                                rs2 = stmt2.executeQuery();

                                // Check if a car was found
                                if (rs2.next()) {

                                    carName = rs2.getString("car_name");
                                    carPrice = rs2.getString("car_price");
                            %>
                            <!-- 1 car show  -->  


                            <div class="col-xs-12">
                                <div class="product-elem-style1 product-elem-style  wheel-bg11 clearfix">
                                    <div class="product-table2">
                                        <div class="img-wrap img-wrap2 product-cell">
                                            <img src="<%=rs2.getString("car_image")%>" alt="img" class="img-responsive">
                                        </div>
                                    </div> 
                                    <div class="product-table3  ">
                                        <div class="text-wrap text-wrap2 product-cell">
                                            <div class="title"> <%=rs2.getString("car_name")%></div> (<span><%=rs2.getString("car_type")%></span>)
                                            <div class="price-wrap product-cell"> 
                                                <span>&#x20B9;<%= carPrice%></SPAN>/Day
                                            </div> 
                                            <ul class="metadata2 metadata ">
                                                <li> <%=rs2.getString("no_p")%> Seater</li>
                                                <li><%=rs2.getString("no_d")%> Doors</li>
                                                <li><%=rs2.getString("ac_no")%></li>
                                                <li><%=rs2.getString("lug_no")%></li>
                                                <li><%=rs2.getString("gear_t")%></li>
                                                <li><%=rs2.getString("fuel_t")%></li>
                                            </ul>

                                        </div>
                                        <div class="img-wrap img-wrap3 product-cell">
                                            <img src="<%=rs2.getString("car_image")%>" alt="img" class="img-responsive">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- show complete -->
                            <%
                            } else {
                                String redirectUrl = "car_select.jsp";
                            %>
                            <script>
                            window.location.replace('<%= redirectUrl%>');
                            </script>

                            <%
                                }

                                // Close JDBC objects
                                rs2.close();
                                stmt2.close();
                                con1.close();
                            %>

                        </div> 
                    </div>
                </div>
            </div>



<form method="post" action="reservation" >
            <!-- coding for form staart here -->
            <!-- second part of the  -->
            <div class="container">
                <div class="row">
                    <div class="col-md-6 padd-lr0">
                        <div class="wheel-billing">
                            <div class="wheel-header marg-lg-t50 marg-lg-b50 marg-sm-t100">
                                <h5>Reservation Details</h5>
                                
                            </div>

                            <label for="input-val10" ><span>Pickup Location *</span></label>
                            <select class="selectpicker" name="pickuplocation" >
                                <option value="Jaipur Airport">Jaipur Airport</option>
                                <option value="Jaipur Railway Station">Jaipur Railway Station</option>
                                <option value="Sindhi Camp Bus Stand">Sindhi Camp Bus Stand</option>
                                <option value="Vaishali Nagar">Vaishali Nagar</option>
                                <option value="Malviya Nagar">Malviya Nagar</option>
                                <option value="JLN Marg">JLN Marg</option>
                                <option value="Tonk Road">Tonk Road</option>
                                <option value="Bani Park">Bani Park</option>
                                <option value="C-Scheme">C-Scheme</option>
                            </select>
                            <!-- pickup date and time  -->
                            <label for="input-val10"><span>Pickup Date/time *</span></label>
                            <input id="pd" type="date" placeholder=" pickup Date" class="wheel-half-width pull-left" name = "pickupdate">
                            <input id="pt" type="time" placeholder="pickup time" class="wheel-half-width pull-right marg-r0" name = "pickuptime">

                            <label for="input-val10"><span>Drop Location *</span></label>
                            <select class="selectpicker" name = "droplocation">
                                <option value="Jaipur Airport">Jaipur Airport</option>
                                <option value="Jaipur Railway Station">Jaipur Railway Station</option>
                                <option value="Sindhi Camp Bus Stand">Sindhi Camp Bus Stand</option>
                                <option value="Vaishali Nagar">Vaishali Nagar</option>
                                <option value="Malviya Nagar">Malviya Nagar</option>
                                <option value="JLN Marg">JLN Marg</option>
                                <option value="Tonk Road">Tonk Road</option>
                                <option value="Bani Park">Bani Park</option>
                                <option value="C-Scheme">C-Scheme</option>
                            </select>
                            <!-- Drop  date and time  -->
                            <label for="input-val10"><span>Drop Date/time *</span></label>
                            <input id="dd" type="date" placeholder="Drop Date" class="wheel-half-width pull-left" name ="dropdate">
                            <input id="dt" type="time" placeholder="pickup time" class="wheel-half-width pull-right marg-r0" name="droptime">




                            <br><br>
                            <div class="wheel-header marg-lg-t200 marg-lg-b30 marg-sm-t00">
                                <h5>Personel Details</h5>
                            </div>


                            <label for="input-val10"><span>Choose Your Gender * </span></label>
                            <select class="selectpicker " name ="gender">
                                <option>Male</option>
                                <option>Female</option>
                                <option>Other</option>

                            </select>
                            <label for="input-val10"><span>Date-of-Birth * </span></label>
                            <input type="date" placeholder="Date" name ="dob">
                            <label for="input-val10"><span>Choose Your Age *</span></label>
                            <select class="selectpicker" name="age" >
                                <option>21</option>
                                <option>22</option>
                                <option>23</option>
                                <option>24</option>
                                <option>25</option>
                                <option>26</option>
                                <option>27</option>
                                <option>28</option>
                                <option>29</option>
                                <option>30</option>
                                <option>31</option>
                                <option>32</option>
                                <option>33</option>
                                <option>34</option>
                                <option>35</option>
                                <option>36</option>
                                <option>37</option>
                                <option>38</option>
                                <option>39</option>
                                <option>40</option>
                                <option>41</option>
                                <option>42</option>
                                <option>43</option>
                                <option>44</option>
                                <option>45</option>
                                <option>46</option>
                                <option>47</option>
                                <option>48</option>
                                <option>49</option>
                                <option>50</option>
                            </select>

                            <label for="input-val10"><span>Enter Your Address:</span></label>
                            <textarea placeholder="Address *" name="address"></textarea><br><br><br>
                            <input name="zip" type="text" placeholder="ZIP / Postcode *"/>

                        </div>
                    </div>


                    <!-- protection plan radio button  -->

                    <div class="col-md-6 padd-lr0">
                        <div class="wheel-order marg-lg-t50 marg-lg-b150 marg-sm-b100 marg-sm-t100">


                            <div class="wheel-header ">
                                <h5>Insurance Plan</h5>
                            </div>
                            <div class="wheel-order-price marg-lg-t30 marg-lg-b30"> 

                                <div class="option">
                                    <input type="radio" name="card" id="silver" value="399.00" checked = "checked">
                                    <label id="t" for="silver" aria-label="Silver">
                                        <span></span>

                                        Elite &nbsp;&nbsp; (&#x20B9; 399)

                                        <div class="card card--white card--sm">
                                            <div class="card__chip"></div>
                                            <div class="card__content">
                                                <div class="card__text">
                                                    <div class="text__row">
                                                        <div class="text__lo"></div>
                                                        <div class="text__lo"></div>
                                                    </div>
                                                    <div class="text__row">
                                                        <div class="text__lo"></div>
                                                        <div class="text__lo"></div>
                                                    </div>
                                                </div>
                                                <div class="card__symbol">
                                                    <span></span>
                                                    <span></span>
                                                </div>
                                            </div>
                                        </div>
                                    </label>
                                </div>

                                <div class="option">
                                    <input type="radio" name="card" id="royal" value="599.00" >
                                    <label id="t" for="royal" aria-label="Royal blue">
                                        <span></span>

                                        Elite Plus &nbsp;&nbsp; (&#x20B9; 599)

                                        <div class="card card--blue card--sm">
                                            <div class="card__chip"></div>
                                            <div class="card__content">
                                                <div class="card__text">
                                                    <div class="text__row">
                                                        <div class="text__lo"></div>
                                                        <div class="text__lo"></div>
                                                    </div>
                                                    <div class="text__row">
                                                        <div class="text__lo"></div>
                                                        <div class="text__lo"></div>
                                                    </div>
                                                </div>
                                                <div class="card__symbol">
                                                    <span></span>
                                                    <span></span>
                                                </div>
                                            </div>
                                        </div>
                                    </label>
                                </div>

                                <div class="option">
                                    <input type="radio" name="card" id="dark" value="999.00">
                                    <label id="t" for="dark" aria-label="Dark grey">
                                        <span></span>

                                        VIP &nbsp;&nbsp; (&#x20B9; 999)

                                        <div class="card card--dark card--sm">
                                            <div class="card__chip"></div>
                                            <div class="card__content">
                                                <div class="card__text">
                                                    <div class="text__row">
                                                        <div class="text__lo"></div>
                                                        <div class="text__lo"></div>
                                                    </div>
                                                    <div class="text__row">
                                                        <div class="text__lo"></div>
                                                        <div class="text__lo"></div>
                                                    </div>
                                                </div>
                                                <div class="card__symbol">
                                                    <span></span>
                                                    <span></span>
                                                </div>
                                            </div>
                                        </div>
                                    </label>
                                </div>
                            </div>                

                            <!-- mainnn fdaf; dfdfkl -->
                            <div class="wheel-order-price marg-lg-t30 marg-lg-b30">
                                <div class="wheel-header ">
                                    <h5>Your Order &nbsp; &nbsp; &nbsp;  ( Pricing in &#x20B9;) </h5> 
                                </div>
                                <ul>
                                    <li class="clearfix"><span id="cm" ><%= carName%></span><b id="cp" > <%= carPrice%></b></li>

                                    <li class="clearfix"><span >NO.of Days :   &nbsp;&nbsp;&nbsp;X </span> <b id="nod" >0 </b></li>

                                    <li class="clearfix"><span >Insurance Plan Charge &nbsp;&nbsp;&nbsp;(+)</span> <b id="iprate" >399.00</b></li>

                                    <li class="clearfix"><span >Shipping and Handling &nbsp;&nbsp;&nbsp; (+)</span><b id="sah">0</b></li>

                                    <li class="clearfix"><span>GST<sup>(9%)</sup> &nbsp;&nbsp;&nbsp;(+)</span><b id="gst">0</b></li>



                                    <li class="clearfix"><span>Discount <sup>(2%)</sup> &nbsp;&nbsp;&nbsp; (-)</span><b id="discount"> 0</b></li>



                                    <li class="clearfix wheel-total">
                                        <h4>Order Total</h4>
                                          <b id="gt">0</b>
                                    </li>
                                </ul>

                            </div>


                            <div class="wheel-payment marg-lg-t60 marg-lg-b30">



                                <label for="input-val10" class="wheel-agree">
                                    <input type="checkbox" id='input-val10' >
                                    <span>I agree with the</span>
                                    <a href="#" data-toggle="modal" data-target="#terms-modal">Terms and Conditions</a>

                                </label>
                                <button  type="submit">Place Order</button>
                            </div>
                        </div>
                                    
                    </div>
                </div>
            </div>

    
            <input type="hidden" name="carname" value="<%= carName %>">
            <input type="hidden" name="carprice" value="<%= carPrice %>">
            <input type="hidden" name="name" value="<%= n %>">
            <input type="hidden" name="email" value="<%= e %>">
            <hr>
            <input type="hidden" name="noofdays" id ="nod1">
            <input type="hidden" name="insuranceplancharge" id ="iprate1">
            <input type="hidden" name="shippingcharge" id ="soh1">
            <input type="hidden" name="gst" id ="gst1">
            <input type="hidden" name="discount" id ="discount1">
            <input type="hidden" name="ordertotal" id ="gt1">


   
        </form>

        <!-- Button trigger modal -->


        <div class="modal fade" id="terms-modal" tabindex="-1" role="dialog" aria-labelledby="terms-modal-label">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title" id="terms-modal-label">Drive Wheel - Terms and Conditions</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>1. Reservation and Payment</p>
                        <p>1.1 <b>Cargo.com</b> requires all reservations to be made in advance. The customer is required to pay a deposit at the time of reservation, and the full rental amount is due at the time of pick up. <b>Cargo.com</b> accepts major credit cards and cash.</p>
                        <p>2. Driver Requirements</p>
                        <p>2.1 The renter and any additional drivers must be at least 21 years of age and possess a valid driver's license. International renters must have a valid passport and a valid international driver's license.</p>
                        <p>3. Insurance</p>
                        <p>3.1 All <b>Cargo.com</b> rentals include liability insurance. Additional insurance options, such as collision damage waiver and theft protection, are available for an additional fee. It is the customer's responsibility to ensure that they are adequately insured.</p>
                        <p>4. Vehicle Use</p>
                        <p>4.1 The rented vehicle may only be used by the renter and any additional drivers listed on the rental agreement. The vehicle must be used in accordance with all applicable laws and regulations. The vehicle must not be used for any illegal activities or for racing.</p>
                        <p>5. Vehicle Return</p>
                        <p>5.1 The rented vehicle must be returned to the designated location at the end of the rental period, at the agreed-upon time. If the vehicle is returned late, the customer may be charged additional fees. The vehicle must be returned in the same condition it was rented, with a full tank of gas.</p>
                        <p>6. Cancellation Policy</p>
                        <p>6.1 If the customer wishes to cancel their reservation, they must do so at least 24 hours prior to the pick-up time. If the customer fails to cancel their reservation, or cancels within 24 hours of the pick-up time, they will be charged a cancellation fee.</p>
                        <p>7. Limitation of Liability</p>
                        <p>7.1 <b>Cargo.com</b> is not liable for any damages, losses, or injuries incurred during the rental period, including but not limited to damage to the rental vehicle, personal injury, or loss of personal property. The customer is responsible for any damages to the rental vehicle or third-party property, and any associated costs.</p>
                        <p>8. Governing Law</p>
                        <p>8.1 These terms and conditions shall be governed by and construed in accordance with the laws of the jurisdiction in which <b>Cargo.com</b> operates.</p>
                        <p>9. Entire Agreement</p>
                        <p>9.1 These terms and conditions constitute the entire agreement between the customer and <b>Cargo.com</b>, and supersede all prior or contemporaneous agreements, proposals, discussions, or understandings, whether written or oral, relating to the rental of the vehicle.</p>
                    </div>
                    <div class="modal-footer">
                        <button target ="conf_page.jsp" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="comp/footer.jsp" %>
        <%@include file="comp/jsl.jsp" %>




    </body>



</html>
