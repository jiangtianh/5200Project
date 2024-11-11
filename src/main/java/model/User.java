package model;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private java.sql.Date dob;
    private String mbti;
    private Profession profession;

    public enum Profession {
        Accountant, Auditor, Financial_Analyst, Bookkeeper, Investment_Banker, Graphic_Designer, Illustrator, Photographer, Fashion_Designer, Animator,
        Manager, Consultant, Business_Analyst, Human_Resources_Manager, Operations_Manager, Civil_Engineer, Architect, Construction_Worker, Electrical_Engineer, Mechanical_Engineer,
        Teacher, Professor, Educational_Administrator, Tutor, School_Counselor, Doctor, Nurse, Pharmacist, Dentist, Physical_Therapist,
        Software_Developer, Data_Analyst, Systems_Administrator, Cybersecurity_Specialist, IT_Support_Specialist, Lawyer, Paralegal, Judge, Legal_Assistant, Court_Clerk,
        Marketing_Manager, Sales_Representative, Social_Media_Manager, Brand_Manager, Advertising_Executive, Biologist, Chemist, Physicist, Research_Scientist, Lab_Technician,
        Electrician, Plumber, Carpenter, Mechanic, Welder, Truck_Driver, Delivery_Driver, Pilot, Train_Conductor, Logistics_Coordinator,
        Chef, Waiter, Hotel_Manager, Event_Planner, Bartender, Police_Officer, Firefighter, Postal_Worker, Social_Worker, Public_Administrator,
        Journalist, Editor, Public_Relations_Specialist, TV_Host, Copywriter, Store_Manager, Cashier, Merchandiser, Sales_Associate, Inventory_Specialist,
        Real_Estate_Agent, Property_Manager, Appraiser, Real_Estate_Developer, Leasing_Agent, Actor, Musician, Director, Comedian, Dancer,
        Athlete, Coach, Personal_Trainer, Sports_Manager, Physical_Education_Teacher, Customer_Service_Representative, Call_Center_Agent, Technical_Support_Specialist, Concierge, Help_Desk_Specialist;

        public static Profession[] getAllProfessions() {
            return Profession.values();
        }
    }

    public User(String username, String password, String firstName, String lastName, java.sql.Date dob, String mbti, Profession profession) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.mbti = mbti;
        this.profession = profession;
    }
    public User(String username) {
        this.username = username;
    }



    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public java.sql.Date getDob() {
        return dob;
    }

    public void setDob(java.sql.Date dob) {
        this.dob = dob;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}