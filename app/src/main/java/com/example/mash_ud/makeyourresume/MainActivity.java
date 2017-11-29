package com.example.mash_ud.makeyourresume;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1 ;
    private EditText fname,lname,email,phone,scl,clg,varsity,sGPA,cGPA,CGPA,projects,achv;
    private Spinner mnth,day,year;

    private Switch prg,busi,data;
    private LinearLayout pL,dL,bL;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private CheckBox java,cplus,php,phython,cSharp,css;
    private CheckBox oracle,mysql,sqlite,firebase;
    private CheckBox communication,creativity,leadership;

    private String name,emailID,phnNo,dateOfB,gend,selectedMonth,selectedYear,selectedDay;
    private String eduQualification,prgmingSkill,databaseSkill,businessSkill,projectsText,achivement;

    private Button cnf;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializing();
        initSpinner();

        cnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(validform())
               {
                   personalInfo();
                   eduQualification();
                   skills();
                   projectsText = projects.getText().toString().trim();
                   achivement = achv.getText().toString().trim();
                   sartSendingDataViaIntent();
               }
               else
                   Toast.makeText(MainActivity.this, "Enter Required Data", Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getimage();
            }
        });


        spinnerListener();
        switchListener();

    }

    private void sartSendingDataViaIntent() {
        Intent intent = new Intent(MainActivity.this,Resume_Activity.class);
        intent.putExtra("Name",name);
        intent.putExtra("Email",emailID);
        intent.putExtra("Phone",phnNo);
        intent.putExtra("Birthday",dateOfB);
        intent.putExtra("Gender",gend);
        intent.putExtra("Education",eduQualification);
        intent.putExtra("Programing",prgmingSkill);
        intent.putExtra("Database",databaseSkill);
        intent.putExtra("Business",businessSkill);
        intent.putExtra("Project",projectsText);
        intent.putExtra("Achivement",achivement);


        imageView.buildDrawingCache();
        Bitmap image= imageView.getDrawingCache();

        Bundle extras = new Bundle();
        extras.putParcelable("imagebitmap", image);
        intent.putExtras(extras);


        startActivity(intent);

    }

    private void skills() {
        prgSkills();
        databaseSkills();
        businessSkills();
    }

    private void businessSkills() {
        StringBuilder str = new StringBuilder();
        int i=1;
        if(communication.isChecked())
        {
            str.append(i++).append(". Communication\n");
        }
        if(leadership.isChecked())
        {
            str.append(i++).append(". Leadership\n");
        }
        if(creativity.isChecked())
        {
            str.append(i++).append(". Creativity\n");
        }
        businessSkill = str.toString();
    }

    private void databaseSkills() {
        StringBuilder str = new StringBuilder();
        int i=1;
        if(oracle.isChecked())
        {
            str.append(i++).append(". Oracle Database\n");
        }
        if(mysql.isChecked())
        {
            str.append(i++).append(". MY SQL\n");
        }
        if(sqlite.isChecked())
        {
            str.append(i++).append(". SQLite Database\n");
        }
        if(firebase.isChecked())
        {
            str.append(i++).append(". Firebase Database\n");
        }
        databaseSkill = str.toString();

    }

    private void prgSkills() {
        StringBuilder str = new StringBuilder();
        int i=1;
        if(java.isChecked())
        {
            str.append(i++).append(". JAVA\n");
        }
        if(cplus.isChecked())
        {
            str.append(i++).append(". C++/C\n");
        }
        if(cSharp.isChecked())
        {
            str.append(i++).append(". C##\n");
        }
        if(phython.isChecked())
        {
            str.append(i++).append(". PHYTHON\n");
        }
        if(php.isChecked())
        {
            str.append(i++).append(". PHP\n");
        }
        if(css.isChecked())
        {
            str.append(i++).append(". HTML/CSS\n");
        }
        prgmingSkill = str.toString();

    }

    private void eduQualification() {
        StringBuilder str= new StringBuilder();
        String school = scl.getText().toString().trim();
        String college = clg.getText().toString().trim();
        String university = varsity.getText().toString().trim();
        String sscGPA = sGPA.getText().toString().trim();
        String hscGPA = cGPA.getText().toString().trim();
        String uniCGPA = CGPA.getText().toString().trim();

        str.append("*SSC from ").append(school).append(" with ").append(sscGPA+"\n");
        str.append("*HSC from ").append(college).append(" with ").append(hscGPA+"\n");
        str.append("*B.Sc from ").append(school).append(" with ").append(uniCGPA+"\n");

        eduQualification = str.toString();
    }

    private void switchListener() {

        prg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    pL.setVisibility(View.VISIBLE);
                }
                else
                {
                    pL.setVisibility(View.GONE);
                }
            }
        });
        data.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    dL.setVisibility(View.VISIBLE);
                }
                else
                {
                    dL.setVisibility(View.GONE);
                }
            }
        });
        busi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    bL.setVisibility(View.VISIBLE);
                }
                else
                {
                    bL.setVisibility(View.GONE);
                }
            }
        });

    }


    private void spinnerListener() {

        //monthSelected
        mnth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMonth = adapterView.getItemAtPosition(i).toString();
                if(selectedMonth=="Select Month")
                {
                    Toast.makeText(MainActivity.this, selectedMonth, Toast.LENGTH_SHORT).show();
                }
                else if(selectedMonth=="February")
                {
                    genFebDay(false);
                    //Toast.makeText(MainActivity.this, selectedMonth, Toast.LENGTH_SHORT).show();
                }
                else if(selectedMonth=="April" || selectedMonth=="June" || selectedMonth=="September" || selectedMonth=="November")
                {
                    gen30day();
                  //  Toast.makeText(MainActivity.this, "30 Days", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    gen31day();
                   // Toast.makeText(MainActivity.this, "31 days", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //yearSelected
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedYear = adapterView.getItemAtPosition(i).toString();
                if(selectedMonth=="February")
                {
                    int yr = Integer.parseInt(selectedYear);
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                genFebDay(true);
                            }
                            else
                                genFebDay(false);
                        }
                        else {
                            genFebDay(true);
                        }
                    }
                    else
                        genFebDay(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDay = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void gen30day() {
        ArrayList<Integer> days = new ArrayList<Integer>();
        for(int i=1;i<=30; i++)
        {
            days.add(i);
        }
        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, days);
        day.setAdapter(dataAdapter3);
    }

    private void genFebDay(boolean b) {
        ArrayList<Integer> days = new ArrayList<Integer>();
        for(int i=1;i<=28; i++)
        {
            days.add(i);
        }
        if(b)days.add(29);
        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, days);
        day.setAdapter(dataAdapter3);
    }

    private void gen31day() {
        ArrayList<Integer> days = new ArrayList<Integer>();
        for(int i=1;i<=31; i++)
        {
            days.add(i);
        }
        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, days);
        day.setAdapter(dataAdapter3);
    }

    private void initSpinner() {
        ArrayList<String> mnths = new ArrayList<String>();
        mnths.add("Select Month");
        mnths.add("January");
        mnths.add("February");
        mnths.add("March");
        mnths.add("April");
        mnths.add("May");
        mnths.add("June");
        mnths.add("July");
        mnths.add("August");
        mnths.add("September");
        mnths.add("October");
        mnths.add("November");
        mnths.add("December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mnths);
        mnth.setAdapter(dataAdapter);

       ArrayList<Integer> years = new ArrayList<Integer>();
        for(int i=1980;i<=2000; i++)
        {
            years.add(i);
        }
        ArrayAdapter<Integer> dataAdapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, years);
        year.setAdapter(dataAdapter2);
    }

    private void personalInfo() {
        name=fname.getText().toString().trim()+" "+lname.getText().toString().trim();
        emailID=email.getText().toString().trim();
        phnNo=phone.getText().toString().trim();
        dateOfB = getDateOFBirth();
        gend = getGender();
    }

    private boolean validform() {
        boolean validity=true;
        if(TextUtils.isEmpty(fname.getText().toString().trim()))
        {
            fname.setError("required");
             validity=false;
        }
        if(TextUtils.isEmpty(lname.getText().toString().trim()))
        {
            lname.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(email.getText().toString().trim()))
        {
            email.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(phone.getText().toString().trim()))
        {
            phone.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(getGender()))
        {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            validity=false;
        }
        if(TextUtils.isEmpty(selectedYear) || TextUtils.isEmpty(selectedDay) ||TextUtils.isEmpty(selectedMonth))
        {
            Toast.makeText(this, "Select Proper Birthday", Toast.LENGTH_SHORT).show();
            validity=false;
        }
        if(TextUtils.isEmpty(scl.getText().toString().trim()))
        {
            scl.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(clg.getText().toString().trim()))
        {
            clg.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(varsity.getText().toString().trim()))
        {
            varsity.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(sGPA.getText().toString().trim()))
        {
            sGPA.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(cGPA.getText().toString().trim()))
        {
            cGPA.setError("required");
            validity=false;
        }
        if(TextUtils.isEmpty(CGPA.getText().toString().trim()))
        {
            CGPA.setError("required");
            validity=false;
        }
        return validity;
    }

    private void initializing() {

        //Edit Texts
        fname = (EditText) findViewById(R.id.fName);
        lname = (EditText) findViewById(R.id.lName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phn);
        scl = (EditText) findViewById(R.id.school);
        clg = (EditText) findViewById(R.id.college);
        varsity = (EditText) findViewById(R.id.uni);
        sGPA = (EditText) findViewById(R.id.sclGPA);
        cGPA = (EditText) findViewById(R.id.hscGPA);
        CGPA = (EditText) findViewById(R.id.CGPA);
        projects = (EditText) findViewById(R.id.project);
        achv = (EditText) findViewById(R.id.achivement);

        //Spinner
        mnth = (Spinner) findViewById(R.id.month);
        day = (Spinner) findViewById(R.id.day);
        year = (Spinner) findViewById(R.id.year);

        //switcher
        prg = (Switch) findViewById(R.id.programmingSwt);
        data = (Switch) findViewById(R.id.databaseSwt);
        busi = (Switch) findViewById(R.id.businessSwt);

        //LinearLayout
        pL = (LinearLayout) findViewById(R.id.programmingLyt);
        dL = (LinearLayout) findViewById(R.id.databaseLyt);
        bL = (LinearLayout) findViewById(R.id.businessLyt);

        //checkbox
        java = (CheckBox) findViewById(R.id.java);
        cplus = (CheckBox) findViewById(R.id.CPlus);
        cSharp = (CheckBox) findViewById(R.id.cSharp);
        php = (CheckBox) findViewById(R.id.php);
        phython = (CheckBox) findViewById(R.id.phython);
        css = (CheckBox) findViewById(R.id.css);


        oracle = (CheckBox) findViewById(R.id.oracle);
        mysql = (CheckBox) findViewById(R.id.sql);
        sqlite = (CheckBox) findViewById(R.id.sqlite);
        firebase = (CheckBox) findViewById(R.id.firebase);


        communication = (CheckBox) findViewById(R.id.com);
        leadership = (CheckBox) findViewById(R.id.lead);
        creativity = (CheckBox) findViewById(R.id.creativity);

        //RadioGroup
        radioGroup = (RadioGroup) findViewById(R.id.gender);
        //Button
        cnf= (Button) findViewById(R.id.confirm);

        imageView = (ImageView) findViewById(R.id.pic);
    }

    public String getDateOFBirth() {
        String dateOFBirth;
        if(selectedDay=="1")
            dateOFBirth="1st "+selectedMonth+" "+selectedYear;
        else if(selectedDay=="2")
            dateOFBirth="2nd "+selectedMonth+" "+selectedYear;
        else if(selectedDay=="3")
            dateOFBirth="3rd "+selectedMonth+" "+selectedYear;
        else
            dateOFBirth=selectedDay+"th "+selectedMonth+" "+selectedYear;
        return dateOFBirth;
    }

    public String getGender() {

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId==-1) return null;
        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);
        String gender=radioButton.getText().toString();
        return gender;
    }

    public void getimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {

            if (requestCode == PICK_IMAGE) {
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
            }
        }
    }
}
