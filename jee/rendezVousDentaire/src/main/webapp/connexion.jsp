<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="css/mesStyles.css">
</head>
<body>
    <div class="connexion-container">
        <h2>Connexion</h2>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="connexion" method="post" class="connexion-form">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="password">Mot de passe:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label>Type d'utilisateur:</label>
                <div class="radio-group">
                    <input type="radio" id="patient" name="userType" value="patient" checked>
                    <label for="patient">Patient</label>
                    
                    <input type="radio" id="dentiste" name="userType" value="dentiste">
                    <label for="dentiste">Aide-soignant</label>
                </div>
            </div>
            
            <button type="submit" class="btn-primary">Se connecter</button>
        </form>
        
        <div class="links">
            <p>Vous n'avez pas de compte ?</p>
            <a href="inscriptionPatient" class="link">Inscription Patient</a>
            <a href="listeDentistes" class="link">Espace Aide-soignant</a>
        </div>
    </div>
</body>
</html>