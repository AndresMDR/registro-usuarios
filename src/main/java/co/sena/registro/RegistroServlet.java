package co.sena.registro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrar")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String documento = req.getParameter("documento").trim();
        String nombres   = req.getParameter("nombres").trim();
        String apellidos = req.getParameter("apellidos").trim();
        String correo    = req.getParameter("correo").trim();
        String telefono  = req.getParameter("telefono").trim();

        String mensaje;
        boolean exito = false;

        try (Connection con = Conexion.getConnection()) {

            String sqlBuscar = "SELECT COUNT(*) FROM usuarios WHERE documento = ? OR correo = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlBuscar)) {
                ps.setString(1, documento);
                ps.setString(2, correo);
                ResultSet rs = ps.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    mensaje = "Ya existe un usuario con ese documento o correo.";
                } else {
                    String sqlInsert = "INSERT INTO usuarios (documento, nombres, apellidos, correo, telefono) VALUES (?,?,?,?,?)";
                    try (PreparedStatement ins = con.prepareStatement(sqlInsert)) {
                        ins.setString(1, documento);
                        ins.setString(2, nombres);
                        ins.setString(3, apellidos);
                        ins.setString(4, correo);
                        ins.setString(5, telefono);
                        ins.executeUpdate();
                    }
                    mensaje = "Usuario registrado correctamente.";
                    exito = true;
                }
            }
        } catch (Exception e) {
            mensaje = "Error: " + e.getMessage();
        }

        req.setAttribute("mensaje", mensaje);
        req.setAttribute("exito", exito);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}