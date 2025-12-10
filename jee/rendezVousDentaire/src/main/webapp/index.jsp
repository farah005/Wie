<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cabinet Dentaire - Accueil</title>
    <link rel="stylesheet" href="css/mesStyles.css">
</head>
<body>
    <!-- Zone 1: Entête -->
    <header class="header">
        <div class="logo">
            <img src="images/logo.png" alt="Logo Cabinet Dentaire" width="80">
        </div>
        <h1>Un sourire en un clic : simplifiez vos rendez-vous dentaires !</h1>
        <nav class="menu">
            <a href="connexion">Connexion</a>
            <a href="inscriptionPatient">Patient</a>
            <a href="listeDentistes">Aide-soignant</a>
            <a href="serviceMedical?action=liste">Service</a>
            <a href="publication">Publication</a>
            <a href="rendezvous">Rendez-vous</a>
        </nav>
    </header>

    <!-- Zone 2: Contenu Principal -->
    <main class="content">
        <jsp:include page="connexion.jsp" />
    </main>

    <!-- Zone 3: Pied de page -->
    <footer class="footer">
        <marquee>votre.email@example.com</marquee>
    </footer>
</body>
</html>