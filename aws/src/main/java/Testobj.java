public class Testobj {

    private String name;
    private String company;
    private String project;

    @Override
    public String toString() {
        return "Testobj{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", project='" + project + '\'' +
                '}';
    }

    public Testobj(String name, String company, String project) {
        this.name = name;
        this.company = company;
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
