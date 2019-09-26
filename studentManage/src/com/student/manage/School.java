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
	 * 添加班级
	 * @param banji 班级对象
	 */
	public void addBanji(Banji banji) {
		boolean flag = false;
		for (Banji bj : schoolMap.values()) {
			if (bj.equals(banji)) {
				flag = true;
				System.out.println("班级（"+bj.getClassId()+","+bj.getClassName()+"）已存在！");
				break;
			}
		}
		if (flag == false)
			schoolMap.put(banji.getClassName(), banji);

	}
	
	/**
	 * 删除班级
	 * @param bjName 班级名称
	 */
	public void deleteBanji(String bjName){
		Banji bj=searchByName(bjName);
		if(bj != null){
			schoolMap.remove(bjName);
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败，该班级不存在！");
		}
		
	}
	
	//通过班级名称查找班级
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
	 * 按班级的语文平均成绩倒序
	 */
	public void sortChineseByAverage(){
		List<Banji> banjiList=getBanjiList();
		if(banjiList.isEmpty()){
			System.out.println("该班级还没有添加学 生！");
		}else
		{
		Collections.sort(banjiList,new ChineseComparator());
		System.out.println("按各班的语文平均成绩倒序如下：");
		for(Banji bj:banjiList){
			System.out.println("班级："+bj.getClassName()+"，语文平均分："+bj.getChineseAverageScore());
		}
		}
	}

	public void sortMathByAverage(){
		List<Banji> banjiList=getBanjiList();
		if(banjiList.isEmpty()){
			System.out.println("该学校还没有添加班级！");
		}else
		{
		Collections.sort(banjiList,new MathComparator());
		System.out.println("按各班的语文平均成绩倒序如下：");
		for(Banji bj:banjiList){
			System.out.println("班级："+bj.getClassName()+"，数学平均分："+bj.getMathAverageScore());
		}
		}
	}

	public void displayBanjiName() {
		if (schoolMap.keySet().isEmpty()) {
			System.out.println("学校暂时还没有班级信息！");
		} else {
			System.out.println("所有班级名称：");
			for (String name : schoolMap.keySet()) {
				System.out.println(name);
			}
		}
		
	}
	
}
