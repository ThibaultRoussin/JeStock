package fr.epf.jestock.service;

import java.util.List;

import fr.epf.jestock.model.Emprunts;
import fr.epf.jestock.model.Etudiant;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;
import fr.epf.jestock.model.SuccesRequete;
import fr.epf.jestock.model.ResultatRecherche;
import fr.epf.jestock.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Thibault on 31/05/2018.
 */

public interface IAppelBDD {

    @FormUrlEncoded
    @POST("/JeStock/connection.php")
    Call<User> sendUser(
            @Field("email") String username,
            @Field("mdp") String password);

    @FormUrlEncoded
    @POST("/JeStock/refStock.php")
    Call<ResultatRecherche> sendReferenceStock(
            @Field("referenceStock") long reference);

    @FormUrlEncoded
    @POST("/JeStock/modifQuantite.php")
    Call<SuccesRequete> modifierQuantite(
            @Field("typeMateriel") String typeMateriel,
            @Field("typeModification") String typeModification,
            @Field("reference") long reference,
            @Field("quantite") int quantite,
            @Field("campus") String campus);

    @FormUrlEncoded
    @POST("/JeStock/recupEtudiant.php")
    Call<Etudiant> recupEtudiant(
            @Field("numEtu") String numEtu);

    @FormUrlEncoded
    @POST("/JeStock/emprunt.php")
    Call<Void> emprunter(
            @Field("type") String type,
            @Field("ref") long ref,
            @Field("nomRef") String nomRef,
            @Field("nomEmprunteur") String nomEmprunteur,
            @Field("prenomEmprunteur") String prenomEmprunteur,
            @Field("campus") String campus);

    @FormUrlEncoded
    @POST("/JeStock/affichageListeStock.php")
    Call<List<MaterielEnStock>> affichageListeStock(
            @Field("campus") String campus);

    @FormUrlEncoded
    @POST("/JeStock/affichageListeEmpruntable.php")
    Call<List<MaterielEmpruntable>> affichageListeEmpruntable(
            @Field("campus") String campus);

    @FormUrlEncoded
    @POST("/JeStock/affichageListeEmprunt.php")
    Call<List<Emprunts>> affichageListeEmprunt(
            @Field("campus") String campus);

    @FormUrlEncoded
    @POST("/JeStock/nouvelleRef.php")
    Call<Void> nouvelleRef(
            @Field("type") String type,
            @Field("ref") long ref,
            @Field("nomRef") String nomRef,
            @Field("quantiteAjoutee") int quantiteAjoutee,
            @Field("quantiteConseillee") int prenomEmprunteur,
            @Field("campus") String campus);
}
