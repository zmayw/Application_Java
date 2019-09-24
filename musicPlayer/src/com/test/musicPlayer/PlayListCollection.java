package com.test.musicPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class PlayListCollection {
	private Map<String, PlayList> playListMap; 
	
	public PlayListCollection(){
		playListMap=new HashMap<String,PlayList>();
	}
	


	public Map<String,PlayList> getPlayListMap() {
		return playListMap;
	}

	public void setPlayListMap(Map<String,PlayList> playListMap) {
		this.playListMap = playListMap;
	}
	
	public void addPlayList(PlayList playList){
		playListMap.put(playList.getPlayListName(),playList);
	}
	
	public void deletePlayList(PlayList playList){
		playListMap.remove(playList.getPlayListName());
		System.out.println("删除成功！");
	}
	
	public PlayList SearchPlayListByName(String playListName){
		PlayList playlist=null;
		Set<String> nameSet= playListMap.keySet();
		for(String name:nameSet){
			if(name.equals(playListName)){
				playlist=playListMap.get(playListName);
			}
		}
		return playlist;
	}
	
	
	public void displayListName(){
		Set<String> playListSet=playListMap.keySet();
		System.out.println("所有播放列表名称为：");
		for(String name:playListSet){
			System.out.println(name);
		}
		
	}



}
