<html>
<head><title>Cart JSP</title></head>
<body>
  <h1>Hello
  <%= request.getAttribute("myname") %>
  </h1>
  <c><%= request.getAttribute("products") %></c>
</body>
</html>