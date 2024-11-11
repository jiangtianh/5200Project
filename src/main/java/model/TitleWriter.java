package model;

public class TitleWriter {
    private Title title;
    private Person person;

    public TitleWriter(Title title, Person person) {
        this.title = title;
        this.person = person;
    }

    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

}
