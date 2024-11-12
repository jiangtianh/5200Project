package model;



public class PeopleTitle {
    private Person person;
    private Title title;

    public PeopleTitle(Person person, Title title) {
        this.person = person;
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
}
