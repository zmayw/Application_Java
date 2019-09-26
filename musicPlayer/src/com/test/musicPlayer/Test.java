package com.test.musicPlayer;

import java.util.Scanner;

public class Test {

	// ������

	public String[] createPLMenu() {
		String[] plMenu = { "�����б����", "1-��������ӵ��������б�", "2-��������ӵ���ͨ�����б�",
				"3-ͨ������ID��ѯ�����б��еĸ���", "4-ͨ���������Ʋ�ѯ�����б��еĸ���", "5-�޸Ĳ����б��еĸ���",
				"6-ɾ�������б��еĸ���", "7-��ʾ�����б��е����и���", "8-�����赥","9-������һ���˵�" };
		return plMenu;
	}

	public String[] createPMenu() {

		String[] pMenu = { "����������", "1-�򲥷�����Ӳ����б�", "2-�Ӳ�����ɾ�������б�",
				"3-ͨ�����Ʋ�ѯ�����б�", "4-��ʾ���в����б�����", "9-������һ�� �˵� 	" };
		return pMenu;
	}

	public void mainMenu() {
		System.out.println("******************************");
		System.out.println("		1-�����б����			");
		System.out.println("		2-����������			");
		System.out.println("		0-�˳�			");

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

	// ע����
	public void test() {
		Test t = new Test();
		Scanner sc = new Scanner(System.in);
		int input = 0, input1 = 0, input2 = 0;
		// ����һ�������б���������������
		PlayListCollection plc = new PlayListCollection();
		// �����������б�
		PlayList mainPlayList = new PlayList("�������б�");
		PlayList favouritePlayList;
		// ���������б���ӵ�������
		plc.addPlayList(mainPlayList);
		while (true) {
			t.mainMenu();
			System.out.println("�������Ӧ�����ֽ��в���");
			input = sc.nextInt();
			if (input == 0)
				break;
			switch (input) {
			case 1:// �����б����
				t.playListMenu();
				System.out.println("�������Ӧ�����ֶԲ����б���й���");
				input1 = sc.nextInt();
				if (input1 == 9)
					break;
				switch (input1) {
				case 1:
					System.out.println(getPLMenu(1));
					System.out.println("������Ҫ��ӵĸ���������");
					int count = sc.nextInt();
					for (int i = 1; i <= count; i++) {
						System.out.println("�������" + i + "�׸���.");
						System.out.println("�����������ID");
						String strId = sc.next();
						System.out.println("���������������");
						String strName = sc.next();
						System.out.println("����������ݳ���");
						String strSinger = sc.next();
						Song s = new Song(strId, strName, strSinger);
						mainPlayList.addToPlayList(s);
						// mainPlayList.displayAllSong();
					}

					break;
				case 2:
					System.out.println(getPLMenu(2));
					System.out.println("������Ҫ��ӵĲ����б�����");
					String sName = sc.next();
					favouritePlayList = plc.SearchPlayListByName(sName);
					if (favouritePlayList == null) {
						System.out.println("�ò����б����ڣ����Ƚ����б���ӵ���������");
					} else {
						System.out.println("������Ҫ��ӵĸ�������");
						int count2 = sc.nextInt();
						for (int i = 1; i <= count2; i++) {
							System.out.println("�������" + i + "�׸���");
							System.out.println("���������Id");
							String strId = sc.next();
							Song tmpS = mainPlayList.searchSongById(strId);
							if (tmpS == null) {
								System.out.println("�ø������������б��в����ڣ���������������Ϣ");
								System.out.println("�������������");
								String strName = sc.next();
								System.out.println("�������ݳ���");
								String strSinger = sc.next();
								Song s = new Song(strId, strName, strSinger);
								// �ֱ𽫸�����ӵ��������б����ͨ�����б�
								mainPlayList.addToPlayList(s);
								favouritePlayList.addToPlayList(s);
							} else {
								// ��������������������б���ֱ����ӵ���ǰ�����б�
								favouritePlayList.addToPlayList(tmpS);
								System.out.println("���������������б��д��ڣ���ӵ���ǰ�б�ɹ���");
							}

						}

					}
					break;
				case 3:
					System.out.println(getPLMenu(3));
					System.out.println("������Ҫ��ѯ�Ĳ����б�����");
					String strPlayListName1 = sc.next();
					PlayList pl = plc.SearchPlayListByName(strPlayListName1);
					if (pl == null) {
						System.out.println("�ò����б�����!");
						break;
					} else {
						System.out.println("������Ҫ��ѯ�ĸ���Id");
						String strId1 = sc.next();
						Song tmpS = pl.searchSongById(strId1);
						if (tmpS == null) {
							System.out.println("�ø����ڲ����б�" + strPlayListName1
									+ "�в�����");
						} else {
							System.out.println("�ø�����Ϣ�ǣ�");
							System.out.println(tmpS);
						}
					}

					break;
				case 4:
					System.out.println(getPLMenu(4));
					System.out.println("������Ҫ��ѯ�Ĳ����б�����");
					String strPlayListName2 = sc.next();
					PlayList pl2 = plc.SearchPlayListByName(strPlayListName2);
					if (pl2 == null) {
						System.out.println("�ò����б�����!");
						break;
					} else {
						System.out.println("������Ҫ��ѯ�ĸ�������");
						String strName = sc.next();
						Song tmpS = pl2.searchSongByName(strName);
						if (tmpS == null) {
							System.out.println("�ø����ڲ����б�" + strPlayListName2
									+ "�в�����");
						} else {
							System.out.println("�ø�����Ϣ�ǣ�");
							System.out.println(tmpS);
						}
					}

					break;
				case 5:
					System.out.println(getPLMenu(5));
					System.out.println("������Ҫ��ѯ�Ĳ����б�����");
					String strPlayListName = sc.next();
					PlayList tempPL = plc.SearchPlayListByName(strPlayListName);
					if (tempPL == null) {
						System.out.println("�����б�" + strPlayListName + "�����ڣ�");
						break;
					} else {
						System.out.println("������Ҫ�޸ĵĸ���Id");
						String modifyId = sc.next();
						Song tempS = tempPL.searchSongById(modifyId);
						if (tempS == null) {
							System.out.println("��Ҫ���ҵĸ���Id�����ڣ�");
							break;
						} else {
							System.out.println("������Ҫ�޸ĵĸ�������");
							String strName=sc.next();
							System.out.println("������ø������ݳ���");
							String strSinger=sc.next();
							Song newSong=new Song(modifyId,strName,strSinger);
							tempPL.updateSong(modifyId, newSong);
							System.out.println("�޸ĳɹ���������Ϣ����");
							System.out.println(newSong);
						}
					}

					break;
				case 6:
					System.out.println(getPLMenu(6));
					System.out.println("������Ҫ��ѯ�Ĳ����б�����");
					String strPlayListName3 = sc.next();
					PlayList pl3 = plc.SearchPlayListByName(strPlayListName3);
					if (pl3 == null) {
						System.out.println("�����б�" + strPlayListName3 + "�����ڣ�");
						break;
					} else {
						System.out.println("������Ҫɾ���ĸ���Id");
						String delId = sc.next();
						Song tempS = pl3.searchSongById(delId);
						if (tempS == null) {
							System.out.println("��Ҫ���ҵĸ���Id�����ڣ�");
							break;
						} else {
							pl3.deleteSong(delId);
							System.out.println("ɾ���ɹ���");
							pl3.displayAllSong();
						}
					}

					break;
				case 7:
					System.out.println(getPLMenu(7));
					System.out.println("������Ҫ�鿴�Ĳ����б�����");
					String strPlayListName4 = sc.next();
					PlayList pl4 = plc.SearchPlayListByName(strPlayListName4);
					if (pl4 == null) {
						System.out.println("�����б�" + strPlayListName4 + "�����ڣ�");
						break;
					} else {
						pl4.displayAllSong();
					}
					break;
				case 8:
					System.out.println(getPLMenu(8));
					System.out.println("������Ҫ�����赥�Ĳ����б�����");
					String strPlayListName5 = sc.next();
					PlayList pl5 = plc.SearchPlayListByName(strPlayListName5);
					if (pl5 == null) {
						System.out.println("�����б�" + strPlayListName5 + "�����ڣ�");
						break;
					} else {
						pl5.exportToFile("musicList.txt");
						System.out.println("�����ɹ���");
						System.out.println("��ȡ��������Ϣ����ʾ���£�");
						pl5.readFromExportFile("musicList.txt");
					}
					break;
				default:
					System.out.println("û�ж�Ӧ�Ĳ����˵� ��");
					break;
				}
				break;
			case 2:// ����������
				t.playerMenu();
				System.out.println("�������Ӧ�����ֶԲ��������й���");
				input2 = sc.nextInt();
				if (input2 == 9)
					break;
				switch (input2) {
				case 1:
					System.out.println(getPMenu(1));
					System.out.println("����Ҫ��ӵĲ����б�����");
					String plName = sc.next();
					favouritePlayList = new PlayList(plName);
					plc.addPlayList(favouritePlayList);
					break;
				case 2:
					System.out.println(getPMenu(2));
					System.out.println("������Ҫɾ���Ĳ����б�����");
					String plName2=sc.next();
					if(plName2.equals("�������б�")){
						System.out.println("�������б���ɾ����");
						break;
					}
					PlayList pl=plc.SearchPlayListByName(plName2);
					if(pl==null){
						System.out.println("���б��ڲ������в����ڣ�");
					}else{
						plc.deletePlayList(pl);
					}
					break;
				case 3:
					System.out.println(getPMenu(3));
					System.out.println("������Ҫ���ҵĲ����б�����");
					String plName3=sc.next();
					PlayList pl3=plc.SearchPlayListByName(plName3);
					if(pl3==null){
						System.out.println("�ò����б��ڲ������в����ڣ�");
					}else{
						pl3.displayAllSong();
					}
					break;
				case 4:
					System.out.println(getPMenu(4));
					plc.displayListName();
					break;
				default:
					System.out.println("û�ж�Ӧ�Ĳ����˵� ��");
					
					break;
				}
				break;
			default:
				System.out.println("������û�ж�Ӧ�Ĳ���");

			}
		}

	}

	public static void main(String[] args) {
		// Song s1 = new Song("001", "����·�ںη�", "���μ�");
		// Song s2 = new Song("002", "Ů����", "���μ�");
		// Song s3 = new Song("003", "�����׺�ɣ��", "����");
		// Song s4 = new Song("004", "�����׺�ɣ��", "����");
		//
		// PlayList p1 = new PlayList("���μ�1");
		// p1.addToPlayList(s1);
		// p1.addToPlayList(s3);
		//
		// p1.displayAllSong();
		// PlayList p2 = new PlayList("��ϲ����");
		// p2.addToPlayList(s2);
		// p2.addToPlayList(s4);
		//
		// PlayListCollection plc = new PlayListCollection();
		// plc.addPlayList(p1);
		// plc.addPlayList(p2);
		//
		// plc.displayListName();
		//
		// plc.SearchPlayListByName("�ҵ�ϲ����");
		// plc.deletePlayList(p2);
		// System.out.println("**********");
		// plc.displayListName();
		Test t = new Test();
		t.test();

	}

}
