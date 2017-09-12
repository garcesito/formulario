package com.garcesito.formulario;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String loggin,password,reppassword,correo,sexo,ciudad,hobbies="";
    private Button bFecha,bAceptar;
    private RadioButton rMasculino,rFemenino;
    private CheckBox cAnime,cGames,cDeporte,cDibujar;
    private Spinner sCiudad;
    private TextView tInformacion;

    private EditText eFecha,eLoggin,ePassword,eRepetirpassword,eCorreo;
    private int dia, mes, ano,eCant=0,bCant=0,cCant=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bFecha = (Button) findViewById(R.id.bFecha);
        bAceptar = (Button) findViewById(R.id.bAceptar);
        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        cAnime = (CheckBox) findViewById(R.id.cAnime);
        cDeporte = (CheckBox) findViewById(R.id.cDeporte);
        cDibujar = (CheckBox) findViewById(R.id.cDibujar);
        cGames = (CheckBox) findViewById(R.id.cVideojuegos);
        sCiudad = (Spinner) findViewById(R.id.sCiudad);
        tInformacion = (TextView) findViewById(R.id.tInformacion);
        eFecha = (EditText) findViewById(R.id.eFecha);
        eLoggin = (EditText) findViewById(R.id.eLoggin);
        ePassword = (EditText) findViewById(R.id.ePassword);
        eRepetirpassword = (EditText) findViewById(R.id.eRepetirPassword);
        eCorreo = (EditText) findViewById(R.id.eCorreo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudad.setAdapter(adapter);

        sCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showDatePickerDialog(View view) {
        final Calendar fecha= Calendar.getInstance();
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        mes = fecha.get(Calendar.MONTH);
        ano = fecha.get(Calendar.YEAR);
        bCant=1;

        DatePickerDialog datePickerDialog =new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                eFecha.setText(day+"/"+(month+1)+"/"+year);
                dia=day;
                mes=month+1;
                ano=year;
            }
        }
        ,dia,mes,ano);
        datePickerDialog.show();

    }

    public void Aceptar(View view) {
        loggin= eLoggin.getText().toString();
        password= ePassword.getText().toString();
        reppassword= eRepetirpassword.getText().toString();
        correo= eCorreo.getText().toString();

        if(loggin.equals("")){
            eCant=eCant+1;
        }
        if(password.equals("")){
            eCant=eCant+1;
        }
        if(reppassword.equals("")){
            eCant=eCant+1;
        }
        if(correo.equals("")){
            eCant=eCant+1;
        }
        if (rMasculino.isChecked()) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }
        if (cAnime.isChecked()) {
            hobbies += "Anime ";
            cCant=cCant+1;
        }
        if (cGames.isChecked()) {
            hobbies += "Games ";
            cCant=cCant+1;
        }
        if (cDeporte.isChecked()) {
            hobbies += "Deporte ";
            cCant=cCant+1;
        }
        if (cDibujar.isChecked()) {
            hobbies += "Dibujar ";
            cCant=cCant+1;
        }

        if(eCant!=0){
            tInformacion.setText("Por favor ingrese todos los datos ");
            eCant=0;
            cCant=0;
            hobbies="";
        }else
        {
            if(cCant==0)
            {
                tInformacion.setText("Por favor seleccione almenos un Hobbie");
                eCant=0;
                cCant=0;
                hobbies="";

            }else
            {
                if(bCant==0)
                {
                    tInformacion.setText("Por favor seleccione la fecha de nacimiento");
                    eCant=0;
                    cCant=0;
                    hobbies="";

                }else
                {
                    if(password.equals(reppassword))
                    {

                        tInformacion.setText("Loggin: " + loggin + " \nPassword: " + password + " \nCorreo: " + correo  +
                                " \nSexo: " + sexo + " \nhobbies: " + hobbies+ " \nciudad: " + ciudad+ " \nFecha de nacimiento: " + dia+"/"+mes+"/"+ano);
                        eCant=0;
                        cCant=0;
                        hobbies="";

                   }else
                    {
                        tInformacion.setText("Las contraseñas no coinciden");
                        eCant=0;
                        cCant=0;
                        hobbies="";
                    }
                }
            }
        }






    }
}
