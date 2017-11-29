package com.example.mash_ud.makeyourresume;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class Resume_Activity extends AppCompatActivity {

    //private static String FILE = "/Internal shared storage/cv.pdf"; // add permission in your manifest...
    //  private static String FILE = "mnt/sdcard/test.pdf"; // add permission in your manifest...

    TextView name, email, phn, birthday, gender, edu, prg, database, business, project, achv;

    Button jpg, pdf;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_);
        intializing();
        updateUI();
        jpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenShot();
            }
        });
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pdfmaking();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void pdfmaking() throws IOException, DocumentException {
        takeScreenShot();
        Document document=new Document();
        String dirpath=android.os.Environment.getExternalStorageDirectory().toString();
        PdfWriter.getInstance(document,new FileOutputStream(dirpath+"/CV.pdf"));
        document.open();
        Image img =Image.getInstance(dirpath+"/"+"cv.jpg");

        float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin() - 0) / img.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
        img.scalePercent(scaler);
        img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
        //img.setAlignment(Image.LEFT| Image.TEXTWRAP);


        document.add(img);
        document.close();
    }

    private void takeScreenShot() {

        View u = findViewById(R.id.scroll);

        ScrollView z = (ScrollView) findViewById(R.id.scroll);
        int totalHeight = z.getChildAt(0).getHeight();
        int totalWidth = z.getChildAt(0).getWidth();

        Bitmap b = getBitmapFromView(u, totalHeight, totalWidth);
        //Save bitmap
        String extr = Environment.getExternalStorageDirectory() + "/";
        String fileName = "cv.jpg";
        File myPath = new File(extr, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            MediaStore.Images.Media.insertImage(this.getContentResolver(), b, "Screen", "screen");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth) {

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    private void updateUI() {

        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

        image.setImageBitmap(bmp );

        name.setText(getIntent().getExtras().getString("Name"));
        email.setText(getIntent().getExtras().getString("Email"));
        phn.setText(getIntent().getExtras().getString("Phone"));
        birthday.setText(getIntent().getExtras().getString("Birthday"));
        gender.setText(getIntent().getExtras().getString("Gender"));
        edu.setText(getIntent().getExtras().getString("Education"));
        prg.setText(getIntent().getExtras().getString("Programing"));
        database.setText(getIntent().getExtras().getString("Database"));
        business.setText(getIntent().getExtras().getString("Business"));
        project.setText(getIntent().getExtras().getString("Project"));
        achv.setText(getIntent().getExtras().getString("Achivement"));


    }

    private void intializing() {
        name = (TextView) findViewById(R.id.nameText);
        email = (TextView) findViewById(R.id.emailText);
        phn = (TextView) findViewById(R.id.phnText);
        birthday = (TextView) findViewById(R.id.dateOfBirth);
        gender = (TextView) findViewById(R.id.genderText);
        edu = (TextView) findViewById(R.id.edu_quality);
        prg = (TextView) findViewById(R.id.programmingSkills);
        database = (TextView) findViewById(R.id.databaseSkills);
        business = (TextView) findViewById(R.id.businessSkills);
        project = (TextView) findViewById(R.id.projectText);
        achv = (TextView) findViewById(R.id.achivementText);

        jpg = (Button) findViewById(R.id.jpg);
        pdf = (Button) findViewById(R.id.pdf);

        image = (ImageView) findViewById(R.id.img);
    }
}
