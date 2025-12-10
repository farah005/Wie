<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription Patient</title>
    <link rel="stylesheet" href="css/mesStyles.css">
</head>
<body>
    <div class="inscription-container">
        <h2>Inscription Patient</h2>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="inscriptionPatient" method="post" class="inscription-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="nom">Nom *</label>
                    <input type="text" id="nom" name="nom" required>
                </div>
                
                <div class="form-group">
                    <label for="prenom">Prénom *</label>
                    <input type="text" id="prenom" name="prenom" required>
                </div>
            </div>
            
            <div class="form-group">
                <label for="email">Email *</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="motDePasse">Mot de passe *</label>
                <input type="password" id="motDePasse" name="motDePasse" maxlength="10" required>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label>Sexe *</label>
                    <select name="sexe" required>
                        <option value="">Sélectionner</option>
                        <option value="M">Masculin</option>
                        <option value="F">Féminin</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="dateNaissance">Date de naissance</label>
                    <input type="date" id="dateNaissance" name="dateNaissance">
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label>Groupe sanguin</label>
                    <select name="groupeSanguin">
                        <option value="">Sélectionner</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="O">O</option>
                        <option value="AB">AB</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="recouvrement">Recouvrement social</label>
                    <input type="text" id="recouvrement" name="recouvrement">
                </div>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-primary">Enregistrer</button>
                <button type="reset" class="btn-secondary">Réinitialiser</button>
            </div>
        </form>
        
        <div class="back-link">
            <a href="connexion">← Retour à la connexion</a>
        </div>
    </div>
</body>
</html>