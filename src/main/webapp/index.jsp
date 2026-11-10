<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registro de usuarios</title>
  <style>
    body { font-family: Arial, sans-serif; max-width: 420px; margin: 40px auto; }
    label { display: block; margin-top: 12px; }
    input { width: 100%; padding: 8px; box-sizing: border-box; }
    button { margin-top: 16px; padding: 10px 20px; }
    .ok { color: green; }
    .error { color: crimson; }
  </style>
</head>
<body>
  <h2>Registro de usuarios</h2>

  <% if (request.getAttribute("mensaje") != null) { %>
    <p class="<%= (Boolean) request.getAttribute("exito") ? "ok" : "error" %>">
      <%= request.getAttribute("mensaje") %>
    </p>
  <% } %>

  <form action="registrar" method="post">
    <label>Documento de identidad
      <input type="text" name="documento" required>
    </label>
    <label>Nombres
      <input type="text" name="nombres" required>
    </label>
    <label>Apellidos
      <input type="text" name="apellidos" required>
    </label>
    <label>Correo electrónico
      <input type="email" name="correo" required>
    </label>
    <label>Teléfono de contacto
      <input type="tel" name="telefono" required>
    </label>
    <button type="submit">Registrar</button>
  </form>
</body>
</html>