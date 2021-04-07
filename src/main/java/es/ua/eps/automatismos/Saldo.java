package es.ua.eps.automatismos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class Saldo extends AppCompatActivity {
    private TextView mTV;
    private Button LO;
    String spLine;
    DatabaseReference mRC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);
        Intent I = getIntent();
        spLine = I.getStringExtra("SC");
        DatabaseReference mDBR = FirebaseDatabase.getInstance().getReference();
        mRC = mDBR.child(spLine);
        LO = (Button)findViewById(R.id.btn);

        LO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent b = new Intent(Saldo.this,MainActivity.class);
                b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(b);
            }
        });

    mTV = (TextView) findViewById(R.id.saldo);
}

    @Override
    protected void onStart() {
        super.onStart();

        mRC.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                String Money = dataSnapshot.getValue().toString();
                mTV.setText(Money);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
