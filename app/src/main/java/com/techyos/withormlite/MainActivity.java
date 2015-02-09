package com.techyos.withormlite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    /**
     * Attributes
     */

    private EditText editFirstName;
    private EditText editLastName;
    private Button addButton;
    private ListView contactsList;
    private ContactItemAdapter contactItemAdapter;

    private RuntimeExceptionDao<Contact, Integer> contactDao;

    /**
     * Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDao = getHelper().getContactREDao();

        registerViews();
        registerListeners();
        setupAdapters();
    }

    /**
     * Private Methods
     */

    private void registerViews() {
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        addButton = (Button) findViewById(R.id.addButton);
        contactsList = (ListView) findViewById(R.id.dataList);
    }

    private void registerListeners() {
        addButton.setOnClickListener(this);
        contactsList.setOnItemLongClickListener(this);
    }

    private void setupAdapters() {
        contactItemAdapter = new ContactItemAdapter(this, contactDao.queryForAll());
        contactsList.setAdapter(contactItemAdapter);
    }

    private void refreshList() {
        contactItemAdapter.clear();
        contactItemAdapter.addAll(contactDao.queryForAll());
    }

    /**
     * Overridden Methods: OnClickListener
     */

    @Override
    public void onClick(View v) {
        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();

        contactDao.createIfNotExists(new Contact(firstName, lastName));
        refreshList();

        editFirstName.setText("");
        editFirstName.requestFocus();
        editLastName.setText("");
    }

    /**
     * Overridden Methods: OnItemLongClickListener
     */

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) parent.getItemAtPosition(position);
        contactDao.deleteById(contact.getId());
        refreshList();
        return true;
    }
}
