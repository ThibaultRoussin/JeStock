package fr.epf.jestock.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.AfficherInfoCarteEtuActivity;
import fr.epf.jestock.R;

public class EmpruntsEleveFragment extends Fragment {

    @BindView(R.id.edit_numero_etu)
    EditText numEtu;


    public EmpruntsEleveFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emprunts_eleve, container, false);

        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.bt_valider_eleve)
    public void valideSaisie(){
        Intent intent2 = new Intent(getContext(), AfficherInfoCarteEtuActivity.class);
        intent2.putExtra("NumEtu", numEtu.getText().toString());
        startActivity(intent2);
    }
}
