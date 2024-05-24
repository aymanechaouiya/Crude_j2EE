<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Articles</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding-top: 20px;
        }

        .container {
            width: 80%;
            margin: auto;
        }

        h1 {
            color: #333;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .action-buttons a {
            margin-right: 10px;
        }
    </style>
</head>
<body>


<div class="container">
    <h1>Articles</h1>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}">
            <tr>
                <td>${article.id}</td>
                <td>${article.description}</td>
                <td>${article.quantite}</td>
                <td>${article.price}</td>
                <td class="action-buttons">
                    <a href="updateArticle.do?id=${article.id}" class="btn btn-primary btn-sm">
                        <i class="fas fa-edit"></i> Update
                    </a>
                    <a href="deleteArticle.do?id=${article.id}" class="btn btn-danger btn-sm">
                        <i class="fas fa-trash-alt"></i> Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="addArticle.do" class="btn btn-success">
        <i class="fas fa-plus"></i> Add New Article
    </a>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
<%@include file="common/footer.jsp"%>

</html>
