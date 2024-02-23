package jdbc02;

public class StudentDTO extends JoDTO {
	// 1) private 으로 멤버 변수 정의
	private int sno;
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String cname;
	
	public StudentDTO() { }
	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname) {
		this.sno=sno;
		this.name=name;
		this.age=age;
		this.jno=jno;
		this.info=info;
		this.point=point;
		this.cname=cname;
	}
	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname,
         String jname, int captain, String project, String slogan) {
	      super(jno,jname,captain,project,slogan);
	      this.sno=sno;
	      this.name=name;
	      this.age=age;
	      this.jno=jno;
	      this.info=info;
	      this.point=point;
	      this.cname=cname;
   }
	// 2) getter / setter
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getJno() {
		return jno;
	}
	public void setJno(int jno) {
		this.jno = jno;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "StudentDTO [sno=" + sno + ", name=" + name + ", age=" + age 
				+ ", jno=" + jno + ", jname=" + getJname() 
				+ ", info=" + info + ", point=" + point  
				+ ", captain=" + getCaptain() + ", cname=" + cname 
				+ ", project=" + getProject() + "]";
	}
	
} // class