<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Article</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container">
    <h1 class="text-center">Update Article</h1>
    <form action="updateArticle.do" method="post">
        <input type="hidden" name="id" value="${article.id}">
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" class="form-control" value="${article.description}" required>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" class="form-control" value="${article.quantite}" required>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" class="form-control" value="${article.price}" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary mr-2">Update Article</button>
            <a href="articles.do" class="btn btn-secondary">Return</a>
        </div>
    </form>
</div>
</body>

<%@include file="common/footer.jsp"%>

</html>
