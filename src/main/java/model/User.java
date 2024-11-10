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
        ACCOUNTANT, AUDITOR, FINANCIAL_ANALYST, BOOKKEEPER, INVESTMENT_BANKER, GRAPHIC_DESIGNER, ILLUSTRATOR, PHOTOGRAPHER, FASHION_DESIGNER, ANIMATOR,
        MANAGER, CONSULTANT, BUSINESS_ANALYST, HUMAN_RESOURCES_MANAGER, OPERATIONS_MANAGER, CIVIL_ENGINEER, ARCHITECT, CONSTRUCTION_WORKER, ELECTRICAL_ENGINEER, MECHANICAL_ENGINEER,
        TEACHER, PROFESSOR, EDUCATIONAL_ADMINISTRATOR, TUTOR, SCHOOL_COUNSELOR, DOCTOR, NURSE, PHARMACIST, DENTIST, PHYSICAL_THERAPIST,
        SOFTWARE_DEVELOPER, DATA_ANALYST, SYSTEMS_ADMINISTRATOR, CYBERSECURITY_SPECIALIST, IT_SUPPORT_SPECIALIST, LAWYER, PARALEGAL, JUDGE, LEGAL_ASSISTANT, COURT_CLERK,
        MARKETING_MANAGER, SALES_REPRESENTATIVE, SOCIAL_MEDIA_MANAGER, BRAND_MANAGER, ADVERTISING_EXECUTIVE, BIOLOGIST, CHEMIST, PHYSICIST, RESEARCH_SCIENTIST, LAB_TECHNICIAN,
        ELECTRICIAN, PLUMBER, CARPENTER, MECHANIC, WELDER, TRUCK_DRIVER, DELIVERY_DRIVER, PILOT, TRAIN_CONDUCTOR, LOGISTICS_COORDINATOR,
        CHEF, WAITER, HOTEL_MANAGER, EVENT_PLANNER, BARTENDER, POLICE_OFFICER, FIREFIGHTER, POSTAL_WORKER, SOCIAL_WORKER, PUBLIC_ADMINISTRATOR,
        JOURNALIST, EDITOR, PUBLIC_RELATIONS_SPECIALIST, TV_HOST, COPYWRITER, STORE_MANAGER, CASHIER, MERCHANDISER, SALES_ASSOCIATE, INVENTORY_SPECIALIST,
        REAL_ESTATE_AGENT, PROPERTY_MANAGER, APPRAISER, REAL_ESTATE_DEVELOPER, LEASING_AGENT, ACTOR, MUSICIAN, DIRECTOR, COMEDIAN, DANCER,
        ATHLETE, COACH, PERSONAL_TRAINER, SPORTS_MANAGER, PHYSICAL_EDUCATION_TEACHER, CUSTOMER_SERVICE_REPRESENTATIVE, CALL_CENTER_AGENT, TECHNICAL_SUPPORT_SPECIALIST, CONCIERGE, HELP_DESK_SPECIALIST
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