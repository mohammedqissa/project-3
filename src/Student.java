import java.sql.Date;

public class Student implements Comparable<Student>{


	private Integer id;
	private String name;
	private Date birthDate;
	private String address;
	private int classId;
	private Date enrollmentDate;
	private String status;








	public Integer getId() {
		return id;
	}
	public void setId(Integer id) throws Exception {

		try {		
			School.addId(id);
			this.id = id;

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}


	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
		School.addClass(classId);

	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isGraduated(){
		return(status.equals("Graduated"));
	}
	public void graduated(boolean a){
		if(a)
			setStatus("Graduated");
		else
			setStatus("UnGraduated");
	}




	public Student() {
		super();
	}

	public Student(int id, String name, Date birthDate, String address, int classId, Date enrollmentDate,
			boolean graduated) throws Exception {
		super();
		this.setId(id);
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
		this.classId = classId;
		School.addClass(classId);
		this.enrollmentDate = enrollmentDate;
		this.graduated(graduated);
	}

	public String toSave(){

		String[] a = birthDate.toString().split("-");
		String[] b = enrollmentDate.toString().split("-");


		return(id+","+name+","+(Integer.parseInt(a[0])-1900) + "-"+ (Integer.parseInt(a[1])-1)+"-"+(Integer.parseInt(a[2]))+","+address+","+ classId+","+(Integer.parseInt(b[0])-1900) + "-"+ (Integer.parseInt(b[1])-1)+"-"+(Integer.parseInt(b[2]))+","+(status.equals("Graduated")));
	}



	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub

		return this.name.compareTo(o.getName());

	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", address=" + address
				+ ", classId=" + classId + ", enrollmentDate=" + enrollmentDate + ", status=" + status + "]";
	}





}
