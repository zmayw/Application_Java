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
		return "班级信息:  [ 班级ID:" + classId + ", 班级名称:" + className +" ]";
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	/**
	 * 向班级添加学生对象
	 * @param stu  学生对象
	 */
	public void addStudent(Student stu) {
		boolean flag = false;
		for (Student s : stuList) {
			if (s.equals(stu)) {
				flag = true;
				System.out.println("该学生" + stu + "已在班级中存在！");
				break;
			}
		}
		if (flag == false)
			stuList.add(stu);
	}

	
	/**
	 * 根据学号查询班级中的学生
	 * @param stuNum 学号
	 * @return 如果查到返回该学生对象，如果没查到返回null
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
	 * 修改班级某学生的语文成绩
	 * @param stuNum 学号
	 * @param score 成绩
	 */
	public void modifyChineseScore(String stuNum, float score) {
		Student s = searchStudentByNum(stuNum);
		if (s != null) {
			s.setChinese(score);
		} else {
			System.out.println("插入学生语文成绩失败，该学号在班级中不存在！");
		}

	}

	/**
	 * 修改班级某学生的数学成绩
	 * @param stuNum 学 号
	 * @param score 成绩
	 */
	public void modifyMathScore(String stuNum, float score) {
		Student s = searchStudentByNum(stuNum);
		if (s != null) {
			s.setMath(score);
		} else {
			System.out.println("插入学生数学成绩失败，该学号在班级中不存在");
		}
	}
	/**
	 * 依次班级学生的语文成绩
	 * @param stuNum 学号
	 * @param score 成绩
	 */
	public void insertChineseScore() {
		if (stuList == null) {
			System.out.println("该班级还没有学生！");
		} else {
			Scanner sc = new Scanner(System.in);
			for (int i=0;i<stuList.size();i++) {
				Student stu=stuList.get(i);
				try {
					System.out.println(stu);
					System.out.println("请输入该学生的语文成绩：");
					float score = sc.nextFloat();
					if(score>=1.0f && score<=100.00f){
						stu.setChinese(score);
					}else{
						System.out.println("分值范围错误！请输入1-100之间的小数！");
						i=i-1;
						continue;
					}
					
				} catch (InputMismatchException e) {
					System.out.println("输入非法！请输入1-100之间的小数！");
					sc.next();
					continue;
				}
			}
		}
	}

	/**
	 * 依次输入班级学生的数学成绩
	 * @param stuNum 学 号
	 * @param score 成绩
	 */
	public void insertMathScore() {
		if (stuList == null) {
			System.out.println("该班级还没有学生！");
		} else {
			Scanner sc = new Scanner(System.in);
			for (int i=0;i<stuList.size();i++) {
				Student stu=stuList.get(i);
				try {
					System.out.println(stu);
					System.out.println("请输入该学生的数学成绩：");
					float score = sc.nextFloat();
					if(score>=1.0f && score<=100.00f){
						stu.setMath(score);
					}else{
						System.out.println("分值范围错误！请输入1-100之间的小数！");
						i=i-1;
						continue;
					}
					
				} catch (InputMismatchException e) {
					System.out.println("输入非法！请输入1-100之间的小数！");
					sc.next();
					continue;
				}
			}
		}
	}
	
	/**
	 * 删除某个学生的信息
	 * @param stuNum 学号
	 */
	public void deleteStudent(String stuNum) {
		Student s = searchStudentByNum(stuNum);
		if (s != null) {
			stuList.remove(s);
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败，该学号在班级中不存在！");
		}
	}

	//计算班级数学平均成绩
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

	// 计算班级的语文平均成绩
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

	//显示班级内的所有学生信息
	public void displayAllStudent() {
		System.out.println("班级（" + classId + "," + className + ")所有学生信息：");
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
