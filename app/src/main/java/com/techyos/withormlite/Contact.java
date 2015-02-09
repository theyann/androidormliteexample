package com.techyos.withormlite;

import com.j256.ormlite.field.DatabaseField;

public class Contact {

    /**
     * Attributes
     */

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;

    /**
     * Constructors
     */

    public Contact() {
        // Needed for ORMLite
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Accessors
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
