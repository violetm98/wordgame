package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class AdjustSetting extends AppCompatActivity {

    public static final String boardSizeExtra = "boardSizeExtra";
    // public static final HashMap<Character,Integer> spinnerMapExtra = new HashMap<Character, Integer>();
    public static final String spinnerMapExtra = "spinnerMapExtra";
    public static final String timerExtra = "timerExtra";
    private Spinner spinner; //boardsize
    private Spinner spinner2; //timesetting
    private ImageButton confirmSettingButton;

    //letter weight
    private Spinner spinnerA;
    private Spinner spinnerB;
    private Spinner spinnerC;
    private Spinner spinnerD;
    private Spinner spinnerE;
    private Spinner spinnerF;
    private Spinner spinnerG;
    private Spinner spinnerH;
    private Spinner spinnerI;
    private Spinner spinnerJ;
    private Spinner spinnerK;
    private Spinner spinnerL;
    private Spinner spinnerM;
    private Spinner spinnerN;
    private Spinner spinnerO;
    private Spinner spinnerP;
    private Spinner spinnerQ;
    private Spinner spinnerR;
    private Spinner spinnerS;
    private Spinner spinnerT;
    private Spinner spinnerU;
    private Spinner spinnerV;
    private Spinner spinnerW;
    private Spinner spinnerX;
    private Spinner spinnerY;
    private Spinner spinnerZ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //go back to parent: app manager
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //setting listener

        confirmSettingButton = (ImageButton) findViewById(R.id.confirmSettingButton);
        confirmSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                confirmSetting();
            }

        });

    }

    public void confirmSetting(){
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinnerA = (Spinner) findViewById(R.id.spinnerA);
        spinnerB = (Spinner) findViewById(R.id.spinnerB);
        spinnerC = (Spinner) findViewById(R.id.spinnerC);
        spinnerD = (Spinner) findViewById(R.id.spinnerD);
        spinnerE = (Spinner) findViewById(R.id.spinnerE);
        spinnerF = (Spinner) findViewById(R.id.spinnerF);
        spinnerG = (Spinner) findViewById(R.id.spinnerG);
        spinnerH = (Spinner) findViewById(R.id.spinnerH);
        spinnerI = (Spinner) findViewById(R.id.spinnerI);
        spinnerJ = (Spinner) findViewById(R.id.spinnerJ);
        spinnerK = (Spinner) findViewById(R.id.spinnerK);
        spinnerL = (Spinner) findViewById(R.id.spinnerL);
        spinnerM = (Spinner) findViewById(R.id.spinnerM);
        spinnerN = (Spinner) findViewById(R.id.spinnerN);
        spinnerO = (Spinner) findViewById(R.id.spinnerO);
        spinnerP = (Spinner) findViewById(R.id.spinnerP);
        spinnerQ = (Spinner) findViewById(R.id.spinnerQ);
        spinnerR = (Spinner) findViewById(R.id.spinnerR);
        spinnerS = (Spinner) findViewById(R.id.spinnerS);
        spinnerT = (Spinner) findViewById(R.id.spinnerT);
        spinnerU = (Spinner) findViewById(R.id.spinnerU);
        spinnerV = (Spinner) findViewById(R.id.spinnerV);
        spinnerW = (Spinner) findViewById(R.id.spinnerW);
        spinnerX = (Spinner) findViewById(R.id.spinnerX);
        spinnerY = (Spinner) findViewById(R.id.spinnerY);
        spinnerZ = (Spinner) findViewById(R.id.spinnerZ);

        String boardSize = spinner.getSelectedItem().toString();
        String timer = spinner2.getSelectedItem().toString();

        String wtA = spinnerA.getSelectedItem().toString();
        String wtB = spinnerB.getSelectedItem().toString();
        String wtC = spinnerC.getSelectedItem().toString();
        String wtD = spinnerD.getSelectedItem().toString();
        String wtE = spinnerE.getSelectedItem().toString();
        String wtF = spinnerF.getSelectedItem().toString();
        String wtG = spinnerG.getSelectedItem().toString();
        String wtH = spinnerH.getSelectedItem().toString();
        String wtI = spinnerI.getSelectedItem().toString();
        String wtJ = spinnerJ.getSelectedItem().toString();
        String wtK = spinnerK.getSelectedItem().toString();
        String wtL = spinnerL.getSelectedItem().toString();
        String wtM = spinnerM.getSelectedItem().toString();
        String wtN = spinnerN.getSelectedItem().toString();
        String wtO = spinnerO.getSelectedItem().toString();
        String wtP = spinnerP.getSelectedItem().toString();
        String wtQ = spinnerQ.getSelectedItem().toString();
        String wtR = spinnerR.getSelectedItem().toString();
        String wtS = spinnerS.getSelectedItem().toString();
        String wtT = spinnerT.getSelectedItem().toString();
        String wtU = spinnerU.getSelectedItem().toString();
        String wtV = spinnerV.getSelectedItem().toString();
        String wtW = spinnerW.getSelectedItem().toString();
        String wtX = spinnerX.getSelectedItem().toString();
        String wtY = spinnerY.getSelectedItem().toString();
        String wtZ = spinnerZ.getSelectedItem().toString();

        String spinnerList;
        spinnerList = wtA + "," + wtB  + ","  +wtC  + ","  + wtD  + ","  + wtE  + ","  + wtF  + ","
                + wtG  + ","  + wtH  + ","  +wtI  + ","  +wtJ  + ","  +wtK + ","
                + wtL  + ","  +wtM  + ","  +wtN  + ","  +wtO  + ","  +wtP  + ","  +wtQ + ","
                + wtR  + ","  +wtS  + ","  +wtT  + ","  + wtU  + ","  +wtV  + ","  +wtW + ","
                +wtX  + ","  +wtY  + ","  +wtZ ;



        Intent intent =new Intent(this, AppManager.class); //sender, receiver
        intent.putExtra(boardSizeExtra, boardSize);
        intent.putExtra(spinnerMapExtra, spinnerList);
        intent.putExtra(timerExtra, timer);
        startActivity(intent);
    }

}
