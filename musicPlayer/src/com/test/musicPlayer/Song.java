package com.test.musicPlayer;

import java.io.Serializable;


//implements Comparable<Song>
public class Song implements Serializable {
	private String id;
	private String name;
	private String singer;
	
	
	public Song(){}
	
	public Song(String id,String name,String singer){
		this.setId(id);
		this.setName(name);
		this.setSinger(singer);
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		return result;
	}
	
	/**
	 * �������ƺ��ݳ�����ͬʱ����Ϊ������������ȵ�
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj.getClass()==Song.class){
			Song s=(Song)obj;
			return (s.getName()==this.getName() && s.getSinger()==this.getSinger());
		}
		return false;			
	}
		
	
	@Override
	public String toString(){
		String str="����[����ID��"+this.getId()+",�������ƣ�"+this.getName()+",�ݳ��ߣ�"+this.getSinger()+"]";
		return str;
	}
	
	
	


}
	

