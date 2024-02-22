package com.example.demo1;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import java.text.SimpleDateFormat;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    Connection connection;

    /*inicializamos la conexion con la base de datos */
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Market", "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

        //obtenemos los datos de las facturas desde la BD
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {
                Statement stmt = connection.createStatement();
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                ResultSet rs = stmt.executeQuery("SELECT id, fecha_emision, fecha_vencimiento, cliente_id, factura_tipo_id, moneda_id FROM factura;");

                // Comienza la tabla
                out.println("<table border=\"1\">");

                // Encabezados de la tabla
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>FECHA EMISION</th>");
                out.println("<th>FECHA VENCIMIENTO</th>");
                out.println("<th>ID CLIENTE</th>");
                out.println("<th>TIPO DE FACTURA (ID)</th>");
                out.println("<th>TIPO DE MONEDA (ID)</th>");
                out.println("</tr>");

                // Datos de la tabla
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("fecha_emision") + "</td>");
                    out.println("<td>" + rs.getString("fecha_vencimiento") + "</td>");
                    out.println("<td>" + rs.getInt("cliente_id") + "</td>");
                    out.println("<td>" + rs.getInt("factura_tipo_id") + "</td>");
                    out.println("<td>" + rs.getInt("moneda_id") + "</td>");
                    out.println("</tr>");
                }

                // Cierra la tabla
                out.println("</table>");

                rs.close();
                stmt.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            Date fechaEstr = Date.valueOf(request.getParameter("fechaEstr"));
            Date fechaVstr = Date.valueOf(request.getParameter("fechaVstr"));
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int idFactura = Integer.parseInt(request.getParameter("idFactura"));
            int idMoneda = Integer.parseInt(request.getParameter("idMoneda"));


            // Insertar los datos en la base de datos
            PreparedStatement statement = connection.prepareStatement("INSERT INTO factura (id, fecha_emision, " +
                    "fecha_vencimiento, cliente_id, factura_tipo_id, moneda_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");

            statement.setInt(1, id);
            statement.setDate(2, fechaEstr);
            statement.setDate(3, fechaVstr);
            statement.setInt(4, idCliente);
            statement.setInt(5, idFactura);
            statement.setInt(6, idMoneda);


            statement.executeUpdate();
            statement.close();

            // Redirigir a alguna página de éxito o mostrar un mensaje de éxito
            response.sendRedirect("exito.jsp");
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante el procesamiento de la solicitud
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirigir a una página de error en caso de que falle la operación
        }
    }


    public void destroy() {
        try {
            // Cerrar la conexión a la base de datos al finalizar
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}