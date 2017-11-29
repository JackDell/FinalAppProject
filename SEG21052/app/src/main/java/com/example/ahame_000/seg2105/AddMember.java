package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddMember extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
    }
    public void saveNewMemberBttnClick(View view){

        EditText memberName = findViewById(R.id.MemberName_EditText_AddMember);
        String memberNameString = memberName.getText().toString();

        EditText memberPassword = findViewById(R.id.MemberPassword_EditText_AddMember);
        String memberPassString = memberPassword.getText().toString();

        RadioButton Adult =findViewById(R.id.Adult_RadioButton_AddMember);
        RadioButton Child =findViewById(R.id.Child_RadioButton_AddMember);

        DBmangment db = DBmangment.getInstance();
        Account acc = db.getAccount();

        if (Adult.isChecked()){

            //Parent newMember = new Parent(memberNameString, memberPassString, acc);

        }
        else if(Child.isChecked()){

            //Child newMember = new Child(memberNameString, memberPassString, acc);

        }
        if(!memberNameString.isEmpty() && !memberPassString.isEmpty() && (Adult.isChecked() || Child.isChecked())){
            Intent intent = new Intent(this,CreateAccount.class);
            startActivity(intent);

        }


    }


}
