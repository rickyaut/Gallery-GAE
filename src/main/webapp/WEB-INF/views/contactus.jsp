<div>
	<h1>Contact us</h1>
	<p>
	 	Please fill the form, and we'll contact you ASAP.
	 	<br />
	</p>
	<form action="/contact-us" method="post" class="contact-us" id="contact-us-form">
		<div class="input-group">
		  	<span class="input-group-addon">Your Name: </span>
		  	<input type="text" class="form-control" placeholder="Name" name="name" required>
		</div>
		<div class="input-group">
		  	<span class="input-group-addon">Phone: </span>
		  	<input type="text" class="form-control" placeholder="Phone" name="phone" required>
		</div>
		<div class="input-group">
		  	<span class="input-group-addon">eMail: </span>
		  	<input type="email" class="form-control" placeholder="eMail" name="email" required>
		</div>
		<div class="input-group">
		  	<span class="input-group-addon">Content: </span>
		  	<textarea class="form-control" name="content" required></textarea>
		</div>
		<div class="input-group">
			<button type="reset" class="btn btn-default">Reset</button>
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>
	<script type="text/javascript">$(function(){$("#contact-us-form").validate()})</script>
</div>
