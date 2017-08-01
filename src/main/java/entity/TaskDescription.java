package entity;

/**
 * Created by nikita93 on 03.07.2017.
 */
/**
 * Created by nikita93 on 03.07.2017.
 */
public class TaskDescription {
    private String task_id;
    private String shop_id;
    private String shop_name;
    private String id_is;
    private String important;
    private String status;
    private String type;
    private String problem;
    private String symptom;
    private String grade;
    private String category;
    private String create_date;
    private String agree_date;
    private String comming_date;
    private String solving_date;
    private String comming_date_fact;
    private String solving_date_fact;
    private String perfomer;
    private String coordinator;
    private String commentary;

    public TaskDescription(String task_id, String shop_id, String shop_name, String id_is, String important, String status, String type, String problem, String symptom, String grade, String category, String create_date, String agree_date, String comming_date, String solving_date, String comming_date_fact, String solving_date_fact, String perfomer, String coordinator, String commentary) {
        this.task_id = task_id;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.id_is = id_is;
        this.important = important;
        this.status = status;
        this.type = type;
        this.problem = problem;
        this.symptom = symptom;
        this.grade = grade;
        this.category = category;
        this.create_date = create_date;
        this.agree_date = agree_date;
        this.comming_date = comming_date;
        this.solving_date = solving_date;
        this.comming_date_fact = comming_date_fact;
        this.solving_date_fact = solving_date_fact;
        this.perfomer = perfomer;
        this.coordinator = coordinator;
        this.commentary = commentary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getId_is() {
        return id_is;
    }

    public void setId_is(String id_is) {
        this.id_is = id_is;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getAgree_date() {
        return agree_date;
    }

    public void setAgree_date(String agree_date) {
        this.agree_date = agree_date;
    }

    public String getComming_date() {
        return comming_date;
    }

    public void setComming_date(String comming_date) {
        this.comming_date = comming_date;
    }

    public String getSolving_date() {
        return solving_date;
    }

    public void setSolving_date(String solving_date) {
        this.solving_date = solving_date;
    }

    public String getComming_date_fact() {
        return comming_date_fact;
    }

    public void setComming_date_fact(String comming_date_fact) {
        this.comming_date_fact = comming_date_fact;
    }

    public String getSolving_date_fact() {
        return solving_date_fact;
    }

    public void setSolving_date_fact(String solving_date_fact) {
        this.solving_date_fact = solving_date_fact;
    }

    public String getPerfomer() {
        return perfomer;
    }

    public void setPerfomer(String perfomer) {
        this.perfomer = perfomer;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Override
    public String toString() {
        return "TaskDescription{" +
                "task_id='" + task_id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", id_is='" + id_is + '\'' +
                ", important='" + important + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", problem='" + problem + '\'' +
                ", symptom='" + symptom + '\'' +
                ", grade='" + grade + '\'' +
                ", create_date='" + create_date + '\'' +
                ", agree_date='" + agree_date + '\'' +
                ", comming_date='" + comming_date + '\'' +
                ", solving_date='" + solving_date + '\'' +
                ", comming_date_fact='" + comming_date_fact + '\'' +
                ", solving_date_fact='" + solving_date_fact + '\'' +
                ", perfomer='" + perfomer + '\'' +
                ", coordinator='" + coordinator + '\'' +
                ", commentary='" + commentary + '\'' +
                '}';
    }

    public String prettyOut() {
        return  "\nЗаявка:" +
                "\nНомер заявки --> " + task_id +
                "\nМагазин --> " + shop_name +
                "\nСоздана --> " + agree_date +
                "\nРешение --> " + solving_date +
                "\nИсполнитель -->" + perfomer +
                "\nОписание -->" + problem + "\n";
    }
}

