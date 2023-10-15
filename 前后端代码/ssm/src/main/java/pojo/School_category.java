package pojo;

public class School_category {
    private Integer sid;

    private String school_name;

    private String tag;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name == null ? null : school_name.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public School_category() {
    }

    public School_category(Integer sid, String school_name, String tag) {
        this.sid = sid;
        this.school_name = school_name;
        this.tag = tag;
    }
}