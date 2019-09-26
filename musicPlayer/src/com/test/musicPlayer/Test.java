package com.test.musicPlayer;

import java.util.Scanner;

public class Test {

	// 主流程

	public String[] createPLMenu() {
		String[] plMenu = { "播放列表管理", "1-将歌曲添加到主播放列表", "2-将歌曲添加到普通播放列表",
				"3-通过歌曲ID查询播放列表中的歌曲", "4-通过歌曲名称查询播放列表中的歌曲", "5-修改播放列表中的歌曲",
				"6-删除播放列表中的歌曲", "7-显示播放列表中的所有歌曲", "8-导出歌单","9-返回上一级菜单" };
		return plMenu;
	}

	public String[] createPMenu() {

		String[] pMenu = { "播放器管理", "1-向播放器添加播放列表", "2-从播放器删除播放列表",
				"3-通过名称查询播放列表", "4-显示所有播放列表名称", "9-返回上一级 菜单 	" };
		return pMenu;
	}

	public void mainMenu() {
		System.out.println("******************************");
		System.out.println("		1-播放列表管理			");
		System.out.println("		2-播放器管理			");
		System.out.println("		0-退出			");

	}

	public String getPLMenu(int n) {
		String[] list = createPLMenu();
		if (0 <= n && n < list.length) {
			return list[n];
		} else {
			return null;
		}

	}

	public String getPMenu(int n) {
		String[] list = createPMenu();
		if (0 <= n && n < list.length) {
			return list[n];
		} else {
			return null;
		}

	}

	public void playListMenu() {
		System.out.println("*******************************");
		int n = createPLMenu().length;
		for (int i = 0; i < n; i++) {
			System.out.println("		" + getPLMenu(i) + "		");
		}
		System.out.println("*******************************");
	}

	public void playerMenu() {
		System.out.println("*******************************");
		int n = createPMenu().length;
		for (int i = 0; i < n; i++) {
			System.out.println("		" + getPMenu(i) + "		");
		}
		System.out.println("*******************************");
	}

