package app0504;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

/*
 배치관리자 중 BorderLayout은 동, 서, 남, 북, 센터 라는 범위를 갖는 배치방법
 즉 5가지의 방위에 따른 위치가 이미 정해져 있다.
 */ 
public class BorderTest {
	public static void main(String[] args) {
			Frame frame = new Frame("BorderLayout"); 
		
			BorderLayout layout = new BorderLayout(); // 레이아웃 매니저 생성
			
			frame.setLayout(layout); // 프레임에 BorderLayout 적용!
			
			Button bt_north = new Button("North");
			Button bt_east = new Button("East");
			Button bt_west = new Button("West");
			Button bt_south = new Button("South");
			Button bt_center = new Button("Center");
			
			// 모든 방향값은 BorderLayout이 상수로 보유하고 있다.
			// public static final int 대문자
			// 북쪽에 넣기
			frame.add(bt_north, BorderLayout.NORTH);
			frame.add(bt_east, BorderLayout.EAST);
			frame.add(bt_west, BorderLayout.WEST);
			frame.add(bt_south, BorderLayout.SOUTH);
			frame.add(bt_center, BorderLayout.CENTER);
			
			// 프레임 크기 정하고, 보이게 처리
			frame.setSize(400,250);
			frame.setVisible(true);
	}
}
