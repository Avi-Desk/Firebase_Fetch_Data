package com.example.retrieve_data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.StringReader;

public class MainActivity extends AppCompatActivity {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    Button btn;
    Integer t;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a=(TextView)findViewById(R.id.nameview);
        b=(TextView)findViewById(R.id.ageview);
        c=(TextView)findViewById(R.id.htview);
        d=(TextView)findViewById(R.id.phview);
        e=(TextView)findViewById(R.id.idview);
        btn=(Button)findViewById((R.id.btnshow));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = e.getText().toString();
                reff= FirebaseDatabase.getInstance().getReference().child("Member").child(t);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String age =dataSnapshot.child("age").getValue().toString();
                        String height=dataSnapshot.child("height").getValue().toString();
                        String phone =dataSnapshot.child("phone").getValue().toString();

                        a.setText(name);
                        b.setText(age);;
                        c.setText(height);
                        d.setText(phone);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
