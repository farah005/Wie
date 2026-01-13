<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Publication — Sourire & Co</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mesStyles.css">
</head>
<body>

    <div class="container">
        <div class="header-box">
            <h2>Découvrez les dernières avancées en dentisterie pour des soins plus précis, confortables et esthétiques</h2>
        </div>

        <form action="publication" method="post" enctype="multipart/form-data">
            <div class="form-grid">
                
                <div class="form-group">
                    <label>Titre</label>
                    <input type="text" name="titreP" placeholder="Saisir le titre de publication" required> [cite: 141]
                </div>

                <div class="form-group">
                    <label>Fichier</label>
                    <input type="file" name="fichierP"> [cite: 146]
                </div>

                <div class="form-group">
                    <label>Date de publication</label>
                    <input type="date" name="dateP"> [cite: 144]
                </div>

                <div class="form-group">
                    <label>Affiche</label>
                    <input type="file" name="afficheP"> [cite: 147]
                </div>

                <div class="form-group">
                    <label>Type de publication</label>
                    <select name="typeP">
                        <option value="">[Choisir catégorie]</option> [cite: 187, 188]
                        <option value="Article">Article scientifique</option> [cite: 189]
                        <option value="Etude">Étude de cas</option> [cite: 190]
                        <option value="Lancement">Lancement d'un produit ou service</option> [cite: 191]
                        <option value="Innovation">Actualités/innovation</option> [cite: 192]
                    </select>
                </div>

                <div class="form-group full-width">
                    <label>Résumé</label>
                    <textarea name="resumeP" placeholder="Saisir la description associée"></textarea> [cite: 150]
                </div>
            </div>

            <div class="button-container">
                <button type="submit" class="btn-save">Enregistrer</button> [cite: 152]
                <button type="reset" class="btn-cancel">Annuler</button> [cite: 153]
            </div>
        </form>
    </div>

</body>
</html>