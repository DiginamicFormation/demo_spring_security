## Se connecter avec POSTMAN

### Le modèle
La classe User a une association ManyToMany avec  Role.   
Le rôle a un nom par exemple ADMIN.  
  
Les méthodes sont sécurisées avec l'annotation et le nom du rôle autorisé.  
Exemple: @Secured("ADMIN")

### Se connecter   
Envoyer une requête POST sur /api/auth/login avec le JSON suivant dans le body :  

{  
    "username":"root",  
    "password":"a"  
}    
![xxx](media/requete_login.jpg)
Attention, pour que cela fonctionne il faut que dans la table user le mdp soit la valeur de a encryptée avec BCryptPasswordEncoder
  
### Récupérer le jeton 
Le jeton est dans le header de la réponse HTTP, stocké avec la clé Authorization

### Inclure le jeton JWT dans les requêtes
Une fois logué, vous devez ajouter le token JWT Dans le header de toutes les requêtes futures    
Ajouter la clé/valeur suivante dans le header  
key=Authorization  
value=Bearer {{valeur_jwt_token}}   
![xxx](media/requete_avec_header.jpg)  
Il faut bien entendu remplacer {{valeur_jwt_token}} par sa valeur
### Comment gérer le token dans un contexte Angular 
Le token jwt doit être ajouté dans le header des requêtes HTTP via un intercepteur Angular.