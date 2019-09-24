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
	 * 将歌曲添加到播放列表
	 * 
	 * @param song
	 *            要添加的歌曲
	 */
	public void addToPlayList(Song newsong) {
		// 要排除重复添加的情况
		boolean flag = false;// 判断播放列表中的歌曲是否存在
		for (Song song : musicList) {
			if (song.equals(newsong)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(newsong + "已经存在不喜欢列表中，添加失败！");
		} else {
			musicList.add(newsong);
		}

	}

	/**
	 * 显示当前列表中所有歌曲
	 */
	public void displayAllSong() {
		System.out.println("当前列表(" + this.getPlayListName() + ")所有歌曲如下：");
		for (Song song : musicList) {
			System.out.println(song);
		}
	}

	/**
	 * 根据歌曲ID查询歌曲
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
	 * 根据歌曲名称查询
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
	 * 把歌曲导出到文件中
	 * 
	 * @param fileName
	 *            存放歌曲的文件名
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
			// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
