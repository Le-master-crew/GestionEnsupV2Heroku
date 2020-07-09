<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>S'enregistrer</title>
</head>
<body>
<h1>S'enregistrer</h1>  
    
    <form method="POST" action="/registerOK" id="registerForm">
    
        <label for="username">Username: </label>
        <input type="text" name="username"/><br/>

        <label for="password">Password: </label>
        <input type="password" name="password"/><br/>

        <label for="confirm">Confirm password: </label>
        <input type="password" name="confirm"/><br/>

        <label for="fullname">Full name: </label>
        <input type="text" name="fullname"/><br/>
    
        <label for="street">Street: </label>
        <input type="text" name="street"/><br/>
    
        <label for="city">City: </label>
        <input type="text" name="city"/><br/>
    
        <label for="state">State: </label>
        <input type="text" name="state"/><br/>
    
        <label for="zip">Zip: </label>
        <input type="text" name="zip"/><br/>
    
        <label for="phone">Phone: </label>
        <input type="text" name="phone"/><br/>
    
        <input type="submit" value="S'enregistrer"/>
    </form>
</body>
</html>