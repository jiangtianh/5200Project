package model;

public class Principals {

    public enum JobCategory {
        editor, archive_sound, cinematographer, actress, self, production_designer,
        casting_director, producer, director, writer, composer, actor, archive_footage,
    }


    private Title title;
    private int ordering;
    private Person person;
    private JobCategory category;
    private String job;
    private String characters;


    public Principals(Title title, int ordering, Person person, JobCategory category, String job, String characters) {
        this.title = title;
        this.ordering = ordering;
        this.person = person;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }



    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
    public int getOrdering() {
        return ordering;
    }
    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public JobCategory getCategory() {
        return category;
    }
    public void setCategory(JobCategory category) {
        this.category = category;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getCharacters() {
        return characters;
    }
    public void setCharacters(String characters) {
        this.characters = characters;
    }

}
