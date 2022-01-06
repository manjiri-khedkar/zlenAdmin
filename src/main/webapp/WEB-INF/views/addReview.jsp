
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<section class="login min-height">
	<div id="wrapper">

		<div class="container">
			<div class="clearfix">&nbsp;</div>
			<form:form action="${pageContext.request.contextPath}/review/addReview1"  method="post" modelAttribute="addreview">

				<div class="panel-heading">
					<h2 class="text-center" style="color: black;">
						<b> Review Details</b>
					</h2>
				</div>
				<br />

				<form:hidden path="reviewId"/>
				<form:hidden path="feedbackId"/>
				<form:hidden path="username"/>
				<div class="row">
					<div class="col-md-12">
						<div class="panel-body">
							<div class="row justify-content-center">
								<div class="col-md-2 text-right">
									<form:label path="comments" class="control-label">Comments </form:label>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<form:textarea path="comments" class="form-control"
											placeholder="Enter comment" autofocus="autofocus" />
									</div>
								</div>
								<br>
								<div class="col-md-2 text-right">
									<form:label path="status" class="control-label">Status </form:label>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<form:checkbox path="status" class="form-control"
										 autofocus="autofocus" />
									</div>
								</div>
								
							</div>
						</div>
						<div class="col-md-12 text-center">
                        <button type="submit" class="btn-primary btn">Save</button>&nbsp;

					</div>
				</div>
			</form:form>
		</div>
	</div>
</section>