	// 注流程
	public void test() {
		Test t = new Test();
		Scanner sc = new Scanner(System.in);
		int input = 0, input1 = 0, input2 = 0;
		// 创建一个播放列表容器（播放器）
		PlayListCollection plc = new PlayListCollection();
		// 创建主播放列表
		PlayList mainPlayList = new PlayList("主播放列表");
		PlayList favouritePlayList;
		// 将主播放列表添加到播放器
		plc.addPlayList(mainPlayList);
		while (true) {
			t.mainMenu();
			System.out.println("请输入对应的数字进行操作");
			input = sc.nextInt();
			if (input == 0)
				break;
			switch (input) {
			case 1:// 播放列表管理
				t.playListMenu();
				System.out.println("请输入对应的数字对播放列表进行管理");
				input1 = sc.nextInt();
				if (input1 == 9)
					break;
				switch (input1) {
				case 1:
					System.out.println(getPLMenu(1));
					System.out.println("请输入要添加的歌曲的数量");
					int count = sc.nextInt();
					for (int i = 1; i <= count; i++) {
						System.out.println("请输入第" + i + "首歌曲.");
						System.out.println("请输入歌曲的ID");
						String strId = sc.next();
						System.out.println("请输入歌曲的名称");
						String strName = sc.next();
						System.out.println("请输歌曲的演唱者");
						String strSinger = sc.next();
						Song s = new Song(strId, strName, strSinger);
						mainPlayList.addToPlayList(s);
						// mainPlayList.displayAllSong();
					}

					break;
				case 2:
					System.out.println(getPLMenu(2));
					System.out.println("请输入要添加的播放列表名称");
					String sName = sc.next();
					favouritePlayList = plc.SearchPlayListByName(sName);
					if (favouritePlayList == null) {
						System.out.println("该播放列表不存在，请先将该列表添加到播放器中");
					} else {
						System.out.println("请输入要添加的歌曲数量");
						int count2 = sc.nextInt();
						for (int i = 1; i <= count2; i++) {
							System.out.println("请输入第" + i + "首歌曲");
							System.out.println("请输入歌曲Id");
							String strId = sc.next();
							Song tmpS = mainPlayList.searchSongById(strId);
							if (tmpS == null) {
								System.out.println("该歌曲在主播放列表中不存在，继续输入其它信息");
								System.out.println("请输入歌曲名称");
								String strName = sc.next();
								System.out.println("请输入演唱者");
								String strSinger = sc.next();
								Song s = new Song(strId, strName, strSinger);
								// 分别将歌曲添加到主播放列表和普通播放列表
								mainPlayList.addToPlayList(s);
								favouritePlayList.addToPlayList(s);
							} else {
								// 如果歌曲存在于主播放列表，则直接添加到当前播放列表
								favouritePlayList.addToPlayList(tmpS);
								System.out.println("歌曲已在主播放列表中存在，添加到当前列表成功！");
							}

						}

					}
					break;
				case 3:
					System.out.println(getPLMenu(3));
					System.out.println("请输入要查询的播放列表名称");
					String strPlayListName1 = sc.next();
					PlayList pl = plc.SearchPlayListByName(strPlayListName1);
					if (pl == null) {
						System.out.println("该播放列表不存在!");
						break;
					} else {
						System.out.println("请输入要查询的歌曲Id");
						String strId1 = sc.next();
						Song tmpS = pl.searchSongById(strId1);
						if (tmpS == null) {
							System.out.println("该歌曲在播放列表" + strPlayListName1
									+ "中不存在");
						} else {
							System.out.println("该歌曲信息是：");
							System.out.println(tmpS);
						}
					}

					break;
				case 4:
					System.out.println(getPLMenu(4));
					System.out.println("请输入要查询的播放列表名称");
					String strPlayListName2 = sc.next();
					PlayList pl2 = plc.SearchPlayListByName(strPlayListName2);
					if (pl2 == null) {
						System.out.println("该播放列表不存在!");
						break;
					} else {
						System.out.println("请输入要查询的歌曲名称");
						String strName = sc.next();
						Song tmpS = pl2.searchSongByName(strName);
						if (tmpS == null) {
							System.out.println("该歌曲在播放列表" + strPlayListName2
									+ "中不存在");
						} else {
							System.out.println("该歌曲信息是：");
							System.out.println(tmpS);
						}
					}

					break;
				case 5:
					System.out.println(getPLMenu(5));
					System.out.println("请输入要查询的播放列表名称");
					String strPlayListName = sc.next();
					PlayList tempPL = plc.SearchPlayListByName(strPlayListName);
					if (tempPL == null) {
						System.out.println("播放列表" + strPlayListName + "不存在！");
						break;
					} else {
						System.out.println("请输入要修改的歌曲Id");
						String modifyId = sc.next();
						Song tempS = tempPL.searchSongById(modifyId);
						if (tempS == null) {
							System.out.println("您要查找的歌曲Id不存在！");
							break;
						} else {
							System.out.println("请输入要修改的歌曲名称");
							String strName=sc.next();
							System.out.println("请输入该歌曲的演唱者");
							String strSinger=sc.next();
							Song newSong=new Song(modifyId,strName,strSinger);
							tempPL.updateSong(modifyId, newSong);
							System.out.println("修改成功，歌曲信息如下");
							System.out.println(newSong);
						}
					}

					break;
				case 6:
					System.out.println(getPLMenu(6));
					System.out.println("请输入要查询的播放列表名称");
					String strPlayListName3 = sc.next();
					PlayList pl3 = plc.SearchPlayListByName(strPlayListName3);
					if (pl3 == null) {
						System.out.println("播放列表" + strPlayListName3 + "不存在！");
						break;
					} else {
						System.out.println("请输入要删除的歌曲Id");
						String delId = sc.next();
						Song tempS = pl3.searchSongById(delId);
						if (tempS == null) {
							System.out.println("您要查找的歌曲Id不存在！");
							break;
						} else {
							pl3.deleteSong(delId);
							System.out.println("删除成功！");
							pl3.displayAllSong();
						}
					}

					break;
				case 7:
					System.out.println(getPLMenu(7));
					System.out.println("请输入要查看的播放列表名称");
					String strPlayListName4 = sc.next();
					PlayList pl4 = plc.SearchPlayListByName(strPlayListName4);
					if (pl4 == null) {
						System.out.println("播放列表" + strPlayListName4 + "不存在！");
						break;
					} else {
						pl4.displayAllSong();
					}
					break;
				case 8:
					System.out.println(getPLMenu(8));
					System.out.println("请输入要导出歌单的播放列表名称");
					String strPlayListName5 = sc.next();
					PlayList pl5 = plc.SearchPlayListByName(strPlayListName5);
					if (pl5 == null) {
						System.out.println("播放列表" + strPlayListName5 + "不存在！");
						break;
					} else {
						pl5.exportToFile("musicList.txt");
						System.out.println("导出成功！");
						System.out.println("读取导出的信息，显示如下：");
						pl5.readFromExportFile("musicList.txt");
					}
					break;
				default:
					System.out.println("没有对应的操作菜单 ！");
					break;
				}
				break;
			case 2:// 播放器管理
				t.playerMenu();
				System.out.println("请输入对应的数字对播放器进行管理");
				input2 = sc.nextInt();
				if (input2 == 9)
					break;
				switch (input2) {
				case 1:
					System.out.println(getPMenu(1));
					System.out.println("输入要添加的播放列表名称");
					String plName = sc.next();
					favouritePlayList = new PlayList(plName);
					plc.addPlayList(favouritePlayList);
					break;
				case 2:
					System.out.println(getPMenu(2));
					System.out.println("请输入要删除的播放列表名称");
					String plName2=sc.next();
					if(plName2.equals("主播放列表")){
						System.out.println("主播放列表不能删除！");
						break;
					}
					PlayList pl=plc.SearchPlayListByName(plName2);
					if(pl==null){
						System.out.println("该列表在播放器中不存在！");
					}else{
						plc.deletePlayList(pl);
					}
					break;
				case 3:
					System.out.println(getPMenu(3));
					System.out.println("请输入要查找的播放列表名称");
					String plName3=sc.next();
					PlayList pl3=plc.SearchPlayListByName(plName3);
					if(pl3==null){
						System.out.println("该播放列表在播放器中不存在！");
					}else{
						pl3.displayAllSong();
					}
					break;
				case 4:
					System.out.println(getPMenu(4));
					plc.displayListName();
					break;
				default:
					System.out.println("没有对应的操作菜单 ！");
					
					break;
				}
				break;
			default:
				System.out.println("该数字没有对应的操作");

			}
		}

	}

	public static void main(String[] args) {
		// Song s1 = new Song("001", "敢问路在何方", "西游记");
		// Song s2 = new Song("002", "女儿情", "西游记");
		// Song s3 = new Song("003", "五百年沧海桑田", "郁金剑");
		// Song s4 = new Song("004", "五百年沧海桑田", "郁金剑");
		//
		// PlayList p1 = new PlayList("西游记1");
		// p1.addToPlayList(s1);
		// p1.addToPlayList(s3);
		//
		// p1.displayAllSong();
		// PlayList p2 = new PlayList("我喜欢的");
		// p2.addToPlayList(s2);
		// p2.addToPlayList(s4);
		//
		// PlayListCollection plc = new PlayListCollection();
		// plc.addPlayList(p1);
		// plc.addPlayList(p2);
		//
		// plc.displayListName();
		//
		// plc.SearchPlayListByName("我的喜欢的");
		// plc.deletePlayList(p2);
		// System.out.println("**********");
		// plc.displayListName();
		Test t = new Test();
		t.test();

	}

}
