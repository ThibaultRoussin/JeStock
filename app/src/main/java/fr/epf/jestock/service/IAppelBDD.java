package fr.epf.jestock.service;

import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;
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
    @POST("/connection.php")
    Call<User> sendUser(@Field("email") String username, @Field("mdp") String password);

    @FormUrlEncoded
    @POST("/refStock.php")
    Call<ResultatRecherche> sendReferenceStock(@Field("referenceStock") long reference, @Field("campus") String campus);

}
