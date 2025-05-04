import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class Todo extends JFrame{
	JPanel cardPanel;
    CardLayout layout;

	public static void main(String[] args) {
		// ここで最新のファイルを読み込んで，2次元リストに変換
		String[][] data_read = {
				{"タスク１", "10%"},
				{"タスク１", "10%"},
				{"タスク１", "10%"},
				{"授業１", "20%"},
				{"", ""}
		};
		new Todo(data_read);
	}
	
	public String getDefaultTask(String[][] data_read, int row) {
		String task = new String("");
		if(data_read[row][0] != "") {
			task = new String(data_read[row][0]);
		}
		
		return task;
	}
	
	
	
	Todo(String[][] data_read) {
		super("TODOリスト");
		// 各コンポーネントを格納する配列
		JTextField[] task_list = new JTextField[5];
		JComboBox[] progress_list = new JComboBox[5];
		String[][] data = new String[5][2];
		
		// 全体パネルを設定
		JPanel whole_panel = new JPanel();
		whole_panel.setLayout(new BorderLayout());
		//whole_panel.setLayout(null);
		
		// TODOリストのパネル
		JPanel panel_list = new JPanel();
		panel_list.setBounds(20, 100, 480, 500);
		panel_list.setBorder(BorderFactory.createEmptyBorder(10, 30, 50, 30));
		panel_list.setBackground(Color.WHITE);
		//panel_list.setLayout(new BoxLayout(panel_list, BoxLayout.Y_AXIS));
		panel_list.setLayout(null);
		// パネルにコンポーネントを追加
		// 進捗状況のリスト
		String[] progress_label = {"", "0%", "10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%"};
		int p_y = 10;
		for(int i=0; i < 5; i++) {
			// 各項目に対応するパネル
			JPanel p = new JPanel();
			p.setOpaque(false);
			p.setBounds(30, p_y, 430, 40);
			p.setBorder(new LineBorder(Color.BLACK, 1));
			p_y += 38;
			p.setLayout(null);
			//p.setBackground(Color.BLUE);
			
			// コンポーネントに表示する文字
			String task = new String("");
			if(data_read[i][0] != "") {
				task = new String(data_read[i][0]);
			}
			int default_index = 0;
			if(data_read[i][1] != "") {
				for(int j=0; j < 12; j++) {
					if(data_read[i][1] == progress_label[j]) {
						default_index = j;
					}
				}
			}
			// タスク名のテキストフィールド
			task_list[i] = new JTextField(task);
			p.add(task_list[i]);
			task_list[i].setBounds(2, 5, 300, 30);
			//p.add(Box.createRigidArea(new Dimension(10,1)));
			// 進捗状況のコンボボックス
			progress_list[i] = new JComboBox(progress_label);
			progress_list[i].setSelectedIndex(default_index);
			p.add(progress_list[i]);
			progress_list[i].setBounds(300, 5, 100, 30);
			// TODOリストパネルに各項目パネルを追加
			panel_list.add(p);
			panel_list.add(Box.createRigidArea(new Dimension(10,1)));
		}
		
		
		// 新規作成と保存ボタンのパネル
		JPanel panel_menu = new JPanel();
		panel_menu.setBounds(0, 0, 300, 50);
		// 新規作成ボタン
		JButton b_create_list = new JButton("新規作成");
		b_create_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("新規作成ボタンがクリックされました。");
				// ここに新規作成ボタンが押されたときの処理を記述
						
			}
		});
		panel_menu.add(b_create_list);
		// 保存ボタン
		JButton b_save_list = new JButton("保存");
		b_save_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("保存ボタンがクリックされました。");
				for (int i=0; i<5; i++) {
					data[i][0] = task_list[i].getText();
					data[i][1] = (String)progress_list[i].getSelectedItem();
				}
				for (int i=0; i<5; i++) {
					System.out.print(data[i][0] + ", ");
					System.out.println(data[i][1]);
				}
				// ここに保存ボタンが押された時の処理を記述
				// 変数 data は，2次元配列で [ ["タスク1", "10%"], ["タスク2", "50%"], ... , ["タスク5", "20%"] ] のようになっている
				
				
				
			}
		});
		panel_menu.add(b_save_list);
		
		
		// 全体のパネルにメニューパネルとTODOリストパネルを追加
		whole_panel.add(panel_menu, BorderLayout.NORTH);
		whole_panel.add(panel_list, BorderLayout.CENTER);
		whole_panel.setBackground(new Color(180, 150, 75));
		
		// フレームに全体パネルを追加
		add(whole_panel);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 700);
		setVisible(true);
		
	}
	
	

}
