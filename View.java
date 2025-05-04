import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class View extends JFrame{
	JPanel cardPanel;
    CardLayout layout;

	public static void main(String[] args) {
		// ここで最新のファイルを読み込んで，2次元リストに変換
		
		ArrayList<ArrayList<String>> data_read = new ArrayList<ArrayList<String>>();
		data_read.add(new ArrayList<String>(List.of("タスク1", "10%")));
		data_read.add(new ArrayList<String>(List.of("タスク2", "10%")));
		data_read.add(new ArrayList<String>(List.of("タスク3", "10%")));
		data_read.add(new ArrayList<String>(List.of("タスク4", "20%")));
		data_read.add(new ArrayList<String>(List.of("", "")));
		
		new View(data_read);
	}
	
	public double getWholeProgress(ArrayList<JComboBox> progress_list) {
		double sum = 0;
		double finish_sum = 0;
		for (int i=0; i<progress_list.size(); i++) {
			String progress = (String)progress_list.get(i).getSelectedItem();
			if (progress != "") {
				progress = progress.replace("%", "");
				double progress_value = Double.parseDouble(progress);
				sum += progress_value;
				finish_sum += 100;
			}
		}
		if (finish_sum == 0) {
			return -1;
		}else {
			return sum * 100/finish_sum;
		}
	}
	
	public
	
	
	
	View(ArrayList<ArrayList<String>> data_read) {
		super("TODOリスト");
		// 各コンポーネントを格納する配列
		ArrayList<JTextField> task_list = new ArrayList<JTextField>();
		ArrayList<JComboBox> progress_list = new ArrayList<JComboBox>();
		//JTextField[] task_list = new JTextField[5];
		//JComboBox[] progress_list = new JComboBox[5];
		//ArrayList<ArrayList<String>> data = new ArrayList<>();
		//String[][] data = new String[5][2];
		
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
		
		JLabel whole_progress_label = new JLabel("全体の進捗状況");
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
			if(data_read.get(i).get(0) != "") {
				task = new String(data_read.get(i).get(0));
			}
			int default_index = 0;
			if(data_read.get(i).get(1) != "") {
				for(int j=0; j < 12; j++) {
					if(data_read.get(i).get(1) == progress_label[j]) {
						default_index = j;
					}
				}
			}
			// タスク名のテキストフィールド
			task_list.add(new JTextField(task));
			//task_list[i] = new JTextField(task);
			p.add(task_list.get(i));
			task_list.get(i).setBounds(2, 5, 300, 30);
			//p.add(Box.createRigidArea(new Dimension(10,1)));
			// 進捗状況のコンボボックス
			progress_list.add(new JComboBox(progress_label));
			progress_list.get(i).setSelectedIndex(default_index);
			progress_list.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					double whole_progress_value = getWholeProgress(progress_list);
					if (whole_progress_value == -1) {
						whole_progress_label.setText("全体の進捗状況：");
					}else {
						whole_progress_label.setText("全体の進捗状況：" + String.format("%1$.1f", whole_progress_value) + "%");
					}
				}
			});
			p.add(progress_list.get(i));
			progress_list.get(i).setBounds(300, 5, 100, 30);
			// TODOリストパネルに各項目パネルを追加
			panel_list.add(p);
			//panel_list.add(Box.createRigidArea(new Dimension(10,1)));
		}
		int whole_progress_y = p_y + 20;
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setBounds(30, whole_progress_y, 200, 40);
		p.setBorder(new LineBorder(Color.BLACK, 1));
		p.setLayout(null);
		whole_progress_label.setBounds(2, 5, 200, 30);
		double whole_progress_value = getWholeProgress(progress_list);
		if (whole_progress_value == -1) {
			whole_progress_label.setText("全体の進捗状況：");
		}else {
			whole_progress_label.setText("全体の進捗状況：" + String.format("%1$.1f", whole_progress_value) + "%");
		}
		
		p.add(whole_progress_label);
		
		panel_list.add(p);
		
		
		
		
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
				// テキストフィールドやコンボボックスの値をクリア
				for (int i=0; i<task_list.size(); i++) {
					task_list.get(i).setText("");
					progress_list.get(i).setSelectedIndex(0);
				}
				// ファイルの新規作成など
				
						
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
				//ArrayList<ArrayList<String>> data = new ArrayList<>();
				//for (int i=0; i<task_list.size(); i++) {
				//	if (!task_list.get(i).getText().isEmpty()) {
				//		ArrayList<String> row_data = new ArrayList<String>();
				//		row_data.add(task_list.get(i).getText());
				//		row_data.add((String)progress_list.get(i).getSelectedItem());
				//		data.add(row_data);
				//	}
				//}
				//for (int i=0; i<data.size(); i++) {
				//	System.out.print(data.get(i).get(0) + ", ");
				//	System.out.println(data.get(i).get(1));
				//}
				List<Todo> todos = new ArrayList<Todo>();
				for (int i=0; i < task_list.size(); i++) {
					if (!task_list.get(i).getText().isEmpty()) {
						String task_name = task_list.get(i).getText();
						String progress_string = (String)progress_list.get(i).getSelectedItem();
						progress_string = progress_string.replace("%", "");
						int progress = (int)Double.parseDouble(progress_string);
						todos.add(new Todo(task_name, progress));
					}
				}
				for (int i=0; i < todos.size(); i++) {
					System.out.print(todos.get(i).task_name + "，");
					System.out.println(todos.get(i).progress);
				}
				// ここに保存ボタンが押された時の処理を記述
				// todos は，Todo型のリスト
				// TaskManagerクラスの getTasks() で Taskのリストを得る
				List<Task> tasks = TaskManager.getTasks(todos);
				// TaskManagerクラスの saveTasks() で保存
				TaskManager.saveTasks(tasks);
				
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
