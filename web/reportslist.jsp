<%--
  Created by IntelliJ IDEA.
  User: bryukhaa
  Date: 10/11/15
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Reports</h1>
<div class="row">
    <div class="col-md-12 reports">
        <div class="report">
            <a>
                <h4><i class="fa fa-usd"></i> My meeting cost</h4>
                <div data-bind="text: '$' + totalCost"></div>
            </a>
        </div>
        <div class="report">
            <a>
                <h4><i class="fa fa-file-o"></i> No agenda</h4>
                <div data-bind="text: agendaless + ' meetings with no agenda.'"></div>
            </a>
        </div>
    </div>
</div>