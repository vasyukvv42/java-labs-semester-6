<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 02:13
  To change this template use File | Settings | File Templates.
--%>
<form method="post">
    <h1>Add a new hotel</h1>
    <div class="form-group">
        <label for="inputName">Name</label>
        <input type="text" class="form-control" id="inputName" name="name" placeholder="Enter name" required>
    </div>
    <div class="form-group">
        <label for="inputCountry">Country</label>
        <input type="text" class="form-control" id="inputCountry" name="country" placeholder="Country" required>
    </div>
    <div class="form-group">
        <label for="inputCity">City</label>
        <input type="text" class="form-control" id="inputCity" name="city" placeholder="City" required>
    </div>
    <div class="form-group">
        <label for="inputAddress">Address</label>
        <input type="text" class="form-control" id="inputAddress" name="address" placeholder="Address" required>
    </div>
    <div class="form-group">
        <label for="inputRating">Star rating</label>
        <input type="number" class="form-control" id="inputRating" name="rating" placeholder="Rating" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>