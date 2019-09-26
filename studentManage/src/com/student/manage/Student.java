package com.student.manage;

public class Student implements Comparable<Student>{
	
	private String stuNum;
	private String stuName;
	private float math;
	private float chinese;
	
	public Student(){}
	
	//˫�ι��췽����������ѧ�ţ�������
	public Student(String stuNum,String stuName){
		this.setStuNum(stuNum);
		this.setStuName(stuName);
	}
	
	//ȫ�ι��췽����������ѧ�ţ���������ѧ�ɼ������ĳɼ���
	public Student(String stuNum,String stuName,float math,float chinese){
		this.setChinese(chinese);
		this.setMath(math);
		this.setStuNum(stuNum);
		this.setStuName(stuName);
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public float getMath() {
		return math;
	}

	public void setMath(float math) {
		this.math = math;
	}

	public float getChinese() {
		return chinese;
	}

	public void setChinese(float chinese) {
		this.chinese = chinese;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(chinese);
		result = prime * result + Float.floatToIntBits(math);
		result = prime * result + ((stuName == null) ? 0 : stuName.hashCode());
		result = prime * result + ((stuNum == null) ? 0 : stuNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj.getClass()==Student.class){
			Student s=(Student)obj;
			if(s.getStuNum().equals(this.getStuNum()) && s.getStuName().equals(this.getStuName())){
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString(){
		String str="ѧ����Ϣ: [ "+"ѧ��:"+this.getStuNum()+",����:"+this.getStuName()+",���ĳɼ�:"+this.getChinese()+",��ѧ�ɼ�:"+this.getMath()+" ]";
		return str;
	}
	
	
	@Override
	public int compareTo(Student stu){
		
		float f1=this.getMath();
		float f2=stu.getMath();
		int n=0;
		if(f1>f2){
			n=-1;
		}else if(f1<f2)
		{	n=1;
		}else{
			n=0;
		}
	
		return n;
		
	}
	
	
	
}
