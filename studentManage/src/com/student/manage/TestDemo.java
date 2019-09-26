package com.student.manage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestDemo {

	public void testBanji(){
		Student s1=new Student("a001","aa",98.0f,88.9f);
		Student s2=new Student("a002","bb",78.0f,88.0f);
		Student s3=new Student("a003","cc",98.0f,88.9f);
		Student s4=new Student("a004","dd",88.0f,88.9f);
		Banji bj1=new Banji("b1","大一班");
		Banji bj2=new Banji("b2","大二班");
		bj1.addStudent(s1);
		bj1.addStudent(s2);
		bj2.addStudent(s3);
		bj2.addStudent(s4);
		
		bj1.displayAllStudent();
		System.out.println("***************");
		bj2.displayAllStudent();
		System.out.println(bj1.getClassName()+"语文平均成绩："+bj1.getChineseAverageScore());
		//bj1.deleteStudent("a001");
		bj1.displayAllStudent();
		//bj1.insertChineseScore("a001",87。0f);
		bj1.insertChineseScore();
		bj1.displayAllStudent();
	}
	
	public void testSchool(){
		School sch=new School();
		Banji bj1=new Banji("b001","大一班");
		Banji bj2=new Banji("b002","大二班");
		Banji bj3=new Banji("b001","大一班");
		Banji bj4=new Banji("b004","大四班");
		Student s1=new Student("a001","aa",98.0f,88.9f);
		Student s2=new Student("a002","bb",78.0f,88.0f);
		Student s3=new Student("a003","cc",98.0f,88.9f);
		Student s4=new Student("a004","dd",88.0f,88.9f);
		Student s5=new Student("a003","cc",68.0f,58.9f);
		Student s6=new Student("a004","dd",99.0f,80.9f);

		bj2.addStudent(s3);
		bj2.addStudent(s4);
		bj4.addStudent(s5);
		bj4.addStudent(s6);
		bj1.addStudent(s1);
		bj1.addStudent(s2);
		sch.addBanji(bj1);
		sch.addBanji(bj3);
		sch.addBanji(bj2);
		sch.addBanji(bj4);
		sch.displayBanjiName();
		sch.sortChineseByAverage();
		sch.sortMathByAverage();
		sch.deleteBanji("大二班");
		sch.displayBanjiName();		
		
	}
	
	//学校管理菜单内容，存放在数组中
	public String[] schoolMenuArray(){
		String[] schMenu={
				"***学校管理***",
				"1-创建班级",
				"2-删除班级",
				"3-通过班级名称查询班级信息",
				"4-对各班语文平均成绩倒序",
				"5-对各班数学 平均成绩倒序",
				"6-显示所有班级名称",
				"9-返回上一级菜单"
		};
		return schMenu;
	}
	
	//主菜单内容，存放在数组中
	public String[] mainMenuArray(){
		String[] mainMenu={
				"***主菜单***",
				"1-班级管理",
				"2-学校管理",
				"0-退出"
		};
		return mainMenu;
	}
	
	//班级管理菜单内容，存放在数组中
	public String[] banjiMenuArray(){
		String[] banjiMenu={
				"***班级管理***",
				"1-添加学生信息到主学生列表",
				"2-添加学生信息到普通班级",
				"3-通过学号查询学生信息",
				"4-输入班级的语文成绩",
				"5-输入班级的数学成绩",
				"6-删除学生信息",
				"7-显示所所有学生信息",
				"9-返回上一级菜单"
		};
		return banjiMenu;
	}
	
	//显示学校管理菜单
	public void schoolMenu(){
		String[] slMenu=schoolMenuArray();
		System.out.println("*******************************");
		for(String s:slMenu){
			System.out.println("		"+s+"		");
		}
		System.out.println("*******************************");
	}
	
	//显示班级管理菜单
	public void banJiMenu(){
		String[] bjMenu=banjiMenuArray();
		System.out.println("*******************************");
		for(String s:bjMenu){
			System.out.println("		"+s+"		");
		}
		System.out.println("*******************************");
	}
	
	//显示主菜单
	public void mainMenu(){
		String[] mMenu=mainMenuArray();
		System.out.println("*******************************");
		for(String s:mMenu){
			System.out.println("		"+s+"		");
		}
		System.out.println("*******************************");
	}
	
	public Banji createBanji(Scanner sc){
		System.out.println("请输入班级编号:");
		String bjNum=sc.next();
		System.out.println("请输入班级名称:");
		String bjName=sc.next();
		Banji bj=new Banji(bjNum,bjName);
		return bj;
	}
	
	/**
	 * 向班级中添加学生，如果学生已在主学生列表中存在，则直接添加，否则新建学生。
	 * @param banji 要添加到的班级
	 * @param mainBanji 主学生列表
	 * @param sc 输入控制
	 */
	public void createStudentToBanji(Banji banji,Banji mainBanji,Scanner sc){
		System.out.println("请输入要添加的学生数量");
		int countStu=sc.nextInt();
		for(int i=1;i<=countStu;i++){
			System.out.println("请输入第"+i+"个学生信息");
			System.out.println("请输入学生ID:");
			String sId=sc.next();
			Student stu=mainBanji.searchStudentByNum(sId);
			if(stu==null){
				System.out.println("请输入学生姓名:");
				String sName=sc.next();
				Student stuNew=new Student(sId,sName);
				if(banji!=null)//只向主学生列表添加学生时，该变量值为null
					banji.addStudent(stuNew);
				mainBanji.addStudent(stuNew);
				System.out.println("添加成功！");
			}else{
				if(banji!=null)//只向主学生列表添加学生时，该变量值为null
					banji.addStudent(stu);
				//mainBanji.addStudent(stu);
				System.out.println("该学生已在主学生列表中存在，添加成功！");
			}
		}
	}

	// 主流程
	public void test() {
		
		Scanner sc = new Scanner(System.in);
		// 创建学校
		School school = new School();
		// 创建主班级列表
		Banji bj = new Banji("000", "主学生列表");
		school.addBanji(bj);
		// 创建普通班级
		Banji bjNew ;
		int n=0;
		while (true) {
			mainMenu();
			try {			
				System.out.println("请输入相应操作对应的数字:");
				n = sc.nextInt();
				if (n == 0) {
					System.out.println("退出!");
					break;
				}
				switch (n) {
				case 1:	
					int bjN = 0;
					String bjName;
					
					while (true) {
						banJiMenu();
						bjN=sc.nextInt();			
						if (bjN == 9) {
							System.out.println("返回上一级菜单!");
							break;
						}
						switch (bjN) {
						case 1:
							System.out.println(banjiMenuArray()[bjN]);
							createStudentToBanji(null, bj, sc);//普通班级的参数传值 为null
							bj.displayAllStudent();
							break;
						case 2:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("请输入要添加学生的班级名称");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("该班级不存在！请先去学校管理中创建班级");
								break;
							} else {
								createStudentToBanji(bjNew, bj, sc);
								bjNew.displayAllStudent();
							}
							break;
						case 3:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("请输入要查询学生的班级名称");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("该班级不存在！");
								break;
							}
							System.out.println("请输入要查询的学生学号:");
							String stuNum1 = sc.next();
							Student stu2 = bjNew.searchStudentByNum(stuNum1);
							if (stu2 == null) {
								System.out.println("没有查询到该学生信息！");
							}
							else{
								System.out.println(stu2);
							}
							break;
						case 4:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("请输要输入语文成绩的班级名称:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("该班级不存在，请先去创建!");
								break;
							}
							bjNew.insertChineseScore();
							break;
						case 5:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("请输要输入数学成绩的班级名称:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("该班级不存在，请先去创建!");
								break;
							}
							bjNew.insertMathScore();
							break;
						case 6:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("请输要删除的班级名称:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("该班级不存在！");
								break;
							}
							System.out.println("请输入要删除的学生ID:");
							String stuNum = sc.next();
							bjNew.deleteStudent(stuNum);
							break;
						case 7:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("请输要查看的班级名称:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("该班级不存在，请先去创建!");
								break;
							}
							bjNew.displayAllStudent();
							break;
						case 9:
							System.out.println("9-返回上一级菜单");
							break;
						default:
							System.out.println("输入有误，没有对应的操作菜单");
							break;
						}

					}
					break;
				case 2:
					int scN = 0;
					String bjNum2;
					String bjName2;
					while (true) {
						schoolMenu();
						scN=sc.nextInt();
						if (scN == 9) {
							System.out.println("返回上一级菜单！");
							break;
						}
						switch (scN) {
						case 1:
							System.out.println(schoolMenuArray()[scN]);
							System.out.println("请输入创建的班级编号:");
							bjNum2 = sc.next();
							System.out.println("请输入创建的班级名称:");
							bjName2 = sc.next();
							bjNew=new Banji(bjNum2, bjName2);
							school.addBanji(bjNew);
							System.out.println("添加班级成功！");
							break;
						case 2:
							System.out.println(schoolMenuArray()[scN]);
							System.out.println("请输入删除的班级名称:");
							bjName2 = sc.next();
							school.deleteBanji(bjName2);
							break;
						case 3:
							System.out.println(schoolMenuArray()[scN]);
							System.out.println("请输入要查询的班级名称:");
							bjName2 = sc.next();
							bjNew = school.searchByName(bjName2);
							if (bjNew != null) {
								System.out.println(bjNew);
								bjNew.displayAllStudent();
							}else{
								System.out.println("查询失败，班级不存在！");
							}
							break;
						case 4:
							System.out.println(schoolMenuArray()[scN]);
							if (school.getSchoolMap() == null) {
								System.out.println("学校还没有任何班级信息，请先去创建班级！");
							} else {
								school.sortChineseByAverage();
							}
							break;
						case 5:
							System.out.println(schoolMenuArray()[scN]);
							if (school.getSchoolMap() == null) {
								System.out.println("学校还没有任何班级信息，请先去创建班级！");
							} else {
								school.sortMathByAverage();
							}
							break;
						case 6:
							System.out.println(schoolMenuArray()[scN]);
							school.displayBanjiName();
							break;
						default:
							System.out.println("输入有误，没有对应的操作菜单！");
							break;
						}
					}
					break;
				default:
					System.out.println("输入有误，没有对应的操作菜单！");
					break;

				}
				
			} catch (InputMismatchException e) {
				System.out.println("输入内容非法，请输入菜单中对应的数字");
				sc.next();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		TestDemo td=new TestDemo();
		//td.testBanji();
		//td.testSchool();
		td.test();
		
	}
	


}
