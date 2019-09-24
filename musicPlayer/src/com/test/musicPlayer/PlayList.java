package com.test.musicPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayList {

	private String playListName;
	private List<Song> musicList;

	public PlayList() {
	}

	public PlayList(String listName) {
		this.setPlayListName(listName);
		this.setMusicList(musicList);
		musicList = new ArrayList<Song>();

	}

	public List<Song> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<Song> musicList) {
		this.musicList = musicList;
	}

	public void setPlayListName(String name) {
		this.playListName = name;
	}

	public String getPlayListName() {
		return playListName;
	}

	/**
	 * ��������ӵ������б�
	 * 
	 * @param song
	 *            Ҫ��ӵĸ���
	 */
	public void addToPlayList(Song newsong) {
		// Ҫ�ų��ظ���ӵ����
		boolean flag = false;// �жϲ����б��еĸ����Ƿ����
		for (Song song : musicList) {
			if (song.equals(newsong)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(newsong + "�Ѿ����ڲ�ϲ���б��У����ʧ�ܣ�");
		} else {
			musicList.add(newsong);
		}

	}

	/**
	 * ��ʾ��ǰ�б������и���
	 */
	public void displayAllSong() {
		System.out.println("��ǰ�б�(" + this.getPlayListName() + ")���и������£�");
		for (Song song : musicList) {
			System.out.println(song);
		}
	}

	/**
	 * ���ݸ���ID��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public Song searchSongById(String id) {

		for (Song song : this.getMusicList()) {
			if (song.getId().equals(id))
				return song;
		}
		return null;
	}

	/**
	 * ���ݸ������Ʋ�ѯ
	 * 
	 * @param name
	 * @return
	 */
	public Song searchSongByName(String name) {
		for (Song song : this.getMusicList()) {
			if (song.getName().equals(name)) {
				return song;
			}
		}
		return null;
	}

	public void updateSong(String id, Song newsong) {
		for (Song song : this.getMusicList()) {
			if (song.getId().equals(id)) {
				song.setName(newsong.getName());
				song.setId(newsong.getId());
				song.setSinger(newsong.getSinger());
				break;
			}
		}
	}

	public void deleteSong(String id) {
		for (int i = 0; i < this.getMusicList().size(); i++) {
			Song song = this.getMusicList().get(i);
			if (song.getId().equals(id)) {
				this.musicList.remove(song);
				break;
			}
		}
	}

	public void sortBySongName() {

	}

	/**
	 * �Ѹ����������ļ���
	 * 
	 * @param fileName
	 *            ��Ÿ������ļ���
	 */
	public void exportToFile(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// for(Song s:getMusicList()){
			// oos.writeObject(s);
			// }
			oos.writeObject(musicList);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readFromExportFile(String fileName) {

		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				List<Song> ls = (ArrayList<Song>) ois.readObject();
				for(Song s:ls){
					System.out.println(s);
				}
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
