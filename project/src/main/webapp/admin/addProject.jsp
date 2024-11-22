<!DOCTYPE html>
<%@ page import="fr.xavier.moyon.DAO.Project.*"  %>
<%@ page import="fr.xavier.moyon.DTO.Project.*"  %>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">

	

 

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer un Projet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            
        }
        .container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 400px;
            text-align: center;
        }
        h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        input, select, textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Créer un Nouveau Projet</h1>
        <form action="../project/" method="POST" id="myForm">
            
            <!-- Nom du projet -->
            <div class="form-group">
                <label for="projectName">Nom du projet :</label>
                <input type="text" id="projectName" name="projectName" required>
            </div>
            
            <!-- Date du projet -->
            <div class="form-group">
                <label for="dateProject">Date du projet :</label>
                <input type="date" id="dateProject" name="dateProject" required>
            </div>
            
            <!-- Période -->
            <div class="form-group">
                <label for="perno">Période :</label>
                <select id="perno" name="perno" required>
                    <!-- Les options seront chargées via JSP (voir ci-dessous) -->
                    <% 
                    PeriodDAO pedao = new PeriodDAO();
                    List<PeriodDTO> myPeriodList = pedao.getAllPeriod();
                    
                    for(PeriodDTO pedto : myPeriodList){
                        %>
                        <option value="<%= pedto.getPerno() %>"><%= pedto.getName() %> </option>
                        <%
                    }
               
                    %>
                    
                   
                </select>
            </div>

            <!-- Description du projet -->
            <div class="form-group">
                <label for="description">Description du projet :</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>

            <!-- URL de l'image -->
            <div class="form-group">
                <label for="imageURL">URL de l'image :</label>
                <input type="url" id="imageURL" name="imageURL" required>
            </div>

            <!-- Liens externes dans le projet -->
            <!--<div class="form-group">
                <label for="linkTitle">Titre du lien :</label>
                <input type="text" id="linkTitle" name="linkTitle">
                <label for="lien">Lien :</label>
                <input type="url" id="lien" name="lien">
            </div>

            
            <div class="form-group">
                <label for="ytbTitle">Titre YouTube :</label>
                <input type="text" id="ytbTitle" name="ytbTitle">
                <label for="iframe">Lien YouTube (iframe) :</label>
                <input type="url" id="iframe" name="iframe">
            </div>-->

            <!-- Compétences nécessaires -->
            <div class="form-group">
                <label for="requirements">Compétences nécessaires :</label>
                <select id="requirements" name="requirements" multiple>
                    <!-- Les options seront chargées via JSP (voir ci-dessous) -->
                    <% SkillsDAO skdao = new SkillsDAO();
                    List<SkillsDTO> myList = skdao.getListOfSkill();
                    for(SkillsDTO skdto : myList){
                        %>
                        <option value=<%= skdto.getSkino() %>><%= skdto.getTitle() %></option>
                        <%
                    }
                        
                    %>
                </select>
            </div>
 
        
            <!-- Soumettre le formulaire -->
            <button type="submit" >Créer le projet</button>

        </form>
    </div>
     

</body>
</html>
