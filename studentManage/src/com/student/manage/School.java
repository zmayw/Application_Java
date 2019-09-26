package com.student.manage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
	private Map<String,Banji> schoolMap;
	
	public School(){
		schoolMap=new HashMap<String,Banji>();
	}
	
	public Map<String, Banji> getSchoolMap() {
		return schoolMap;
	}

	public void setSchoolMap(Map<String, Banji> schoolMap) {
		this.schoolMap = schoolMap;
	}
	
	/**
	 * ��Ӱ༶
	 * @param banji �༶����
	 */
	public void addBanji(Banji banji) {
		boolean flag = false;
		for (Banji bj : schoolMap.values()) {
			if (bj.equals(banji)) {
				flag = true;
				System.out.println("�༶��"+bj.getClassId()+","+bj.getClassName()+"���Ѵ��ڣ�");
				break;
			}
		}
		if (flag == false)
			schoolMap.put(banji.getClassName(), banji);

	}
	
	/**
	 * ɾ���༶
	 * @param bjName �༶����
	 */
	public void deleteBanji(String bjName){
		Banji bj=searchByName(bjName);
		if(bj != null){
			schoolMap.remove(bjName);
			System.out.println("ɾ���ɹ�");
		}else{
			System.out.println("ɾ��ʧ�ܣ��ð༶�����ڣ�");
		}
		
	}
	
	//ͨ���༶���Ʋ��Ұ༶
	public Banji searchByName(String className){
		for(String name:schoolMap.keySet()){
			if(name.equals(className)){
				return schoolMap.get(className);
			}
		}
		return null;
	}
	
	
	public List<Banji> getBanjiList(){
		List<Banji> banjiList=new ArrayList<Banji>();
		for(Banji bj:schoolMap.values()){
			banjiList.add(bj);
		}
		return banjiList;
	}
	
	/**
	 * ���༶������ƽ���ɼ�����
	 */
	public void sortChineseByAverage(){
		List<Banji> banjiList=getBanjiList();
		if(banjiList.isEmpty()){
			System.out.println("�ð༶��û�����ѧ ����");
		}else
		{
		Collections.sort(banjiList,new ChineseComparator());
		System.out.println("�����������ƽ���ɼ��������£�");
		for(Banji bj:banjiList){
			System.out.println("�༶��"+bj.getClassName()+"������ƽ���֣�"+bj.getChineseAverageScore());
		}
		}
	}

	public void sortMathByAverage(){
		List<Banji> banjiList=getBanjiList();
		if(banjiList.isEmpty()){
			System.out.println("��ѧУ��û����Ӱ༶��");
		}else
		{
		Collections.sort(banjiList,new MathComparator());
		System.out.println("�����������ƽ���ɼ��������£�");
		for(Banji bj:banjiList){
			System.out.println("�༶��"+bj.getClassName()+"����ѧƽ���֣�"+bj.getMathAverageScore());
		}
		}
	}

	public void displayBanjiName() {
		if (schoolMap.keySet().isEmpty()) {
			System.out.println("ѧУ��ʱ��û�а༶��Ϣ��");
		} else {
			System.out.println("���а༶���ƣ�");
			for (String name : schoolMap.keySet()) {
				System.out.println(name);
			}
		}
		
	}
	
}
