package com.student.manage;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Banji {

	private String classId;
	private String className;
	private List<Student> stuList;

	public Banji() {
	}

	public Banji(String classId, String className) {
		this.setClassId(classId);
		this.setClassName(className);
		stuList = new ArrayList<Student>();

	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Student> getStuList() {
		return stuList;
	}

	@Override
	public String toString() {
		return "�༶��Ϣ:  [ �༶ID:" + classId + ", �༶����:" + className +" ]";
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	/**
	 * ��༶���ѧ������
	 * @param stu  ѧ������
	 */
	public void addStudent(Student stu) {
		boolean flag = false;
		for (Student s : stuList) {
			if (s.equals(stu)) {
				flag = true;
				System.out.println("��ѧ��" + stu + "���ڰ༶�д��ڣ�");
				break;
			}
		}
		if (flag == false)
			stuList.add(stu);
	}

	
	/**
	 * ����ѧ�Ų�ѯ�༶�е�ѧ��
	 * @param stuNum ѧ��
	 * @return ����鵽���ظ�ѧ���������û�鵽����null
	 */
	public Student searchStudentByNum(String stuNum) {
		for (Student s : stuList) {
			if (s.getStuNum().equals(stuNum)) {
				return s;
			}
		}

		return null;
	}

	/**
	 * �޸İ༶ĳѧ�������ĳɼ�
	 * @param stuNum ѧ��
	 * @param score �ɼ�
	 */
	public void modifyChineseScore(String stuNum, float score) {
		Student s = searchStudentByNum(stuNum);
		if (s != null) {
			s.setChinese(score);
		} else {
			System.out.println("����ѧ�����ĳɼ�ʧ�ܣ���ѧ���ڰ༶�в����ڣ�");
		}

	}

	/**
	 * �޸İ༶ĳѧ������ѧ�ɼ�
	 * @param stuNum ѧ ��
	 * @param score �ɼ�
	 */
	public void modifyMathScore(String stuNum, float score) {
		Student s = searchStudentByNum(stuNum);
		if (s != null) {
			s.setMath(score);
		} else {
			System.out.println("����ѧ����ѧ�ɼ�ʧ�ܣ���ѧ���ڰ༶�в�����");
		}
	}
	/**
	 * ���ΰ༶ѧ�������ĳɼ�
	 * @param stuNum ѧ��
	 * @param score �ɼ�
	 */
	public void insertChineseScore() {
		if (stuList == null) {
			System.out.println("�ð༶��û��ѧ����");
		} else {
			Scanner sc = new Scanner(System.in);
			for (int i=0;i<stuList.size();i++) {
				Student stu=stuList.get(i);
				try {
					System.out.println(stu);
					System.out.println("�������ѧ�������ĳɼ���");
					float score = sc.nextFloat();
					if(score>=1.0f && score<=100.00f){
						stu.setChinese(score);
					}else{
						System.out.println("��ֵ��Χ����������1-100֮���С����");
						i=i-1;
						continue;
					}
					
				} catch (InputMismatchException e) {
					System.out.println("����Ƿ���������1-100֮���С����");
					sc.next();
					continue;
				}
			}
		}
	}

	/**
	 * ��������༶ѧ������ѧ�ɼ�
	 * @param stuNum ѧ ��
	 * @param score �ɼ�
	 */
	public void insertMathScore() {
		if (stuList == null) {
			System.out.println("�ð༶��û��ѧ����");
		} else {
			Scanner sc = new Scanner(System.in);
			for (int i=0;i<stuList.size();i++) {
				Student stu=stuList.get(i);
				try {
					System.out.println(stu);
					System.out.println("�������ѧ������ѧ�ɼ���");
					float score = sc.nextFloat();
					if(score>=1.0f && score<=100.00f){
						stu.setMath(score);
					}else{
						System.out.println("��ֵ��Χ����������1-100֮���С����");
						i=i-1;
						continue;
					}
					
				} catch (InputMismatchException e) {
					System.out.println("����Ƿ���������1-100֮���С����");
					sc.next();
					continue;
				}
			}
		}
	}
	
	/**
	 * ɾ��ĳ��ѧ������Ϣ
	 * @param stuNum ѧ��
	 */
	public void deleteStudent(String stuNum) {
		Student s = searchStudentByNum(stuNum);
		if (s != null) {
			stuList.remove(s);
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ���ѧ���ڰ༶�в����ڣ�");
		}
	}

	//����༶��ѧƽ���ɼ�
	public float getMathAverageScore() {
		float avg = 0.0f;
		float sum = 0.0f;
		if (stuList.isEmpty()) {
			avg = 0.0f;
		} else {

			for (Student s : stuList) {
				sum = sum + s.getMath();
			}
			avg = sum / stuList.size();
		}
		return avg;
	}

	// ����༶������ƽ���ɼ�
	public float getChineseAverageScore() {
		float avg = 0.0f;
		float sum = 0.0f;
		if (stuList.isEmpty()) {
			avg = 0.0f;
		} else {
			for (Student s : stuList) {
				sum = sum + s.getChinese();
			}
			avg = sum / stuList.size();
		}
		return avg;
	}

	//��ʾ�༶�ڵ�����ѧ����Ϣ
	public void displayAllStudent() {
		System.out.println("�༶��" + classId + "," + className + ")����ѧ����Ϣ��");
		Iterator<Student> t = stuList.iterator();
		while (t.hasNext()) {
			System.out.println(t.next());
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classId == null) ? 0 : classId.hashCode());
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((stuList == null) ? 0 : stuList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj.getClass()==Banji.class){
			Banji bj=(Banji)obj;
			if(bj.getClassId().equals(this.getClassId()) && bj.getClassName().equals(this.getClassName())){
				return true;
			}
		}
		return false;
	}
	
	

}
