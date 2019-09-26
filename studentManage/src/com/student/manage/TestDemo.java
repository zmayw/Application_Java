package com.student.manage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestDemo {

	public void testBanji(){
		Student s1=new Student("a001","aa",98.0f,88.9f);
		Student s2=new Student("a002","bb",78.0f,88.0f);
		Student s3=new Student("a003","cc",98.0f,88.9f);
		Student s4=new Student("a004","dd",88.0f,88.9f);
		Banji bj1=new Banji("b1","��һ��");
		Banji bj2=new Banji("b2","�����");
		bj1.addStudent(s1);
		bj1.addStudent(s2);
		bj2.addStudent(s3);
		bj2.addStudent(s4);
		
		bj1.displayAllStudent();
		System.out.println("***************");
		bj2.displayAllStudent();
		System.out.println(bj1.getClassName()+"����ƽ���ɼ���"+bj1.getChineseAverageScore());
		//bj1.deleteStudent("a001");
		bj1.displayAllStudent();
		//bj1.insertChineseScore("a001",87��0f);
		bj1.insertChineseScore();
		bj1.displayAllStudent();
	}
	
	public void testSchool(){
		School sch=new School();
		Banji bj1=new Banji("b001","��һ��");
		Banji bj2=new Banji("b002","�����");
		Banji bj3=new Banji("b001","��һ��");
		Banji bj4=new Banji("b004","���İ�");
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
		sch.deleteBanji("�����");
		sch.displayBanjiName();		
		
	}
	
	//ѧУ����˵����ݣ������������
	public String[] schoolMenuArray(){
		String[] schMenu={
				"***ѧУ����***",
				"1-�����༶",
				"2-ɾ���༶",
				"3-ͨ���༶���Ʋ�ѯ�༶��Ϣ",
				"4-�Ը�������ƽ���ɼ�����",
				"5-�Ը�����ѧ ƽ���ɼ�����",
				"6-��ʾ���а༶����",
				"9-������һ���˵�"
		};
		return schMenu;
	}
	
	//���˵����ݣ������������
	public String[] mainMenuArray(){
		String[] mainMenu={
				"***���˵�***",
				"1-�༶����",
				"2-ѧУ����",
				"0-�˳�"
		};
		return mainMenu;
	}
	
	//�༶����˵����ݣ������������
	public String[] banjiMenuArray(){
		String[] banjiMenu={
				"***�༶����***",
				"1-���ѧ����Ϣ����ѧ���б�",
				"2-���ѧ����Ϣ����ͨ�༶",
				"3-ͨ��ѧ�Ų�ѯѧ����Ϣ",
				"4-����༶�����ĳɼ�",
				"5-����༶����ѧ�ɼ�",
				"6-ɾ��ѧ����Ϣ",
				"7-��ʾ������ѧ����Ϣ",
				"9-������һ���˵�"
		};
		return banjiMenu;
	}
	
	//��ʾѧУ����˵�
	public void schoolMenu(){
		String[] slMenu=schoolMenuArray();
		System.out.println("*******************************");
		for(String s:slMenu){
			System.out.println("		"+s+"		");
		}
		System.out.println("*******************************");
	}
	
	//��ʾ�༶����˵�
	public void banJiMenu(){
		String[] bjMenu=banjiMenuArray();
		System.out.println("*******************************");
		for(String s:bjMenu){
			System.out.println("		"+s+"		");
		}
		System.out.println("*******************************");
	}
	
	//��ʾ���˵�
	public void mainMenu(){
		String[] mMenu=mainMenuArray();
		System.out.println("*******************************");
		for(String s:mMenu){
			System.out.println("		"+s+"		");
		}
		System.out.println("*******************************");
	}
	
	public Banji createBanji(Scanner sc){
		System.out.println("������༶���:");
		String bjNum=sc.next();
		System.out.println("������༶����:");
		String bjName=sc.next();
		Banji bj=new Banji(bjNum,bjName);
		return bj;
	}
	
	/**
	 * ��༶�����ѧ�������ѧ��������ѧ���б��д��ڣ���ֱ����ӣ������½�ѧ����
	 * @param banji Ҫ��ӵ��İ༶
	 * @param mainBanji ��ѧ���б�
	 * @param sc �������
	 */
	public void createStudentToBanji(Banji banji,Banji mainBanji,Scanner sc){
		System.out.println("������Ҫ��ӵ�ѧ������");
		int countStu=sc.nextInt();
		for(int i=1;i<=countStu;i++){
			System.out.println("�������"+i+"��ѧ����Ϣ");
			System.out.println("������ѧ��ID:");
			String sId=sc.next();
			Student stu=mainBanji.searchStudentByNum(sId);
			if(stu==null){
				System.out.println("������ѧ������:");
				String sName=sc.next();
				Student stuNew=new Student(sId,sName);
				if(banji!=null)//ֻ����ѧ���б����ѧ��ʱ���ñ���ֵΪnull
					banji.addStudent(stuNew);
				mainBanji.addStudent(stuNew);
				System.out.println("��ӳɹ���");
			}else{
				if(banji!=null)//ֻ����ѧ���б����ѧ��ʱ���ñ���ֵΪnull
					banji.addStudent(stu);
				//mainBanji.addStudent(stu);
				System.out.println("��ѧ��������ѧ���б��д��ڣ���ӳɹ���");
			}
		}
	}

	// ������
	public void test() {
		
		Scanner sc = new Scanner(System.in);
		// ����ѧУ
		School school = new School();
		// �������༶�б�
		Banji bj = new Banji("000", "��ѧ���б�");
		school.addBanji(bj);
		// ������ͨ�༶
		Banji bjNew ;
		int n=0;
		while (true) {
			mainMenu();
			try {			
				System.out.println("��������Ӧ������Ӧ������:");
				n = sc.nextInt();
				if (n == 0) {
					System.out.println("�˳�!");
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
							System.out.println("������һ���˵�!");
							break;
						}
						switch (bjN) {
						case 1:
							System.out.println(banjiMenuArray()[bjN]);
							createStudentToBanji(null, bj, sc);//��ͨ�༶�Ĳ�����ֵ Ϊnull
							bj.displayAllStudent();
							break;
						case 2:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("������Ҫ���ѧ���İ༶����");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("�ð༶�����ڣ�����ȥѧУ�����д����༶");
								break;
							} else {
								createStudentToBanji(bjNew, bj, sc);
								bjNew.displayAllStudent();
							}
							break;
						case 3:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("������Ҫ��ѯѧ���İ༶����");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("�ð༶�����ڣ�");
								break;
							}
							System.out.println("������Ҫ��ѯ��ѧ��ѧ��:");
							String stuNum1 = sc.next();
							Student stu2 = bjNew.searchStudentByNum(stuNum1);
							if (stu2 == null) {
								System.out.println("û�в�ѯ����ѧ����Ϣ��");
							}
							else{
								System.out.println(stu2);
							}
							break;
						case 4:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("����Ҫ�������ĳɼ��İ༶����:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("�ð༶�����ڣ�����ȥ����!");
								break;
							}
							bjNew.insertChineseScore();
							break;
						case 5:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("����Ҫ������ѧ�ɼ��İ༶����:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("�ð༶�����ڣ�����ȥ����!");
								break;
							}
							bjNew.insertMathScore();
							break;
						case 6:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("����Ҫɾ���İ༶����:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("�ð༶�����ڣ�");
								break;
							}
							System.out.println("������Ҫɾ����ѧ��ID:");
							String stuNum = sc.next();
							bjNew.deleteStudent(stuNum);
							break;
						case 7:
							System.out.println(banjiMenuArray()[bjN]);
							System.out.println("����Ҫ�鿴�İ༶����:");
							bjName = sc.next();
							bjNew = school.searchByName(bjName);
							if (bjNew == null) {
								System.out.println("�ð༶�����ڣ�����ȥ����!");
								break;
							}
							bjNew.displayAllStudent();
							break;
						case 9:
							System.out.println("9-������һ���˵�");
							break;
						default:
							System.out.println("��������û�ж�Ӧ�Ĳ����˵�");
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
							System.out.println("������һ���˵���");
							break;
						}
						switch (scN) {
						case 1:
							System.out.println(schoolMenuArray()[scN]);
							System.out.println("�����봴���İ༶���:");
							bjNum2 = sc.next();
							System.out.println("�����봴���İ༶����:");
							bjName2 = sc.next();
							bjNew=new Banji(bjNum2, bjName2);
							school.addBanji(bjNew);
							System.out.println("��Ӱ༶�ɹ���");
							break;
						case 2:
							System.out.println(schoolMenuArray()[scN]);
							System.out.println("������ɾ���İ༶����:");
							bjName2 = sc.next();
							school.deleteBanji(bjName2);
							break;
						case 3:
							System.out.println(schoolMenuArray()[scN]);
							System.out.println("������Ҫ��ѯ�İ༶����:");
							bjName2 = sc.next();
							bjNew = school.searchByName(bjName2);
							if (bjNew != null) {
								System.out.println(bjNew);
								bjNew.displayAllStudent();
							}else{
								System.out.println("��ѯʧ�ܣ��༶�����ڣ�");
							}
							break;
						case 4:
							System.out.println(schoolMenuArray()[scN]);
							if (school.getSchoolMap() == null) {
								System.out.println("ѧУ��û���κΰ༶��Ϣ������ȥ�����༶��");
							} else {
								school.sortChineseByAverage();
							}
							break;
						case 5:
							System.out.println(schoolMenuArray()[scN]);
							if (school.getSchoolMap() == null) {
								System.out.println("ѧУ��û���κΰ༶��Ϣ������ȥ�����༶��");
							} else {
								school.sortMathByAverage();
							}
							break;
						case 6:
							System.out.println(schoolMenuArray()[scN]);
							school.displayBanjiName();
							break;
						default:
							System.out.println("��������û�ж�Ӧ�Ĳ����˵���");
							break;
						}
					}
					break;
				default:
					System.out.println("��������û�ж�Ӧ�Ĳ����˵���");
					break;

				}
				
			} catch (InputMismatchException e) {
				System.out.println("�������ݷǷ���������˵��ж�Ӧ������");
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
