package com.example.clp_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, delete, update, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        name = findViewById( R.id.name );
        contact = findViewById( R.id.contact );
        dob = findViewById( R.id.dob );

        insert = findViewById( R.id.insert );
        update = findViewById( R.id.update );
        delete = findViewById( R.id.delete );
        view = findViewById( R.id.view );

        DB = new DBHelper( this );

        insert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insetuserdata( nameTXT,contactTXT,dobTXT );
                if(checkinsertdata==true)
                    Toast.makeText( MainActivity.this,"New Entry",Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( MainActivity.this,"Not inserted",Toast.LENGTH_SHORT ).show();
            }
        } );

        update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata( nameTXT,contactTXT,dobTXT );
                if(checkupdatedata==true)
                    Toast.makeText( MainActivity.this,"Update",Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( MainActivity.this,"Not Update",Toast.LENGTH_SHORT ).show();
            }
        } );

        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata( nameTXT );
                if(checkdeletedata==true)
                    Toast.makeText( MainActivity.this,"Delete",Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( MainActivity.this,"Not Delete",Toast.LENGTH_SHORT ).show();
            }
        } );

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText( MainActivity.this,"No Entry Exits", Toast.LENGTH_SHORT ).show();
                    return;
                }
                StringBuffer Buffer = new StringBuffer();
                while (res.moveToNext()){
                    Buffer.append( "Name :"+res.getString( 0 )+"\n" );
                    Buffer.append( "Contact :"+res.getString( 1 )+"\n" );
                    Buffer.append( "Date of Birth :"+res.getString( 2 )+"\n\n" );
                }
                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
                builder.setCancelable( true );
                builder.setTitle( "User Entries" );
                builder.setMessage( Buffer.toString() );
                builder.show();
            }
        } );
    }
}