<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .button {
            border: none;
            color: white;
            padding: 8px 14px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .button1 {
            background-color: blue; /* Green */
            border-radius: 8px;
        }

        .button2 {
            background-color: green; /* Green */
            border-radius: 8px;
        }

        .content-table {
            border-collapse: collapse;
            margin-left: auto;
            margin-right: auto;
            font-size: 0.9em;
            min-width: 400px;
            border-radius: 5px 5px 0 0;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }

        .content-table thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
            font-weight: bold;
        }

        .content-table th,
        .content-table td {
            padding: 12px 15px;
        }

        .content-table tbody tr {
            border-bottom: 1px solid #dddddd;
        }

        .content-table tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        .content-table tbody tr:last-of-type {
            border-bottom: 2px solid #009879;
        }


    </style>
</head>

<body>

<form action="login" method="post">
    <table class="content-table">
        <thead>
        <tr>
            <th>Welcome to quiz website</th>
        </tr>
        </thead>
        <tbody>
        <tr><th>
            <h4>Please log in or create new account</h4>
            <label for ="uName"> User Name: </label>
            <input type ="text" name="username" id="uName" placeholder="Your user name..." required><br><br>
            <label for="password"> Password:</label>
            <input type="password" name="password" id="password" placeholder="Your password..."  required><br><br>
            <button class="button button1" type="submit">Submit</button><br>
            <p><a href="createAccount" class="button button2">Create New Account</a></p>
        </th></tr>
        </tbody>
    </table>

</form>
</body>
</html>
