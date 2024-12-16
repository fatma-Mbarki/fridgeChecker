# FridgeCheck 🧊

FridgeCheck est une application Android permettant aux utilisateurs de suivre les ingrédients qu'ils ont achetés, leurs dates d'expiration, et de recevoir des notifications pour éviter le gaspillage alimentaire.  

## 📋 Fonctionnalités
- **Ajout d'ingrédients** avec leur nom, date d'achat et date d'expiration.
- **Notifications automatiques** pour avertir les utilisateurs lorsque les ingrédients approchent de leur date d'expiration.
- **Interface utilisateur intuitive** avec une liste claire des ingrédients.
- Support des appareils Android API 22+.

## 🛠️ Technologies utilisées
- **Langage principal** : Java
- **Architecture** : MVVM (Model-View-ViewModel)
- **Base de données** : Room (Android Jetpack)
- **Notifications** : AlarmManager et NotificationCompat
- **IDE** : Android Studio

## 📂 Structure du projet
Voici un aperçu des principaux composants du projet :

### **1. Activities**
- `MainActivity` : Affiche la liste des ingrédients et planifie les notifications.
- `AddIngredientActivity` : Permet d'ajouter un nouvel ingrédient à la base de données.

### **2. Database**
- **`Ingredient`** : Entité représentant un ingrédient dans la base de données.
- **`IngredientDao`** : Interface DAO pour effectuer les opérations CRUD.
- **`IngredientDatabase`** : Base de données Room pour stocker les données.

### **3. Notifications**
- **`NotificationHelper`** : Gère la création des notifications et des canaux de notification.
- **`NotificationReceiver`** : Diffuseur recevant les alarmes et déclenchant les notifications.

### **4. ViewModel et Repository**
- `IngredientViewModel` : Fournit les données à l'interface utilisateur tout en respectant le cycle de vie.
- `IngredientRepository` : Gère les opérations de données entre la base de données Room et le ViewModel.

## ⚙️ Installation et exécution
1. Clonez le repository :
   ```bash
   git clone https://github.com/fatma-Mbarki/fridgeChecker.git
   cd fridgeChecker
