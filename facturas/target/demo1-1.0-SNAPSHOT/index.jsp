<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
        }

        table {
            margin: 0 auto; /* Centrar la tabla */
        }

        table td {
            padding: 5px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }

        form {
            margin-top: 20px;
        }

        label {
            font-weight: bold;
        }

        input {
            padding: 8px;
            margin: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Informe de Facturas</h2>

    <p>Para ver el informe de facturas, haz clic en el siguiente botón</p>
    <form action="hello-servlet" method="GET">
        <button type="submit">VER</button>
    </form>

    <hr>

    <p>Para cargar un informe de facturas, haz clic en el siguiente botón</p>
    <button type="button" onclick="mostrarFormulario()">Continuar</button>
    <form id="form" action="hello-servlet" method="POST" style="display: none;">
        <table>
            <tr>
                <td><label>ID:</label></td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td><label>FECHA EMISION:</label></td>
                <td><input type="text" name="fechaEstr"></td>
            </tr>
            <tr>
                <td><label>FECHA VENCIMIENTO:</label></td>
                <td><input type="text" name="fechaVstr"></td>
            </tr>
            <tr>
                <td><label>ID CLIENTE:</label></td>
                <td><input type="text" name="idCliente"></td>
            </tr>
            <tr>
                <td><label>TIPO DE FACTURA (ID):</label></td>
                <td><input type="text" name="idFactura"></td>
            </tr>
            <tr>
                <td><label>TIPO DE MONEDA (ID):</label></td>
                <td><input type="text" name="idMoneda"></td>
            </tr>
        </table>
        <button type="submit">Enviar</button>
    </form>
</div>

<script>
    function mostrarFormulario() {
        var form = document.getElementById("form");
        if (form.style.display === "none") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    }
</script>

</body>
</html>
