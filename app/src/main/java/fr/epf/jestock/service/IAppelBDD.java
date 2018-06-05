package fr.epf.jestock.service;

import java.util.List;

import fr.epf.jestock.model.Emprunts;
import fr.epf.jestock.model.Etudiant;
import fr.epf.jestock.model.MaterielDeficit;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;
import fr.epf.jestock.model.SuccesRequete;
import fr.epf.jestock.model.ResultatRecherche;
import fr.epf.jestock.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
    Nom ......... : IAppelBDD.java
    Role ........ : Interface contenant toutes les requetes HTTP permettant de communiquer avec le serveur
    Auteur ...... : DSI_2

*/

public interface IAppelBDD {

    //Requete permettant la connexion
    @FormUrlEncoded
    @POST("/android/connection.php")
    Call<User> sendUser(
            @Field("email") String username,
            @Field("mdp") String password);

    //Requete permettant l'envoie du code EAN 13 scanner
    @FormUrlEncoded
    @POST("/android/refStock.php")
    Call<ResultatRecherche> sendReferenceStock(
            @Field("referenceStock") long reference);

    //Requete permettant la modification des quantités de stock
    @FormUrlEncoded
    @POST("/android/modifQuantite.php")
    Call<SuccesRequete> modifierQuantite(
            @Field("typeMateriel") String typeMateriel,
            @Field("typeModification") String typeModification,
            @Field("reference") long reference,
            @Field("quantite") int quantite,
            @Field("campus") String campus);

    //Requete permettant de récupérer les informations d'un étudiant à partir de son numero
    @FormUrlEncoded
    @POST("/android/recupEtudiant.php")
    Call<Etudiant> recupEtudiant(
            @Field("numEtu") String numEtu);

    //Requete permettant l'emprunt d'un matériel
    @FormUrlEncoded
    @POST("/android/emprunt.php")
    Call<Void> emprunter(
            @Field("type") String type,
            @Field("ref") long ref,
            @Field("nomRef") String nomRef,
            @Field("nomEmprunteur") String nomEmprunteur,
            @Field("prenomEmprunteur") String prenomEmprunteur,
            @Field("campus") String campus);

    //Requete permettant l'affichage de la liste de matériel du stock
    @FormUrlEncoded
    @POST("/android/affichageListeStock.php")
    Call<List<MaterielEnStock>> affichageListeStock(
            @Field("campus") String campus);

    //Requete permettant l'affichade de la liste de matériel empruntable
    @FormUrlEncoded
    @POST("/android/affichageListeEmpruntable.php")
    Call<List<MaterielEmpruntable>> affichageListeEmpruntable(
            @Field("campus") String campus);

    //Requete permettant l'affichage de la liste des emprunts
    @FormUrlEncoded
    @POST("/android/affichageListeEmprunt.php")
    Call<List<Emprunts>> affichageListeEmprunt(
            @Field("campus") String campus);

    //Requete permettant l'affichage des listes de matériel à commander
    @FormUrlEncoded
    @POST("/android/affichageListeDeficit.php")
    Call<List<MaterielDeficit>> affichageListeDeficit(
            @Field("type") String type,
            @Field("campus") String campus);

    //Requete permettant d'ajouter une nouvelle référence aux stocks
    @FormUrlEncoded
    @POST("/android/nouvelleRef.php")
    Call<Void> nouvelleRef(
            @Field("type") String type,
            @Field("ref") long ref,
            @Field("nomRef") String nomRef,
            @Field("quantiteAjoutee") int quantiteAjoutee,
            @Field("quantiteConseillee") int prenomEmprunteur,
            @Field("campus") String campus);
}
