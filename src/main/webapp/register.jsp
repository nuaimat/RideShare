<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="templates/header_template.jsp" />
<div class="container register">
	<Form>
		Full Name<input type="Text" name="name" placeholder="Your first name" /><br>
		PassWord<input type="password" name="pass" placeholder="password"
			pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$" /><br> State<select
			name="state">
			<option>Iowa</option>
			<option>Colorado</option>
			<option>washington</option>
			<option>Alaska</option>
			<option>California</option>
			<option>Florida</option>
		</select> <br> Email<input type="Text" name="email"
			placeholder="Your Email" /><br>
		<fieldset>
			<legend>Sex</legend>
			Male <input type="radio" name="sex" /> Female <input type="radio"
				name="sex" />
		</fieldset>
		<input type="text" name='city' placeholder="Your city"> <input
			type="text" name='street' placeholder="Your Street"> <input
			type="text" name='year' placeholder="YYYY"> <input
			type="text" name='zip' placeholder="ZIP"> ' <input
			type='submit' metho="post" action="RegisterController"
			Value="Register">

	</Form>
</div>
<jsp:include page="templates/footer_template.jsp" />