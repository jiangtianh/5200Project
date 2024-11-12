package model;

public class Person {
    private String personId;
    private String name;
    private int birthYear;
    private int deathYear;
    private String primaryProfession;

    public Person(String personId, String name, int birthYear, int deathYear, String primaryProfession) {
        this.personId = personId;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.primaryProfession = primaryProfession;
    }
    public Person(String personId) {
        this.personId = personId;
    }





    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public int getDeathYear() {
        return deathYear;
    }
    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }
    public String getPrimaryProfession() {
        return primaryProfession.replace('_', ' ').replace(",",", ");
    }
    public void setPrimaryProfession(String primaryProfession) {
        this.primaryProfession = primaryProfession;
    }
}
