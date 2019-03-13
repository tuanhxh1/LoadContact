package com.example.tuan.loadcontact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listContact;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContact = findViewById(R.id.list_contact);

        showContacts();
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            ArrayList<PersonContact> personContacts = getContact();
            ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.activity_main, personContacts);
            listContact.setAdapter(contactAdapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we cannot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private ArrayList<PersonContact> getContact() {
        ArrayList<PersonContact> personContacts = new ArrayList<>();

        mCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'", null,
                ContactsContract.Data.DISPLAY_NAME + " ASC");
        int name = mCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME);
        int phone = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        while (mCursor.moveToNext()) {
            String contactName = mCursor.getString(name);
            String contactNumber = mCursor.getString(phone);
            personContacts.add(new PersonContact(contactName, contactNumber));
        }
        mCursor.close();
        return personContacts;
    }

}
